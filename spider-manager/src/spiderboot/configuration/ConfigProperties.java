package spiderboot.configuration;

import java.io.File;
import java.io.FileInputStream;
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
	File configFile = null;

	private ConfigProperties() {
		if(configFile == null) {
			try {
				String path = new File(".").getCanonicalPath() + "\\config\\config.properties";
				configFile = new File(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error! Can not load config file path.");
				e.printStackTrace();
			}
		}
	}
	
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
			inputStream = new FileInputStream(configFile);
			prop = new Properties();
			prop.load(inputStream);
			isOk = true;
			System.out.println("Load configuration file successful!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Load config file false!!!");
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
		System.out.println("Load config file false!!!");
		return isOk;
	}

	public void writeProperties(String key, String value) {
		try {
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
