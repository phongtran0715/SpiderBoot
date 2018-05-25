package spiderboot.render;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class RenderTimerManager {
	
	public static HashMap<String, Timer> timerMap = new HashMap<String, Timer>();
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
		if (timer != null) {
			timerMap.put(taskId, timer);
		}
		isSuccess = true;
		return isSuccess;
	}

	public void initTimerTask() {
		startRenderTimer("RenderApp_01", 60 * 1000);
	}
}
