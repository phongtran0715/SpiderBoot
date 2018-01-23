package com.itc.edu.dlvideo.main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.itc.edu.database.MySqlAccess;
import com.itc.edu.timer.TimerWrapper;

/*------------------------------------------------------------------------------
** History
23-01-2018, [CR-005] phapnd
    Modify close ket noi db sau khi thuc hien lenh

 */
public class DownloadTimerManager extends TimerWrapper {

    private static DownloadTimerManager instance = null;
    private static Logger logger = Logger.getLogger(DownloadTimerManager.class);

    public DownloadTimerManager() {
    }

    public static DownloadTimerManager getInstance() {
        if (instance == null) {
            instance = new DownloadTimerManager();
        }
        return instance;
    }

    public boolean startDownloadTimer(String taskId, String cHomeId, String cMonitorId, int timerInterval) {
        boolean isSuccess = false;
        TimerTask timerTask = new DownloadExecuteTimer(taskId, cHomeId, cMonitorId);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, timerInterval);
        if (timer != null) {
            timerMap.put(taskId, timer);
        }
        isSuccess = true;
        return isSuccess;
    }

    @Override
    public void initTimerTask() {

        long startTime = 0L;

        logger.info("init Get MonitorChannel");
        Statement stmt = null;
        String query = "SELECT Id, HomeChannelId, MonitorChannelId, TimeIntervalSync, StatusSync "
                + "FROM home_monitor_channel_mapping WHERE StatusSync = '1';";
        try {
            stmt = MySqlAccess.getInstance().connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String id = Integer.toString(rs.getInt(1));
                String cHomeId = rs.getString(2);
                String cMonitorId = rs.getString(3);
                int syncInterval = rs.getInt(4);
                startDownloadTimer(id, cHomeId, cMonitorId, syncInterval * 1000);
                logger.info("Start sync task : " + id + " with timer interval = " + 1 + " seconds");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            logger.error("ERR_GET_MONITORCHANNEL|" + e);
            return;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    // TODO Auto-generated catch block
                    logger.error("ERR_GET_MONITORCHANNEL|" + ex.getMessage());
                    return;
                }
            }
        }

    }
}
