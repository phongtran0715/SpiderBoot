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
import java.util.Queue;
import java.util.TimerTask;
import org.apache.log4j.Logger;

import com.google.api.services.samples.youtube.cmdline.data.UploadVideo;
import com.spider.corba.UploadCorbaClient;

import SpiderCorba.SpiderDefinePackage.AuthenInfo;
import SpiderCorba.SpiderDefinePackage.ClusterInfo;
import SpiderCorba.SpiderDefinePackage.VideoInfo;
import spiderboot.configuration.UploadConfig;
import spiderboot.data.DataController;
import spiderboot.util.Utility;

public class UploadExecuteTimer extends TimerTask{

	String cHomeId;
	boolean isComplete = true;
	String videoFolderBase;
	static Utility util = new Utility();
	private static final Logger logger = Logger.getLogger(UploadExecuteTimer.class);
	private static final String CREDENTIALS_DIRECTORY = ".oauth-credentials";
	UploadConfig uploadConfig;
	UploadCorbaClient uploadClient;

	public UploadExecuteTimer(String cHomeId) {
		logger.info("Function UploadExecuteTimer >>>");
		this.cHomeId = cHomeId;
		uploadConfig = DataController.getInstance().uploadConfig;
		uploadClient = new UploadCorbaClient();
		uploadClient.initCorba(uploadConfig.corbaRef);
	}

	@Override
	public void run() {
		completeTask();
	}

