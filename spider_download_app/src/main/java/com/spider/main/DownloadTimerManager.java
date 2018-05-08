package com.spider.main;

import java.util.Timer;
import java.util.TimerTask;
import spiderboot.timer.TimerWrapper;

public class DownloadTimerManager extends TimerWrapper {

	private static DownloadTimerManager instance = null;

	public DownloadTimerManager() {
	}

	public static DownloadTimerManager getInstance() {
		if (instance == null) {
			instance = new DownloadTimerManager();
		}
		return instance;
	}

	public boolean startDownloadTimer(String taskId, String cHomeId, String cMonitorId, int timerInterval) {
		boolean isSuccess = false;
		TimerTask timerTask = new DownloadExecuteTimer(taskId, cHomeId, cMonitorId);
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, timerInterval);
		if (timer != null) {
			timerMap.put(taskId, timer);
		}
		isSuccess = true;
		return isSuccess;
	}

	@Override
	public void initTimerTask() {
		//TODO: get mapping channel list from data base
		//Start one download timer for each mapping items
		//startDownloadTimer(id, cHomeId, cMonitorId, syncInterval * 1000);
	}
}
