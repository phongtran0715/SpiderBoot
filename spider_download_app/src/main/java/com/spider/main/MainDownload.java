/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spider.main;

import com.spider.corba.DownloadCorbaClient;
import com.spider.corba.DownloadCorbaServer;

public class MainDownload {

	public static void main(String[] args) {
		//Init corba server

		Thread serverThread = new Thread(){
			public void run(){
				System.out.println("Beginning to init download corba server...");
				DownloadCorbaServer downloadServer = new DownloadCorbaServer();
				downloadServer.initCorba();
			}
		};
		serverThread.start();
		
		Thread clientThread = new Thread(){
			public void run(){
				//Init corba client 
				System.out.println("Beginning to init download corba client...");
				DownloadCorbaClient downloadClient = new DownloadCorbaClient();
				boolean isInitClient = downloadClient.initCorba();
				if(isInitClient == false)
				{
					System.out.println("Error! Can not init download corba client connection.");
				}else {
					System.out.println("Init download corba client connection successful!");

					System.out.println("Notify startup status to agent");
					try
					{
						downloadClient.downloadAppImpl.onDownloadStartup();	
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
