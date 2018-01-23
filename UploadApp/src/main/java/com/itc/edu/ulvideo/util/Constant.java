/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itc.edu.ulvideo.util;

import static com.itc.edu.ulvideo.util.Utility.prefixOS;

/*------------------------------------------------------------------------------
** History

*/

public class Constant {
    private static final String prefixOS = prefixOS();
    public static final String FILE_CONFIG = System.getProperty("user.dir") + prefixOS + "etc" + prefixOS + "app.properties";
    //config system
    public static final String VIDEO_FILE_FORMAT = "VIDEO_FILE_FORMAT";
    public static final String AUTH_LINK = "AUTH_LINK";
    public static final String CLIENT_SCERET = "CLIENT_SCERET";
    public static final String NUMBER_OF_VIDEOS_RETURNED = "NUMBER_OF_VIDEOS_RETURNED";
    
    public static final String VIDEO_FOLDER = "VIDEO_FOLDER";

    // string ket noi DB
    public static final String DBSERVER = "DBSERVER";
    public static final String DBNAME = "DBNAME";
    public static final String USERNAME= "USERNAME";
    public static final String PASSWD = "****";
    //ma loi KET NOI DB
    public static final Integer SUCCESS = 0;
    public static final Integer ERR_DRIVER = 1;
    public static final Integer ERR_OTHER = 2;
    
}