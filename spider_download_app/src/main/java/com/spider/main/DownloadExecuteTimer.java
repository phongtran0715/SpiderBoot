package com.spider.main;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.TimerTask;
import org.apache.log4j.Logger;
import org.omg.CORBA.StringHolder;

import com.github.axet.vget.DirectDownload;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.Sleeper;
import com.google.api.services.samples.youtube.cmdline.data.Search;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import com.spider.corba.DownloadCorbaClient;

import spiderboot.configuration.Config;
import spiderboot.util.SpiderTypeDef;
import spiderboot.util.Utility;
import spiderboot.util.VideoWraper;
import java.util.List;
import SpiderDownloadApp.*;

public class DownloadExecuteTimer extends TimerTask {
	int timerId;
	String cHomeId;
	String cMonitorId;
	boolean isComplete = true;
	static Utility util;;
	String videoFolderBase;
	DateFormat dateFormat;
	DownloadCorbaClient downloadClient;
	boolean isInitCorba = false;

	public DownloadExecuteTimer(int timerId, String cHomeId, String cMonitorId) {
		this.timerId = timerId;
		this.cHomeId = cHomeId;
		this.cMonitorId = cMonitorId;
		//TODO: set video folder base
		util = new Utility();
		videoFolderBase = "/home/phongtran0715/Downloads/Video/test";
		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		downloadClient = new DownloadCorbaClient();
		isInitCorba = downloadClient.initCorba();
	}

	@Override
	public void run() {
		completeTask();
	}

	private void completeTask() {
		if (isComplete) {
			isComplete = false;
			System.out.println("Timer " + timerId + " started at " + new Date().toString());
			Date lastSyncTime = getLastSyncTime(timerId);

			DateTime ytbTime = new DateTime(lastSyncTime);
			List<SearchResult> result = Search.getInstance().getVideoByPublishDate(cMonitorId, ytbTime);
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
						String path = videoFolderBase + "/" + cHomeId + "-" + cMonitorId;
						util.createFolderIfNotExist(path);
						File theDir = new File(path);
						if (theDir.exists()) {
							//startTime = System.currentTimeMillis();
							String lastSeq = genVideoName();
							String ext = dowloadHandle.download(vId, path, "video_" + lastSeq);
							// Get video info
							List<Video> videoList = Search.getInstance().getVideoInfo(vId, Config.youtubeKey);
							Iterator<Video> iteratorSRS = videoList.iterator();
							if (videoList != null) {
								if(iteratorSRS.hasNext()) {
									Video sgVideo = iteratorSRS.next();
									//Insert video info to data base
									VideoWraper vWraper = getVideoInfor(sgVideo, lastSeq, ext);
									//saveVideoInfo(vWraper);
								}
							}
						}
					}
				}
			}

			//Update last sync time
			lastSyncTime = new Date();
			updateLastSyncTime(lastSyncTime);
			System.out.println("Timer " + timerId + " finished at " + new Date().toString());
			isComplete = true;
		} else {
		}
	}

	private Date getLastSyncTime(int timerId)
	{
		Date result = null;
		if(isInitCorba)
		{
			if(downloadClient.downloadAppImpl != null)
			{
				try {
					long lastTime = downloadClient.downloadAppImpl.getLastSyncTime(timerId);
					System.out.println("last sync time = " + lastTime);
					Date date = new Date(lastTime);
					result = date;
				}catch (Exception e) {
					System.out.println("Can not call corba agent server function");
					System.out.println(e.toString());
				}
			}else {
				System.out.println("Download client implementation is NULL");
			}
		}else {
			System.out.println("Init corba client FALSE");
		}
		return result;
	}

	private void updateLastSyncTime(Date lastSyncTime)
	{
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
				System.out.println("Download client implementation is NULL");
			}
		}else {
			System.out.println("Init corba client FALSE");
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

	private String genVideoName()
	{
		String vName = Long.toString(System.currentTimeMillis());
		return vName;
	}
}
