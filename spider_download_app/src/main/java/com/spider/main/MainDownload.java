/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spider.main;

import org.apache.log4j.Logger;

import com.spider.corba.DownloadCorbaClient;
import com.spider.corba.DownloadCorbaServer;

import spiderboot.configuration.DownloadConfig;
import spiderboot.data.DataController;

public class MainDownload {
	private static final Logger logger = Logger.getLogger(MainDownload.class);
	public static void main(String[] args) {
		if (args.length <= 0){
			logger.error("You must set config file for application");
			return;
		}
		//Load configuration file
		String configFile = args[0];
		final DownloadConfig downloadConfig = new DownloadConfig(configFile);
		DataController.getInstance().setDownloadConfigObj(downloadConfig);
		//Init corba server

		Thread serverThread = new Thread(){
			public void run(){
				logger.info("Beginning to init download corba server >>>");
				DownloadCorbaServer downloadServer = new DownloadCorbaServer();
				downloadServer.initCorba(downloadConfig.corbaRef);
			}
		};
		serverThread.start();

		//Init corba client 
		Thread clientThread = new Thread(){
			public void run(){
				logger.info("Beginning to init download corba client >>>");
				DownloadCorbaClient downloadClient = DownloadCorbaClient.getInstance();
				if(downloadClient.isSuccess == false)
				{
					downloadClient.initCorba(downloadConfig.corbaRef);
				}
				
				if(downloadClient.isSuccess == false)
				{
					logger.error("Error! Can not init download corba client connection.");
				}else {
					logger.info("Init download corba client connection successful!");
					logger.info("Notify startup status to agent");
					try
					{
						downloadClient.downloadAppImpl.onDownloadStartup(downloadConfig.appId);	
					}catch (Exception e) {
						logger.error(e);
					}
				}
			}
		};

		clientThread.start();
		
		logger.info("Download app ID: " + downloadConfig.appId);
	}
}
