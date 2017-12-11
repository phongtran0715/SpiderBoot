package spiderboot.video.download;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;

import spiderboot.databaseconnection.MySqlAccess;
import spiderboot.timer.TimerWrapper;

public class DownloadTimerManager extends TimerWrapper{
	private static DownloadTimerManager instance = null;
	
	public DownloadTimerManager() {
	}
	
	public static DownloadTimerManager getInstance(){
		if(instance == null){
			instance = new DownloadTimerManager();
		}
		return instance;
	}
	
	public boolean startDownloadTimer(String taskId, String cHomeId, String cMonitorId, int timerInterval) {
		boolean isSuccess = false;
		TimerTask timerTask = new DownloadExecuteTimer(taskId, cHomeId, cMonitorId);
		Timer timer = new Timer(true);
		timer.scheduleAtFixedRate(timerTask, 0, timerInterval);
		if(timer!= null){
			timerMap.put(taskId, timer);	
		}
		isSuccess = true;
		return isSuccess;
	}
	
	public void initTimerTask(){
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
				startDownloadTimer(id, cHomeId, cMonitorId, syncInterval * 1000);
				System.out.println("Start sync task : " + id + " with timer interval = " + syncInterval + " seconds");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
