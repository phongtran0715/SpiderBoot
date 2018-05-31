package com.spider.main;

import java.io.File;
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
import com.spider.corba.DownloadCorbaClient;

import SpiderAgentApp.AgentSidePackage.VideoInfo;
import spiderboot.configuration.DownloadConfig;
import spiderboot.data.DataController;
import spiderboot.util.Utility;
import spiderboot.util.VideoWraper;
import java.util.List;

public class DownloadExecuteTimer extends TimerTask {
	int timerId;
	String cHomeId;
	String cMonitorId;
	static Utility util;;
	String videoFolderBase;
	DateFormat dateFormat;
	DownloadCorbaClient downloadClient;
	DownloadConfig downloadConfig;
	boolean isInitCorba = false;
	boolean isComplete = true;
	private static final Logger logger = Logger.getLogger(DownloadExecuteTimer.class);

	public DownloadExecuteTimer(int timerId, String cHomeId, String cMonitorId) {
		this.timerId = timerId;
		this.cHomeId = cHomeId;
		this.cMonitorId = cMonitorId;
		util = new Utility();
		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		downloadConfig = DataController.getInstance().downloadConfig;
		downloadClient = new DownloadCorbaClient();
		isInitCorba = downloadClient.initCorba(downloadConfig.corbaRef);
		videoFolderBase = downloadConfig.outputVideo;
	}

	@Override
	public void run() {
		completeTask();
	}

	private void completeTask() {
		if (isComplete) {
			isComplete = false;
			Date lastSyncTime = getLastSyncTime(timerId);
			DateTime ytbTime = new DateTime(lastSyncTime);
			List<SearchResult> result = Search.getInstance().getVideoByPublishDate(cMonitorId, ytbTime, downloadConfig.maxResult);
			Iterator<SearchResult> iteratorSearchResults = result.iterator();
			if (!iteratorSearchResults.hasNext()) {
				isComplete = true;
			} 
			else 
			{
				while (iteratorSearchResults.hasNext()) {
					SearchResult singleVideo = iteratorSearchResults.next();
					ResourceId rId = singleVideo.getId();
					if (rId.getKind().equals("youtube#video")) {
						String vId = rId.getVideoId();
						//Download video
						DirectDownload dowloadHandle = new DirectDownload();
						String videoLocation = videoFolderBase + "/" + cHomeId + "-" + cMonitorId;
						util.createFolderIfNotExist(videoLocation);
						File theDir = new File(videoLocation);
						if (theDir.exists()) {
							String ext = dowloadHandle.download(vId, videoLocation);
							// Get video info
							List<Video> videoList = Search.getInstance().getVideoInfo(vId, DataController.getInstance().downloadConfig.apiKey);
							Iterator<Video> iteratorSRS = videoList.iterator();
							if (videoList != null) {
								if(iteratorSRS.hasNext()) {
									Video sgVideo = iteratorSRS.next();
									//Insert video info to data base
									VideoWraper vWraper = getVideoInfor(sgVideo, vId, ext);
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
			logger.info("Timer " + timerId + " finished at " + new Date().toString());
			isComplete = true;
		} else {
		}
	}

	private Date getLastSyncTime(int timerId)
	{
		Date result = null;
		isInitCorba = downloadClient.initCorba(downloadConfig.corbaRef);
		if(isInitCorba)
		{
			if(downloadClient.downloadAppImpl != null)
			{
				try {
					long lastTime = downloadClient.downloadAppImpl.getLastSyncTime(timerId);
					Date date = new Date(lastTime * 1000);
					result = date;
				}catch (Exception e) {
					logger.error("Can not call corba agent server function");
					logger.error(e.toString());
				}
			}else {
				logger.error("Download client implementation is NULL");
			}
		}else {
			logger.error("Init corba client FALSE");
		}
		return result;
	}

	private void updateLastSyncTime(Date lastSyncTime)
	{
		isInitCorba = downloadClient.initCorba(downloadConfig.corbaRef);
		if(isInitCorba)
		{
			if(downloadClient.downloadAppImpl != null)
			{
				try {
					Timestamp ts = new Timestamp(new Date().getTime());
					System.out.println("---> Update last Sync time by time stamp = " + ts.getTime() / 1000);
					downloadClient.downloadAppImpl.updateLastSyntime(this.timerId, ts.getTime() / 1000);
				}catch (Exception e) {
					System.out.println(e.toString());
				}
			}else {
				logger.error("Download client implementation is NULL");
			}
		}else {
			logger.error("Init corba client FALSE");
		}
	}

	private VideoWraper getVideoInfor(Video singleVideo, String videoName, String ext) {
		logger.info("Function getVideoInfor : >>>");
		String videoId = singleVideo.getId();
		String title = singleVideo.getSnippet().getTitle();
		String desc = singleVideo.getSnippet().getDescription();
		String thumb = singleVideo.getSnippet().getThumbnails().getDefault().getUrl();
		String vDownloadPath = videoFolderBase + "/" + cHomeId + "-" + cMonitorId + "/" + videoName + "." + ext;
		String vRenderPath = "";
		String tags = "";
		List<String> lstTags = singleVideo.getSnippet().getTags();
		if (lstTags != null) {
			Iterator<String> iteratorTags = lstTags.iterator();
			while (iteratorTags.hasNext()) {
				tags += iteratorTags.next() + System.getProperty("line.separator");
			}
		}
		int processStatus = 1;
		int license = 0;
		VideoWraper vWraper = new VideoWraper(videoId, title, tags, desc, thumb, 
				vDownloadPath, vRenderPath, cHomeId, cMonitorId, processStatus, license);
		return vWraper;
	}

	private void saveVideoInfo(VideoWraper videoWrapper)
	{
		logger.info("Function saveVideoInfo:: >>>>");
		isInitCorba = downloadClient.initCorba(downloadConfig.corbaRef);
		if(isInitCorba)
		{
			if(downloadClient.downloadAppImpl != null)
			{
				try {
					System.out.println(videoWrapper.description);
					SpiderAgentApp.AgentSidePackage.VideoInfo vInfo = new VideoInfo(videoWrapper.videoId, videoWrapper.title, 
							videoWrapper.tag, "", videoWrapper.thumbnail, 
							videoWrapper.vDownloadPath, videoWrapper.vRenderPath, videoWrapper.cHomeId, 
							videoWrapper.cMonitorId, videoWrapper.processStatus, videoWrapper.license);
					
					downloadClient.downloadAppImpl.updateDownloadedVideo(vInfo);
				}catch (Exception e) {
					System.out.println(e.toString());
				}
			}else {
				logger.error("Download client implementation is NULL");
			}
		}else {
			logger.error("Init corba client FALSE");
		}
	}
}