	private void completeTask() {
		if(isComplete){
			isComplete = false;
			Queue<DataDefine.UploadJobData> uploadQueue = UploadTimerManager.queueMap.get(cHomeId); 
			if( uploadQueue != null && uploadQueue.isEmpty() == false)
			{
				logger.info("\n\n");
				logger.info("\n==========>> Timer id [" + cHomeId + "] BEGINNIG UPLOAD VIDEO <<==========");
				DataDefine.UploadJobData jobData = uploadQueue.poll();
				VideoInfo vInfo = jobData.vInfo;
				logger.info("Begining upload for job : " + jobData.jobId + " at : " + new Date().toString());

				SpiderCorba.SpiderDefinePackage.UploadConfig uploadVideoCfg = getUploadCfg(vInfo.mappingId);
				if(uploadVideoCfg == null)
				{
					logger.error("Error! Can not get upload video configuration");
					isComplete = true;
					return;
				}
				logger.info(">>>> Upload config:");
				logger.info(" + Video ID :" + vInfo.videoId);
				logger.info(" + Title :" + vInfo.title);
				logger.info(" + Video location :" + vInfo.vRenderPath);
				logger.info(" + Mapping ID :" + vInfo.mappingId);
				logger.info("<<<<");

				File uploadFile = new File(vInfo.vRenderPath);
				if (!uploadFile.exists()) {
					logger.error("FALSE! File to upload : <" + vInfo.vRenderPath + "> not Exist");	
					logger.info("Upload complete video " + vInfo.videoId);
					isComplete = true;
					updateUploadedInfo(jobData.jobId);
					return;
				}
				ClusterInfo clusterInfo = getClusterInfo(vInfo.mappingId, 
						DataController.getInstance().TYPE_CLUSTER_RENDER);
				if(clusterInfo == null)
				{
					logger.error("Error! Can not get upload video configuration");
					isComplete = true;
					return;
				}
				String uploadVideoPath = null;
				if(clusterInfo.clusterIp.equals(uploadConfig.ip)== false)
				{
					uploadVideoPath = copyRenderedVideo(vInfo.vRenderPath, clusterInfo);
				}else {
					uploadVideoPath = vInfo.vRenderPath;
				}

				AuthenInfo authInfo = getAuthenInfo(vInfo.mappingId, vInfo.mappingId);
				logger.info(">>>> Authen info:");
				logger.info("Authen infor : user name = " + authInfo.userName);
				logger.info("Authen infor : api key = " + authInfo.apiKey);
				logger.info("Authen infor : client secret = " + authInfo.clientSecret);
				logger.info("Authen infor : client id = " + authInfo.clientId);
				logger.info("<<<<");
				String clientFile = "/tmp/" + cHomeId + "_client_secrets.json";
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
						logger.error(e);
					}
				}
				jsonCreate.execute(authInfo.clientSecret, authInfo.clientId, clientFile);
				UploadVideo.setclientSecretsFile(clientFile);
				String storeFile = System.getProperty("user.home") + "/" 
						+ CREDENTIALS_DIRECTORY + "/upload_" + authInfo.userName;
				File file = new File(storeFile);
				if(file.exists() == false)
				{
					logger.error("ERROR : Can not get authen store upload");
					isComplete = true;
					updateUploadedInfo(jobData.jobId);
					return;
				}

				UploadVideo.setStoreFile( "upload_" + authInfo.userName);

				String title = standardizeTitle(vInfo.title, uploadVideoCfg.vTitleTemp, uploadVideoCfg.enableTitle);
				String desc = standardizeDesc(vInfo.description, uploadVideoCfg.vDescTemp, uploadVideoCfg.enableDes);
				String tags = standardizeTags(vInfo.tags, uploadVideoCfg.vTagsTemp, uploadVideoCfg.enableTags);

				logger.info("Beginning upload video " + vInfo.videoId);
				logger.info("Create authen file for email : " + authInfo.userName);
				boolean isSuccess = UploadVideo.execute(title, desc, tags, uploadVideoPath, "public");
				if(isSuccess)
				{
					//update process status 
					updateUploadedInfo(jobData.jobId);
					deleteTempFile(uploadVideoPath);
					deleteTempFile(clientFile);
					logger.info("Upload complete video " + vInfo.videoId);
					//Sleep for next upload
					try {
						Thread.sleep(uploadConfig.delayTime *1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else {
					logger.error("FALSE : Can not upload video id = " + vInfo.videoId);
				}
				logger.info("\n==========>> Timer id [" + cHomeId + "] COMPLETE UPLOAD VIDEO <<==========");
			}
			isComplete = true;
		}
		else{
			//do nothing
			logger.info("Process timer task is still running ...");
		}
	}

	private String copyRenderedVideo(String renderedVideoPath, ClusterInfo clusterInfo) {
		String tranferFile = null;
		SCPDownload scpDownload = new SCPDownload();
		logger.info("Begining coopy file : " + renderedVideoPath);
		boolean success = scpDownload.execute(clusterInfo.clusterIp, clusterInfo.userName, 
				clusterInfo.password, renderedVideoPath, "/tmp/");
		if(success == false)
		{
			logger.error("ERROR! Can not download renderd video");
		}else {
			Path p = Paths.get(renderedVideoPath);
			String fileName = p.getFileName().toString();
			tranferFile = "/tmp/" + fileName;	
		}
		return tranferFile;
	}

	private String standardizeTitle(String originTitle, String titleTemp, boolean enableTitle) {
		logger.info("Function standardizeTitle <<<<< ");
		String result = "";
		if(enableTitle)
		{
			String [] data = titleTemp.split("---", 2);
			if(data.length >= 2)
			{
				result += data[0];
				result += originTitle;
				result += data[1];	
			}else {
				logger.error("Description template format incorrect");
			}

		}else {
			logger.info("enable title template = false");
			result = originTitle;
		}
		logger.info("standardizeTitle result = " + result);
		if(result.length() > 90)
		{
			result = result.substring(0, 90);
		}
		return result;
	}

	private String standardizeTags(String originTags, String tagTemp, boolean enableTag) {
		logger.info("Function standardizeTags <<<< ");
		String result = "";
		List<String> listTags = new ArrayList<>();
		List<String> originListTags = Arrays.asList(originTags.split(System.getProperty("line.separator")));
		listTags.addAll(originListTags);
		if(enableTag)
		{
			List<String> customTag = Arrays.asList(tagTemp.split(System.getProperty("line.separator")));
			listTags.addAll(customTag);
		}
		Collections.shuffle(listTags);
		//
		if (listTags != null) {
			Iterator<String> iteratorTags = listTags.iterator();
			while (iteratorTags.hasNext()) {
				String tagItem = iteratorTags.next();
				if((result + tagItem).length() < 400)
				{
					result += tagItem + System.getProperty("line.separator");	
				}else {
					break;	
				}
			}
		}
		logger.info("standardizeTags result = " + result);
		return result;
	}

	private String standardizeDesc(String originDesc, String desctemp, boolean enableDesc) {
		logger.info("Function standardizeDesc : <<<< ");
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
		if(enableDesc)
		{
			String [] data = desctemp.split("---", 2);
			if(data.length >= 2)
			{
				result = data[0] + result;
				result = result + data[1];	
			}else {
				logger.error("Description template format incorrect");
			}
		}
		if(result.length() > 4500)
		{
			result = result.substring(0, 4500);
		}
		logger.info("standardizeDesc result = " + result);
		return result;
	}

	private boolean checkIgnoreKeyword(String inputLine)
	{
		boolean result = true;
		for (String keyword : new DataDefine().IGNORE_KEYWORD) {
			if(inputLine.contains(keyword))
			{
				result = false;
				break;
			}
		}
		return result;
	}

	private void updateUploadedInfo(int jobId) 
	{
		logger.info(">>> Function [updateUploadedInfo] : job Id = " + jobId);
		boolean isInitCorba = uploadClient.initCorba(uploadConfig.corbaRef);
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

	private ClusterInfo getClusterInfo (int mappingId, int clusterType)
	{
		logger.info("Function getClusterInfo : mapping ID = " + mappingId );
		ClusterInfo clusterInfor = null;
		boolean isInitCorba = uploadClient.initCorba(uploadConfig.corbaRef);
		if(isInitCorba)
		{
			if(uploadClient.uploadAppImpl != null)
			{
				try {

					clusterInfor = uploadClient.uploadAppImpl.getClusterInfo(mappingId, clusterType);
				}catch (Exception e) {
					System.out.println(e.toString());
				}
			}else {
				logger.error("Upload client implementation is NULL");
			}
		}else {
			logger.error("Init corba client FALSE");
		}
		return clusterInfor;
	}

	private SpiderCorba.SpiderDefinePackage.UploadConfig getUploadCfg (int mappingId)
	{
		SpiderCorba.SpiderDefinePackage.UploadConfig uploadCfg = new SpiderCorba.SpiderDefinePackage.UploadConfig();
		boolean isInitCorba = uploadClient.initCorba(uploadConfig.corbaRef);
		if(isInitCorba)
		{
			if(uploadClient.uploadAppImpl != null)
			{
				try {

					uploadCfg = uploadClient.uploadAppImpl.getUploadConfig(mappingId);
				}catch (Exception e) {
					System.out.println(e.toString());
				}
			}else {
				logger.error("Upload client implementation is NULL");
			}
		}else {
			logger.error("Init corba client FALSE");
		}
		return uploadCfg;
	}

	private AuthenInfo getAuthenInfo(int mappingId, int mappingType)
	{
		logger.info("Function getAuthenInfo : mapping ID = " + mappingId 
				+ " mapping type = " + mappingType);
		AuthenInfo authInfo = null;
		boolean isInitCorba = uploadClient.initCorba(uploadConfig.corbaRef);
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