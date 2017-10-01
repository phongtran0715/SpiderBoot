package spiderboot.helper;

import java.io.IOException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;

public class Util {
	private static final int PORT = 9999;
	@SuppressWarnings("unused")
	private static ServerSocket socket;    

	public static boolean checkIfRunning() {
		boolean isRunning = false;
		try {
			//Bind to localhost adapter with a zero connection queue 
			socket = new ServerSocket(PORT,0,InetAddress.getByAddress(new byte[] {127,0,0,1}));
			isRunning = false;
		}
		catch (BindException e) {
			System.err.println("Already running.");
			System.exit(1);
			isRunning = true;
		}
		catch (IOException e) {
			System.err.println("Unexpected error.");
			e.printStackTrace();
			System.exit(2);
		}
		return isRunning;
	}
}
