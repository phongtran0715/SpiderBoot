/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itc.edu.ulvideo.ulsysdao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
/**
 *
 * @author hanht
 */
public class SystemRowMapper implements RowMapper<SystemBo> {

    /**
     *
     * @param resultSet
     * @param arg1
     * @return
     * @throws SQLException
     */
    @Override
    public SystemBo mapRow(ResultSet resultSet, int arg1) throws SQLException {
        SystemBo sysParams = new SystemBo();
        sysParams.setID(resultSet.getInt("id"));
        sysParams.setNumberLDown(resultSet.getInt("nldown"));
        sysParams.setNumberLUp(resultSet.getInt("nlup"));
        sysParams.setNumberThread(resultSet.getInt("thread"));
        sysParams.setNumberCore(resultSet.getInt("core"));
        sysParams.setPathDown(resultSet.getString("dirdown"));
        sysParams.setPathUp(resultSet.getString("dirup"));
        sysParams.setPathTemp(resultSet.getString("dirdowntemp"));
        sysParams.setNumberRetry(resultSet.getInt("retry"));
        sysParams.setTimeout(resultSet.getInt("timeout"));
        sysParams.setVideoQuality(resultSet.getString("qlity"));
        sysParams.setVideoType(resultSet.getString("vtype"));
        return sysParams;
    }
}