package spiderboot.video.upload;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.google.api.services.samples.youtube.cmdline.data.UploadVideo;

import spider.video.VideoWraper;
import spiderboot.databaseconnection.MySqlAccess;

public class UploadExecuteThread implements Runnable{
	BlockingQueue<VideoWraper> uploadQueue = new LinkedBlockingQueue<VideoWraper>();
	private static UploadExecuteThread instance = null;
	Thread thread;
	boolean isCompleted = true;

	public UploadExecuteThread()
	{
		thread = new Thread(this);
	}

	public UploadExecuteThread getInstance(){
		if(instance == null){
			instance = new UploadExecuteThread();
		}
		return instance;
	}

	public Thread getUploadThead() {
		return thread;
	}

	public void run() {
		while (true) {
			if(isCompleted)
			{
				isCompleted = false;
				if(!uploadQueue.isEmpty())
				{
					VideoWraper vWrapper = uploadQueue.poll();
					System.out.println("UploadThread : beginning upload video : " + vWrapper.title);
					if(vWrapper != null){
						UploadVideo uploadVideo = new UploadVideo();
						uploadVideo.execute(vWrapper.title, vWrapper.description, vWrapper.tag, vWrapper.vLocation);
						//delete record on data base
						deleteRecord(vWrapper.recordId);
						System.out.println("UploadThread : upload video : " + vWrapper.title + " completed");	
					}
				}
				else{
					System.out.println("upload queue empty");
				}
				isCompleted = true;
			}	
			try {
				Thread.sleep(5 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void addElement(VideoWraper vWrapper) {
		try {
			System.out.println("UploadThread : Add new video : " + vWrapper.title + " to queue");
			uploadQueue.put(vWrapper);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void startThread() {
		thread.start();
	}
	
	private void deleteRecord(int recordId) {
		String query = "DELETE FROM video_container WHERE Id = ? ;";
		PreparedStatement preparedStm;
		try {
			preparedStm = MySqlAccess.getInstance().connect.prepareStatement(query);
			preparedStm.setInt(1, recordId);
			preparedStm.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}	
	}
}
