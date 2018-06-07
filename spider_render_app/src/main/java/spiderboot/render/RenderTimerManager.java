package spiderboot.render;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

public class RenderTimerManager {
	
	public static Queue<DataDefine.RenderJobData> qRenderJob = new LinkedList<DataDefine.RenderJobData>();
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
