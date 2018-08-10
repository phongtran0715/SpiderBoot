package spiderboot.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import spiderboot.util.Constant;
import spiderboot.util.Utility;

public class YoutubeAgentProperty {
	Utility util; 
	String configFile;
	public String apiKey;
	public String corbaRef;
	
	public YoutubeAgentProperty(String configFile) {
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
