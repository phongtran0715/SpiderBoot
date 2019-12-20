/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spider.helper;

import spider.helper.Utility;

public class Constant {
	static Utility util = new Utility();
    private static final String prefixOS = util.prefixOS();
    public static final String FILE_CONFIG = System.getProperty("user.dir") + prefixOS + "etc" + prefixOS + "app.properties";
    //global configuration parameter
    public static final String CORBA_REF	 		= "CorbaRef";
    
    //download configuration parameter
    public static final String DOWNLOAD_DEPLOY 				= "DownloadOn";
    public static final String DOWNLOAD_API_KEY 			= "APIKey";
    public static final String DOWNLOAD_CLIENT_SCERET 		= "ClientSecret";
    public static final String DOWNLOAD_APP_ID 				= "DownloadAppId";
    public static final String DOWNLOAD_OUTPUT_VIDEO_PATH 	= "DownloadOutputPath";
    public static final String DOWNLOAD_VIDEO_FORMAT	 	= "DownloadVideoFormat";
    public static final String DOWNLOAD_MAX_RESULT	 		= "MaxResult";
    
    //render configuration parameter
    public static final String RENDER_DEPLOY 				= "RenderOn";
    public static final String RENDER_APP_ID				= "RenderAppId";
    public static final String RENDER_OUTPUT_VIDEO_PATH		= "RenderOutputPath";
    public static final String RENDER_VIDEO_FORMAT			= "RenderVideoFormat";
    public static final String RENDER_IP					= "RenderIp";
    
    //upload configuration parameter
    public static final String UPLOAD_DEPLOY 				= "UploadOn";
    public static final String UPLOAD_APP_ID				= "UploadAppId";
    public static final String UPLOAD_DELAY_TIME			= "UploadDelayTime";
    public static final String UPLOAD_CLUSTER_IP			= "UploadIp";
}
