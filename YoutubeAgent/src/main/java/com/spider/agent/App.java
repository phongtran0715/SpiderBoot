package com.spider.agent;

import org.apache.log4j.Logger;

import com.spider.corba.CorbaServer;

public class App 
{
	private static final Logger logger = Logger.getLogger(App.class);
	public static void main( String[] args )
	{
		System.out.println( "Hello World!" );
		//Init corba server

		Thread serverThread = new Thread(){
			public void run(){
				logger.info("Beginning to init download corba server >>>");
				System.out.println("Beginning to init download corba server >>>");
				CorbaServer corbarServer = new CorbaServer();
				corbarServer.initCorba("NameService=corbaloc:iiop:127.0.0.1:2809/NameService");
			}
		};
		serverThread.start();
	}
}
