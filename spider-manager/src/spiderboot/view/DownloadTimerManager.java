package spiderboot.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import spiderboot.databaseconnection.MySqlAccess;
import spiderboot.helper.DownloadTimer;

public class DownloadTimerManager {
	HashMap<String, Timer> timerMap = new HashMap<String, Timer>();
	private static DownloadTimerManager instance = null;
	
	public static DownloadTimerManager getInstance(){
		if(instance == null){
			instance = new DownloadTimerManager();
		}
		return instance;
	}
	
	public boolean startSyncThread(String taskId, String cHomeId, String cMonitorId, int timerInterval) {
		boolean isSuccess = false;
		TimerTask timerTask = new DownloadTimer(taskId, cHomeId, cMonitorId);
		Timer timer = new Timer(true);
		timer.scheduleAtFixedRate(timerTask, 0, timerInterval *1000);
		timerMap.put(taskId, timer);        
		isSuccess = true;
		return isSuccess;
	}

	public boolean stopSyncThread(String taskId) {
		boolean isSuccess = false;
		Timer timer = timerMap.get(taskId);
		timer.cancel();
		timerMap.remove(taskId);
		isSuccess = true;
		return isSuccess;
	}
	
	public void addNewTask(String taskId, Timer timer) {
		timerMap.put(taskId, timer);
	}
	
	public void removeTask(String taskId) {
		timerMap.remove(taskId);
	}
	
	public HashMap<String, Timer> getTaskMap() {
		return timerMap;
	}
	
	public void initSyncTask() {
		Statement stmt;
		String query = "SELECT Id, HomeChannelId, MonitorChannelId, TimeIntervalSync, StatusSync "
				+ "FROM home_monitor_channel_mapping WHERE StatusSync = '1';";
		try {
			stmt = MySqlAccess.getInstance().connect.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String id = Integer.toString(rs.getInt(1));
				String cHomeId = rs.getString(2);
				String cMonitorId = rs.getString(3);
				int syncInterval = rs.getInt(4);
				startSyncThread(id, cHomeId, cMonitorId, syncInterval * 1000);
				System.out.println("Start sync task : " + id + " with timer interval = " + syncInterval);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
