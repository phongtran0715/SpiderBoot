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
    public int mappingId = 0;
    public int mappingType = 0;
    public int processStatus = 0;
    public int license = 0;
    
    public VideoWraper(String videoId, String title, String tag, String description,
    		String thumbnail, String vDownloadPath, String vRenderPath, int mappingId,
    		int mappingType, int processStatus, int license) {
    	this.videoId = videoId;
    	this.title = title;
    	this.tag = tag;
    	this.description = description;
    	this.thumbnail = thumbnail;
    	this.vDownloadPath = vDownloadPath;
    	this.vRenderPath = vRenderPath;
    	this.mappingId = mappingId;
    	this.mappingType = mappingType;
    	this.processStatus = processStatus;
    	this.license = license;
	}
}
