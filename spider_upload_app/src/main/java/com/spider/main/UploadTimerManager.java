package com.spider.main;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import SpiderUploadApp.SpiderFootSidePackage.UploadInfo;

public class UploadTimerManager {

	private static UploadTimerManager instance = null;
	public static Queue<UploadInfo> qUploadJob = new LinkedList<UploadInfo>();

	public UploadTimerManager() {
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
		isSuccess = true;
		return isSuccess;
	}
	public void initTimerTask() {
		startUploadTimer("UploadApp_01", 10 * 1000);
	}
}
