/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spiderboot.util;

import spiderboot.util.Utility;

public class Constant {
	static Utility util = new Utility();
    private static final String prefixOS = util.prefixOS();
    public static final String FILE_CONFIG = System.getProperty("user.dir") + prefixOS + "etc" + prefixOS + "app.properties";
    //download configuration parameter
    public static final String DOWNLOAD_API_KEY 			= "APIKey";
    public static final String DOWNLOAD_CLIENT_SCERET 		= "ClientSecret";
    public static final String DOWNLOAD_APP_ID 				= "AppId";
    public static final String DOWNLOAD_OUTPUT_VIDEO_PATH 	= "OutputVideo";
    public static final String DOWNLOAD_VIDEO_FORMAT	 	= "VideoFormat";
    public static final String DOWNLOAD_MAX_RESULT	 		= "MaxResult";
    public static final String DOWNLOAD_CORBA_REF	 		= "CorbaRef";
    
    //render configuration parameter
    public static final String RENDER_APP_ID				= "AppId";
    public static final String RENDER_OUTPUT_VIDEO_PATH		= "OutputVideo";
    public static final String RENDER_VIDEO_FORMAT			= "VideoFormat";
    public static final String RENDER_CORBA_REF	 			= "CorbaRef";
    
    //upload configuration parameter
    public static final String UPLOAD_APP_ID				= "AppId";
    public static final String UPLOAD_CORBA_REF				= "CorbaRef";
    public static final String UPLOAD_DELAY_TIME			= "UploadDelayTime";
}
