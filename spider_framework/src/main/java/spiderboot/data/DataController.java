package spiderboot.data;

import spiderboot.configuration.DownloadConfig;

public class DataController {

	// static variable single_instance of type Singleton
	private static DataController single_instance = null;

	public DownloadConfig downloadConfig;
	// private constructor restricted to this class itself
	private DataController()
	{
	}

	// static method to create instance of Singleton class
	public static DataController getInstance()
	{
		if (single_instance == null)
			single_instance = new DataController();

		return single_instance;
	}
	
	public void setDownloadConfigObj(DownloadConfig downloadConfig)
	{
		this.downloadConfig = downloadConfig;
	}
}
