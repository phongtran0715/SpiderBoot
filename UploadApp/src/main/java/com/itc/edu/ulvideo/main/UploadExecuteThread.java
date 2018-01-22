package com.itc.edu.ulvideo.main;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import com.itc.edu.ulvideo.util.VideoWraper;
import com.itc.edu.database.MySqlAccess;
import org.apache.log4j.Logger;

/*------------------------------------------------------------------------------
** History


 */
public class UploadExecuteThread implements Runnable {

    private static final Logger logger = Logger.getLogger(UploadExecuteThread.class);
    BlockingQueue<VideoWraper> uploadQueue = new LinkedBlockingQueue<VideoWraper>();
    private static UploadExecuteThread instance = null;
    Thread thread;
    boolean isCompleted = true;

    public UploadExecuteThread() {
        thread = new Thread(this);
    }

    public UploadExecuteThread getInstance() {
        if (instance == null) {
            instance = new UploadExecuteThread();
        }
        return instance;
    }

    public Thread getUploadThead() {
        return thread;
    }

    public void run() {
        while (true) {
            if (isCompleted) {
                isCompleted = false;
                if (!uploadQueue.isEmpty()) {
                    VideoWraper vWrapper = uploadQueue.poll();
                    logger.info("UploadThread : beginning upload video : " + vWrapper.title);
                    if (vWrapper != null) {
                        UploadVideo uploadVideo = new UploadVideo();
                        uploadVideo.execute(vWrapper.title, vWrapper.description, vWrapper.tag, vWrapper.vLocation);
                        //delete record on data base
                        //deleteRecord(vWrapper.recordId);
                        uploadStatus(vWrapper.title, vWrapper.recordId, 2);
                        logger.info("UploadThread : upload video : " + vWrapper.title + " completed");
                    }
                } else {
                    logger.info("upload queue empty");
                }
                isCompleted = true;
            }
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                logger.error("ERR_IMP_THREAD|" + e);
            }
        }
    }

    public void addElement(VideoWraper vWrapper) {
        try {
            logger.info("UploadThread : Add new video : " + vWrapper.title + " to queue");
            uploadQueue.put(vWrapper);
        } catch (InterruptedException e) {
            logger.error("ERR_ADD_ELEMENT|" + e);
        }
    }

    public void startThread() {
        thread.start();
    }

    private void deleteRecord(int recordId) {
        String query = "DELETE FROM video_container WHERE Id = ? ;";
        PreparedStatement preparedStm;
        try {
            preparedStm = MySqlAccess.getInstance().connect.prepareStatement(query);
            preparedStm.setInt(1, recordId);
            preparedStm.executeUpdate();
        } catch (SQLException ex) {
            logger.error("ERR_DELETERECORD|" + ex);
        }
    }

    private void uploadStatus(String title, int recordId, int status) {
        String query = "{ call UPLOADSTATUS(?,?) }";
        PreparedStatement preparedStm;
        try {
            preparedStm = MySqlAccess.getInstance().connect.prepareStatement(query);
            preparedStm.setInt(1, recordId);
            preparedStm.setInt(2, status);
            preparedStm.executeUpdate();
            logger.info("Update UPLOAD STATUS: Video= " + title + "|STATUS=" + status);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            logger.error("ERR_UPDATE_UPLOADSTATUS|" + e.getMessage());
            return;
        }
        //logger.info("Insert info: MontiorChannelID=" + cMonitorId + "|VideoID=" + vWraper.vId + "|TITLE:" + vWraper.title + "|take time:" + (System.currentTimeMillis() - startTime));

    }
}
