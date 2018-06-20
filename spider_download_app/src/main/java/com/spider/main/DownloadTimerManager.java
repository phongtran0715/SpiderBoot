package com.spider.main;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

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

	public boolean createDownloadTimer(int timerId, 
			SpiderCorba.SpiderDefinePackage.DownloadConfig downloadCfg) {
		boolean isSuccess = false;
		//check timer is existed
		if(timerMap.get(timerId)!= null)
		{
			timerMap.get(timerId).cancel();
			timerMap.get(timerId).purge();
			timerMap.remove(timerId);
		}
		TimerTask timerTask = new DownloadExecuteTimer(timerId, downloadCfg);
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, downloadCfg.timerInterval * 1000);
		if (timer != null) {
			timerMap.put(timerId, timer);
		}
		isSuccess = true;
		return isSuccess;
	}

	public boolean modifyDownloadTimer(int timerId,
			SpiderCorba.SpiderDefinePackage.DownloadConfig downloadCfg) 
	{
		boolean isSuccess = false;
		//check timer  id is existed
		boolean isIdExisted = timerMap.get(timerId) != null;

		if(isIdExisted == true )
		{
			//reset this timer
			timerMap.get(timerId).cancel();
			timerMap.get(timerId).purge();
			timerMap.remove(timerId);
			if(downloadCfg.synStatus == 1)
			{
				createDownloadTimer(timerId, downloadCfg);	
			}
		}else
		{
			if(downloadCfg.synStatus == 1)
			{
				createDownloadTimer(timerId, downloadCfg);
			}
		}
		return isSuccess;
	}

	public boolean deleteDownloadTimer(int timerId) 
	{
		boolean isSuccess = false;
		boolean isIdExisted = timerMap.get(timerId) != null;
		if(isIdExisted == true)
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
