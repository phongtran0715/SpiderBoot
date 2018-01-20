/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itc.edu.ulvideo.link;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author hanht
 */
public interface ConfigDao {
     public JdbcTemplate getJdbcTemplate(DataSource dataSource);
     public void loadParams();
     public void updateStat (String link, int status);
     @Override
     public String toString();
             
}
