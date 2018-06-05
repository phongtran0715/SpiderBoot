package spiderboot.render;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import SpiderRenderApp.SpiderFootSidePackage.RenderInfo;

public class RenderTimerManager {
	
	public static Queue<RenderInfo> qRenderJob = new LinkedList<RenderInfo>();
	private static RenderTimerManager instance = null;
	public static RenderTimerManager getInstance() {
		if (instance == null) {
			instance = new RenderTimerManager();
		}
		return instance;
	}

	public boolean startRenderTimer(String taskId, int timerInterval) {
		boolean isSuccess = false;
		TimerTask timerTask = new RenderExecuteTimer(taskId);
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, timerInterval);
		isSuccess = true;
		return isSuccess;
	}

	public void initTimerTask() {
		//x*1000 -> (x second) 
		startRenderTimer("RenderApp_01", 5 * 1000);
	}
}
