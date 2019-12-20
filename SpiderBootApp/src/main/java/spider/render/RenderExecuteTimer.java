package spider.render;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.TimerTask;
import org.apache.log4j.Logger;

import SpiderCorba.SpiderDefinePackage.RenderConfig;
import spider.corba.SpiderCorbaClient;
import spider.config.SpiderBootProperty;
import spider.main.DataController;
import spider.helper.Utility;

class StreamGobbler extends Thread {
	final Logger logger = Logger.getLogger(RenderExecuteTimer.class);
	InputStream is;
	String type;

	StreamGobbler(InputStream is, String type) {
		this.is = is;
		this.type = type;
	}

	@Override
	public void run() {
		try {
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				logger.info(type + "> " + line);
			}
		} catch (IOException ioe) {
			logger.error(ioe.toString());
			ioe.printStackTrace();
		}
	}
}

public class RenderExecuteTimer extends TimerTask{

	boolean isComplete = true;
	String outputFolder;
	Utility util = new Utility();
	SpiderBootProperty spiderConfig;
	SpiderCorbaClient renderClient;
	final Logger logger = Logger.getLogger(RenderExecuteTimer.class);
	final int NUM_RETRY	= 3;

	public RenderExecuteTimer(String appId) {
		logger.info("[RENDER] : Function RenderExecuteTimer >>>");
		spiderConfig = DataController.getInstance().spiderConfig;
		outputFolder = spiderConfig.rOutPath;
		renderClient = SpiderCorbaClient.getInstance();
	}

	@Override
	public void run() {
		try {
			completeTask();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("[RENDER] :" + e.toString());
		}
	}

	private void completeTask() throws IOException {
		if(isComplete){
			isComplete = false;

			if(RenderTimerManager.getInstance().checkEmptyQueue() == false)
			{
				DataDefine.RenderJobData jobData = RenderTimerManager.getInstance().getJob();
				SpiderCorba.SpiderDefinePackage.VideoInfo vInfo = jobData.vInfo;

				logger.info("[RENDER] : Render job ( id = )"  + jobData.jobId + " started at:" + new Date());
				logger.info("\n\n");
				logger.info("\n[RENDER] : =================== BEGINNING RENDER VIDEO ===================");
				logger.info(" + Video ID :" + vInfo.videoId);
				logger.info(" + Title :" + vInfo.title);
				logger.info(" + Video location :" + vInfo.vDownloadPath);
				logger.info(" + Mapping ID :" + vInfo.mappingId);
				RenderConfig renderCfg = null;
				try {
					renderCfg = getRenderConfig(vInfo.mappingId);
					if(renderCfg == null)
					{
						logger.error("[RENDER] : Error! Can not get render config");
						isComplete = true;
						return;
					}
				}catch (Exception e) {
					logger.error("[RENDER] : " + e.toString());
				}

				File file = new File(vInfo.vDownloadPath);
				if (!file.exists()) {
					logger.error("[RENDER] : Error! File " + vInfo.vDownloadPath + " not Exist");
					isComplete = true;
					return;
				}
				
				util.createFolderIfNotExist(outputFolder);
				String vOutput = outputFolder + util.prefixOS() + "video_" +vInfo.videoId + "_" + new Date().getTime() +   ".mp4";
				//process video
				boolean isSuccess = false;
				if(renderCfg.renderCommand == null || renderCfg.renderCommand.isEmpty())
				{
					vOutput = vInfo.vDownloadPath;
					isSuccess = true;
				}else {
					isSuccess = processVideo(renderCfg.renderCommand, vInfo.vDownloadPath,vOutput);
				}
				if(isSuccess)
				{
					vInfo.vRenderPath = vOutput;
					vInfo.processStatus = 2;
					updateRenderedInfo(jobData.jobId, vInfo);
				}else {
					logger.error("[RENDER] : Render video [ " + vInfo.videoId + "] FALSE");
				}
				logger.info("\n[RENDER] : =================== COMPLETE RENDER VIDEO ===================\n\n");
			}
			isComplete = true;
		}
		else{
		}
	}

	private RenderConfig getRenderConfig(int mappingId)
	{
		RenderConfig renderCfg = null;
		int count = 1;
		do {
			if(renderClient.agentSide != null)
			{
				try {
					renderCfg = renderClient.agentSide.getRenderConfig(mappingId);
					return renderCfg;
				}catch (Exception e) {
					logger.error("[RENDER] : Can not call corba server");
					logger.error("[RENDER] :" + e.toString());
					//retry
					logger.info("[RENDER] : [Count = " + count +"] Begin retry to connetion corba server...");
					renderClient.resolveAgain();
				}
			}else {
				logger.error("[RENDER] : Render client implementation is NULL");
			}
			count++;
		}while (count < NUM_RETRY);

		return renderCfg;
	}
	
	private boolean processVideo(String command, String vInput, String vOutput)
	{
		boolean isSuccess = false;
		if(command == null || command.isEmpty())
		{
			logger.info("[RENDER] : FFMPEG command is empty!!!");
			return isSuccess;
		}
		//create script file
		String scriptFile = "/tmp/" + new Date().getTime() + ".sh";
		boolean isCreated = util.createFile(scriptFile, command);
		if(isCreated == true)
		{
			 File file = new File(scriptFile);
			 file.setExecutable(true);
		}else {
			logger.error("[RENDER] : Can not create ffmpeg script file");
			return isSuccess;
		}
		
		//create new thread to execute ffmpeg command
		String[] processCmd = {"sh", scriptFile, vInput, vOutput };
		ProcessBuilder builder = new ProcessBuilder();
		builder.command(processCmd);
		builder.directory(new File(System.getProperty("user.home")));
		try {
			Process process = builder.start();
			StreamGobbler errorGobbler = new StreamGobbler(process.getErrorStream(), "ERROR");
			StreamGobbler outputGobbler = new StreamGobbler(process.getInputStream(), "OUTPUT");
			outputGobbler.start();
			errorGobbler.start();
			int exitCode = process.waitFor();
			assert exitCode == 0;
			isSuccess = process.exitValue() == 0 ? true : false;;
		} catch (IOException | InterruptedException e1) {
			logger.error(e1.toString());
			e1.printStackTrace();
		}
		
		//delete tmp file
		util.deleteFile(scriptFile);
		return isSuccess;
	}

	private void updateRenderedInfo(int jobId, SpiderCorba.SpiderDefinePackage.VideoInfo vInfo)
	{
		logger.info("[RENDER] : >>> Function [updateRenderedInfo] : job Id = " + jobId);
		int count = 1;
		do {
			if(renderClient.agentSide != null)
			{
				try {
					renderClient.agentSide.updateRenderedVideo(jobId, vInfo);
					return;
				}catch (Exception e) {
					logger.error("[RENDER] : Can not call corba server");
					logger.error(e.toString());
					//retry
					logger.info("[RENDER] : [Count = " + count +"] Begin retry to connetion corba server...");
					renderClient.resolveAgain();
				}
			}else {
				logger.error("[RENDER] : Render client implementation is NULL");
			}
			count++;
		}while(count < NUM_RETRY);
	}
}