package spiderboot.util;

import java.sql.Timestamp;

/*------------------------------------------------------------------------------
** History

*/

public class VideoWraper {

    //private static final Logger logger = Logger.getLogger(VideoWraper.class);
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

    public VideoWraper(int recordId, String vId, String title, String tag, String description,
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
    	/*
        logger.info("Record ID = " + recordId);
        logger.info("Video ID = " + vId);
        logger.info("Title = " + title);
        logger.info("Tag = " + tag);
        logger.info("Description = " + description);
        logger.info("Thumbnail = " + thumbnail);
        logger.info("Video location = " + vLocation);
        logger.info("Home Channel Id = " + homeChannelId);
        logger.info("Monitor channel Id = " + monitorChannelId);
        //logger.info("Download Date = " + downloadDate.toString());
         * 
         */
    }
}
