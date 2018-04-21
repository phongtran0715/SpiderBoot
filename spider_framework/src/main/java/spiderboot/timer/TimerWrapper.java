package spiderboot.timer;
import java.util.HashMap;
import java.util.Timer;

public class TimerWrapper {
	public HashMap<String, Timer> timerMap = new HashMap<String, Timer>();
	
	public TimerWrapper() {
		// TODO Auto-generated constructor stub
	}
	
	public TimerWrapper(HashMap<String, Timer> timerMap){
		this.timerMap = timerMap;
	}
	
	public boolean stopDownloadTimer(String timerId) {
		boolean isSuccess = false;
		Timer timer = timerMap.get(timerId);
		if(timer != null) {
			timer.cancel();
			timerMap.remove(timerId);	
		}
		isSuccess = true;
		return isSuccess;
	}
	
	public void addNewTimer(String timerId, Timer timer) {
		if(timer != null) {
			timerMap.put(timerId, timer);	
		}
	}
	
	public void removeTimerById(String timerId) {
		timerMap.remove(timerId);
	}
	
	public HashMap<String, Timer> getDownloadTimerMap() {
		return timerMap;
	}
	
	public void initTimerTask() {
		
	}
}
