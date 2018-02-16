/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spiderboot.util;

import spiderboot.util.Utility;

/*------------------------------------------------------------------------------
** History
21-01-2018, [CR-001] phapnd
    Khoi tao class luu thong tin mac dinh

21-01-2018, [CR-002] phapnd
    Cap nhat them thong tin videoFolderBase

21-01-2018, [CR-003] phapnd
    Cap nhat them thong tin load file app.properties tu dong, ko phan biet OS

25-01-2018, [CR-007] phapnd
    Cap nhat them thong tin load file youtube.properties

*/

public class Constant {
	static Utility util = new Utility();
    private static final String prefixOS = util.prefixOS();
    public static final String FILE_CONFIG = System.getProperty("user.dir") + prefixOS + "etc" + prefixOS + "app.properties";
    //config system
    public static final String VIDEO_FILE_FORMAT = "VIDEO_FILE_FORMAT";
    public static final String AUTH_LINK = "AUTH_LINK";
    public static final String CLIENT_SCERET = "CLIENT_SCERET";
    public static final String NUMBER_OF_VIDEOS_RETURNED = "NUMBER_OF_VIDEOS_RETURNED";
    public static final String YOUTUBE_API_KEY = "youtube.apikey";
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
