package spider.download;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.log4j.Logger;

public class DownloadTimerManager {
	final Logger logger = Logger.getLogger(DownloadTimerManager.class);
	static DownloadTimerManager instance = null;
	public static HashMap<Integer, Timer> timerMap = new HashMap<Integer, Timer>();
	Object timer_mutex = new Object();

	public static DownloadTimerManager getInstance() {
		if (instance == null) {
			instance = new DownloadTimerManager();
		}
		return instance;
	}

	public boolean createDownloadTimer(int timerId, int timerInterval) {
		logger.info("[DOWNLOAD] : Create new download timer id = " + timerId  + "timer interval " + timerInterval);
		boolean isSuccess = false;
		//check timer is existed
		synchronized (timer_mutex) {
			if(timerMap.get(timerId)!= null)
			{
				timerMap.get(timerId).cancel();
				timerMap.get(timerId).purge();
				timerMap.remove(timerId);
			}	
		}

		TimerTask timerTask = new DownloadExecuteTimer(timerId);
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, timerInterval * 1000);
		if (timer != null) {
			synchronized (timer_mutex) {
				timerMap.put(timerId, timer);	
			}
			isSuccess = true;
		}
		return isSuccess;
	}

	public boolean modifyDownloadTimer(int timerId, int timerInterval, int syncStatus) 
	{
		logger.info("[DOWNLOAD] : Modify download timer id = " + timerId );	
		boolean isSuccess = false;
		//check timer  id is existed
		boolean isIdExisted = timerMap.get(timerId) != null;

		if(isIdExisted == true )
		{
			//reset this timer
			synchronized (timer_mutex) {
				timerMap.get(timerId).cancel();
				timerMap.get(timerId).purge();
				timerMap.remove(timerId);	
			}
			if(syncStatus == 1)
			{
				createDownloadTimer(timerId, timerInterval);
			}
		}else
		{
			if(syncStatus == 1)
			{
				createDownloadTimer(timerId, timerInterval);
			}
		}
		isSuccess = true;
		return isSuccess;
	}

	public boolean deleteDownloadTimer(int timerId) 
	{
		logger.info("[DOWNLOAD] : Delete download timer id = " + timerId );
		boolean isSuccess = false;
		synchronized (timer_mutex) {
			boolean isIdExisted = timerMap.get(timerId) != null;
			if(isIdExisted == true)
			{
				Timer timer = timerMap.get(timerId);
				timer.cancel();
				timer.purge();
				timerMap.remove(timerId);
			}	
		}

		isSuccess = true;		
		return isSuccess;
	}
}
