/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itc.edu.ulvideo.vuploaed;

import java.util.List;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author hanht
 */
public class VUploadedDaoImpl implements VUploadedDao {

    private static VUploadedDao instancevideo;
    private DataSource dataSourcevideo;
    private static final Logger logger = Logger.getLogger(VUploadedDao.class);
    private static List<VUploadedBo> lstCfgDrbBovideo;

    public List<VUploadedBo> getConfigBo() {
        return lstCfgDrbBovideo;
    }

    public void setDataSource(DataSource dataSourcevideo) {
        this.dataSourcevideo = dataSourcevideo;
    }

    @Override
    public JdbcTemplate getJdbcTemplate(DataSource dataSourcevideo) {
        return new JdbcTemplate(dataSourcevideo);
    }

    public static VUploadedDao getInstance() {
        if (instancevideo == null) {
            ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
            instancevideo = (VUploadedDao) context.getBean("springVideoUploaded");
            //instancevideo.loadParams();
            //logger.info("Inited link download videos| size: " + lstCfgDrbBovideo.size() + "|arrays:" + instancevideo);
            logger.info("Inited file upload videos|" + instancevideo);
        }
        return instancevideo;
    }

    @Override
    public void updateStat(String link, boolean status) {
        String SQL = "UPDATE VIDEODOWNLOAD SET STATUS = ? WHERE LINK = ?";
        try {
            JdbcTemplate updateStat = getJdbcTemplate(dataSourcevideo);
            updateStat.update(SQL, status, link);
            logger.info("Update status video downloaded VIDEODOWNLOAD|LINK = " + link + "|STATUS = " + status);
        } catch (Exception e) {
            logger.error("ERR_DAO_UPDATE_STATUS|" + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        return "VideoDownloaedParamsDao{ hmSysParam=" + lstCfgDrbBovideo + '}';
    }

    @Override
    public void insertVideo(String link, String path, boolean status) {
        String SQL = "INSERT INTO VIDEODOWNLOAD(LINK,PATH,STATUS) VALUES(?,?,?)";
        try {
            JdbcTemplate insertVideo = getJdbcTemplate(dataSourcevideo);
            insertVideo.update(SQL, new Object[] { link,path,status});
            logger.info("Inserted video downloaded VIDEODOWNLOAD|LINK = " + link + "|FILE =" + path +"|STATUS = " + status);
        } catch (Exception e) {
            logger.error("ERR_DAO_INSERT_VIDEO_DOWNLOADED|" + e.getMessage(), e);
        }
    }

    @Override
    public void insertVideo(String link, String path) {
        String SQL = "INSERT INTO VIDEODOWNLOAD(LINK,PATH,STATUS) VALUES(?,?)";
        try {
            JdbcTemplate insertVideo = getJdbcTemplate(dataSourcevideo);
            insertVideo.update(SQL, new Object[] { link,path});
            logger.info("Inserted video downloaded VIDEODOWNLOAD|LINK = " + link + "|FILE =" + path);
        } catch (Exception e) {
            logger.error("ERR_DAO_INSERT_VIDEO_DOWNLOADED|" + e.getMessage(), e);
        }
    }
}
