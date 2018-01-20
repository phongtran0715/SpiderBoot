package processApp.processApp;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class App 
{
	public static void main( String[] args ) throws IOException
	{
		System.out.println("Starting ...");

		//open database connection
		int dbResult = MySqlAccess.getInstance().DBConnect();
		if(dbResult == 0){
			System.out.println("Open connection successful");
			TimerTask timerTask = new ProcessExecuteTimer("ProcessVideo");
			Timer timer = new Timer();
			timer.scheduleAtFixedRate(timerTask, 0, 1*1000);
		}else{
			System.out.println("Open connection false.");
		}

		System.out.println("Started process video timer !");
	}
}
