/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itc.edu.ulvideo.ulsysdao;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author hanht
 */
public interface SystemDao {
     public JdbcTemplate getJdbcTemplate(DataSource dataSource);
     public void loadSysParams();
     @Override
     public String toString();
             
}
