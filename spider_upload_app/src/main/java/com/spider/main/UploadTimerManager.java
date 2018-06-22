package com.spider.main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import SpiderCorba.SpiderDefinePackage.VideoInfo;

public class UploadTimerManager {
	private static final Logger logger = Logger.getLogger(UploadTimerManager.class);
	private static UploadTimerManager instance = null;
	public static HashMap<String, Queue<DataDefine.UploadJobData>> queueMap = new HashMap<String, Queue<DataDefine.UploadJobData>>();
	public static HashMap<String, Timer> timerMap = new HashMap<String, Timer>();

	public UploadTimerManager() {
	}

	public static UploadTimerManager getInstance() {
		if (instance == null) {
			instance = new UploadTimerManager();
		}
		return instance;
	}

	public boolean createUploadTimer(String cHomeId) {
		logger.info("Create new upload timer for channel : " + cHomeId );
		boolean isSuccess = false;
		//check timer is existed
		if(timerMap.get(cHomeId) == null)
		{
			TimerTask timerTask = new UploadExecuteTimer(cHomeId);
			Timer timer = new Timer();
			timer.scheduleAtFixedRate(timerTask, 0, 10 * 1000);
			if (timer != null) {
				timerMap.put(cHomeId, timer);
			}
		}else {
			logger.info("Upload timer id" + cHomeId +" had existed in timer map. It will be ignore!");
		}
		
		if(queueMap.get(cHomeId)==null)
		{
			Queue<DataDefine.UploadJobData> qUploadJob = new LinkedList<DataDefine.UploadJobData>();
			queueMap.put(cHomeId, qUploadJob);
		}
		isSuccess = true;
		return isSuccess;
	}
	
	public boolean deleteUploadTimer(String cHomeId) 
	{
		logger.info("Delete upload timer for channel : " + cHomeId);
		boolean isSuccess = false;
		if(timerMap.get(cHomeId) != null)
		{
			Timer timer = timerMap.get(cHomeId);
			timer.cancel();
			timer.purge();
			timerMap.remove(cHomeId);
			logger.info("Upload timer " + cHomeId + " is removed from timer map");
		}
		else {
			logger.error("Not found timer " + cHomeId + " in timer map");
		}
		if(queueMap.get(cHomeId) != null)
		{
			queueMap.remove(cHomeId);
			logger.info("Queue of upload timer : " + cHomeId + " removed from queue map");
		}else {
			logger.error("Not found queue of upload timer :" + cHomeId + " in queue map");
		}
		isSuccess = true;		
		return isSuccess;
	}
	
	public boolean createUploadJob(int jobId, VideoInfo vInfo, String cHomeId)
	{
		logger.info("Create upload jod id = " + jobId);
		boolean isSuccess = false;
		DataDefine.UploadJobData uploadJobData = new DataDefine().new UploadJobData(jobId, vInfo);
		if(queueMap.get(cHomeId) != null)
		{
			queueMap.get(cHomeId).add(uploadJobData);
			logger.info("JobId  " + jobId + " is added to queue : " + cHomeId);
		}else {
			logger.error("Can not found queue for upload id : " + jobId);
		}
		isSuccess = true;		
		return isSuccess;
	}
}
