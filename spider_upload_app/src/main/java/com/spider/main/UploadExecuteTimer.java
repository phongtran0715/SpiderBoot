package com.spider.main;

import java.io.File;
import java.util.Date;
import java.util.TimerTask;
import org.apache.log4j.Logger;

import com.google.api.client.util.DateTime;
import com.google.api.services.samples.youtube.cmdline.data.UploadVideo;
import com.spider.corba.UploadCorbaClient;
import SpiderUploadApp.SpiderFootSidePackage.UploadInfo;
import spiderboot.configuration.UploadConfig;
import spiderboot.data.DataController;
import spiderboot.util.Utility;

public class UploadExecuteTimer extends TimerTask{

	boolean isComplete = true;
	String videoFolderBase;
	static Utility util = new Utility();
	private static final Logger logger = Logger.getLogger(UploadExecuteTimer.class);
	UploadVideo uploadVideo;
	UploadConfig uploadConfig;
	UploadCorbaClient uploadClient;
	boolean isInitCorba = false;

	public UploadExecuteTimer(String timerId) {
		logger.info("Function UploadExecuteTimer >>>");
		uploadConfig = DataController.getInstance().uploadConfig;
		uploadClient = new UploadCorbaClient();
		isInitCorba = uploadClient.initCorba(uploadConfig.corbaRef);
		uploadVideo = new UploadVideo();
	}

	@Override
	public void run() {
		System.out.println("Timer task started at:" + new Date());
		completeTask();
		System.out.println("Timer task finished at:" + new Date());
	}

	private void completeTask() {
		if(isComplete){
			isComplete = false;
			if(UploadTimerManager.qUploadJob.isEmpty() == false)
			{
				UploadInfo vInfo = UploadTimerManager.qUploadJob.poll();
				//upload video
				File uploadFile = new File(vInfo.vLocation);
				if (!uploadFile.exists()) {
					logger.error("File " + vInfo.vLocation + " not Exist");
					return;
				}
				
				logger.info("Beginning get authen file >>>>");
				String clientFile = getAuthAtr(vInfo.cHomeId,"ClientSecret");
				String userName = getHomeAtrr(vInfo.cHomeId,"GoogleAccount");
				System.out.println("setclientSecretsFile : " + clientFile);
				UploadVideo.setclientSecretsFile(clientFile);
				System.out.println("setStoreFile : " + "upload_" + userName);
				UploadVideo.setStoreFile( "upload_" + userName);
				logger.info("Complete get authen file <<<<");
				
				logger.info("Beginning standardize meta data >>>>");
				String title = standardizeTitle(vInfo.vTitle);
				String desc = standardizeDesc(vInfo.vDesc);
				String tags = standardizeTags(vInfo.vTags);
				logger.info("Complete standardize meta data <<<<");
				
				logger.info("Beginning upload video " + vInfo.videoId);
				//uploadVideo.execute(title, desc, tags, vInfo.vLocation);
				//update process status 
				updateProcessStatus(vInfo.jobId);
				
				logger.info("Upload complete video " + vInfo.videoId);
				//Delay time for next upload video
				try {
					Thread.sleep(uploadConfig.delayTime * 1000);
				} catch (InterruptedException e) {
					logger.error(e.toString());
					e.printStackTrace();
				}
			}
			isComplete = true;
		}
		else{
			//do nothing
			System.out.println("Process timer task is still running ...");
		}
	}
	private String standardizeTitle(String originTitle) {
		String result = originTitle;
		return result;

	}

	private String standardizeTags(String originTags) {
		String result = originTags;
		return result;
	}

	private String standardizeDesc(String originDesc) {
		String result = originDesc;
		return result;
	}
	private void updateProcessStatus(int id) 
	{
	}

	private String getHomeAtrr(String cHomeId, String key)
	{
		String result = "";
		return result;
	}

	private String getAuthAtr(String cHomeId, String key)
	{
		String result = "";
		return result;
	}
}