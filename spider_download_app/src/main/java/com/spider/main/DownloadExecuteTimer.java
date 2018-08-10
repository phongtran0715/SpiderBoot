package com.spider.main;

import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.TimerTask;
import org.apache.log4j.Logger;

import com.google.api.client.util.DateTime;
import com.google.api.services.samples.youtube.cmdline.data.Search;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import com.spider.corba.DownloadCorbaClient;

import SpiderCorba.SpiderDefinePackage.VideoInfo;
import spiderboot.configuration.DownloadProperty;
import spiderboot.data.DataController;
import spiderboot.util.Utility;
import spiderboot.util.VideoWraper;
import java.util.List;

public class DownloadExecuteTimer extends TimerTask {
	int timerId;
	int mappingType = -1;
	static Utility util;;
	String videoFolderBase;
	DateFormat dateFormat;
	DownloadCorbaClient downloadClient;
	DownloadProperty appProperty;
	boolean isComplete = true;
	private final int MONITOR_CHANNEL 		= 0;
	private final int MONITOR_PLAYLIST	 	= 1;
	private final int MONITOR_KEYWORK		= 2;
	private final int LIST_VIDEO_ONLINE		= 3;
	private final int LIST_VIDEO_OFFLINE	= 4;
	private final int NUM_RETRY				= 3;

	private final Logger logger = Logger.getLogger(DownloadExecuteTimer.class);

	public DownloadExecuteTimer(int timerId) {
		this.timerId = timerId;
		util = new Utility();
		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		appProperty = DataController.getInstance().downloadConfig;
		downloadClient = DownloadCorbaClient.getInstance();		
		videoFolderBase = appProperty.outputVideo;
		//get mapping type
		SpiderCorba.SpiderDefinePackage.DownloadConfig downloadCfg = getDownloadConfig();
		if(downloadCfg == null)
		{
			logger.error("Can not get download config");
			cancel();
		}else {
			this.mappingType = downloadCfg.mappingType;	
		}
	}

	@Override
	public void run() {
		completeTask();
	}

	private void handleMonitorChannel()
	{
		logger.error("Timer Id [" + timerId + "] Start query to get new video");
		//get mapping content
		SpiderCorba.SpiderDefinePackage.DownloadConfig downloadCfg = getDownloadConfig();
		String cMonitorIds [] = downloadCfg.monitorContent.split(",");
		for (String channelId : cMonitorIds) {
			Date lastSyncTime = getLastSyncTime(timerId);
			if(lastSyncTime == null)
			{
				logger.error("Timer Id [" + timerId + "] Error! Can not get last sync time.");
				break;
			}
			DateTime ytbTime = new DateTime(lastSyncTime);
			List<SearchResult> result = Search.getInstance().getVideoByPublishDate(channelId, ytbTime, 
					appProperty.maxResult, appProperty.apiKey);
			Iterator<SearchResult> iteratorSearchResults = result.iterator();
			if (!iteratorSearchResults.hasNext()) {
			} 
			else 
			{
				while (iteratorSearchResults.hasNext()) {
					SearchResult singleVideo = iteratorSearchResults.next();
					ResourceId rId = singleVideo.getId();
					if (rId.getKind().equals("youtube#video")) {
						String vId = rId.getVideoId();
						logger.info("\n\n");
						logger.info("==========>> Timer id [" + timerId + "] DOWNLOADING VIDEO <<==========");
						// Get video info

						logger.info("Begin getvideo information video Id = " + vId );
						VideoWraper vWraper = null;
						List<Video> videoList = Search.getInstance().getVideoInfo(vId, 
								DataController.getInstance().downloadConfig.apiKey);
						Iterator<Video> iteratorSRS = videoList.iterator();
						if (videoList != null) {
							if(iteratorSRS.hasNext()) {
								Video sgVideo = iteratorSRS.next();
								vWraper = extractVideoInfor(sgVideo, vId);
							}
						}

						logger.info("Begin download Video ID = " + vId);
						//Download video
						/*
						if(vWraper.license == 1)
						{
							logger.error("Timer id [" + timerId + "]: Video id = " + vId + " have license = 1. This video will be ignore");
							continue;
						}
						 */

						String videoLocation = videoFolderBase + "/" + timerId;
						util.createFolderIfNotExist(videoLocation);
						File theDir = new File(videoLocation);
						if (theDir.exists()) {
							YoutubeDownload ytDownload = new YoutubeDownload();
							int exisCode = ytDownload.execute(vId, videoLocation);
							if(exisCode == 0)
							{
								vWraper.vDownloadPath = videoLocation + "/" + vId + ".mp4";
								//save video infomation
								if(vWraper != null)
								{
									insertVideoInfo(vWraper);	
								}
							}else {
								logger.error("Can not download video :" + vId );
							}
							logger.info("Download exit code : " + exisCode);
						}
						logger.info("Timer id [" + timerId + "]: Finish download video ID = " + vId);
						logger.info("==========>> Timer id [" + timerId + "] DOWNLOADING COMPLETED <<==========\n\n");
					}
				}
			}
		}
		//Update last sync time
		updateLastSyncTime(new Date());
	}

