package spiderboot.video.download;

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
import spiderboot.databaseconnection.MySqlAccess;

public class DownloadTimer extends TimerTask{
	String timerId;
	String cHomeId;
	String cMonitorId;
	boolean isComplete = true;
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	String storeLocation = "C:\\Users\\phong.tran\\Downloads\\Video\\spider_video\\";

	public DownloadTimer(String timerId, String cHomeId, String cMonitorId) {
		// TODO Auto-generated constructor stub
		this.timerId = timerId;
		this.cHomeId = cHomeId;
		this.cMonitorId = cMonitorId;
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
		System.out.println("Timer " + timerId + " started at:"+new Date());
		completeTask();
		System.out.println("Timer " + timerId + " finished at:"+new Date());
	}

	private void completeTask() {
		if(isComplete){
			isComplete = false;
			Date lastSyncTime = getLastSyncTime(timerId);
			System.out.println("last sync time = " + dateFormat.format(lastSyncTime));
			DateTime ytbTime = new DateTime(lastSyncTime);
			//TODO: check new video and download them
			List<SearchResult> result = Search.getInstance().getVideoByPublishDate(cMonitorId, ytbTime);
			Iterator<SearchResult> iteratorSearchResults = result.iterator();
			if (!iteratorSearchResults.hasNext()) {
				System.out.println(" There aren't any results for your query.");
				return;
			}else{
				while (iteratorSearchResults.hasNext()) {

					SearchResult singleVideo = iteratorSearchResults.next();
					ResourceId rId = singleVideo.getId();
					if (rId.getKind().equals("youtube#video")) {
						String vId = rId.getVideoId();
						//Download video
						DirectDownload dowloadHandle = new DirectDownload();
						dowloadHandle.execute(vId, storeLocation);
						//TODO: insert video info to data base
						VideoWraper vWraper = getVideoInfor(singleVideo);
						vWraper.dummyData();
						saveVideoInfo(vWraper);
						//Update last sync time
						lastSyncTime = new Date();
						updateLastSyncTime(lastSyncTime);
					}
				}
			}
			//Update last sync time
			lastSyncTime = new Date();
			System.out.println("Update last sync time  = " + dateFormat.format(lastSyncTime));
			updateLastSyncTime(lastSyncTime);
			isComplete = true;	
		}else{
			System.out.println("Task have not yet completeed.");
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
			//preparedStm.executeUpdate();
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
		vWraper.description = singleVideo.getSnippet().getDescription();
		vWraper.tag = singleVideo.getEtag();
		vWraper.thumbnail = singleVideo.getSnippet().getThumbnails().getDefault().getUrl();
		vWraper.vLocation = storeLocation + vWraper.title + ".mp4";
		return vWraper;
	}

	private void saveVideoInfo(VideoWraper vWraper) {
		Timestamp timestamp = new Timestamp(new Date().getTime());
		//insert to database
		PreparedStatement preparedStm = null;
		String query = "INSERT INTO video_container (VideoId, Title, Tag, Description, Thumbnail, "
				+ "VideoLocation, TargetChannelId, DownloadDate) VALUES (?,?,?,?,?,?,?,?)";
		try {
			preparedStm = MySqlAccess.getInstance().connect.prepareStatement(query);
			// execute insert SQL statement
			preparedStm.setString(1, vWraper.vId);
			preparedStm.setString(2, vWraper.title);
			preparedStm.setString(3, vWraper.tag);
			preparedStm.setString(4, vWraper.description);
			preparedStm.setString(5, vWraper.thumbnail);
			preparedStm.setString(6, vWraper.vLocation);
			preparedStm.setString(7, cMonitorId);
			preparedStm.setTimestamp(8, timestamp);
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
