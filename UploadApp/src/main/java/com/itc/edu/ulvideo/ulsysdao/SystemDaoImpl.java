/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itc.edu.ulvideo.ulsysdao;
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
public class SystemDaoImpl implements SystemDao {
    
    private static SystemDao instance;
    private DataSource dataSource;
    private static final Logger logger = Logger.getLogger(SystemDao.class);
    private static List<SystemBo> lstCfgDrbBo;
    
    public List<SystemBo> getConfigBo () {
        return lstCfgDrbBo;
    }
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    
    public static SystemDao getInstance() {
        if (instance == null) {
            ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
            instance = (SystemDao) context.getBean("sysParams");
            //instance.loadParams();
            //logger.info("Inited link download videos| size: " + lstCfgDrbBo.size() + "|arrays:" + instance);
            logger.info("Inited link download videos|" + instance);
        }
        return instance;
    }

    @Override
    public void loadSysParams() {
        long startTime = System.currentTimeMillis();
        List<SystemBo> lstParams = null;
        lstCfgDrbBo = new ArrayList<>();
        try {
            JdbcTemplate selectLinkDown = getJdbcTemplate(dataSource);
            lstParams = selectLinkDown.query("SELECT * FROM UTBLCFG",
                    new Object[]{},
                    new SystemRowMapper());
            for (Iterator<SystemBo> it = lstParams.iterator(); it.hasNext();) {
                SystemBo configSysParamsBo = it.next();
                lstCfgDrbBo.add(configSysParamsBo);
            }
        } catch (Exception e) {
            logger.error("ERR_DAO_LOAD_SYSPARAMS|" + e.getMessage(), e);
        }
        logger.info("load sysParams| " + lstParams
                + "|time: " + (System.currentTimeMillis() - startTime));
    }
    
    @Override
    public String toString() {
        return "ConfigSysParamsDao{ hmSysParam=" + lstCfgDrbBo + '}';
    }
}
