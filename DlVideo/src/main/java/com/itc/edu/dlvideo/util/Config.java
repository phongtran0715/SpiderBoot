/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itc.edu.dlvideo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import spiderboot.util.Utility;

/*------------------------------------------------------------------------------
** History
21-01-2018, [CR-001] phapnd
    Khoi tao class load file cau hinh

21-01-2018, [CR-002] phapnd
    Cap nhat cau hinh folde luu video download $HOME/VIDEO_FOLDER

25-01-2018, [CR-007] phapnd
    Cap nhat cau hinh youtube.key


 */
public class Config {

    private static Logger logger = Logger.getLogger(Config.class);
    static Utility util = new Utility();

    public static String dbServer;
    public static String dbName;
    public static String dbUserName;
    public static String dbPassword;

    public static String youtubeKey="AIzaSyDGGgByVC_2oIOaqznVM03GSpIfb5Ghyuc";
    public static String videoFolder;
    public static String videoFormat;
    public static String authLink;
    public static String clientSecret;
    public static Integer noVideoReturn;

    public static boolean loadConfig() {
    	//Get file from resources folder
    	ClassLoader classLoader = Config.class.getClassLoader();
    	//TODO: load config file from resource
    	//File fileConfig = new File(classLoader.getResource("app.properties").getFile());
    	File fileConfig = new File("/etc/spider_config/app.properties");
    	
        //File fileConfig = new File(Constant.FILE_CONFIG);
        Properties prop;
        FileInputStream fileReader = null;
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
            strTemp = prop.getProperty(Constant.VIDEO_FOLDER, "").trim();
            videoFolder = strTemp;
            if (strTemp.isEmpty()) {
                logger.warn(Constant.VIDEO_FOLDER + " is empty. Set default is video");
                videoFolder = "video";
            }
            logger.info(Constant.VIDEO_FOLDER + "|" + videoFolder);
            util.createFolder(System.getProperty("user.dir") + util.prefixOS() + videoFolder);
            logger.info("Create" + Constant.VIDEO_FOLDER + videoFolder);

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
            strTemp = prop.getProperty(Constant.NUMBER_OF_VIDEOS_RETURNED, "").trim();
            if (strTemp.isEmpty()) {
                logger.warn(Constant.NUMBER_OF_VIDEOS_RETURNED + " is empty. Set default is 2");
                noVideoReturn = 25;
            } else {
                try {
                    noVideoReturn = Integer.parseInt(strTemp);
                    if (noVideoReturn <= 0) {
                        logger.warn(Constant.NUMBER_OF_VIDEOS_RETURNED + " invalid. Set default is 2");
                        noVideoReturn = 25;
                    }
                } catch (Exception e) {
                    logger.warn(Constant.NUMBER_OF_VIDEOS_RETURNED + " invalid. Set default is 25", e);
                    noVideoReturn = 25;
                }
            }
            logger.info(Constant.NUMBER_OF_VIDEOS_RETURNED + "= " + noVideoReturn);

            strTemp = prop.getProperty(Constant.CLIENT_SCERET, "").trim();
            clientSecret = strTemp;
            if (strTemp.isEmpty()) {
                logger.warn(Constant.CLIENT_SCERET + " is empty. Set defaul");
                clientSecret = "../etc/client_secrets.json";
            }
            logger.info(Constant.CLIENT_SCERET + "|" + clientSecret);

            strTemp = prop.getProperty(Constant.DBSERVER, "").trim();
            dbServer = strTemp;
            if (strTemp.isEmpty()) {
                logger.error(Constant.DBSERVER + " is empty.");
                return false;
            } else {
                logger.info(Constant.DBSERVER + "|" + dbServer);
            }

            strTemp = prop.getProperty(Constant.DBNAME, "").trim();
            dbName = strTemp;
            if (strTemp.isEmpty()) {
                logger.error(Constant.DBNAME + " is empty.");
                return false;
            } else {
                logger.info(Constant.DBNAME + "|" + dbName);
            }

            strTemp = prop.getProperty(Constant.USERNAME, "").trim();
            dbUserName = strTemp;
            if (strTemp.isEmpty()) {
                logger.error(Constant.USERNAME + " is empty");
                return false;
            } else {
                logger.info(Constant.USERNAME + "|" + dbUserName);
            }

            strTemp = prop.getProperty(Constant.PASSWD, "").trim();
            dbPassword = strTemp;
            if (strTemp.isEmpty()) {
                logger.warn(Constant.PASSWD + " is empty");
            }

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
        return true;
    }

}
