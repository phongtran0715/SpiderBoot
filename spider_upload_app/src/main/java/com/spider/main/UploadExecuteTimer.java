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
	boolean isInitCorba = false;
	
	public UploadExecuteTimer(String cHomeId) {
		logger.info("Function UploadExecuteTimer >>>");
		this.cHomeId = cHomeId;
		uploadConfig = DataController.getInstance().uploadConfig;
		uploadClient = new UploadCorbaClient();
		isInitCorba = uploadClient.initCorba(uploadConfig.corbaRef);
	}

	@Override
	public void run() {
		completeTask();
	}

	private void completeTask() {
		logger.info("Upload timer channel : " + cHomeId + " at : " + new Date());
		if(isComplete){
			isComplete = false;
			Queue<DataDefine.UploadJobData> uploadQueue = UploadTimerManager.queueMap.get(cHomeId); 
			if( uploadQueue != null && uploadQueue.isEmpty() == false)
			{
				DataDefine.UploadJobData jobData = uploadQueue.poll();
				VideoInfo vInfo = jobData.vInfo;
				logger.info("Begining upload for job : " + jobData.jobId);
				logger.info("");
				if(vInfo.license == 1)
				{
					logger.error("Video (id =  " + vInfo.videoId + ") license = true. This video will be ignore");
					updateUploadedInfo(jobData.jobId);
					isComplete = true;
					return;
				}
				
				SpiderCorba.SpiderDefinePackage.UploadConfig uploadVideoCfg = getUploadCfg(vInfo.mappingId, vInfo.mappingType);
				
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
				ClusterInfo clusterInfo = getClusterInfo(vInfo.mappingId, vInfo.mappingType, 
						DataController.getInstance().TYPE_CLUSTER_RENDER);
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

				AuthenInfo authInfo = getAuthenInfo(vInfo.mappingId, vInfo.mappingId);
				logger.info("Authen infor : user name = " + authInfo.userName);
				logger.info("Authen infor : api key = " + authInfo.apiKey);
				logger.info("Authen infor : client secret = " + authInfo.clientSecret);
				logger.info("Authen infor : client id = " + authInfo.clientId);

				logger.info("Beginning get authen file >>>>");
				String clientFile = "/tmp/ " + cHomeId + "_client_secrets.json";
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
				System.out.println("Store file = " + storeFile);
				File file = new File(storeFile);
				
				if(file.exists() == false)
				{
					logger.error("ERROR : Can not get authen store upload");
					isComplete = true;
					updateUploadedInfo(jobData.jobId);
					return;
				}
				
				UploadVideo.setStoreFile( "upload_" + authInfo.userName);
				logger.info("Complete get authen file <<<<");

				logger.info("Beginning standardize meta data >>>>");
				String title = standardizeTitle(vInfo.title, uploadVideoCfg.vTitleTemp, uploadVideoCfg.enableTitle);
				String desc = standardizeDesc(vInfo.description, uploadVideoCfg.vDescTemp, uploadVideoCfg.enableDes);
				String tags = standardizeTags(vInfo.tags, uploadVideoCfg.vTagsTemp, uploadVideoCfg.enableTags);
				logger.info("Complete standardize meta data <<<<");

				logger.info("Beginning upload video " + vInfo.videoId);
				logger.info("create authen file for email : " + authInfo.userName);
				
				boolean isSuccess = UploadVideo.execute(title, desc, tags, tranferFile, "public");
				if(isSuccess)
				{
					//update process status 
					updateUploadedInfo(jobData.jobId);
					deleteTempFile(tranferFile);
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
			}
			isComplete = true;
		}
		else{
			//do nothing
			logger.info("Process timer task is still running ...");
		}
	}
	private String standardizeTitle(String originTitle, String titleTemp, boolean enableTitle) {
		logger.info("Function standardizeTitle <<<<< ");
		logger.info("origin title : " + originTitle);
		logger.info("title template : " + titleTemp);
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
				result += iteratorTags.next() + System.getProperty("line.separator");
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
				//logger.info("invalid line : " + inputLine);
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

	private ClusterInfo getClusterInfo (int mappingId, int mappingType, int clusterType)
	{
		logger.info("Function getClusterInfo : mapping ID = " + mappingId 
				+ " mapping type = " + mappingType);
		ClusterInfo clusterInfor = null;
		if(isInitCorba == false)
		{
			isInitCorba = uploadClient.initCorba(uploadConfig.corbaRef);	
		}
		if(isInitCorba)
		{
			if(uploadClient.uploadAppImpl != null)
			{
				try {

					clusterInfor = uploadClient.uploadAppImpl.getClusterInfo(mappingId, mappingType, clusterType);
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
	
	private SpiderCorba.SpiderDefinePackage.UploadConfig getUploadCfg (int mappingId, int mappingType)
	{
		SpiderCorba.SpiderDefinePackage.UploadConfig uploadCfg = new SpiderCorba.SpiderDefinePackage.UploadConfig();
		if(isInitCorba == false)
		{
			isInitCorba = uploadClient.initCorba(uploadConfig.corbaRef);	
		}
		if(isInitCorba)
		{
			if(uploadClient.uploadAppImpl != null)
			{
				try {

					uploadCfg = uploadClient.uploadAppImpl.getUploadConfig(mappingId, mappingType);
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
		if(isInitCorba == false)
		{
			isInitCorba = uploadClient.initCorba(uploadConfig.corbaRef);	
		}
		if(isInitCorba)
		{
			if(uploadClient.uploadAppImpl != null)
			{
				try {

					authInfo = uploadClient.uploadAppImpl.getAuthenInfo(mappingId, mappingType);
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