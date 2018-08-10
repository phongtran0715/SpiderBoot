package spiderboot.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import spiderboot.util.Constant;
import spiderboot.util.Utility;

public class RenderProperty {

	Utility util; 
	String configFile;
	public String appId;
	public String outputVideo;
	public String videoFormat;
	public String corbaRef;
	public String ip;

	public RenderProperty (String configFile)
	{
		util = new Utility();
		this.configFile = configFile;
		System.out.println("config file : " + this.configFile);
		boolean isSuccess = loadConfig();
		if(isSuccess == false)
		{
			System.out.println("Load render config file FALSE");
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
			videoFormat = prop.getProperty(Constant.RENDER_VIDEO_FORMAT, "mp4").trim();
			appId = prop.getProperty(Constant.RENDER_APP_ID, "").trim();
			outputVideo = prop.getProperty(Constant.RENDER_OUTPUT_VIDEO_PATH, "").trim();
			corbaRef = prop.getProperty(Constant.RENDER_CORBA_REF, "").trim();
			ip = prop.getProperty(Constant.RENDER_IP, "127.0.0.1").trim();
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
