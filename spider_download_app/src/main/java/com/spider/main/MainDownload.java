/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spider.main;

import org.apache.log4j.Logger;

import com.spider.corba.DownloadCorbaClient;
import com.spider.corba.DownloadCorbaServer;

import spiderboot.configuration.DownloadProperty;
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
		final DownloadProperty downloadConfig = new DownloadProperty(configFile);
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
				try
				{
					downloadClient.downloadAppImpl.onDownloadStartup(downloadConfig.appId);	
				}catch (Exception e) {
					logger.error(e);
				}
			}
		};

		clientThread.start();
		
		logger.info("Download app ID: " + downloadConfig.appId);
	}
}
