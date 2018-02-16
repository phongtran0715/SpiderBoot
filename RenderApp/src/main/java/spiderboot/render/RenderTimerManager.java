package spiderboot.render;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import spiderboot.timer.TimerWrapper;

public class RenderTimerManager extends TimerWrapper {

	private static RenderTimerManager instance = null;
	private static Logger logger = Logger.getLogger(RenderTimerManager.class);
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
		if (timer != null) {
			timerMap.put(taskId, timer);
		}
		isSuccess = true;
		return isSuccess;
	}

	@Override
	public void initTimerTask() {
		startRenderTimer("RenderApp_01", 60 * 1000);
	}
}
