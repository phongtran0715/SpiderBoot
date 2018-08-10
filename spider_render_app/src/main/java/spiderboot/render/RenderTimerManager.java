package spiderboot.render;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

public class RenderTimerManager {

	Queue<DataDefine.RenderJobData> qRenderJob = new LinkedList<DataDefine.RenderJobData>();
	static RenderTimerManager instance = null;
	Object queue_lock = new Object();

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

	public void addJob(DataDefine.RenderJobData data)
	{
		synchronized (queue_lock) {
			qRenderJob.add(data);	
		}
	}

	public boolean checkEmptyQueue() {
		return qRenderJob.isEmpty();
	}

	public DataDefine.RenderJobData getJob(){
		synchronized (queue_lock) {
			return qRenderJob.poll();	
		}
	}
}
