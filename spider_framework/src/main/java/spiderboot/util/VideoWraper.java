package spiderboot.util;

public class VideoWraper {

    public int recordId = 0;
    public String videoId = "";
    public String title = "";
    public String tag = "";
    public String description = "";
    public String thumbnail = "";
    public String vDownloadPath = "";
    public String vRenderPath = "";
    public String cHomeId = "";
    public String cMonitorId = "";
    public int processStatus = 0;
    public int license = 0;
    
    public VideoWraper(String videoId, String title, String tag, String description,
    		String thumbnail, String vDownloadPath, String vRenderPath, String cHomeId, 
    		String cMonitorId, int processStatus, int license) {
    	this.videoId = videoId;
    	this.title = title;
    	this.tag = tag;
    	this.description = description;
    	this.thumbnail = thumbnail;
    	this.vDownloadPath = vDownloadPath;
    	this.vRenderPath = vRenderPath;
    	this.cHomeId = cHomeId;
    	this.cMonitorId = cMonitorId;
    	this.processStatus = processStatus;
    	this.license = license;
	}
}
