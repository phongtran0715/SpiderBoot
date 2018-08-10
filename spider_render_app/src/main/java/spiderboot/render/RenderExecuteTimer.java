package spiderboot.render;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import com.spider.corba.RenderCorbaClient;

import SpiderCorba.SpiderDefinePackage.VideoInfo;
import spiderboot.configuration.RenderProperty;
import spiderboot.data.DataController;
import spiderboot.util.Utility;

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
	RenderProperty renderProperty;
	RenderCorbaClient renderClient;
	final Logger logger = Logger.getLogger(RenderExecuteTimer.class);
	final int NUM_RETRY	= 3;

	public RenderExecuteTimer(String appId) {
		logger.info("Function RenderExecuteTimer >>>");
		renderProperty = DataController.getInstance().renderConfig;
		outputFolder = renderProperty.outputVideo;
		renderClient = RenderCorbaClient.getInstance();
	}

	@Override
	public void run() {
		try {
			completeTask();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
	}

	private void completeTask() throws IOException {
		if(isComplete){
			isComplete = false;

			if(RenderTimerManager.getInstance().checkEmptyQueue() == false)
			{
				DataDefine.RenderJobData jobData = RenderTimerManager.getInstance().getJob();
				VideoInfo vInfo = jobData.vInfo;

				logger.info("Render job ( id = )"  + jobData.jobId + " started at:" + new Date());
				logger.info("\n\n");
				logger.info("\n=================== BEGINNING RENDER VIDEO ===================");
				logger.info(" + Video ID :" + vInfo.videoId);
				logger.info(" + Title :" + vInfo.title);
				logger.info(" + Video location :" + vInfo.vDownloadPath);
				logger.info(" + Mapping ID :" + vInfo.mappingId);
				SpiderCorba.SpiderDefinePackage.RenderConfig renderCfg = null;
				try {
					renderCfg = getRenderConfig(vInfo.mappingId);
					if(renderCfg == null)
					{
						logger.error("Error! Can not get render config");
						isComplete = true;
						return;
					}
				}catch (Exception e) {
					logger.error(e.toString());
				}

				File file = new File(vInfo.vDownloadPath);
				if (!file.exists()) {
					logger.error("Error! File " + vInfo.vDownloadPath + " not Exist");
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
					logger.error("Render video [ " + vInfo.videoId + "] FALSE");
				}
				logger.info("\n=================== COMPLETE RENDER VIDEO ===================\n\n");
			}
			isComplete = true;
		}
		else{
		}
	}

	private SpiderCorba.SpiderDefinePackage.RenderConfig getRenderConfig(int mappingId)
	{
		SpiderCorba.SpiderDefinePackage.RenderConfig renderCfg = null;
		int count = 1;
		do {
			if(renderClient.renderAppImpl != null)
			{
				try {
					renderCfg = renderClient.renderAppImpl.getRenderConfig(mappingId);
					return renderCfg;
				}catch (Exception e) {
					logger.error("Can not call corba server");
					logger.error(e.toString());
					//retry
					logger.info(" [Count = " + count +"] Begin retry to connetion corba server...");
					renderClient.resolveAgain();
				}
			}else {
				logger.error("Render client implementation is NULL");
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
			logger.info("FFMPEG command is empty!!!");
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
			logger.error("Can not create ffmpeg script file");
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

	private void updateRenderedInfo(int jobId, VideoInfo vInfo)
	{
		logger.info(">>> Function [updateRenderedInfo] : job Id = " + jobId);
		int count = 1;
		do {
			if(renderClient.renderAppImpl != null)
			{
				try {
					renderClient.renderAppImpl.updateRenderedVideo(jobId, vInfo);
					return;
				}catch (Exception e) {
					logger.error("Can not call corba server");
					logger.error(e.toString());
					//retry
					logger.info(" [Count = " + count +"] Begin retry to connetion corba server...");
					renderClient.resolveAgain();
				}
			}else {
				logger.error("Render client implementation is NULL");
			}
			count++;
		}while(count < NUM_RETRY);
	}
}