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
    private static final Logger logger = Logger.getLogger(MainUpload.class);

    public static void main(String[] args) {
        start();
    }

    public static void start() {
    	if (!Config.loadConfig()) {
            logger.error("ERR_LOAD_FILE_CONFIG!");
            return;
        }
        int dbResult = MySqlAccess.getInstance().DBConnect(Config.dbServer, 
        		Config.dbName, Config.dbUserName, Config.dbPassword);
        if (dbResult!= 0) {
            logger.info("Open connection database false.The uploadApp will be exited!!!");
            return;
        }
        UploadTimerManager.getInstance().initTimerTask();
    }
}
