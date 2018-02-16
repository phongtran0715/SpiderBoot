/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itc.edu.ulvideo.main;

import spiderboot.configuration.Config;
import spiderboot.database.MySqlAccess;

import org.apache.log4j.Logger;

/*------------------------------------------------------------------------------
** History

*/

public class MainUpload {

    //private static TimerLoadBalance loadBalance = null;
    private static final Logger logger = Logger.getLogger(MainUpload.class);

    public static void main(String[] args) {
        start();
    }

    public static void start() {
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
        UploadTimerManager.getInstance().initTimerTask();
    }
    
}
