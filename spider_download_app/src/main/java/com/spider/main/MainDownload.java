/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spider.main;

import com.spider.corba.DownloadCorbaClient;
import com.spider.corba.DownloadCorbaServer;

import spiderboot.configuration.DownloadConfig;
import spiderboot.data.DataController;

public class MainDownload {

	public static void main(String[] args) {
		if (args.length <= 0){
			System.out.println("You must set config file for application");
			return;
		}
		//Load configuration file
		String configFile = args[0];
		final DownloadConfig downloadConfig = new DownloadConfig(configFile);
		DataController.getInstance().setDownloadConfigObj(downloadConfig);
		//Init corba server

		Thread serverThread = new Thread(){
			public void run(){
				System.out.println("Beginning to init download corba server...");
				DownloadCorbaServer downloadServer = new DownloadCorbaServer();
				downloadServer.initCorba(downloadConfig.corbaRef);
			}
		};
		serverThread.start();

		Thread clientThread = new Thread(){
			public void run(){
				//Init corba client 
				System.out.println("Beginning to init download corba client...");
				DownloadCorbaClient downloadClient = new DownloadCorbaClient();
				boolean isInitClient = downloadClient.initCorba(downloadConfig.corbaRef);
				if(isInitClient == false)
				{
					System.out.println("Error! Can not init download corba client connection.");
				}else {
					System.out.println("Init download corba client connection successful!");

					System.out.println("Notify startup status to agent");
					try
					{
						System.out.println("appID = " + downloadConfig.appId);
						downloadClient.downloadAppImpl.onDownloadStartup(downloadConfig.appId);	
					}catch (Exception e) {
						System.err.println(e.toString());
					}
				}
			}
		};

		clientThread.start();

		//Start download job
		startJob();
	}

	public static void startJob() 
	{
	}
}
