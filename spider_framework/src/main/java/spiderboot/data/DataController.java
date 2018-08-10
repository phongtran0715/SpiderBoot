package spiderboot.data;

import spiderboot.configuration.DownloadProperty;
import spiderboot.configuration.RenderProperty;
import spiderboot.configuration.UploadProperty;

public class DataController {

	// static variable single_instance of type Singleton
	private static DataController single_instance = null;
	public final int TYPE_CLUSTER_DOWNLOAD = 1;
	public final int TYPE_CLUSTER_RENDER = 2;
	public final int TYPE_CLUSTER_UPLOAD = 3;
	public DownloadProperty downloadConfig;
	public RenderProperty renderConfig;
	public UploadProperty uploadConfig;

	// static method to create instance of Singleton class
	public static DataController getInstance()
	{
		if (single_instance == null)
			single_instance = new DataController();

		return single_instance;
	}
	
	public void setDownloadConfigObj(DownloadProperty downloadConfig)
	{
		this.downloadConfig = downloadConfig;
	}
	
	public void setRenderConfigObj(RenderProperty renderConfig)
	{
		this.renderConfig = renderConfig;
	}
	
	public void setUploadConfigObj(UploadProperty uploadConfig)
	{
		this.uploadConfig = uploadConfig;
	}
}
