/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itc.edu.dlvideo.main;

import com.itc.edu.dlvideo.util.Config;
import com.itc.edu.dlvideo.util.Constant;

import spiderboot.database.MySqlAccess;

import org.apache.log4j.Logger;

/*------------------------------------------------------------------------------
** History
21-01-2018, [CR-001] phapnd
    Cap nhat func Start, khoi tao load file cau hinh de lay dieu lieu
    1. DB connection
    2. So luong kenh search tra ve

*/

public class Main {

    //private static TimerLoadBalance loadBalance = null;
    private final static Logger logger = Logger.getLogger(Main.class);

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
            logger.info("Open connection database false.The DownloadApp will be exited!!!");
            return;
        }
        DownloadTimerManager.getInstance().initTimerTask();
    }
}
