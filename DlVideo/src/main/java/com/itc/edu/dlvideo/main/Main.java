/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itc.edu.dlvideo.main;

import com.itc.edu.database.MySqlAccess;
import org.apache.log4j.Logger;

/**
 *
 * @author phapnd
 */
public class Main {

	//private static TimerLoadBalance loadBalance = null;
	private static final Logger logger = Logger.getLogger(Main.class);

	public static void main(String [] args) {
		start();
    }
	
	public static void start() {
		int dbResult = MySqlAccess.getInstance().DBConnect();
		if(dbResult == 0){
			logger.info("Open connection successful");
		}else{
			logger.info("Open connection false.");
		}
		DownloadTimerManager.getInstance().initTimerTask();
	}
}
