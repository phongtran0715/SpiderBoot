package com.itc.edu.ulvideo.main;

import java.sql.ResultSet;
import java.sql.SQLException;
import spiderboot.database.MySqlAccess;
import spiderboot.timer.TimerWrapper;
import spiderboot.util.VideoWraper;

import java.sql.CallableStatement;
import org.apache.log4j.Logger;

/*------------------------------------------------------------------------------
** History
26-01-2018, [CR-008] phapnd
    Modify de ho tro ket noi db UTF-8


 */
public class UploadTimerManager extends TimerWrapper {

    private static final Logger logger = Logger.getLogger(UploadTimerManager.class);
    private static UploadTimerManager instance = null;
    //UploadThread uploadThread = new UploadThread();

    public UploadTimerManager() {
        // TODO Auto-generated constructor stub
    }

    public static UploadTimerManager getInstance() {
        if (instance == null) {
            instance = new UploadTimerManager();
        }
        return instance;
    }

    public void initTimerTask() {

        Integer vid;
        String videoID, title, tag, desc, thumbnail, location, homeChannelID, monitorChannelID;
        java.sql.Timestamp downloadDate;
        logger.info("init Get Video");
        String query = "{ call GETVIDEO() }";
        CallableStatement cStmt = null;
        try {
            cStmt = MySqlAccess.getInstance().connect.prepareCall(query);
            ResultSet rs = cStmt.executeQuery();
            //logger.info("EXECUTE RESULT=" + rs);
            while (rs.next()) {
                vid = rs.getInt("id");
                videoID = rs.getString("videoID");
                title = rs.getString("title");
                tag = rs.getString("tag");
                desc = rs.getString("description");
                thumbnail = rs.getString("thumbnail");
                location = rs.getString("videolocation");
                homeChannelID = rs.getString("homeChannelID");
                monitorChannelID = rs.getString("monitorChannelID");
                downloadDate = rs.getTimestamp("downloadDate");
                VideoWraper vWrapper = new VideoWraper(vid, videoID, title, tag, desc, thumbnail,
                        location, homeChannelID, monitorChannelID, downloadDate);
                logger.info("Add video " + vWrapper.vId + " to upload thead queue");
                new UploadExecuteThread().getInstance().addElement(vWrapper);
                logger.info("Start sync task : " + vid + " with timer interval = " + 1 + " seconds");
            }
            new UploadExecuteThread().getInstance().startThread();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            logger.error("ERR_GET_VIDEOS|" + e);
            return;
        } finally {
            if (cStmt != null) {
                try {
                    cStmt.close();
                } catch (SQLException ex) {
                    // TODO Auto-generated catch block
                    logger.error("ERR_GET_VIDEOS|" + ex.getMessage());
                    return;
                }
            }
        }

    }
}
