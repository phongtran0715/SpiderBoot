//phapnd comment here
package spider.video;

import java.sql.Timestamp;

public class VideoWraper {
	public int recordId = 0;
	public String vId = "";
	public String title = "";
	public String tag = "";
	public String description = "";
	public String thumbnail = "";
	public String vLocation = "";
	public String homeChannelId = "";
	public String monitorChannelId = "";
	Timestamp downloadDate = null;
	
	
	public VideoWraper() {
		//constructor
	}
	
	public  VideoWraper(int recordId, String vId, String title, String tag, String description,
						String thumbnail, String vLocation, String homeChannelId, String monitorChannelId, Timestamp downloadDate) {
		this.recordId = recordId;
		this.vId = vId;
		this.title = title;
		this.tag = tag;
		this.description = description;
		this.thumbnail = thumbnail;
		this.vLocation = vLocation;
		this.homeChannelId = homeChannelId;
		this.monitorChannelId = monitorChannelId;
		this.downloadDate = downloadDate;
	}
	public void dummyData() {
		System.out.println("Record ID = " + recordId);
		System.out.println("Video ID = " + vId);
		System.out.println("Title = " + title);
		System.out.println("Tag = " + tag);
		System.out.println("Description = " + description);
		System.out.println("Thumbnail = " + thumbnail);
		System.out.println("Video location = " + vLocation);
		System.out.println("Home Channel Id = " + homeChannelId);
		System.out.println("Monitor channel Id = " + monitorChannelId);
		//System.out.println("Download Date = " + downloadDate.toString());
	}
}
