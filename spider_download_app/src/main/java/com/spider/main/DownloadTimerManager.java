package com.spider.main;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class DownloadTimerManager {

	private static DownloadTimerManager instance = null;
	public static HashMap<String, Timer> timerMap = new HashMap<String, Timer>();
	public DownloadTimerManager() {
	}

	public static DownloadTimerManager getInstance() {
		if (instance == null) {
			instance = new DownloadTimerManager();
		}
		return instance;
	}

	public boolean createDownloadTimer(int timerId, int timeType, 
			SpiderCorba.SpiderDefinePackage.DownloadConfig downloadCfg) {
		boolean isSuccess = false;
		//check timer is existed
		String timerIdent = Integer.toString(timerId) + "_" + Integer.toString(timeType);
		if(timerMap.get(timerIdent)!= null)
		{
			timerMap.get(timerIdent).cancel();
			timerMap.get(timerIdent).purge();
			timerMap.remove(timerIdent);
		}
		TimerTask timerTask = new DownloadExecuteTimer(timerId, timeType, downloadCfg);
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, downloadCfg.timerInterval * 1000);
		if (timer != null) {
			timerMap.put(timerIdent, timer);
		}
		isSuccess = true;
		return isSuccess;
	}

	public boolean modifyDownloadTimer(int timerId, int timerType,
			SpiderCorba.SpiderDefinePackage.DownloadConfig downloadCfg) 
	{
		boolean isSuccess = false;
		String timerIdent = Integer.toString(timerId) + "_" + Integer.toString(timerType);
		//check timer  id is existed
		boolean isIdExisted = timerMap.get(timerIdent) != null;

		if(isIdExisted == true )
		{
			//reset this timer
			timerMap.get(timerIdent).cancel();
			timerMap.get(timerIdent).purge();
			timerMap.remove(timerIdent);
			if(downloadCfg.synStatus == 1)
			{
				createDownloadTimer(timerId, timerType, downloadCfg);	
			}
		}else
		{
			if(downloadCfg.synStatus == 1)
			{
				createDownloadTimer(timerId, timerType, downloadCfg);
			}
		}
		return isSuccess;
	}

	public boolean deleteDownloadTimer(int timerId, int timerType) 
	{
		boolean isSuccess = false;
		String timerIdent = Integer.toString(timerId) + "_" + Integer.toString(timerType);
		boolean isIdExisted = timerMap.get(timerIdent) != null;
		if(isIdExisted == true)
		{
			Timer timer = timerMap.get(timerIdent);
			timer.cancel();
			timer.purge();
			timerMap.remove(timerIdent);
		}
		isSuccess = true;		
		return isSuccess;
	}
}
