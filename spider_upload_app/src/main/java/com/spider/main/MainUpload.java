/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spider.main;

import org.apache.log4j.Logger;

import com.spider.corba.UploadCorbaClient;
import com.spider.corba.UploadCorbaServer;

import spiderboot.configuration.UploadConfig;
import spiderboot.data.DataController;

public class MainUpload {
	private static final Logger logger = Logger.getLogger(MainUpload.class);

	public static void main(String[] args) {
		if (args.length <= 0){
			logger.error("You must set config file for application");
			return;
		}
		//Load configuration file
		String configFile = args[0];
		final UploadConfig uploadConfig = new UploadConfig(configFile);
		DataController.getInstance().setUploadConfigObj(uploadConfig);

		//Init corba server

		Thread serverThread = new Thread(){
			public void run(){
				logger.info("Beginning to init upload corba server >>>");
				UploadCorbaServer uploadServer = new UploadCorbaServer();
				uploadServer.initCorba(uploadConfig.corbaRef);
			}
		};
		serverThread.start();

		//Init corba client 
		Thread clientThread = new Thread(){
			public void run(){
				logger.info("Beginning to init upload corba client >>>");
				UploadCorbaClient uploadClient = new UploadCorbaClient();
				boolean isInitClient = uploadClient.initCorba(uploadConfig.corbaRef);
				if(isInitClient == false)
				{
					logger.error("Error! Can not init render upload client connection.");
				}else {
					logger.info("Init upload corba client connection successful!");
					logger.info("Notify startup status to agent");
					try
					{
						logger.info("upload Id = " + uploadConfig.appId);
						uploadClient.uploadAppImpl.onUploadStartup(uploadConfig.appId);	
					}catch (Exception e) {
						logger.error(e);
					}
				}
			}
		};

		clientThread.start();
		
		logger.info("Upload app ID : " + uploadConfig.appId);
	}
}
