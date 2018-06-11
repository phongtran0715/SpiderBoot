package com.spider.main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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
				DataDefine.UploadJobData jobData = UploadTimerManager.qUploadJob.poll();
				VideoInfo vInfo = jobData.vInfo;
				SpiderCorba.UploadSidePackage.UploadConfig uploadVideoCfg = jobData.uploadCfg;
				logger.info("Starting new job (job id = " + jobData.jobId + ") at : " + new Date());
				//upload video
				logger.info("=================== Upload video infor ===================");
				logger.info(" + Video ID :" + vInfo.videoId);
				logger.info(" + Title :" + vInfo.title);
				logger.info(" + Video location :" + vInfo.vRenderPath);
				logger.info(" + Mapping ID :" + vInfo.mappingId);
				logger.info("==========================================================");
				File uploadFile = new File(vInfo.vRenderPath);

				if (!uploadFile.exists()) {
					logger.error("FALSE! File to upload : <" + vInfo.vRenderPath + "> not Exist");	
					logger.info("Upload complete video " + vInfo.videoId);
					isComplete = true;
					updateUploadedInfo(jobData.jobId);
					return;
				}
				ClusterInfo clusterInfo = getClusterInfo(vInfo.mappingId);
				logger.info("IP = " + clusterInfo.clusterIp);
				logger.info("User Name = " + clusterInfo.userName);
				logger.info("Password = " + clusterInfo.password);
				logger.info("appid config = "+ uploadConfig.appId);
				logger.info("ip config = "+ uploadConfig.ip);

				String tranferFile = "";
				if(clusterInfo.clusterIp.equals(uploadConfig.ip)== false)
				{
					//TODO: download video from render cluster
					SCPDownload scpDownload = new SCPDownload();
					logger.info("====================================================");
					logger.info("Begining coopy file : " + vInfo.vRenderPath);
					boolean success = scpDownload.execute(clusterInfo.clusterIp, clusterInfo.userName, clusterInfo.password,
							vInfo.vRenderPath, "/tmp/");
					if(success)
					{
						logger.info("Download from render cluster OK");
					}else {
						logger.error("ERROR! Download file from render cluster FALSE");
						isComplete = true;
						return;
					}
					logger.info("====================================================");
					Path p = Paths.get(vInfo.vRenderPath);
					String fileName = p.getFileName().toString();
					tranferFile = "/tmp/" + fileName;
				}else {
					tranferFile =  vInfo.vRenderPath;
				}


				logger.info("Uploading video : " + tranferFile);

				AuthenInfo authInfo = getAuthenInfo(vInfo.mappingId);
				logger.info("Authen infor : user name = " + authInfo.userName);
				logger.info("Authen infor : api key = " + authInfo.apiKey);
				logger.info("Authen infor : client secret = " + authInfo.clientSecret);
				logger.info("Authen infor : client id = " + authInfo.clientId);

				logger.info("Beginning get authen file >>>>");
				//TODO: create client secret file
				String clientFile = "/tmp/client_secrets.json";
				//TODO: set authen file name
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
				uploadVideo.setclientSecretsFile(clientFile);
				String storeFile = System.getProperty("user.home") + "/" 
						+ CREDENTIALS_DIRECTORY + "/upload_" + authInfo.userName;
				System.out.println("Store file = " + storeFile);
				File file = new File(storeFile);
				/*
				if(file.exists() == false)
				{
					logger.error("ERROR : Can not get authen store upload file. File does not exist");
					isComplete = true;
					updateUploadedInfo(jobData.jobId);
					return;
				}
				 */

				uploadVideo.setStoreFile( "upload_" + authInfo.userName);
				logger.info("Complete get authen file <<<<");

				logger.info("Beginning standardize meta data >>>>");
				String title = standardizeTitle(vInfo.title, uploadVideoCfg.vTitleTemp, uploadVideoCfg.enableTitle);
				String desc = standardizeDesc(vInfo.description, uploadVideoCfg.vDescTemp, uploadVideoCfg.enableDes);
				String tags = standardizeTags(vInfo.tags, uploadVideoCfg.vTagsTemp, uploadVideoCfg.enableTags);
				logger.info("Complete standardize meta data <<<<");

				logger.info("Beginning upload video " + vInfo.videoId);
				logger.info("create authen file for email : " + authInfo.userName);
				//uploadVideo.execute(title, desc, tags, tranferFile, "public");

				//update process status 
				//updateUploadedInfo(jobData.jobId);
				//deleteTempFile(tranferFile);
				logger.info("Upload complete video " + vInfo.videoId);	

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
		String result = "";
		List<String> listTags = new ArrayList<>();
		List<String> originListTags = Arrays.asList(originTags.split(System.getProperty("line.separator")));
		listTags.addAll(originListTags);
		if(enableTag)
		{
			List<String> customTag = Arrays.asList(tagTemp.split(";"));
			listTags.addAll(customTag);
		}
		Collections.shuffle(listTags);
		//
		if (listTags != null) {
			Iterator<String> iteratorTags = listTags.iterator();
			while (iteratorTags.hasNext()) {
				result += iteratorTags.next() + System.getProperty("line.separator");
			}
		}
		logger.info("standardizeTags : " + result);
		return result;
	}

	private String standardizeDesc(String originDesc, String desctemp, boolean enableDesc) {
		logger.info("originDesc : " + originDesc);
		String result = "";
		List<String> listOriginDesc = Arrays.asList(originDesc.split(System.getProperty("line.separator")));
		List<String> listFilter = new ArrayList<String>();
		for (String item : listOriginDesc) {
			if(checkIgnoreKeyword(item))
			{
				listFilter.add(item);
			}
		}
		if (listFilter != null) {
			Iterator<String> iteratorTags = listFilter.iterator();
			while (iteratorTags.hasNext()) {
				result += iteratorTags.next() + System.getProperty("line.separator");
			}
		}
		logger.info("standardizeTags : " + result);
		return result;
	}
	
	private boolean checkIgnoreKeyword(String inputLine)
	{
		boolean result = true;
		for (String keyword : new DataDefine().IGNORE_KEYWORD) {
			if(inputLine.contains(keyword))
			{
				result = false;
				logger.info("invalid line : " + inputLine);
				break;
			}
		}
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


	private void deleteTempFile(String filePath)
	{
		File file = new File(filePath);
		if(file.exists())
		{
			if(file.delete() == false)
			{
				logger.error("Failed to delete the file : " + filePath);
			}	
		}
	}
}