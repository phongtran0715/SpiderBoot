package spiderboot.helper;

import java.util.Date;
import java.util.TimerTask;

public class SyncTimerTask extends TimerTask{
	String timerName;
	boolean isComplete = true;
	
	public SyncTimerTask(String timerName) {
		// TODO Auto-generated constructor stub
		this.timerName = timerName;
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
		System.out.println("Timer " + timerName + " started at:"+new Date());
        completeTask();
        System.out.println("Timer " + timerName + " finished at:"+new Date());
	}
	
	private void completeTask() {
		if(isComplete){
			try {
				isComplete = false;
	            //assuming it takes 20 secs to complete the task
	        	for(int i = 0; i< 20; i++){
	        		System.out.println("Do work " + timerName + " : " +  i);
	        		Thread.sleep(1000);
	        	}
	        	isComplete = true;
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }	
		}else{
			System.out.println("Task have not yet completeed.");
		}
    }
}
