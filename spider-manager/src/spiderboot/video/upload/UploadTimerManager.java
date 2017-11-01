package spiderboot.video.upload;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import spider.video.VideoWraper;
import spiderboot.databaseconnection.MySqlAccess;
import spiderboot.timer.TimerWrapper;

public class UploadTimerManager extends TimerWrapper {
	private static UploadTimerManager instance = null;
	//UploadThread uploadThread = new UploadThread();

	public UploadTimerManager() {
		// TODO Auto-generated constructor stub
	}
	public static UploadTimerManager getInstance(){
		if(instance == null){
			instance = new UploadTimerManager();
		}
		return instance;
	}

	public void initTimerTask(){
		Statement stmt;
		String query = "SELECT Id, VideoId, Title, Tag, Description, Thumbnail, VideoLocation, HomeChannelId,"
				+ " MonitorChannelId, DownloadDate FROM video_container";
		try 
		{
			stmt = MySqlAccess.getInstance().connect.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int recordId = rs.getInt(1);
				String vId = rs.getString(2);
				String title = rs.getString(3);
				String tag = rs.getString(4);
				String description = rs.getString(5);
				String thumbnail = rs.getString(6);
				String vLocation = rs.getString(7);
				String homeChannelId = rs.getString(8);
				String monitorChannelId = rs.getString(9);
				java.sql.Timestamp downloadDate = rs.getTimestamp(10);
				VideoWraper vWrapper = new VideoWraper(recordId, vId, title, tag, description, thumbnail, 
						vLocation, homeChannelId, monitorChannelId, downloadDate);
				System.out.println("Add video " + vWrapper.vId + " to upload thead queue");
				new UploadExecuteThread().getInstance().addElement(vWrapper);
			}
			System.out.println("Start upload thread");
			new UploadExecuteThread().getInstance().startThread();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
