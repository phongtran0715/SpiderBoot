package com.spider.main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import SpiderCorba.UploadSidePackage.UploadConfig;
import spiderboot.data.DataController;

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

	public boolean createUploadTimer(String timerId, String uploadClusterId) {
		boolean isSuccess = false;
		//check timer is existed
		//if timer existed -> nothing to do
		if(timerMap.get(timerId)== null)
		{
			TimerTask timerTask = new UploadExecuteTimer((timerId));
			Timer timer = new Timer();
			timer.scheduleAtFixedRate(timerTask, 0, 10 * 1000);
			if (timer != null) {
				timerMap.put(timerId, timer);
			}	
		}
		isSuccess = true;
		return isSuccess;
	}
	
	public boolean modifyUploadTimer(String timerId, String uploadClusterId, int synStatus, UploadConfig uploadCfg)
	{
		boolean isSuccess = false;
		//check timer  id is existed
		boolean isIdExisted = timerMap.get(timerId) != null;
		boolean isAppIdExisted = uploadClusterId.equals(DataController.getInstance().uploadConfig.appId);
		if(isIdExisted == true && isAppIdExisted == true)
		{
			//reset this timer
			timerMap.get(timerId).cancel();
			timerMap.get(timerId).purge();
			timerMap.remove(timerId);
			if(synStatus == 1)
			{
				createUploadTimer(timerId, uploadClusterId);
			}else {

			}
		}else if(isIdExisted == true && isAppIdExisted == false)
		{
			//remove this timer
			timerMap.get(timerId).cancel();
			timerMap.get(timerId).purge();
			timerMap.remove(timerId);
		}else if(isIdExisted == false && isAppIdExisted == true)
		{
			if(synStatus == 1)
			{
				//create new timer
				createUploadTimer(timerId, uploadClusterId);
			}else {
				//nothing to do
			}
		}else {
			//nothing to do
		}
		return isSuccess;
	}
	public boolean deleteUploadTimer(String timerId, String uploadClusterId) 
	{
		boolean isSuccess = false;
		boolean isIdExisted = timerMap.get(timerId) != null;
		boolean isAppIdExisted = uploadClusterId.equals(DataController.getInstance().uploadConfig.appId);
		if(isIdExisted == true && isAppIdExisted == true)
		{
			Timer timer = timerMap.get(timerId);
			timer.cancel();
			timer.purge();
			timerMap.remove(timerId);
		}
		isSuccess = true;		
		return isSuccess;
	}
}
