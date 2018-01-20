/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itc.edu.ulvideo.vuploaed;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
/**
 *
 * @author hanht
 */
public class VUploadedRowMapper implements RowMapper<VUploadedBo> {

    /**
     *
     * @param resultSet
     * @param arg1
     * @return
     * @throws SQLException
     */
    @Override
    public VUploadedBo mapRow(ResultSet resultSet, int arg1) throws SQLException {
        VUploadedBo link = new VUploadedBo();
        link.setLink(resultSet.getString("link"));
        link.setPath(resultSet.getString("path"));
        link.setStatus(resultSet.getBoolean("status"));
        return link;
    }
}