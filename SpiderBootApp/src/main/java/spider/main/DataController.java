package spider.main;

import spider.config.SpiderBootProperty;

public class DataController {

	// static variable single_instance of type Singleton
	private static DataController single_instance = null;
	public final int TYPE_CLUSTER_DOWNLOAD = 1;
	public final int TYPE_CLUSTER_RENDER = 2;
	public final int TYPE_CLUSTER_UPLOAD = 3;
	public SpiderBootProperty spiderConfig;

	// static method to create instance of Singleton class
	public static DataController getInstance()
	{
		if (single_instance == null)
			single_instance = new DataController();

		return single_instance;
	}
	
	public void setDownloadConfigObj(SpiderBootProperty spiderConfig)
	{
		this.spiderConfig = spiderConfig;
	}
}
