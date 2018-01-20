/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itc.edu.ulvideo.process;

import com.itc.edu.ulvideo.link.ConfigBo;
import com.itc.edu.ulvideo.link.ConfigDaoImpl;
import com.viettel.mmserver.base.ProcessThreadMX;
import com.itc.edu.ulvideo.util.Config;
import com.itc.edu.ulvideo.util.Utility;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import org.apache.log4j.Logger;

/**
 *
 * @author donlv
 */
public class ListFileProcess extends ProcessThreadMX {
    
    private static final Logger logger = Logger.getLogger(ListFileProcess.class);
    private ConfigDaoImpl configDaoImpl;
    private static int totalFile = 0;
    private static final Object objSync = new Object();
    private boolean isRun;
    //queue contains list files
    private static BlockingQueue<String> queueUpload;

    public ListFileProcess(ConfigDaoImpl lstCfgDrbBo, String threadName) {
        super(threadName);
        this.configDaoImpl = lstCfgDrbBo;
        try {
            registerAgent("PokeCallUpload:name=" + threadName);
        } catch (Exception ex) {
            logger.error("Cannot register agent", ex);
        }
        this.isRun = false;
    }

    public static void setQueueUpload(BlockingQueue<String> queueUpload) {
        ListFileProcess.queueUpload = queueUpload;
    }

    public void setIsRun(boolean isRun) {
        this.isRun = isRun;
    }

    @Override
    protected void process() {
        this.buStartTime = new Date();
        int size;

        if (this.isRun) {
            logger.info("Start list file " + totalFile);
            if (totalFile <= 0) {
                //Get list file
                ConfigDaoImpl.getInstance().loadParams();
                logger.info("Getting list file...");
                List<ConfigBo> lstCfgDrbBo = configDaoImpl.getConfigBo();
                size = lstCfgDrbBo.size();
                logger.info("Configdao|" + lstCfgDrbBo);
                if (size > Config.numberLUp) {
                    size = Config.numberLUp;
                }
                for (int index = 0; index < size; index++) {
                    ConfigBo configBo = lstCfgDrbBo.get(index);
                    logger.info("Link "+ index + "|" + configBo.getPath());
                    queueUpload.add(configBo.getPath());
                    incrementFile();
                }
            }
            logger.info("End list file|" + queueUpload);
        }
        this.buStartTime = null;
        Utility.sleep(Config.timeout);
    }

    public ConfigDaoImpl getConfigDaoImpl() {
        return configDaoImpl;
    }

    public static void incrementFile() {
        synchronized (objSync) {
            totalFile++;
        }
    }

    public static void decrementFile() {
        synchronized (objSync) {
            totalFile--;
        }
    }
}
