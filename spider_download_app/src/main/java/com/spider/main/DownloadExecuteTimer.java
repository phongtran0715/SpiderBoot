package com.spider.main;

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
import java.util.TimerTask;
import org.apache.log4j.Logger;
import com.github.axet.vget.DirectDownload;
import com.google.api.client.util.DateTime;
import com.google.api.services.samples.youtube.cmdline.data.Search;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import spiderboot.configuration.Config;
import spiderboot.util.SpiderTypeDef;
import spiderboot.util.Utility;
import spiderboot.util.VideoWraper;
import java.util.List;

public class DownloadExecuteTimer extends TimerTask {
	String timerId;
	String cHomeId;
	String cMonitorId;
	boolean isComplete = true;
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	static Utility util = new Utility();
	String videoFolderBase;
	private static final Logger logger = Logger.getLogger(DownloadExecuteTimer.class);
	private static String prefixOS = util.prefixOS();

	public DownloadExecuteTimer(String timerId, String cHomeId, String cMonitorId) {
		this.timerId = timerId;
		this.cHomeId = cHomeId;
		this.cMonitorId = cMonitorId;
		//TODO: set video folder base
		videoFolderBase = Config.videoFolder;
	}

	@Override
	public void run() {
		completeTask();
	}

	private void completeTask() {
		logger.info("Timer " + timerId + " is  running... ");
		if (isComplete) {
			isComplete = false;
			Date lastSyncTime = getLastSyncTime(timerId);
			DateTime ytbTime = new DateTime(lastSyncTime);
			Long startTime = System.currentTimeMillis();
			List<SearchResult> result = Search.getInstance().getVideoByPublishDate(cMonitorId, ytbTime);
			Iterator<SearchResult> iteratorSearchResults = result.iterator();
			if (!iteratorSearchResults.hasNext()) {
				isComplete = true;
			} else {
				while (iteratorSearchResults.hasNext()) {
					SearchResult singleVideo = iteratorSearchResults.next();
					ResourceId rId = singleVideo.getId();
					if (rId.getKind().equals("youtube#video")) {
						String vId = rId.getVideoId();
						logger.info("===========================================================");
						logger.info("Timer Id  = " + this.timerId + "|DETECTED new VIDEOID = " + vId);
						//Download video
						DirectDownload dowloadHandle = new DirectDownload();
						String path = videoFolderBase + prefixOS + cHomeId + "-" + cMonitorId;
						util.createFolderIfNotExist(path);
						File theDir = new File(path);
						if (theDir.exists()) {
							startTime = System.currentTimeMillis();
							logger.info("Start Downloading:|VIDEOID=" + vId);
							String lastSeq = genVideoName();
							String ext = dowloadHandle.download(vId, path, "video_" + lastSeq);
							logger.info("Download Success:|VIDEOID=" + vId + "|take time=" + (System.currentTimeMillis() - startTime));
							// Get video info
							List<Video> videoList = Search.getInstance().getVideoInfo(vId, Config.youtubeKey);
							Iterator<Video> iteratorSRS = videoList.iterator();
							if (videoList != null) {
								if(iteratorSRS.hasNext()) {
									Video sgVideo = iteratorSRS.next();
									//Insert video info to data base
									VideoWraper vWraper = getVideoInfor(sgVideo, lastSeq, ext);
									saveVideoInfo(vWraper);
								}
							}
						}
					}
				}
			}
			//Update last sync time
			lastSyncTime = new Date();
			updateLastSyncTime(lastSyncTime);
			isComplete = true;
			logger.info("Timer " + timerId + " is running ... ");
		} else {
			logger.info("Timer " + timerId + " is still working ... ");
		}
	}

	private Date getLastSyncTime(String mapId) {
		Date lastSyncTime = null;
		String query = "SELECT LastSyncTime, monitorchannelid FROM home_monitor_channel_mapping WHERE Id = '" + mapId + "';";
		Statement stmt = null;
		try {
//			stmt = MySqlAccess.getInstance().connect.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				lastSyncTime = (Date) rs.getTimestamp("LastSyncTime");
			}
		} catch (Exception ex) {
			logger.info(ex.toString());
		}
		return lastSyncTime;
	}

	private void updateLastSyncTime(Date lastSyncTime) {
		Timestamp timestamp = new Timestamp(lastSyncTime.getTime());
		PreparedStatement preparedStm = null;
		String query = "UPDATE home_monitor_channel_mapping SET LastSyncTime = ? WHERE Id = ? ";
		try {
//			preparedStm = MySqlAccess.getInstance().connect.prepareStatement(query);
			preparedStm.setTimestamp(1, timestamp);
			preparedStm.setInt(2, Integer.valueOf(timerId));
			preparedStm.executeUpdate();
		} catch (SQLException ex) {
			logger.info("ERR_UPDATE_LASTSYNCTIME|" + ex.getMessage());
			return;
		}
	}

	private VideoWraper getVideoInfor(Video singleVideo, String lastSeq, String ext) {
		String vId = singleVideo.getId();
		String title = singleVideo.getSnippet().getTitle();
		String desc = singleVideo.getSnippet().getDescription();
		String thumb = singleVideo.getSnippet().getThumbnails().getDefault().getUrl();
		String vLocation = "video_" + lastSeq + "." + ext;
		String tags = "";
		List<String> lstTags = singleVideo.getSnippet().getTags();
		if (lstTags != null) {
			Iterator<String> iteratorTags = lstTags.iterator();
			while (iteratorTags.hasNext()) {
				tags += iteratorTags.next() + System.getProperty("line.separator");
			}
		}
		VideoWraper vWraper = new VideoWraper(vId, title, tags, desc, thumb, vLocation);
		return vWraper;
	}

	private void saveVideoInfo(VideoWraper vWraper) {
		Timestamp timestamp = new Timestamp(new Date().getTime());
		PreparedStatement preparedStm = null;
		String query = "INSERT INTO video_container (VideoId, Title, Tag, Thumbnail, Description,"
				+ "VideoLocation, HomeChannelId, MonitorChannelId, DownloadDate, ProcessStatus) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
		try {
//			preparedStm = MySqlAccess.getInstance().connect.prepareStatement(query);
			preparedStm.executeQuery("SET NAMES 'utf8mb4'");
			preparedStm.executeQuery("SET CHARACTER SET 'utf8mb4'");
			preparedStm.setString(1, vWraper.vId);
			preparedStm.setString(2, vWraper.title);
			preparedStm.setString(3, vWraper.tag);
			preparedStm.setString(4, vWraper.thumbnail);
			preparedStm.setString(5, vWraper.description);
			preparedStm.setString(6, vWraper.vLocation);
			preparedStm.setString(7, cHomeId);
			preparedStm.setString(8, cMonitorId);
			preparedStm.setTimestamp(9, timestamp);
			preparedStm.setInt(10, SpiderTypeDef.DOWNLOADED);
			preparedStm.executeUpdate();
			logger.info("Saved video " + vWraper.vId + " to database");

		} catch (SQLException e) {
			logger.error("ERR_SAVE_VIDEOCONTAINER| " + e.getMessage());
		}
	}

	private String genVideoName()
	{
		String vName = Long.toString(System.currentTimeMillis());
		return vName;
	}

}