	private void handleListOnlineVideo() {
		SpiderCorba.SpiderDefinePackage.CustomVideoInfor customVideo = getCustomVideo();
		if(customVideo == null)
		{
			logger.error("Timer id [" + timerId + "]: Custom video is NULL");
			return;
		}
		if(customVideo.videoId.isEmpty())
		{
			logger.info("Timer id [" + timerId + "]: Custom video is empty");
			return;
		}
		logger.info("\n==========>> Timer id [" + timerId + "] DOWNLOADING CUSTOM VIDEO <<==========");
		// Get video info
		try {
			logger.info("Begin getvideo information video Id = " + customVideo.videoId );
			VideoWraper vWraper = null;
			List<Video> videoList = Search.getInstance().getVideoInfo(customVideo.videoId, 
					DataController.getInstance().downloadConfig.apiKey);
			Iterator<Video> iteratorSRS = videoList.iterator();
			if (videoList != null) {
				if(iteratorSRS.hasNext()) {
					Video sgVideo = iteratorSRS.next();
					vWraper = extractVideoInfor(sgVideo, customVideo.videoId);
				}
			}
			//Download video
			String videoLocation = videoFolderBase + "/" + timerId;
			util.createFolderIfNotExist(videoLocation);
			File theDir = new File(videoLocation);
			if (theDir.exists()) {
				YoutubeDownload ytDownload = new YoutubeDownload();
				int exisCode = ytDownload.execute(customVideo.videoId, videoLocation);
				if(exisCode == 0)
				{
					vWraper.vDownloadPath = videoLocation + "/" + customVideo.videoId + ".mp4";	
					//save video infomation
					updateVideoInfo(customVideo.id, vWraper);
				}else {
					logger.error("Download exit code : " + exisCode);
					logger.error("Can not download video : " + customVideo.videoId);
				}
			}
			logger.info("Timer id [" + timerId +"]: Finish download video ID = " + customVideo.videoId);

		}catch(Exception ex)
		{
			logger.error(ex.toString());
		}
		logger.info("\n==========>> Timer id [" + timerId + "] COMPLETE DOWNLOAD CUSTOM VIDEO <<==========\n\n");
	}

	private void completeTask() {
		if (isComplete) {
			isComplete = false;
			switch(mappingType)
			{
			case MONITOR_CHANNEL:
				handleMonitorChannel();
				break;
			case MONITOR_PLAYLIST:
				break;
			case MONITOR_KEYWORK:
				break;
			case LIST_VIDEO_ONLINE:
				handleListOnlineVideo();
				break;
			case LIST_VIDEO_OFFLINE:
				break;
			default:
				break;
			}
			isComplete = true;
		} else {
		}
	}

	private SpiderCorba.SpiderDefinePackage.DownloadConfig getDownloadConfig(){
		SpiderCorba.SpiderDefinePackage.DownloadConfig downloadCfg = null;
		int count = 1;
		do {
			if(downloadClient.downloadAppImpl != null)
			{
				try {
					downloadCfg = downloadClient.downloadAppImpl.getDownloadConfig(timerId);
					return downloadCfg;
				}catch (Exception e) {
					logger.error("Can not call corba server");
					logger.error(e.toString());
					//retry
					logger.info(" [Count = " + count +"] Begin retry to connetion corba server...");
					downloadClient.resolveAgain();
				}
			}else {
				logger.error("Download client implementation is NULL");
			}
			count++;
		}while(count < NUM_RETRY);
		return downloadCfg;
	}

	private Date getLastSyncTime(int timerId)
	{
		Date result = null;
		int count = 1;
		do {
			if(downloadClient.downloadAppImpl != null)
			{
				try {
					long lastTime = downloadClient.downloadAppImpl.getLastSyncTime(timerId);
					result = new Date(lastTime * 1000);
					return result;
				}catch (Exception e) {
					logger.error("Can not call corba server");
					logger.error(e.toString());
					//retry
					logger.info(" [Count = " + count +"] Begin retry to connetion corba server...");
					downloadClient.resolveAgain();
				}
			}else {
				logger.error("Download client implementation is NULL");
			}
			count++;
		}while(count < NUM_RETRY);
		return result;
	}

