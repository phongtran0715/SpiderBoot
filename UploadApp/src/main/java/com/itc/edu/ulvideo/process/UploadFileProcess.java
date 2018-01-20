/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itc.edu.ulvideo.process;

import com.itc.edu.ulvideo.business.VupVideo;
import com.itc.edu.ulvideo.link.ConfigDaoImpl;
import com.viettel.mmserver.base.ProcessThreadMX;
import com.itc.edu.ulvideo.util.Config;
import com.itc.edu.ulvideo.util.Utility;
import com.itc.edu.ulvideo.vuploaed.VUploadedDaoImpl;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author donlv
 */
public class UploadFileProcess extends ProcessThreadMX {

    private VupVideo vupVideo;
    private static BlockingQueue<String> queueUpload;
    private static ConfigDaoImpl configDao;
    
    public UploadFileProcess(VupVideo vupVideo, String threadName) {
        super(threadName);
        this.vupVideo = vupVideo;
        //this.ftpServer.connect();
        try {
            registerAgent("PokeCallUpload:name=" + threadName);
        } catch (Exception ex) {
            logger.error("Cannot register agent", ex);
        }
    }

    public static void setQueueUpload(BlockingQueue<String> queueUpload) {
        UploadFileProcess.queueUpload = queueUpload;
    }

    public static void setConfigDaoImpl (ConfigDaoImpl configDao) {
        UploadFileProcess.configDao = configDao;
    }
    @Override
    protected void process() {
        this.buStartTime = new Date();
        String fileUpload;
        try {
            fileUpload = queueUpload.poll(Config.timeoutQueue, TimeUnit.MILLISECONDS);
            if (null != fileUpload) {
                boolean result = this.vupVideo.uploadFile(fileUpload);
                if (result) {
                    logger.info("Upload file success " + fileUpload);
                    /* update status of file upload
                    0: not yet uploaded
                    1: uploaded succesffuly
                    2: uploaded error
                    */
                    ConfigDaoImpl.getInstance().updateStat(fileUpload, 1);
                    // Log file download
                    //VUploadedDaoImpl.getInstance().updateStat(fileUpload, 1);
                } else {
                    logger.info("UPLOAD_FILE_FAIL" + fileUpload);
                    ConfigDaoImpl.getInstance().updateStat(fileUpload, 2);
                }
                ListFileProcess.decrementFile();
            }
        } catch (InterruptedException ex) {
            logger.error("Error take queue upload", ex);
        }
        this.buStartTime = null;
        Utility.sleep(100);
    }
}
