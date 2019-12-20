package spider.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import spider.helper.Constant;
import spider.helper.Utility;

public class SpiderBootProperty {

	Utility util; 
	String configFile;
	//Global config
	public String videoFormat;
	public String corbaRef;

	//Download config
	public boolean downloadDeploy;
	public String apiKey;
	public String clientSecret;
	public String dAppId;
	public String dOutputPath;
	public int maxResult;
	
	//Render config
	public boolean renderDeploy;
	public String rAppId;
	public String rOutPath;
	public String rIp;
	
	//Upload config
	public boolean uploadDeploy;
	public String uAppId;
	public int delayTime;
	public String uIp;

	public SpiderBootProperty (String configFile)
	{
		util = new Utility();
		this.configFile = configFile;
		boolean isSuccess = loadConfig();
		if(isSuccess == false)
		{
			System.out.println("Load download config file FALSE");
		}
	}

	public boolean loadConfig() {
		boolean result;
		File fileConfig = new File(this.configFile);
		Properties prop;
		FileInputStream fileReader = null;
		if (!fileConfig.exists()) {
			result = false;
		}
		prop = new Properties();
		try {
			fileReader = new FileInputStream(fileConfig);
			prop.load(fileReader);

			//load global config value
			corbaRef = prop.getProperty(Constant.CORBA_REF, "").trim();
			videoFormat = prop.getProperty(Constant.DOWNLOAD_VIDEO_FORMAT, "mp4").trim();
			
			//load download config value
			downloadDeploy = Boolean.parseBoolean(prop.getProperty(Constant.DOWNLOAD_DEPLOY, "false").trim());
			apiKey = prop.getProperty(Constant.DOWNLOAD_API_KEY, "").trim();
			clientSecret = prop.getProperty(Constant.DOWNLOAD_CLIENT_SCERET, "").trim();
			dAppId = prop.getProperty(Constant.DOWNLOAD_APP_ID, "").trim();
			dOutputPath = prop.getProperty(Constant.DOWNLOAD_OUTPUT_VIDEO_PATH, "").trim();
			maxResult = Integer.parseInt(prop.getProperty(Constant.DOWNLOAD_MAX_RESULT, "25").trim());
			
			//load render config value
			renderDeploy = Boolean.parseBoolean(prop.getProperty(Constant.RENDER_DEPLOY, "false").trim());
			videoFormat = prop.getProperty(Constant.RENDER_VIDEO_FORMAT, "mp4").trim();
			rAppId = prop.getProperty(Constant.RENDER_APP_ID, "").trim();
			rIp = prop.getProperty(Constant.RENDER_IP, "127.0.0.1").trim();
			
			//load upload config value
			uploadDeploy = Boolean.parseBoolean(prop.getProperty(Constant.UPLOAD_DEPLOY, "false").trim());
			uAppId = prop.getProperty(Constant.UPLOAD_APP_ID, "").trim();
			delayTime = Integer.parseInt(prop.getProperty(Constant.UPLOAD_DELAY_TIME.trim()));
			uIp = prop.getProperty(Constant.UPLOAD_CLUSTER_IP, "127.0.0.1").trim();
			result = true;
		} catch (Exception e) {
			result = false;
		} finally {
			if (null != fileReader) {
				try {
					fileReader.close();
				} catch (IOException ex) {
					System.out.println(ex.toString());
				}
			}
		}
		return result;
	}
}