	private void updateLastSyncTime(Date lastSyncTime)
	{
		int count = 1;
		do
		{
			if(downloadClient.downloadAppImpl != null)
			{
				try {
					Timestamp ts = new Timestamp(new Date().getTime());
					downloadClient.downloadAppImpl.updateLastSyntime(timerId, ts.getTime() / 1000);
					return;
				}catch (Exception e) {
					logger.error("Can not call corba server");
					logger.error(e.toString());
					//retry
					logger.info(" [Count = " + count +"] Begin retry to connetion corba server...");
					downloadClient.resolveAgain();
				}
			}else {
				logger.error("Download client implementation is NULL");
			}
			count++;
		}while(count < NUM_RETRY);
	}

	private VideoWraper extractVideoInfor(Video singleVideo, String videoName) {
		String videoId = singleVideo.getId();
		String title = singleVideo.getSnippet().getTitle();
		String desc = singleVideo.getSnippet().getDescription();
		String thumb = singleVideo.getSnippet().getThumbnails().getDefault().getUrl();
		String vDownloadPath = "";
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
		int license;
		if (singleVideo.getContentDetails().getLicensedContent() == true)
		{
			license = 1;
		}else {
			license = 0;
		}
		logger.info("Video id = " + videoId + " license content = " + license);
		VideoWraper vWraper = new VideoWraper(videoId, title, tags, desc, thumb, 
				vDownloadPath, vRenderPath, timerId, processStatus, license);
		return vWraper;
	}

	private void insertVideoInfo(VideoWraper videoWrapper)
	{
		int count = 1;
		do {
			if(downloadClient.downloadAppImpl != null)
			{
				try {
					SpiderCorba.SpiderDefinePackage.VideoInfo vInfo = new VideoInfo(videoWrapper.videoId, videoWrapper.title, 
							videoWrapper.tag, videoWrapper.description, videoWrapper.thumbnail, 
							videoWrapper.vDownloadPath, videoWrapper.vRenderPath, videoWrapper.mappingId,
							videoWrapper.processStatus, videoWrapper.license);

					downloadClient.downloadAppImpl.insertDownloadedVideo(vInfo);
					return;
				}catch (Exception e) {
					logger.error("Can not call corba server");
					logger.error(e.toString());
					//retry
					logger.info(" [Count = " + count +"] Begin retry to connetion corba server...");
					downloadClient.resolveAgain();
				}
			}else {
				logger.error("Download corba client implementation is NULL");
			}
			count++;
		}while(count < NUM_RETRY);
	}

	private void updateVideoInfo(int id, VideoWraper videoWrapper)
	{
		int count = 1;
		do {
			if(downloadClient.downloadAppImpl != null)
			{
				try {
					SpiderCorba.SpiderDefinePackage.VideoInfo vInfo = new VideoInfo(videoWrapper.videoId, videoWrapper.title, 
							videoWrapper.tag, videoWrapper.description, videoWrapper.thumbnail, 
							videoWrapper.vDownloadPath, videoWrapper.vRenderPath, videoWrapper.mappingId,
							videoWrapper.processStatus, videoWrapper.license);

					downloadClient.downloadAppImpl.updateDownloadedVideo(id, vInfo);
					return;
				}catch (Exception e) {
					logger.error("Can not call corba server");
					logger.error(e.toString());
					//retry
					logger.info(" [Count = " + count +"] Begin retry to connetion corba server...");
					downloadClient.resolveAgain();
				}
			}else {
				logger.error("Download corba client implementation is NULL");
			}
			count++;
		}while(count < NUM_RETRY);

	}

	private SpiderCorba.SpiderDefinePackage.CustomVideoInfor getCustomVideo() {
		SpiderCorba.SpiderDefinePackage.CustomVideoInfor customVideo = null;
		int count = 1;
		do {
			if(downloadClient.downloadAppImpl != null)
			{
				try {
					customVideo = downloadClient.downloadAppImpl.getCustomVideo(appProperty.appId, this.timerId);
					return customVideo;
				}catch (Exception e) {
					logger.error("Can not call corba server");
					logger.error(e.toString());
					//retry
					logger.info(" [Count = " + count +"] Begin retry to connetion corba server...");
					downloadClient.resolveAgain();
				}
				count ++;
			}else {
				logger.error("Download client implementation is NULL");
			}
		}while(count < NUM_RETRY);

		return customVideo;
	}
}
