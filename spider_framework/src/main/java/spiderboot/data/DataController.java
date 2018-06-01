package spiderboot.data;

import spiderboot.configuration.DownloadConfig;
import spiderboot.configuration.RenderConfig;
import spiderboot.configuration.UploadConfig;

public class DataController {

	// static variable single_instance of type Singleton
	private static DataController single_instance = null;

	public DownloadConfig downloadConfig;
	public RenderConfig renderConfig;
	public UploadConfig uploadConfig;

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
	
	public void setRenderConfigObj(RenderConfig renderConfig)
	{
		this.renderConfig = renderConfig;
	}
	
	public void setUploadConfigObj(UploadConfig uploadConfig)
	{
		this.uploadConfig = uploadConfig;
	}
}
