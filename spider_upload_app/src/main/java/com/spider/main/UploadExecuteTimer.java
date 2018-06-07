package com.spider.main;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.TimerTask;
import org.apache.log4j.Logger;

import com.google.api.services.samples.youtube.cmdline.data.UploadVideo;
import com.spider.corba.UploadCorbaClient;

import SpiderCorba.AgentSidePackage.AuthenInfo;
import SpiderCorba.AgentSidePackage.ClusterInfo;
import SpiderCorba.SpiderDefinePackage.VideoInfo;
import spiderboot.configuration.UploadConfig;
import spiderboot.data.DataController;
import spiderboot.util.Utility;

public class UploadExecuteTimer extends TimerTask{

	boolean isComplete = true;
	String videoFolderBase;
	static Utility util = new Utility();
	private static final Logger logger = Logger.getLogger(UploadExecuteTimer.class);
	private static final String CREDENTIALS_DIRECTORY = ".oauth-credentials";
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
		completeTask();
	}

	private void completeTask() {
		if(isComplete){
			isComplete = false;
			if(UploadTimerManager.qUploadJob.isEmpty() == false)
			{
				
				logger.info("Timer task started at:" + new Date());
				DataDefine.UploadJobData jobData = UploadTimerManager.qUploadJob.poll();
				VideoInfo vInfo = jobData.vInfo;
				SpiderCorba.UploadSidePackage.UploadConfig uploadVideoCfg = jobData.uploadCfg;
				//upload video
				File uploadFile = new File(vInfo.vRenderPath);

				if (!uploadFile.exists()) {
					logger.error("File " + vInfo.vRenderPath + " not Exist");	
					logger.info("Upload complete video " + vInfo.videoId);
					isComplete = true;
					return;
				}
				ClusterInfo clusterInfo = getClusterInfo(vInfo.mappingId);
				logger.info("IP = " + clusterInfo.clusterIp);
				logger.info("User Name = " + clusterInfo.userName);
				logger.info("Password = " + clusterInfo.password);
				logger.info("appid config = "+ uploadConfig.appId);
				logger.info("ip config = "+ uploadConfig.ip);
				/*
				if(clusterInfo.clusterIp.equals(uploadConfig.ip)== false)
				{
					//TODO: download video from render cluster
					SCPDownload scpDownload = new SCPDownload();
					boolean success = scpDownload.execute(clusterInfo.clusterIp, clusterInfo.userName, clusterInfo.password,
							vInfo.vLocation, "/tmp/");
					if(success)
					{
						logger.info("Download from render cluster OK");
					}else {
						logger.error("ERROR! Download file from render cluster FALSE");
						isComplete = true;
						return;
					}
				}
				 */

				logger.info("Uploading video : " + vInfo.vRenderPath);

				AuthenInfo authInfo = getAuthenInfo(vInfo.mappingId);
				logger.info("Authen infor : user name = " + authInfo.userName);
				logger.info("Authen infor : api key = " + authInfo.apiKey);
				logger.info("Authen infor : client secret = " + authInfo.clientSecret);
				logger.info("Authen infor : client id = " + authInfo.clientId);

				logger.info("Beginning get authen file >>>>");
				//TODO: create client secret file
				String clientFile = "/tmp/client_secrets.json";
				//TODO: set authen file name
				String userName = "";
				CrunchifyJSONFileWrite jsonCreate = new CrunchifyJSONFileWrite();
				File jsonFile = new File(clientFile);
				if(jsonFile.exists() == false)
				{
					//Create the file
					try {
						if (jsonFile.createNewFile()){
							logger.error("File created : " + clientFile);
						}else{
							logger.error("Can not create file : " + clientFile);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				jsonCreate.execute(authInfo.clientSecret, authInfo.clientId, clientFile);
				UploadVideo.setclientSecretsFile(clientFile);
				File storeFile = new File(System.getProperty("user.home") + "/" 
						+ CREDENTIALS_DIRECTORY + "/upload_" + userName);
				if(storeFile.exists() == false)
				{
					logger.error("ERROR : Can not get authen store upload file. File does not exist");
					isComplete = true;
					return;
				}
				UploadVideo.setStoreFile( "upload_" + userName);
				logger.info("Complete get authen file <<<<");

				logger.info("Beginning standardize meta data >>>>");
				String title = standardizeTitle(vInfo.title, uploadVideoCfg.vTitleTemp, uploadVideoCfg.enableTitle);
				String desc = standardizeDesc(vInfo.description, uploadVideoCfg.vDescTemp, uploadVideoCfg.enableDes);
				String tags = standardizeTags(vInfo.tags, uploadVideoCfg.vTagsTemp, uploadVideoCfg.enableTags);
				logger.info("Complete standardize meta data <<<<");

				logger.info("Beginning upload video " + vInfo.videoId);
				//uploadVideo.execute(title, desc, tags, vInfo.vLocation);
				//update process status 
				//updateUploadedInfo(vInfo.jobId);

				logger.info("Upload complete video " + vInfo.videoId);
				//Delay time for next upload video
				try {
					Thread.sleep(uploadConfig.delayTime * 1000);
				} catch (InterruptedException e) {
					logger.error(e.toString());
					e.printStackTrace();
				}
				logger.info("Timer task finished at:" + new Date());
			}
			isComplete = true;
		}
		else{
			//do nothing
			logger.info("Process timer task is still running ...");
		}
	}
	private String standardizeTitle(String originTitle, String titleTemp, boolean enableTitle) {
		String result = originTitle;
		logger.info("title temp : " + titleTemp);
		return result;

	}

	private String standardizeTags(String originTags, String tagTemp, boolean enableTag) {
		String result = originTags;
		logger.info("tag temp : " + tagTemp);
		return result;
	}

	private String standardizeDesc(String originDesc, String desctemp, boolean enableDesc) {
		String result = originDesc;
		logger.info("desc temp : " + desctemp);
		return result;
	}

	private void updateUploadedInfo(int jobId) 
	{
		logger.info(">>> Function [updateUploadedInfo] : job Id = " + jobId);
		if(isInitCorba == false)
		{
			isInitCorba = uploadClient.initCorba(uploadConfig.corbaRef);	
		}
		if(isInitCorba)
		{
			if(uploadClient.uploadAppImpl != null)
			{
				try {
					uploadClient.uploadAppImpl.updateUploadedVideo(jobId);
				}catch (Exception e) {
					System.out.println(e.toString());
				}
			}else {
				logger.error("Upload client implementation is NULL");
			}
		}else {
			logger.error("Init corba client FALSE");
		}
	}

	private ClusterInfo getClusterInfo(int mappingId)
	{
		logger.info("Function getClusterInfo : mapping ID = " + mappingId + " >>>");
		ClusterInfo clusterInfo = null;
		if(isInitCorba == false)
		{
			isInitCorba = uploadClient.initCorba(uploadConfig.corbaRef);	
		}
		if(isInitCorba)
		{
			if(uploadClient.uploadAppImpl != null)
			{
				try {

					clusterInfo = uploadClient.uploadAppImpl.getClusterInfo(2, mappingId);
				}catch (Exception e) {
					System.out.println(e.toString());
				}
			}else {
				logger.error("Upload client implementation is NULL");
			}
		}else {
			logger.error("Init corba client FALSE");
		}
		return clusterInfo;
	}

	private AuthenInfo getAuthenInfo(int mappingId)
	{
		logger.info("Function getAuthenInfo : mapping ID = " + mappingId + " >>>");
		AuthenInfo authInfo = null;
		if(isInitCorba == false)
		{
			isInitCorba = uploadClient.initCorba(uploadConfig.corbaRef);	
		}
		if(isInitCorba)
		{
			if(uploadClient.uploadAppImpl != null)
			{
				try {

					authInfo = uploadClient.uploadAppImpl.getAuthenInfo(mappingId);
				}catch (Exception e) {
					System.out.println(e.toString());
				}
			}else {
				logger.error("Upload client implementation is NULL");
			}
		}else {
			logger.error("Init corba client FALSE");
		}
		return authInfo;
	}
}