/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itc.edu.ulvideo.link;
import java.util.List;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.ArrayList;
import java.util.Iterator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 *
 * @author hanht
 */
public class ConfigDaoImpl implements ConfigDao {
    
    private static ConfigDao instance;
    private DataSource dataSource;
    private static final Logger logger = Logger.getLogger(ConfigDao.class);
    private static List<ConfigBo> lstCfgDrbBo;
    
    public List<ConfigBo> getConfigBo () {
        return lstCfgDrbBo;
    }
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    
    public static ConfigDao getInstance() {
        if (instance == null) {
            ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
            instance = (ConfigDao) context.getBean("springVideo");
            //instance.loadParams();
            //logger.info("Inited link download videos| size: " + lstCfgDrbBo.size() + "|arrays:" + instance);
            logger.info("Inited link upload videos|" + instance);
        }
        return instance;
    }

    @Override
    public void loadParams() {
        long startTime = System.currentTimeMillis();
        List<ConfigBo> lstParams = null;
        lstCfgDrbBo = new ArrayList<>();
        try {
            JdbcTemplate selectLinkDown = getJdbcTemplate(dataSource);
            lstParams = selectLinkDown.query("SELECT * FROM VIDEODOWNLOAD WHERE STATUS <> 1",
                    new Object[]{},
                    new ConfigRowMapper());
            for (Iterator<ConfigBo> it = lstParams.iterator(); it.hasNext();) {
                ConfigBo configSysParamsBo = it.next();
                lstCfgDrbBo.add(configSysParamsBo);
            }
        } catch (Exception e) {
            logger.error("ERR_DAO_LOAD_LINKUPLOAD|" + e.getMessage(), e);
        }
        logger.info("LOAD VIDEODOWNLOAD| " + lstParams
                + "|TIME: " + (System.currentTimeMillis() - startTime));
    }

    @Override
    public void updateStat (String path, int status) {
        String SQL = "UPDATE VIDEODOWNLOAD SET STATUS = ? WHERE PATH = ?";
        try {
            JdbcTemplate updateStat = getJdbcTemplate(dataSource);
            updateStat.update(SQL, status, path);
            logger.info("Update status file upload VIDEODOWNLOAD|FILE = " + path + "|STATUS = " + status);
        }
        catch (Exception e) {
            logger.error("ERR_DAO_UPDATE_VIDEODOWNLOAD_STATUS|" + e.getMessage(), e);
        }
    }
    
    @Override
    public String toString() {
        return "ConfigSysParamsDao{ hmSysParam=" + lstCfgDrbBo + '}';
    }
}
