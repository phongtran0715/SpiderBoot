package com.itc.edu.dlvideo.main;

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
import spiderboot.database.MySqlAccess;
import spiderboot.util.Utility;
import spiderboot.util.VideoWraper;
import java.sql.CallableStatement;
import java.util.List;

/*------------------------------------------------------------------------------
 ** History
21-01-2018, [CR-001] phapnd
    Cap nhat func saveVideoInfo, goi procedure de luu thong tin video download

21-01-2018, [CR-002] phapnd
    Cap nhat func DownloadExecuteTimer, khoi tao folder luu video download
    tu file cau hinh
    Cap nhat prefix duong dan luu file video

22-01-2018, [CR-004] phapnd
    Modify duong dan luu file video. Ten file viet lien, phan cach dau _

23-01-2018, [CR-005] phapnd
    Modify file name video
    format: video_[video_container.id].mp4
    Modify close ket noi db sau khi thuc hien lenh

25-01-2018, [CR-006] phapnd
    Modify add ham lastSeqVideoContainer, lay filename format 
    video_[video_container.id].mp4, goi vao ham download

26-01-2018, [CR-007] phapnd
    Modify get thong tin video thong qua ham getVideoID, thay vi search Video
    Correct lai thong tin description, tag cua video

27-01-2018, [CR-009] phapnd
    Modify lay thong tin extentsion cua file videodownload. Luu DB voi ext download ve
 */

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
		// TODO Auto-generated method stub
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
			logger.info("Search info: ChannelId=" + cMonitorId + "|LastSyncTime=" + ytbTime + "|take time:" + (System.currentTimeMillis() - startTime));
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
							//String lastSeq = lastSeqVideoContainer().toString();
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
			logger.info("Timer " + timerId + " is running ... ");
		} else {
			//do nothing
			logger.info("Timer " + timerId + " is still working ... ");
		}
	}

	private Date getLastSyncTime(String mapId) {
		Long startTime = System.currentTimeMillis();
		Date lastSyncTime = null;
		String monitorChannelId = null;
		String query = "SELECT LastSyncTime, monitorchannelid FROM home_monitor_channel_mapping WHERE Id = '" + mapId + "';";
		Statement stmt = null;
		try {
			stmt = MySqlAccess.getInstance().connect.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				lastSyncTime = (Date) rs.getTimestamp("LastSyncTime");
				monitorChannelId = rs.getString("monitorchannelid");
			}
		} catch (Exception ex) {
			logger.info(ex.toString());
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					logger.error("ERR_GET_MontiorChannelID|" + ex.getMessage());
					return lastSyncTime;
				}
			}
		}

		logger.info("Get info: MontiorChannelID=" + monitorChannelId + "|LastSyncTime=" + lastSyncTime + "|take time:" + (System.currentTimeMillis() - startTime));
		return lastSyncTime;
	}

	private void updateLastSyncTime(Date lastSyncTime) {
		Long startTime = System.currentTimeMillis();
		Timestamp timestamp = new Timestamp(lastSyncTime.getTime());
		PreparedStatement preparedStm = null;
		String query = "UPDATE home_monitor_channel_mapping SET LastSyncTime = ? WHERE Id = ? ";
		try {
			preparedStm = MySqlAccess.getInstance().connect.prepareStatement(query);
			// execute insert SQL statement
			preparedStm.setTimestamp(1, timestamp);
			preparedStm.setInt(2, Integer.valueOf(timerId));
			preparedStm.executeUpdate();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			logger.info("ERR_UPDATE_LASTSYNCTIME|" + ex.getMessage());
			return;
		} finally {
			if (preparedStm != null) {
				try {
					preparedStm.close();
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					logger.error("ERR_UPDATE_LASTSYNCTIME|" + ex.getMessage());
					return;
				}
			}
		}
		logger.info("Update info: MontiorChannelID=" + timerId + "|LastSyncTime=" + lastSyncTime + "|take time:" + (System.currentTimeMillis() - startTime));
	}

	private VideoWraper getVideoInfor(Video singleVideo, String lastSeq, String ext) {
		VideoWraper vWraper = new VideoWraper();
		vWraper.vId = singleVideo.getId();
		vWraper.title = singleVideo.getSnippet().getTitle();
		vWraper.title = vWraper.title.replaceAll("[!@#$%^&*(){}:|<>?]", " ");
		logger.info("Correct Description|Video=" + vWraper.title + "|Original Desc=" + singleVideo.getSnippet().getDescription());
		vWraper.description = correctDesc(singleVideo.getSnippet().getDescription());
		vWraper.description = vWraper.description.replaceAll(singleVideo.getSnippet().getChannelTitle(), "Us");
		logger.info("Description=" + vWraper.description);
		StringBuilder tag = new StringBuilder("");
		List<String> lstTags = singleVideo.getSnippet().getTags();
		Iterator<String> iteratorTags = lstTags.iterator();
		// logger.info("VIDEO INFO|" + videoList);
		if (lstTags != null) {
			while (iteratorTags.hasNext()) {
				tag = tag.append(iteratorTags.next()).append(System.getProperty("line.separator"));
			}
		}
		vWraper.tag = tag.toString();
		vWraper.thumbnail = singleVideo.getSnippet().getThumbnails().getDefault().getUrl();
		//vWraper.vLocation = videoFolderBase + prefixOS + cHomeId + "-" + cMonitorId + prefixOS + "video_" + lastSeq + "." + ext;
		vWraper.vLocation = "video_" + lastSeq + "." + ext;
		return vWraper;
	}

	private void saveVideoInfo(VideoWraper vWraper) {
		Timestamp timestamp = new Timestamp(new Date().getTime());
		//insert to database
		PreparedStatement preparedStm = null;
		ResultSet rs = null;
		String query = "INSERT INTO video_container (VideoId, Title, Tag, Thumbnail, "
				+ "VideoLocation, HomeChannelId, MonitorChannelId, DownloadDate, ProcessStatus) "
				+ "VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			preparedStm = MySqlAccess.getInstance().connect.prepareStatement(query);
			preparedStm.executeQuery("SET NAMES 'UTF8'");
			preparedStm.executeQuery("SET CHARACTER SET 'UTF8'");
			// execute insert SQL statement
			preparedStm.setString(1, vWraper.vId);
			preparedStm.setString(2, vWraper.title);
			preparedStm.setString(3, vWraper.tag);
			preparedStm.setString(4, vWraper.thumbnail);
			preparedStm.setString(5, vWraper.vLocation);
			preparedStm.setString(6, cHomeId);
			preparedStm.setString(7, cMonitorId);
			preparedStm.setTimestamp(8, timestamp);
			preparedStm.setInt(9, 1);
			preparedStm.executeUpdate();
			logger.info("Saved video " + vWraper.vId + " to database");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("ERR_SAVE_VIDEOCONTAINER| " + e.getMessage());
		}
	}

	//	private Integer saveVideoInfo(VideoWraper vWraper) {
	//		Integer autoIncKeyFromFunc = -1;
	//		Long startTime = System.currentTimeMillis();
	//		Timestamp timestamp = new Timestamp(new Date().getTime());
	//		//insert to database
	//		PreparedStatement preparedStm = null;
	//		ResultSet rs = null;
	//		//        String query = "INSERT INTO video_container (VideoId, Title, Tag, Description, Thumbnail, "
	//		//                + "VideoLocation, HomeChannelId, MonitorChannelId, DownloadDate) VALUES (?,?,?,?,?,?,?,?,?)";
	//		String query = "{ call INSERTNEWMO(?,?,?,?,?,?,?,?,?) }";
	//		try {
	//			logger.info("\n=============V I D E O       I N F O R M A T I O N==================");
	//			logger.info(" Video Id: " + vWraper.vId);
	//			logger.info(" Title: " + vWraper.title);
	//			logger.info(" Tag: " + vWraper.tag);
	//			logger.info(" Description: " + vWraper.description);
	//			logger.info(" Thumbnail: " + vWraper.thumbnail);
	//			logger.info(" Location: " + vWraper.vLocation);
	//			logger.info(" Montior Channel: " + cMonitorId);
	//			logger.info("\n=============================================================\n");
	//
	//			preparedStm = MySqlAccess.getInstance().connect.prepareStatement(query);
	//			preparedStm.executeQuery("SET NAMES 'UTF8'");
	//			preparedStm.executeQuery("SET CHARACTER SET 'UTF8'");
	//			// execute insert SQL statement
	//			preparedStm.setString(1, vWraper.vId);
	//			preparedStm.setString(2, vWraper.title);
	//			preparedStm.setString(3, vWraper.tag);
	//			preparedStm.setString(4, vWraper.description);
	//			preparedStm.setString(5, vWraper.thumbnail);
	//			preparedStm.setString(6, vWraper.vLocation);
	//			preparedStm.setString(7, cHomeId);
	//			preparedStm.setString(8, cMonitorId);
	//			preparedStm.setTimestamp(9, timestamp);
	//			preparedStm.executeUpdate();
	//			logger.info("Saved video " + vWraper.vId + " to database");
	//
	//			//
	//			// Use the MySQL LAST_INSERT_ID()
	//			// function to do the same thing as getGeneratedKeys()
	//			//
	//			rs = preparedStm.executeQuery("SELECT LAST_INSERT_ID()");
	//
	//			if (rs.next()) {
	//				autoIncKeyFromFunc = rs.getInt(1);
	//				logger.info("Last sequence for Indexing VideoID=" + autoIncKeyFromFunc);
	//			} else {
	//				// TODO Auto-generated catch block
	//				logger.error("ERR_LAST_SEQ_VIDEOCONTAINER|" + autoIncKeyFromFunc);
	//				return autoIncKeyFromFunc;
	//			}
	//
	//		} catch (SQLException e) {
	//			// TODO Auto-generated catch block
	//			logger.error("ERR_SAVE_VIDEOCONTAINER|" + e.getMessage());
	//			return autoIncKeyFromFunc;
	//		} finally {
	//			if (rs != null) {
	//				try {
	//					rs.close();
	//				} catch (SQLException ex) {
	//					// TODO Auto-generated catch block
	//					logger.error("ERR_SAVE_VIDEOCONTAINER|" + ex.getMessage());
	//					return autoIncKeyFromFunc;
	//				}
	//			}
	//			if (preparedStm != null) {
	//				try {
	//					preparedStm.close();
	//				} catch (SQLException ex) {
	//					// TODO Auto-generated catch block
	//					logger.error("ERR_SAVE_VIDEOCONTAINER|" + ex.getMessage());
	//					return autoIncKeyFromFunc;
	//				}
	//			}
	//		}
	//		logger.info("Insert info: MontiorChannelID=" + cMonitorId + "|VideoID=" + vWraper.vId + "|TITLE:" + vWraper.title + "|take time:" + (System.currentTimeMillis() - startTime));
	//
	//		return autoIncKeyFromFunc;
	//	}

	private String genVideoName()
	{
		String vName = Long.toString(System.currentTimeMillis());
		return vName;
	}

	private void VideoContainerStatus(String title, int recordId, int status) {
		String query = "{ call UPLOADSTATUS(?,?) }";
		PreparedStatement preparedStm = null;
		try {
			preparedStm = MySqlAccess.getInstance().connect.prepareStatement(query);
			preparedStm.setInt(1, recordId);
			preparedStm.setInt(2, status);
			preparedStm.executeUpdate();
			logger.info("Update VIDEOCONTAINER STATUS: Video= " + title + "|STATUS=" + status);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("ERR_UPDATE_VIDEOCONTAINER_STATUS|" + e.getMessage());
			return;
		} finally {
			if (preparedStm != null) {
				try {
					preparedStm.close();
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					logger.error("ERR_UPDATE_VIDEOCONTAINER_STATUS|" + ex.getMessage());
					return;
				}
			}
		}
		//logger.info("Insert info: MontiorChannelID=" + cMonitorId + "|VideoID=" + vWraper.vId + "|TITLE:" + vWraper.title + "|take time:" + (System.currentTimeMillis() - startTime));

	}

	private Integer lastSeqVideoContainer() {
		Integer lastSeq = 0;
		String query = "{ call GETLASTSEQ() }";
		CallableStatement cStmt = null;
		try {
			cStmt = MySqlAccess.getInstance().connect.prepareCall(query);
			ResultSet rs = cStmt.executeQuery();
			if (rs.next()) {
				lastSeq = rs.getInt(1);

				logger.info("Last Sequence Video_Container : " + lastSeq);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("ERR_GET_LASTSEQ_VIDEOS|" + e);
		} finally {
			if (cStmt != null) {
				try {
					cStmt.close();
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					logger.error("ERR_GET_LASTSEQ_VIDEOS|" + ex.getMessage());
					return lastSeq;
				}
			}
		}
		return lastSeq;
	}

	private String correctDesc(String desc) {
		Boolean bMatch = false;
		Long startTime = System.currentTimeMillis();
		String[] partStrs = desc.split("\\r?\\n");
		StringBuilder returnDesc = new StringBuilder("");
		String query = "SELECT msg FROM utblinvdesc;";
		Statement stmt = null;
		for (String partStr : partStrs) {
			bMatch = false;
			try {
				stmt = MySqlAccess.getInstance().connect.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next() && !bMatch) {
					String temp = rs.getString(1);
					if (partStr.toLowerCase().contains(temp)) {
						bMatch = true;
					}
				}
				if (!bMatch) {
					returnDesc.append(partStr).append(System.getProperty("line.separator"));
				}
			} catch (Exception ex) {
				logger.info(ex.toString());
			} finally {
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException ex) {
						// TODO Auto-generated catch block
						logger.error("ERR_CORRECT_DESCRIPTION|" + ex.getMessage());
					}
				}
			}
		}

		logger.info("Correct Desciption IS|=" + returnDesc.toString() + "|take time:" + (System.currentTimeMillis() - startTime));
		return returnDesc.toString();
	}

}
