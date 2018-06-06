package com.spider.main;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.TimerTask;
import org.apache.log4j.Logger;

import com.google.api.services.samples.youtube.cmdline.data.UploadVideo;
import com.spider.corba.UploadCorbaClient;

import SpiderAgentApp.AgentSidePackage.AuthenInfo;
import SpiderAgentApp.AgentSidePackage.ClusterInfo;
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
		completeTask();
	}

	private void completeTask() {
		if(isComplete){
			isComplete = false;
			System.out.println("Timer task started at:" + new Date());
			if(UploadTimerManager.qUploadJob.isEmpty() == false)
			{
				UploadInfo vInfo = UploadTimerManager.qUploadJob.poll();
				//upload video
				File uploadFile = new File(vInfo.vLocation);

				if (!uploadFile.exists()) {
					logger.error("File " + vInfo.vLocation + " not Exist");	
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

				logger.info("Uploading video : " + vInfo.vLocation);

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
				UploadVideo.setStoreFile( "upload_" + userName);
				logger.info("Complete get authen file <<<<");

				logger.info("Beginning standardize meta data >>>>");
				String title = standardizeTitle(vInfo.vTitle);
				String desc = standardizeDesc(vInfo.vDesc);
				String tags = standardizeTags(vInfo.vTags);
				logger.info("Complete standardize meta data <<<<");

				logger.info("Beginning upload video " + vInfo.videoId);
				uploadVideo.execute(title, desc, tags, vInfo.vLocation);
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
			}
			isComplete = true;
			System.out.println("Timer task finished at:" + new Date());
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