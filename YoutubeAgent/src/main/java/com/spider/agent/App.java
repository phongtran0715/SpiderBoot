package com.spider.agent;

import org.apache.log4j.Logger;
import com.spider.corba.CorbaServer;
import com.spider.config.YoutubeAgentProperty;

public class App 
{
	private static final Logger logger = Logger.getLogger(App.class);
	public static void main( String[] args )
	{
		if (args.length <= 0){
			logger.error("You must set config file for application");
			return;
		}
		//Load configuration file
		String configFile = args[0];
		final YoutubeAgentProperty ytAgentCfg = new YoutubeAgentProperty(configFile);
		YoutubeDataController.getInstance().setYtAgentCfg(ytAgentCfg);
		
		Thread serverThread = new Thread(){
			public void run(){
				logger.info("Beginning to init download corba server >>>");
				CorbaServer corbarServer = new CorbaServer();
				logger.info("Corba ref : " + ytAgentCfg.corbaRef);
				corbarServer.initCorba(ytAgentCfg.corbaRef);
			}
		};
		serverThread.start();
	}
}
