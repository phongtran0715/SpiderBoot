package spider.video;

public class VideoWraper {
	public String vId = "";
	public String title = "";
	public String tag = "";
	public String description = "";
	public String thumbnail = "";
	public String vLocation = "";
	
	public VideoWraper() {
		//constructor
	}
	public void dummyData() {
		System.out.println("Video ID = " + vId);
		System.out.println("Title = " + title);
		System.out.println("Tag = " + tag);
		System.out.println("Description = " + description);
		System.out.println("Thumbnail = " + thumbnail);
		System.out.println("Video location = " + vLocation);
	}
}
