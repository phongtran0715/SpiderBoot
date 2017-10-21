package spiderboot.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import spiderboot.databaseconnection.MySqlAccess;
import spiderboot.helper.SyncTimerTask;

public class SyncTaskManager {
	HashMap<String, Timer> timerMap = new HashMap<String, Timer>();
	private static SyncTaskManager instance = null;
	
	public static SyncTaskManager getInstance(){
		if(instance == null){
			instance = new SyncTaskManager();
		}
		return instance;
	}
	
	public boolean startSyncThread(String taskId, int timerInterval) {
		boolean isSuccess = false;
		TimerTask timerTask = new SyncTimerTask(taskId);
		Timer timer = new Timer(true);
		timer.scheduleAtFixedRate(timerTask, 0, timerInterval);
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
		String query = "SELECT Id, TimeIntervalSync, StatusSync FROM home_monitor_channel_mapping WHERE StatusSync = '1';";
		try {
			stmt = MySqlAccess.getInstance().connect.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String id = Integer.toString(rs.getInt(1));
				int timerInterval = rs.getInt(2);
				System.out.println("timer interval " + timerInterval);
				startSyncThread(id, timerInterval);
				System.out.println("Start sync task : " + id + " with timer interval = " + timerInterval);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
