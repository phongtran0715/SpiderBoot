/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itc.edu.ulvideo.main;

import com.itc.edu.database.MySqlAccess;
import com.itc.edu.ulvideo.util.Config;
import org.apache.log4j.Logger;

/*------------------------------------------------------------------------------
** History

*/

public class Main {

    //private static TimerLoadBalance loadBalance = null;
    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        if (!Config.loadConfig()) {
            logger.error("ERR_LOAD_FILE_CONFIG!");
            return;
        }
        int dbResult = MySqlAccess.getInstance().DBConnect();
        if (dbResult!= 0) {
            logger.info("Open connection database false.");
            return;
        }
        UploadTimerManager.getInstance().initTimerTask();
    }
    
}
