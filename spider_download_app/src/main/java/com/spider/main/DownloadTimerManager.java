package com.spider.main;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import spiderboot.data.DataController;

public class DownloadTimerManager {

	private static DownloadTimerManager instance = null;
	public static HashMap<Integer, Timer> timerMap = new HashMap<Integer, Timer>();
	public DownloadTimerManager() {
	}

	public static DownloadTimerManager getInstance() {
		if (instance == null) {
			instance = new DownloadTimerManager();
		}
		return instance;
	}

	public boolean createDownloadTimer(int timerId, String cHomeId, String cMonitorId, int timerInterval) {
		boolean isSuccess = false;
		//check timer is existed
		if(timerMap.get(timerId)!= null)
		{
			timerMap.get(timerId).cancel();
			timerMap.get(timerId).purge();
			timerMap.remove(timerId);
		}
		TimerTask timerTask = new DownloadExecuteTimer(timerId);
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, timerInterval * 1000);
		if (timer != null) {
			timerMap.put(timerId, timer);
		}
		isSuccess = true;
		return isSuccess;
	}

	public boolean modifyMappingChannel(int timerId, String cHomeId, String cMonitorId, 
			String downloadClusterId, int timerInterval, int synStatus) 
	{
		boolean isSuccess = false;
		//check timer  id is existed
		boolean isIdExisted = timerMap.get(timerId) != null;
		boolean isAppIdExisted = downloadClusterId.equals(DataController.getInstance().downloadConfig.appId);

		if(isIdExisted == true && isAppIdExisted == true)
		{
			//reset this timer
			timerMap.get(timerId).cancel();
			timerMap.get(timerId).purge();
			timerMap.remove(timerId);
			if(synStatus == 1)
			{
				createDownloadTimer(timerId, cHomeId, cMonitorId, timerInterval);	
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
				createDownloadTimer(timerId, cHomeId, cMonitorId, timerInterval);
			}else {
				//nothing to do
			}
		}else {
			//nothing to do
		}
		return isSuccess;
	}

	public boolean deleteDownloadTimer(int timerId, String downloadClusterId) 
	{
		boolean isSuccess = false;
		boolean isIdExisted = timerMap.get(timerId) != null;
		boolean isAppIdExisted = downloadClusterId.equals(DataController.getInstance().downloadConfig.appId);
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
