package spiderboot.render;

import java.io.IOException;

public class Main 
{
	public static void main( String[] args ) throws IOException
	{
		int dbResult = MySqlAccess.getInstance().DBConnect();
        if (dbResult== 0) {
        	RenderTimerManager.getInstance().initTimerTask();
        }else {
        	System.out.println("Error! Open connection database false.");
        }
	}
}
