package spiderboot.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import spiderboot.util.Constant;
import spiderboot.util.Utility;

public class DownloadConfig {

	Utility util; 
	String configFile;
	public String apiKey;
	public String clientSecret;
	public String videoFormat;
	public String appId;
	public String outputVideo;
	public int maxResult;
	public String corbaRef;

	public DownloadConfig (String configFile)
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

			//load file configuration
			apiKey = prop.getProperty(Constant.DOWNLOAD_API_KEY, "").trim();
			clientSecret = prop.getProperty(Constant.DOWNLOAD_CLIENT_SCERET, "").trim();
			videoFormat = prop.getProperty(Constant.DOWNLOAD_VIDEO_FORMAT, "mp4").trim();
			appId = prop.getProperty(Constant.DOWNLOAD_APP_ID, "").trim();
			outputVideo = prop.getProperty(Constant.DOWNLOAD_OUTPUT_VIDEO_PATH, "").trim();
			maxResult = Integer.parseInt(prop.getProperty(Constant.DOWNLOAD_MAX_RESULT, "25").trim());
			corbaRef = prop.getProperty(Constant.DOWNLOAD_CORBA_REF, "").trim();
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
