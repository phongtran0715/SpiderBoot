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
import spiderboot.configuration.DownloadConfig;
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
		this.timerId = timerId;
	}

	@Override
	public void run() {
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
//				stmt = MySqlAccess.getInstance().connect.createStatement();
				if(stmt != null) {
					System.out.println("Create stm successful");
				}
				rs = stmt.executeQuery(query);
				while (rs.next()) {
					System.out.println("Detect new video to upload");

					int id 				= rs.getInt("Id");
					String vName 		= rs.getString("VideoLocation");
					String cHomeId 		= rs.getString("HomeChannelId");
					String cMonitorId 	= rs.getString("MonitorChannelId");
					String title 		= standardizeTitle(rs.getString("Title"));
					String tag 			= standardizeTags(rs.getString("Tag"));
					String description 	= standardizeDesc(rs.getString("Description"));
					String vInputPath 	= videoFolderBase + util.prefixOS() + cHomeId + "-" + cMonitorId;
					String vUpload = vInputPath + util.prefixOS() + vName;
					//String vUpload = vRendered + util.prefixOS() + vName;
					//String vUpload = "/home/phongtran0715/Downloads/Video/outro.mp4";
					//upload video
					File uploadFile = new File(vUpload);
					if (!uploadFile.exists()) {
						logger.error("File " + vUpload + " not Exist");
						continue;
					}
					//TODO: get store file
					String clientFile = getAuthAtr(cHomeId,"ClientSecret");
					String userName = getHomeAtrr(cHomeId,"GoogleAccount");
					System.out.println("setclientSecretsFile : " + clientFile);
					uploadVideo.setclientSecretsFile(clientFile);
					System.out.println("setStoreFile : " + "upload_" + userName);
					uploadVideo.setStoreFile( "upload_" + userName);
					System.out.println("Beginning upload video " + vName);
					uploadVideo.execute(title, description, tag, vUpload);
					//update process status 
					updateProcessStatus(id, 2);
					System.out.println("Upload complete video " + vName);
					//Pause for 60 seconds
					try {
						Thread.sleep(15 * 1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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

	private String standardizeTitle(String originTitle) {
		String outputTitle = originTitle;
		outputTitle = originTitle.replaceAll("[!@#$%^&*(){}:|<>?]", " ");
		return outputTitle;

	}

	private String standardizeTags(String originTags) {
		String outputTags = originTags;
		return outputTags;
	}

	private String standardizeDesc(String originDesc) {
		Boolean bMatch = false;
		Long startTime = System.currentTimeMillis();
		String[] partStrs = originDesc.split("\\r?\\n");
		StringBuilder returnDesc = new StringBuilder("");
		String query = "SELECT msg FROM utblinvdesc;";
		Statement stmt = null;
		for (String partStr : partStrs) {
			bMatch = false;
			try {
//				stmt = MySqlAccess.getInstance().connect.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next() && !bMatch) {
					String temp = rs.getString(1);
					if (partStr.toLowerCase().contains(temp)) {
						bMatch = true;
					}
				}
				if (!bMatch) {
					returnDesc.append(partStr).append(System.getProperty("line.separator"));
				}
			} catch (Exception ex) {
				logger.info(ex.toString());
			} finally {
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException ex) {
						// TODO Auto-generated catch block
						logger.error("ERR_CORRECT_DESCRIPTION|" + ex.getMessage());
					}
				}
			}
		}

		logger.info("Correct Desciption IS|=" + returnDesc.toString() + "|take time:" + (System.currentTimeMillis() - startTime));
		return returnDesc.toString();
	}
	private void updateProcessStatus(int id, int processStatus) 
	{
		System.out.println("Update Process Status");
		PreparedStatement preparedStm = null;
		String query = "UPDATE video_container SET ProcessStatus = ? WHERE Id = ? ";
		try {
//			preparedStm = MySqlAccess.getInstance().connect.prepareStatement(query);
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

	private String getHomeAtrr(String cHomeId, String key)
	{
		String result = null;
		Statement stmt;
		String query = "SELECT " + key + " FROM home_channel_list "
				+ "WHERE ChannelId = '" + cHomeId + "'";
		System.out.println(query);
		try {
//			stmt = MySqlAccess.getInstance().connect.createStatement();
//			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				result = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return result;
	}

	private String getAuthAtr(String cHomeId, String key)
	{
		String result = null;
		Statement stmt;
		String query = "SELECT " + key + " FROM google_account WHERE UserName = " + 
				"(SELECT GoogleAccount FROM home_channel_list WHERE ChannelId = '"+ cHomeId +"')";
		System.out.println(query);
		try {
//			stmt = MySqlAccess.getInstance().connect.createStatement();
//			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				result = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return result;
	}
}