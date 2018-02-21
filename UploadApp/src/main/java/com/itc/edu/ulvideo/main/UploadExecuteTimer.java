package com.itc.edu.ulvideo.main;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.TimerTask;
import org.apache.log4j.Logger;
import com.google.api.services.samples.youtube.cmdline.data.UploadVideo;
import spiderboot.configuration.Config;
import spiderboot.database.MySqlAccess;
import spiderboot.util.Utility;

public class UploadExecuteTimer extends TimerTask{

	boolean isComplete = true;
	String timerId;
	private Statement stmt;
	private ResultSet rs;
	String videoFolderBase;
	static Utility util = new Utility();
	private static final Logger logger = Logger.getLogger(UploadExecuteTimer.class);
	UploadVideo uploadVideo = new UploadVideo();

	public UploadExecuteTimer(String timerId) {
		// TODO Auto-generated constructor stub
		this.timerId = timerId;
		videoFolderBase = Config.videoFolder;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Timer task started at:" + new Date());
		completeTask();
		System.out.println("Timer task finished at:" + new Date());
	}

	private void completeTask() {
		if(isComplete){
			isComplete = false;
			String query = " SELECT Id, Title, Tag, Description, VideoLocation, "
					+ " HomeChannelId, MonitorChannelId "
					+ " FROM video_container WHERE ProcessStatus = '1'";
			System.out.println(query);
			try {
				stmt = MySqlAccess.getInstance().connect.createStatement();
				if(stmt != null) {
					System.out.println("Create stm successful");
				}
				rs = stmt.executeQuery(query);
				while (rs.next()) {
					System.out.println("Detect new video to process");
					int id 				= rs.getInt("Id");
					String vName 		= rs.getString("VideoLocation");
					String cHomeId 		= rs.getString("HomeChannelId");
					String cMonitorId 	= rs.getString("MonitorChannelId");
					String title 		= genTitle(rs.getString("Title"));
					String tag 			= genTags(rs.getString("Tag"));
					String description 	= genDescription(rs.getString("Description"));
					String vInputPath 	= videoFolderBase + util.prefixOS() + cHomeId + "-" + cMonitorId;
					String vRendered 	= vInputPath + util.prefixOS() + "rendered";
					String vUpload = vRendered + util.prefixOS() + vName;
					//upload video
					File uploadFile = new File(vUpload);
					if (!uploadFile.exists()) {
		                logger.error("File " + title + " not Exist");
		                continue;
		            }
					uploadVideo.execute(title, description, tag, vUpload);
					//update process status 
					updateProcessStatus(id, 2);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isComplete = true;
		}
		else{
			//do nothing
			System.out.println("Process timer task is still running ...");
		}
	}

	String genTitle(String originTitle)
	{
		String result = originTitle;
		//TODO: create title
		return result;
	}

	String genTags(String originTags)
	{
		String result = originTags;
		//TODO: create tags
		return result;
	}

	String genDescription(String originDesc)
	{
		String result = originDesc;
		//TODO: create description
		return result;
	}

	private void updateProcessStatus(int id, int processStatus) 
	{
		System.out.println("Update Process Status");
		PreparedStatement preparedStm = null;
		String query = "UPDATE video_container SET ProcessStatus = ? WHERE Id = ? ";
		try {
			preparedStm = MySqlAccess.getInstance().connect.prepareStatement(query);
			// execute insert SQL statement
			preparedStm.setInt(1, processStatus);
			preparedStm.setInt(2, id);
			preparedStm.executeUpdate();
			logger.info("Update process status for video " + id + "successful!");
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			logger.info("ERR_UPDATE_LASTSYNCTIME|" + ex.getMessage());
		} 
	}
}