/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itc.edu.ulvideo.util;

import java.util.List;
import com.itc.edu.ulvideo.ulsysdao.SystemDaoImpl;
import com.itc.edu.ulvideo.ulsysdao.SystemBo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author hanht
 */
public class Config {

    private static Logger logger = Logger.getLogger(Config.class);
    public static int numberLDown;
    public static int numberLUp;
    public static int numberThread;
    public static int numberCore;

    public static String folderDownload;
    public static String folderTemp;
    public static String folderUpload;

    public static int downloadRetry;
    public static int uploadRetry;
    public static long timeout;
    public static long timeoutQueue;

    public static String videoQuality;
    public static String videoType;
    public static String ftpConfig;
    public static String videoFormat;
    public static String authLink;
    public static String clientSecret;

    public static boolean loadConfig() {
        File fileConfig = new File(Constant.FILE_CONFIG);
        Properties prop;
        FileInputStream fileReader = null;

        Integer intTemp;
        String strTemp;
        if (!fileConfig.exists()) {
            logger.error("File config not found");
            return false;
        }
        prop = new Properties();
        try {
            fileReader = new FileInputStream(fileConfig);
            prop.load(fileReader);

            //load file ftpConfig
            strTemp = prop.getProperty(Constant.VIDEO_FILE_FORMAT, "").trim();
            videoFormat = strTemp;
            if (strTemp.isEmpty()) {
                logger.warn(Constant.VIDEO_FILE_FORMAT + " is empty. Set default is all format");
                videoFormat = "video/*";
            }
            logger.info(Constant.VIDEO_FILE_FORMAT + "|" + videoFormat);
            strTemp = prop.getProperty(Constant.AUTH_LINK, "").trim();
            authLink = strTemp;
            if (strTemp.isEmpty()) {
                logger.warn(Constant.AUTH_LINK + " is empty. Set default");
                authLink = "https://www.googleapis.com/auth/youtube.upload";
            }
            logger.info(Constant.AUTH_LINK + "|" + authLink);
            strTemp = prop.getProperty(Constant.CLIENT_SCERET, "").trim();
            clientSecret = strTemp;
            if (strTemp.isEmpty()) {
                logger.warn(Constant.CLIENT_SCERET + " is empty. Set defaul");
                clientSecret = "../etc/client_secrets.json";
            }
            logger.info(Constant.CLIENT_SCERET + "|" + clientSecret);
            strTemp = prop.getProperty(Constant.UPLOAD_RETRY, "").trim();
            if (strTemp.isEmpty()) {
                logger.warn(Constant.UPLOAD_RETRY + " is empty. Set default is 2");
                uploadRetry = 2;
            } else {
                try {
                    uploadRetry = Integer.parseInt(strTemp);
                    if (uploadRetry <= 0) {
                        logger.warn(Constant.UPLOAD_RETRY + " invalid. Set default is 2");
                        uploadRetry = 2;
                    }
                } catch (Exception e) {
                    logger.warn(Constant.UPLOAD_RETRY + " invalid. Set default is 2", e);
                    uploadRetry = 2;
                }
            }
            logger.info(Constant.UPLOAD_RETRY + "= " + uploadRetry);
            
        } catch (Exception e) {
            logger.error("Exception load file config ", e);
            return false;
        } finally {
            if (null != fileReader) {
                try {
                    fileReader.close();
                } catch (IOException ex) {
                    logger.error("Error close file reader", ex);
                }
            }
        }

        SystemDaoImpl systemDao = new SystemDaoImpl();

        SystemDaoImpl.getInstance()
                .loadSysParams();
        List<SystemBo> lstCfgDrbBo = systemDao.getConfigBo();

        logger.info(
                "Size config:" + lstCfgDrbBo.size());
        if (lstCfgDrbBo.size()
                <= 0) {
            logger.error("ERR_DAO_LOAD_SYSPARAM_DOWNLOAD|" + "No system params set");
            return false;
        }

        if (lstCfgDrbBo.size()
                > 1) {
            logger.error("ERR_DAO_LOAD_SYSPARAM_DOWNLOAD|" + "too much record system params set");
            return false;
        }
        for (int index = 0;
                index < lstCfgDrbBo.size();
                index++) {
            SystemBo configSysParamsBo = lstCfgDrbBo.get(index);
            downloadRetry = configSysParamsBo.getNumberRetry();
            if (downloadRetry < 0) {
                logger.warn("DOWNLOAD_RETRY" + " is empty. Set deafault 0");
                downloadRetry = 0;
            }
            logger.info("DOWNLOAD_RETRY" + ": " + downloadRetry);

            numberLDown = configSysParamsBo.getNumberLDown();
            if (numberLDown < 0) {
                logger.warn("NUMBER_LINK_DOWNLOAD" + " is empty. Set deafault 1");
                numberLDown = 1;
            }
            logger.info("NUMBER_LINK_DOWNLOAD" + ": " + numberLDown);

            numberLUp = configSysParamsBo.getNumberLUp();
            if (numberLUp < 0) {
                logger.warn("NUMBER_LINK_UPLOAD" + " is empty. Set deafault 1");
                numberLUp = 1;
            }
            logger.info("NUMBER_LINK_UPLOAD" + ": " + numberLUp);
            numberThread = configSysParamsBo.getNumberThread();
            if (numberThread < 0) {
                logger.warn("NUMBER_THREAD" + " is empty. Set deafault 1");
                numberThread = 1;
            }
            logger.info("DOWNLOAD_RETRY" + ": " + numberThread);

            folderTemp = configSysParamsBo.getPathTemp().trim();
            if (folderTemp.isEmpty()) {
                logger.warn("FOLDER_DOWNLOAD_TEMP" + " is empty. Set default ../FTPDownload/temp");
                folderTemp = "../FTPDownload/temp";
            }
            Utility.createFolder(folderTemp);
            logger.info("FOLDER_DOWNLOAD_TEMP" + ": " + folderTemp);
            folderDownload = configSysParamsBo.getPathDown().trim();
            if (folderDownload.isEmpty()) {
                logger.warn("FOLDER_DOWNLOAD" + " is empty. Set default ../FTPDownload");
                folderDownload = "../FTPDownload";
            }
            Utility.createFolder(folderDownload);
            logger.info("FOLDER_DOWNLOAD" + ": " + folderDownload);
            folderUpload = configSysParamsBo.getPathUp().trim();
            if (folderUpload.isEmpty()) {
                logger.warn("FOLDER_UPLOAD" + " is empty. Set default ../FTPUpload");
                folderUpload = "../FTPDownload";
            }
            Utility.createFolder(folderUpload);
            logger.info("FOLDER_UPLOAD" + ": " + folderUpload);

            numberCore = configSysParamsBo.getNumberCore();
            if (numberCore <= 0) {
                logger.warn("NUMBER_PROCESS_DOWNLOAD" + " invalid. Set default is number core cpu");
                numberCore = Utility.getCoreCPU();
            }
            logger.info("NUMBER_PROCESS_DOWNLOAD" + "= " + numberCore);
            timeout = configSysParamsBo.getTimeout();
            if (timeout <= 0) {
                logger.warn("TIMEOUT" + " invalid. Set default is 1000");
                timeout = 1000;
            }
            logger.info("TIMEOUT" + "= " + timeout);

            videoQuality = configSysParamsBo.getVideoQuality().trim();
            if (videoQuality.isEmpty()) {
                logger.warn("VIDEO_QUALITY" + " invalid. Set default is pMaxQuality");
                videoQuality = "pMaxQuality";
            }
            logger.info("VIDEO_QUALITY" + "= " + videoQuality);

            videoType = configSysParamsBo.getVideoType().trim();
            if (videoType.isEmpty()) {
                logger.warn("VIDEO_TYPE" + " invalid. Set default is MP4");
                videoType = "MP4";
            }
            logger.info("VIDEO_TYPE" + "= " + videoType);
            timeoutQueue = 1000;
            logger.info("timeoutQueue" + "= " + timeoutQueue);
        }

        return true;
    }
}
