package spiderboot.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import spiderboot.util.Constant;
import spiderboot.util.Utility;

public class UploadConfig {

	Utility util; 
	String configFile;
	
	public String appId;
	public int delayTime;
	public String corbaRef;
	public String ip;

	public UploadConfig (String configFile)
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
			appId = prop.getProperty(Constant.UPLOAD_APP_ID, "").trim();
			delayTime = Integer.parseInt(prop.getProperty(Constant.UPLOAD_DELAY_TIME.trim()));
			corbaRef = prop.getProperty(Constant.UPLOAD_CORBA_REF, "").trim();
			ip = prop.getProperty(Constant.UPLOAD_CLUSTER_IP, "127.0.0.1").trim();
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
