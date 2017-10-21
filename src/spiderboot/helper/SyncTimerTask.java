package spiderboot.helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import spiderboot.databaseconnection.MySqlAccess;

public class SyncTimerTask extends TimerTask{
	String timerId;
	boolean isComplete = true;
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public SyncTimerTask(String timerId) {
		// TODO Auto-generated constructor stub
		this.timerId = timerId;
	}

	@Override
	public boolean cancel() {
		// TODO Auto-generated method stub
		return super.cancel();
	}

	@Override
	public long scheduledExecutionTime() {
		// TODO Auto-generated method stub
		return super.scheduledExecutionTime();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Timer " + timerId + " started at:"+new Date());
		completeTask();
		System.out.println("Timer " + timerId + " finished at:"+new Date());
	}

	private void completeTask() {
		if(isComplete){
			try {
				isComplete = false;
				Date lastSyncTime = getLastSyncTime(timerId);
				System.out.println("last sync time = " + dateFormat.format(lastSyncTime));
				//assuming it takes 20 secs to complete the task
				for(int i = 0; i< 15; i++){
					System.out.println("Do work " + timerId + " : " +  i);
					Thread.sleep(1000);
				}
				//Update last sync time
				lastSyncTime = new Date();
				System.out.println("Update last sync time  = " + dateFormat.format(lastSyncTime));
				updateLastSyncTime(lastSyncTime);
				isComplete = true;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}else{
			System.out.println("Task have not yet completeed.");
		}
	}

	private Date getLastSyncTime(String mapId){
		Date lastSyncTime = null;
		String query = "SELECT LastSyncTime FROM home_monitor_channel_mapping WHERE Id = '" + mapId + "';";
		Statement stmt;
		try{
			stmt = MySqlAccess.getInstance().connect.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				lastSyncTime = (Date)rs.getTimestamp(1);
			}
		}catch(Exception ex){
			System.out.println(ex.toString());
		}
		return lastSyncTime;
	}

	private void updateLastSyncTime(Date lastSyncTime) {
		Timestamp timestamp = new Timestamp(lastSyncTime.getTime());
		PreparedStatement preparedStm = null;
		String query = "UPDATE home_monitor_channel_mapping SET LastSyncTime = ? WHERE Id = ? " ;
		try {
			preparedStm = MySqlAccess.getInstance().connect.prepareStatement(query);
			// execute insert SQL statement
			preparedStm.setTimestamp(1, timestamp);
			preparedStm.setInt(2, Integer.valueOf(timerId));
			//preparedStm.executeUpdate();
			preparedStm.executeUpdate();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			System.out.println(ex.getMessage());
			return;
		}
	}

	private List<String> getNewVideo() {
		List<String> vList = new ArrayList<String>();
		return vList;
	}
}
