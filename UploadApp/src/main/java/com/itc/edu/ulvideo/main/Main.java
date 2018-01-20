/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itc.edu.ulvideo.main;

import com.itc.edu.ulvideo.business.VupVideo;
import com.itc.edu.ulvideo.link.ConfigBo;
import com.itc.edu.ulvideo.link.ConfigDaoImpl;
//import com.itc.ftpdownload.business.FTPServer;
import com.itc.edu.ulvideo.process.UploadFileProcess;
import com.itc.edu.ulvideo.process.ListFileProcess;
//import com.itc.ftpdownload.process.TimerLoadBalance;
import com.itc.edu.ulvideo.util.Config;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.apache.log4j.Logger;

/**
 *
 * @author phapnd
 */
public class Main {

    private static UploadFileProcess[] uploadProcess = null;
    private static ListFileProcess getFileProcess = null;
    private static BlockingQueue<String> queueUpload;
    private static ConfigDaoImpl configDao;
    //Load balance
    private static final Timer timerLoadBalance = null;
    //private static TimerLoadBalance loadBalance = null;
    private static final Logger logger = Logger.getLogger(Main.class);

    private static boolean initial() {
        VupVideo vupVideo = null;
        if (!Config.loadConfig()) {
            return false;
        }
        configDao = new ConfigDaoImpl();
        ConfigDaoImpl.getInstance().loadParams();
        List<ConfigBo> lstCfgDrbBo = configDao.getConfigBo();
        logger.info("Size config:" + lstCfgDrbBo.size());
        if (lstCfgDrbBo.size() <= 0 ) {
            logger.error("ERR_DAO_LOAD_FILEUPLOAD|" + "No file set");
            return false;
        }
        //create queue save file
        queueUpload = new ArrayBlockingQueue<String>(Config.numberLUp);
        //create process list file
        ListFileProcess.setQueueUpload(queueUpload);
        getFileProcess = new ListFileProcess(configDao, "GetFileProcess");
        //ListFileProcess.setQueueUpload(queueUpload);
        logger.info("QUEUE|"+ queueUpload);
        //create process download
        UploadFileProcess.setQueueUpload(queueUpload);
        UploadFileProcess.setConfigDaoImpl(configDao);
        uploadProcess = new UploadFileProcess[Config.numberCore];
        for (int i = 0; i < Config.numberCore; i++) {
            uploadProcess[i] = new UploadFileProcess(new VupVideo(vupVideo), "Uploadproces " + (i + 1));
        }
//        TimerLoadBalance.setGetFileProcess(getFileProcess);
//        loadBalance = new TimerLoadBalance(Config.agentId);
//        timerLoadBalance = new Timer("Timer load balance");
        return true;
    }

    public static void start() {
        if (!initial()) {
            return;
        }
        
        for (int i = 0; i < Config.numberCore; i++) {
            uploadProcess[i].start();
        }
        
        getFileProcess.start();
        getFileProcess.setIsRun(true);
        //start timer lb
//        timerLoadBalance.schedule(loadBalance, new Date(), Config.lbInterval);
    }

    public static void stop() {
        //stop timer lb
        if (null != timerLoadBalance) {
            timerLoadBalance.cancel();
        }
    }
}
