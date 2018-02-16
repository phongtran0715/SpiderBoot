package spiderboot.render;

import java.io.IOException;

import org.apache.log4j.Logger;

import spiderboot.configuration.Config;
import spiderboot.database.MySqlAccess;

public class MainRender 
{
	//private static TimerLoadBalance loadBalance = null;
    private final static Logger logger = Logger.getLogger(MainRender.class);
	public static void main( String[] args ) throws IOException
	{
		Config config = new Config();
		if (!Config.loadConfig()) {
            logger.error("ERR_LOAD_FILE_CONFIG!");
            return;
        }
		 //int dbResult = MySqlAccess.getInstance().DBConnect();
        int dbResult = MySqlAccess.getInstance().DBConnect(config.dbServer, 
        		config.dbName, config.dbUserName, config.dbPassword);
        if (dbResult!= 0) {
            logger.info("Open connection database false.The DownloadApp will be exited!!!");
            return;
        }
        RenderTimerManager.getInstance().initTimerTask();
	}
}
