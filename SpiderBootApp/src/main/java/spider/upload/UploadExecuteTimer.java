package spider.upload;

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

import corba.variableDefinePackage.AuthenInfo;
import corba.variableDefinePackage.ClusterInfo;
import corba.variableDefinePackage.UploadConfig;
import corba.variableDefinePackage.VideoInfo;
import spider.corba.SpiderCorbaClient;
import spider.config.SpiderBootProperty;
import spider.main.DataController;
import spider.helper.Utility;;

public class UploadExecuteTimer extends TimerTask{

	String cHomeId;
	boolean isComplete = true;
	String videoFolderBase;
	static Utility util = new Utility();
	static final Logger logger = Logger.getLogger(UploadExecuteTimer.class);
	static final String CREDENTIALS_DIRECTORY = ".oauth-credentials";
	final int NUM_RETRY = 3;
	SpiderBootProperty spiderConfig;
	SpiderCorbaClient uploadClient;

	public UploadExecuteTimer(String cHomeId) {
		logger.info("Function UploadExecuteTimer >>>");
		this.cHomeId = cHomeId;
		spiderConfig = DataController.getInstance().spiderConfig;
		uploadClient = SpiderCorbaClient.getInstance();
	}

	@Override
	public void run() {
		completeTask();
	}

	private void completeTask() {
		if(isComplete){
			isComplete = false; 
			logger.info("\n\n");
			logger.info("\n[UPLOAD] : ==========>> Timer id [" + cHomeId + "] BEGINNIG UPLOAD VIDEO <<==========");
			DataDefine.UploadJobData jobData = UploadTimerManager.getInstance().getJob(cHomeId);
			if(jobData == null)
			{
				logger.info("[UPLOAD] : Queue for channel [" + cHomeId + "] is empty.");
				return;
			}
			VideoInfo vInfo = jobData.vInfo;
			logger.info("[UPLOAD] : Begining upload for job : " + jobData.jobId + " at : " + new Date().toString());

			UploadConfig uploadVideoCfg = getUploadCfg(vInfo.mappingId);
			if(uploadVideoCfg == null)
			{
				logger.error("[UPLOAD] : Error! Can not get upload video configuration");
				isComplete = true;
				return;
			}
			logger.info("[UPLOAD] : >>>> Upload config:");
			logger.info(" + Video ID :" + vInfo.videoId);
			logger.info(" + Title :" + vInfo.title);
			logger.info(" + Video location :" + vInfo.vRenderPath);
			logger.info(" + Mapping ID :" + vInfo.mappingId);
			logger.info("<<<<");

			File uploadFile = new File(vInfo.vRenderPath);
			if (!uploadFile.exists()) {
				logger.error("[UPLOAD] : FALSE! File to upload : <" + vInfo.vRenderPath + "> not Exist");	
				logger.info("[UPLOAD] : Upload complete video " + vInfo.videoId);
				isComplete = true;
				updateUploadedInfo(jobData.jobId);
				return;
			}
			ClusterInfo clusterInfo = getClusterInfo(vInfo.mappingId, 
					DataController.getInstance().TYPE_CLUSTER_RENDER);
			if(clusterInfo == null)
			{
				logger.error("[UPLOAD] : Error! Can not get upload video configuration");
				isComplete = true;
				return;
			}
			String uploadVideoPath = null;
			if(clusterInfo.clusterIp.equals(spiderConfig.uIp)== false)
			{
				uploadVideoPath = copyRenderedVideo(vInfo.vRenderPath, clusterInfo);
			}else {
				uploadVideoPath = vInfo.vRenderPath;
			}

			AuthenInfo authInfo = getAuthenInfo(vInfo.mappingId);
			logger.info("[UPLOAD] : >>>> Authen info:");
			logger.info("[UPLOAD] : Authen infor : user name = " + authInfo.userName);
			logger.info("[UPLOAD] : <<<<");
			String clientFile = "/tmp/" + cHomeId + "_client_secrets.json";
			CrunchifyJSONFileWrite jsonCreate = new CrunchifyJSONFileWrite();
			File jsonFile = new File(clientFile);
			if(jsonFile.exists() == false)
			{
				//Create the file
				try {
					if (jsonFile.createNewFile()){
						logger.error("[UPLOAD] : File created : " + clientFile);
					}else{
						logger.error("[UPLOAD] : Can not create file : " + clientFile);
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
				logger.error("[UPLOAD] : ERROR : Can not get authen store upload");
				isComplete = true;
				updateUploadedInfo(jobData.jobId);
				return;
			}

			UploadVideo.setStoreFile( "upload_" + authInfo.userName);

			String title = standardizeTitle(vInfo.title, uploadVideoCfg.vTitleTemp, uploadVideoCfg.enableTitle);
			String desc = standardizeDesc(vInfo.description, uploadVideoCfg.vDescTemp, uploadVideoCfg.enableDes);
			String tags = standardizeTags(vInfo.tags, uploadVideoCfg.vTagsTemp, uploadVideoCfg.enableTags);

			logger.info("[UPLOAD] : Beginning upload video " + vInfo.videoId);
			logger.info("[UPLOAD] : Create authen file for email : " + authInfo.userName);
			boolean isSuccess = UploadVideo.execute(title, desc, tags, uploadVideoPath, "public");
			if(isSuccess)
			{
				//update process status 
				updateUploadedInfo(jobData.jobId);
				deleteTempFile(uploadVideoPath);
				deleteTempFile(clientFile);
				logger.info("[UPLOAD] : Upload complete video " + vInfo.videoId);
				//Sleep for next upload
				try {
					Thread.sleep(spiderConfig.delayTime *1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else {
				logger.error("[UPLOAD] : FALSE : Can not upload video id = " + vInfo.videoId);
			}
			logger.info("\n[UPLOAD] : ==========>> Timer id [" + cHomeId + "] COMPLETE UPLOAD VIDEO <<==========");
		}
		isComplete = true;
	}

	private String copyRenderedVideo(String renderedVideoPath, ClusterInfo clusterInfo) {
		String tranferFile = null;
		SCPDownload scpDownload = new SCPDownload();
		logger.info("[UPLOAD] : Begining coopy file : " + renderedVideoPath);
		boolean success = scpDownload.execute(clusterInfo.clusterIp, clusterInfo.userName, 
				clusterInfo.password, renderedVideoPath, "/tmp/");
		if(success == false)
		{
			logger.error("[UPLOAD] : ERROR! Can not download renderd video");
		}else {
			Path p = Paths.get(renderedVideoPath);
			String fileName = p.getFileName().toString();
			tranferFile = "/tmp/" + fileName;	
		}
		return tranferFile;
	}

	private String standardizeTitle(String originTitle, String titleTemp, boolean enableTitle) {
		logger.info("[UPLOAD] : Function standardizeTitle <<<<< ");
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
				logger.error("[UPLOAD] : Description template format incorrect");
			}

		}else {
			logger.info("[UPLOAD] : enable title template = false");
			result = originTitle;
		}
		logger.info("[UPLOAD] : standardizeTitle result = " + result);
		if(result.length() > 90)
		{
			result = result.substring(0, 90);
		}
		return result;
	}

	private String standardizeTags(String originTags, String tagTemp, boolean enableTag) {
		logger.info("[UPLOAD] : Function standardizeTags <<<< ");
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
		logger.info("[UPLOAD] : standardizeTags result = " + result);
		return result;
	}

	private String standardizeDesc(String originDesc, String desctemp, boolean enableDesc) {
		logger.info("[UPLOAD] : Function standardizeDesc : <<<< ");
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
				logger.error("[UPLOAD] : Description template format incorrect");
			}
		}
		if(result.length() > 4500)
		{
			result = result.substring(0, 4500);
		}
		logger.info("[UPLOAD] : standardizeDesc result = " + result);
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
		logger.info("[UPLOAD] : >>> Function [updateUploadedInfo] : job Id = " + jobId);
		if(uploadClient.agentSide != null)
		{
			try {
				uploadClient.agentSide.updateUploadedVideo(jobId);
			}catch (Exception e) {
				System.out.println(e.toString());
			}
		}else {
			logger.error("[UPLOAD] : Upload client implementation is NULL");
		}
	}

	private ClusterInfo getClusterInfo (int mappingId, int clusterType)
	{
		logger.info("[UPLOAD] : Function getClusterInfo : mapping ID = " + mappingId );
		ClusterInfo clusterInfor = null;
		int count = 0;
		do {
			if(uploadClient.agentSide != null)
			{
				try {

					clusterInfor = uploadClient.agentSide.getClusterInfo(mappingId, clusterType);
					return clusterInfor;
				}catch (Exception e) {
					System.out.println(e.toString());
				}
			}else {
				logger.error("[UPLOAD] : Upload client implementation is NULL");
			}	
			count++;
		}while(count < NUM_RETRY);

		return clusterInfor;
	}

	private UploadConfig getUploadCfg (int mappingId)
	{
		UploadConfig uploadCfg = new UploadConfig();
		int count = 1;
		do {
			if(uploadClient.agentSide != null)
			{
				try {

					uploadCfg = uploadClient.agentSide.getUploadConfig(mappingId);
					return uploadCfg;
				}catch (Exception e) {
					System.out.println(e.toString());
				}
			}else {
				logger.error("[UPLOAD] : Upload client implementation is NULL");
			}
			count++;
		}while(count < NUM_RETRY);

		return uploadCfg;
	}

	private AuthenInfo getAuthenInfo(int mappingId)
	{
		logger.info("[UPLOAD] : Function getAuthenInfo : mapping ID = " + mappingId );
		AuthenInfo authInfo = null;

		if(uploadClient.agentSide != null)
		{
			try {

				authInfo = uploadClient.agentSide.getAuthenInfo(mappingId);
			}catch (Exception e) {
				System.out.println(e.toString());
			}
		}else {
			logger.error("[UPLOAD] : Upload client implementation is NULL");
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
				logger.error("[UPLOAD] : Failed to delete the file : " + filePath);
			}	
		}
	}
}