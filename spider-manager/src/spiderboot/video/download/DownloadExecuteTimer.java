package spiderboot.video.download;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

import com.github.axet.vget.DirectDownload;
import com.google.api.client.util.DateTime;
import com.google.api.services.samples.youtube.cmdline.data.Search;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchResult;

import spider.video.VideoWraper;
import spiderboot.configuration.ConfigProperties;
import spiderboot.databaseconnection.MySqlAccess;
import spiderboot.helper.Util;
import spiderboot.video.upload.UploadExecuteThread;

public class DownloadExecuteTimer extends TimerTask{
	String timerId;
	String cHomeId;
	String cMonitorId;
	boolean isComplete = true;
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Util util = new Util();
	String videoFolderBase;

	public DownloadExecuteTimer(String timerId, String cHomeId, String cMonitorId) {
		// TODO Auto-generated constructor stub
		this.timerId = timerId;
		this.cHomeId = cHomeId;
		this.cMonitorId = cMonitorId;
		videoFolderBase = ConfigProperties.getInstance().getProperties("VideoFolderPath", "");
	}

	@Override
	public boolean cancel() {
		// TODO Auto-generated method stub
		return super.cancel();
	}

	@Override
	public long scheduledExecutionTime() {
		// TODO Auto-generated method stub
		return super.scheduledExecutionTime();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		completeTask();
	}

	private void completeTask() {
		if(isComplete){
			isComplete = false;
			Date lastSyncTime = getLastSyncTime(timerId);
			DateTime ytbTime = new DateTime(lastSyncTime);
			List<SearchResult> result = Search.getInstance().getVideoByPublishDate(cMonitorId, ytbTime);
			Iterator<SearchResult> iteratorSearchResults = result.iterator();
			if (!iteratorSearchResults.hasNext()) {
				isComplete = true;
			}else{
				while (iteratorSearchResults.hasNext()) {
					SearchResult singleVideo = iteratorSearchResults.next();
					ResourceId rId = singleVideo.getId();
					if (rId.getKind().equals("youtube#video")) {
						String vId = rId.getVideoId();
						System.out.println("===========================================================");
						System.out.println("Timer Id  = " + this.timerId + " dectected new video id = " + vId);
						//Download video
						DirectDownload dowloadHandle = new DirectDownload();
						String path = videoFolderBase + "\\" + cHomeId + "-" + cMonitorId;
						util.createFolderIfNotExist(path);
						File theDir = new File(path);
						if(theDir.exists()) {
							dowloadHandle.download(vId, path);
							//Insert video info to data base
							VideoWraper vWraper = getVideoInfor(singleVideo);
							saveVideoInfo(vWraper);
							//Notify to upload thread
							//new UploadExecuteThread().getInstance().addElement(vWraper);	
						}
					}
				}
			}
			//Update last sync time
			lastSyncTime = new Date();
			updateLastSyncTime(lastSyncTime);
			isComplete = true;
			System.out.println("Timer " + timerId + " is running ... ");
		}else{
			//do nothing
			System.out.println("Timer " + timerId + " is still working ... ");
		}
	}

	private Date getLastSyncTime(String mapId){
		Date lastSyncTime = null;
		String query = "SELECT LastSyncTime FROM home_monitor_channel_mapping WHERE Id = '" + mapId + "';";
		Statement stmt;
		try{
			stmt = MySqlAccess.getInstance().connect.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				lastSyncTime = (Date)rs.getTimestamp(1);
			}
		}catch(Exception ex){
			System.out.println(ex.toString());
		}
		return lastSyncTime;
	}

	private void updateLastSyncTime(Date lastSyncTime) {
		Timestamp timestamp = new Timestamp(lastSyncTime.getTime());
		PreparedStatement preparedStm = null;
		String query = "UPDATE home_monitor_channel_mapping SET LastSyncTime = ? WHERE Id = ? " ;
		try {
			preparedStm = MySqlAccess.getInstance().connect.prepareStatement(query);
			// execute insert SQL statement
			preparedStm.setTimestamp(1, timestamp);
			preparedStm.setInt(2, Integer.valueOf(timerId));
			preparedStm.executeUpdate();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			System.out.println(ex.getMessage());
			return;
		}
	}

	private VideoWraper getVideoInfor(SearchResult singleVideo) {
		VideoWraper vWraper = new VideoWraper();
		vWraper.vId = singleVideo.getId().getVideoId();
		vWraper.title = singleVideo.getSnippet().getTitle();
		vWraper.title = vWraper.title.replaceAll("[!@#$%^&*(){}:|<>?]", " ");
		vWraper.description = singleVideo.getSnippet().getDescription();
		vWraper.tag = singleVideo.getEtag();
		vWraper.thumbnail = singleVideo.getSnippet().getThumbnails().getDefault().getUrl();
		vWraper.vLocation =  videoFolderBase + "\\" + cHomeId + "-" + cMonitorId + "\\" + vWraper.title + ".mp4";
		return vWraper;
	}

	private void saveVideoInfo(VideoWraper vWraper) {
		Timestamp timestamp = new Timestamp(new Date().getTime());
		//insert to database
		PreparedStatement preparedStm = null;
		String query = "INSERT INTO video_container (VideoId, Title, Tag, Description, Thumbnail, "
				+ "VideoLocation, HomeChannelId, MonitorChannelId, DownloadDate) VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			preparedStm = MySqlAccess.getInstance().connect.prepareStatement(query);
			// execute insert SQL statement
			preparedStm.setString(1, vWraper.vId);
			preparedStm.setString(2, vWraper.title);
			preparedStm.setString(3, vWraper.tag);
			preparedStm.setString(4, vWraper.description);
			preparedStm.setString(5, vWraper.thumbnail);
			preparedStm.setString(6, vWraper.vLocation);
			preparedStm.setString(7, cHomeId);
			preparedStm.setString(8, cMonitorId);
			preparedStm.setTimestamp(9, timestamp);
			preparedStm.executeUpdate();
			System.out.println("Saved video " + vWraper.vId + " to database");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
			return;
		}
	}
}
