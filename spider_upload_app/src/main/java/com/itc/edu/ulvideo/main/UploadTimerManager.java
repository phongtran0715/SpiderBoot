package com.itc.edu.ulvideo.main;

import java.util.Timer;
import java.util.TimerTask;
import spiderboot.timer.TimerWrapper;

public class UploadTimerManager extends TimerWrapper {

	private static UploadTimerManager instance = null;

	public UploadTimerManager() {
		// TODO Auto-generated constructor stub
	}

	public static UploadTimerManager getInstance() {
		if (instance == null) {
			instance = new UploadTimerManager();
		}
		return instance;
	}

	public boolean startUploadTimer(String taskId, int timerInterval) {
		boolean isSuccess = false;
		TimerTask timerTask = new UploadExecuteTimer(taskId);
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, timerInterval);
		if (timer != null) {
			timerMap.put(taskId, timer);
		}
		isSuccess = true;
		return isSuccess;
	}
	public void initTimerTask() {
		startUploadTimer("UploadApp_01", 10 * 1000);
	}
}
