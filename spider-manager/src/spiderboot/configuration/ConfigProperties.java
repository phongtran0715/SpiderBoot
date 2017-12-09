package spiderboot.configuration;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class ConfigProperties {
	static ConfigProperties instance;
	Properties prop = null;
	InputStream input = null;
	OutputStream output = null;

	public static ConfigProperties getInstance() {
		if(instance == null){
			instance = new ConfigProperties();
		}
		return instance;
	}

	public boolean loadConfigFile() {
		boolean isOk = false;
		InputStream inputStream;
		try {
			inputStream = this.getClass().getClassLoader().getResourceAsStream("config.properties");
			prop = new Properties();
			prop.load(inputStream);
			isOk = true;
			System.out.println("Load configuration file successful!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
		return isOk;
	}

	public void writeProperties(String key, String value) {
		try {
			String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
			//System.out.println("config path : " + path);
			File configFile = new File(path + "config.properties");
			output = new FileOutputStream(configFile);
			prop.setProperty(key, value);
			// save properties to project root folder
			prop.store(output, null);
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	public String getProperties(String key, String defaultValue) {
		String value = "";
		value = prop.getProperty(key, defaultValue);
		return value;
	}
	
	public void closeConfigFile() {
		//close input stream
		if(input != null){
			try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//close output stream
		if(output != null){
			try {
				output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
