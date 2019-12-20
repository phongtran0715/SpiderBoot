package spider.upload;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.log4j.Logger;

import SpiderCorba.SpiderDefinePackage.VideoInfo;

public class UploadTimerManager {
	static final Logger logger = Logger.getLogger(UploadTimerManager.class);
	static UploadTimerManager instance = null;
	HashMap<String, Queue<DataDefine.UploadJobData>> queueMap = new HashMap<String, Queue<DataDefine.UploadJobData>>();
	HashMap<String, Timer> timerMap = new HashMap<String, Timer>();
	Object lock_timer = new Object();
	Object lock_queue = new Object();

	public UploadTimerManager() {
	}

	public static UploadTimerManager getInstance() {
		if (instance == null) {
			instance = new UploadTimerManager();
		}
		return instance;
	}

	public boolean createUploadTimer(String cHomeId) {
		logger.info("[UPLOAD] : Create new upload timer for channel : " + cHomeId );
		boolean isSuccess = false;
		//check timer is existed
		synchronized (lock_timer) {
			if(timerMap.get(cHomeId) == null)
			{
				TimerTask timerTask = new UploadExecuteTimer(cHomeId);
				Timer timer = new Timer();
				timer.scheduleAtFixedRate(timerTask, 0, 10 * 1000);
				if (timer != null) {
					timerMap.put(cHomeId, timer);
				}
			}else {
				logger.info("[UPLOAD] : Upload timer id" + cHomeId +" had existed in timer map. It will be ignore!");
			}
		}

		synchronized (lock_queue) {
			if(queueMap.get(cHomeId)==null)
			{
				Queue<DataDefine.UploadJobData> qUploadJob = new LinkedList<DataDefine.UploadJobData>();
				queueMap.put(cHomeId, qUploadJob);
			}			
		}

		isSuccess = true;
		return isSuccess;
	}

	public boolean deleteUploadTimer(String cHomeId) 
	{
		logger.info("[UPLOAD] : Delete upload timer for channel : " + cHomeId);
		boolean isSuccess = false;
		synchronized (lock_timer) {
			if(timerMap.get(cHomeId) != null)
			{
				Timer timer = timerMap.get(cHomeId);
				timer.cancel();
				timer.purge();
				timerMap.remove(cHomeId);
				logger.info("[UPLOAD] : Upload timer " + cHomeId + " is removed from timer map");
			}
			else {
				logger.error("[UPLOAD] : Not found timer " + cHomeId + " in timer map");
			}	
		}
		synchronized (lock_queue) {
			if(queueMap.get(cHomeId) != null)
			{
				queueMap.remove(cHomeId);
				logger.info("[UPLOAD] : Queue of upload timer : " + cHomeId + " removed from queue map");
			}else {
				logger.error("[UPLOAD] : Not found queue of upload timer :" + cHomeId + " in queue map");
			}	
		}

		isSuccess = true;		
		return isSuccess;
	}

	public boolean createUploadJob(int jobId, VideoInfo vInfo, String cHomeId)
	{
		logger.info("[UPLOAD] : Create upload jod id = " + jobId);
		boolean isSuccess = false;
		DataDefine.UploadJobData uploadJobData = new DataDefine().new UploadJobData(jobId, vInfo);
		synchronized (lock_queue) {
			if(queueMap.get(cHomeId) != null)
			{
				queueMap.get(cHomeId).add(uploadJobData);
				logger.info("[UPLOAD] : JobId  " + jobId + " is added to queue : " + cHomeId);
			}else {
				logger.error("[UPLOAD] : Can not found queue for upload id : " + jobId);
			}	
		}

		isSuccess = true;		
		return isSuccess;
	}
	
	public DataDefine.UploadJobData getJob(String cHomeId)
	{
		synchronized (lock_queue) {
			return queueMap.get(cHomeId).poll();	
		}
	}
}
