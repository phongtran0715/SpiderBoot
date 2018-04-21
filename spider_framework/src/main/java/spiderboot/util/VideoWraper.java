package spiderboot.util;

public class VideoWraper {

    public int recordId = 0;
    public String vId = "";
    public String title = "";
    public String tag = "";
    public String description = "";
    public String thumbnail = "";
    public String vLocation = "";
    
    public VideoWraper(String vId, String title, String tag, String description,
    		String thumbnail, String vLocation ) {
    	this.vId = vId;
    	this.title = title;
    	this.tag = tag;
    	this.description = description;
    	this.thumbnail = thumbnail;
    	this.vLocation = vLocation;
	}
}
