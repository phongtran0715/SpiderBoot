package com.spider.main;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import com.spider.corba.DownloadCorbaClient;

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

	public boolean createDownloadTimer(int taskId, String cHomeId, String cMonitorId, int timerInterval) {
		boolean isSuccess = false;
		//check timer is existed
		if(timerMap.get(taskId)!= null)
		{
			timerMap.get(taskId).cancel();
			timerMap.get(taskId).purge();
			timerMap.remove(taskId);
		}
		TimerTask timerTask = new DownloadExecuteTimer(taskId, cHomeId, cMonitorId);
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, timerInterval * 1000);
		if (timer != null) {
			timerMap.put(taskId, timer);
		}
		isSuccess = true;
		return isSuccess;
	}

	public boolean modifyMappingChannel(int timerId, String cHomeId, String cMonitorId, int timerInterval, int synStatus) 
	{
		boolean isSuccess = false;
		//check timer  id is existed
		boolean isExisted = timerMap.get(timerId) != null;
		if((isExisted == false) && (synStatus == 1))
		{
			//create new tmer
			createDownloadTimer(timerId, cHomeId, cMonitorId, timerInterval);
		}else if((isExisted == true) && (synStatus == 1)){
			//reset this timer
			timerMap.get(timerId).cancel();
			timerMap.get(timerId).purge();
			timerMap.remove(timerId);
			createDownloadTimer(timerId, cHomeId, cMonitorId, timerInterval);
		}else if((isExisted == true) && (synStatus == 0))
		{
			//remove this timer
			timerMap.get(timerId).cancel();
			timerMap.get(timerId).purge();
			timerMap.remove(timerId);
		}else {
			//nothing to do
		}
		return isSuccess;
	}

	public boolean deleteDownloadTimer(int taskId) 
	{
		boolean isSuccess = false;
		//check timer is existed
		Timer timer = timerMap.get(taskId);
		if(timer != null) {
			timer.cancel();
			timer.purge();
			timerMap.remove(taskId);
			isSuccess = true;
		}
		return isSuccess;
	}
}
