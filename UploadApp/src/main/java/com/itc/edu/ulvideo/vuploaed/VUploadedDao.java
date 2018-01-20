/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itc.edu.ulvideo.vuploaed;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author hanht
 */
public interface VUploadedDao {
     public JdbcTemplate getJdbcTemplate(DataSource dataSource);
     public void updateStat (String link, boolean status);
     public void insertVideo (String link, String path, boolean status);
     public void insertVideo (String link, String path);
     @Override
     public String toString();
             
}
