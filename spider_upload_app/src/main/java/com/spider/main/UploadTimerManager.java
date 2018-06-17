package com.spider.main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import SpiderCorba.SpiderDefinePackage.VideoInfo;

public class UploadTimerManager {

	private static UploadTimerManager instance = null;
	public static Queue<DataDefine.UploadJobData> qUploadJob = new LinkedList<DataDefine.UploadJobData>();
	public static HashMap<String, Timer> timerMap = new HashMap<String, Timer>();

	public UploadTimerManager() {
	}

	public static UploadTimerManager getInstance() {
		if (instance == null) {
			instance = new UploadTimerManager();
		}
		return instance;
	}

	public boolean createUploadTimer(int timerId, int timerType) {
		boolean isSuccess = false;
		//check timer is existed
		String timerIndent = Integer.toString(timerId) + "_" + Integer.toString(timerType);
		if(timerMap.get(timerIndent)== null)
		{
			TimerTask timerTask = new UploadExecuteTimer(timerId, timerType);
			Timer timer = new Timer();
			timer.scheduleAtFixedRate(timerTask, 0, 10 * 1000);
			if (timer != null) {
				timerMap.put(timerIndent, timer);
			}	
		}
		isSuccess = true;
		return isSuccess;
	}
	
	public boolean deleteUploadTimer(int timerId, int timerType) 
	{
		boolean isSuccess = false;
		String timerIndent = Integer.toString(timerId) + "_" + Integer.toString(timerType);
		boolean isIdExisted = timerMap.get(timerIndent) != null;
		if(isIdExisted == true)
		{
			Timer timer = timerMap.get(timerIndent);
			timer.cancel();
			timer.purge();
			timerMap.remove(timerIndent);
		}
		isSuccess = true;		
		return isSuccess;
	}
	
	public boolean createUploadJob(int jobId, VideoInfo vInfo)
	{
		boolean isSuccess = false;
		DataDefine.UploadJobData uploadJobData = new DataDefine().new UploadJobData(jobId, vInfo);
		qUploadJob.add(uploadJobData);
		isSuccess = true;		
		return isSuccess;
	}
}
