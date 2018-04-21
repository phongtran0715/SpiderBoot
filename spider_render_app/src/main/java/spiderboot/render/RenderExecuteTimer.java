package spiderboot.render;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import spiderboot.configuration.Config;
import spiderboot.database.MySqlAccess;
import spiderboot.util.Utility;

public class RenderExecuteTimer extends TimerTask{

	boolean isComplete = true;
	String timerId;
	String vIntro = "";
	String vOutro = "";
	String logo = "";
	FFmpeg ffmpeg;
	FFprobe ffprobe;
	private Statement stmt;
	private ResultSet rs;
	String videoFolderBase;
	static Utility util = new Utility();
	private static final Logger logger = Logger.getLogger(RenderExecuteTimer.class);

	public RenderExecuteTimer(String timerId) {
		// TODO Auto-generated constructor stub
		this.timerId = timerId;
		videoFolderBase = Config.videoFolder;
		try {
			ffmpeg = new FFmpeg();
			ffprobe = new FFprobe();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			String query = "SELECT Id, VideoLocation, HomeChannelId, MonitorChannelId "
					+ "FROM video_container WHERE ProcessStatus = '0';";
			System.out.println(query);
			try {
				stmt = MySqlAccess.getInstance().connect.createStatement();
				if(stmt != null) {
					System.out.println("Create stm successful");
				}
				rs = stmt.executeQuery(query);
				while (rs.next()) {
					System.out.println("Detect new video to process");
					int id = rs.getInt("Id");
					String vName = rs.getString("VideoLocation");
					String cHomeId = rs.getString("HomeChannelId");
					String cMonitorId = rs.getString("MonitorChannelId");
					vIntro = getStringAtrr(cHomeId, "VideoIntro");
					vOutro = getStringAtrr(cHomeId, "VideoOutro");
					logo  = getStringAtrr(cHomeId, "Logo");

					String vInputPath =  videoFolderBase + util.prefixOS() + cHomeId + "-" + cMonitorId;
					String vRendered = vInputPath + util.prefixOS() + "rendered";
					util.createFolderIfNotExist(vInputPath);
					util.createFolderIfNotExist(vRendered);

					String vInput = vInputPath + util.prefixOS() + vName;
					String vOutput = vRendered + util.prefixOS() + vName;
					//process video
					processVideo(vInput, vInputPath + util.prefixOS() + "video_tmp1.mp4");
					//convert video
					convertVideo(vIntro, vInputPath + util.prefixOS() + "intro.ts");
					convertVideo(vOutro, vInputPath + util.prefixOS() + "outro.ts");
					convertVideo(vInputPath + util.prefixOS() + "video_tmp1.mp4", 
							vInputPath + util.prefixOS() + "video_tmp1.ts");
					//concast video 
					concastVideo(vInputPath + util.prefixOS() + "intro.ts",
							vInputPath + util.prefixOS() + "video_tmp1.ts",
							vInputPath + util.prefixOS() + "outro.ts", 
							vOutput);
					//update process status 
					updateProcessStatus(id, 1);
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

	private String getStringAtrr(String cHomeId, String key)
	{
		String result = null;
		Statement stmt;
		String query = "SELECT " + key + " FROM home_channel_list "
				+ "WHERE ChannelId = '" + cHomeId + "'";
		System.out.println(query);
		try {
			stmt = MySqlAccess.getInstance().connect.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				result = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return result;
	}


	private String processVideo(String inputVideo, String outVideo)
	{
		System.out.println("Beginning processVideo function");
		String result = null;
		FFmpegBuilder builder = new FFmpegBuilder()
				.setInput(inputVideo)
				.addInput(logo)
				.setComplexFilter("overlay=main_w-overlay_w-10:10")
				.overrideOutputFiles(true)
				.addOutput(outVideo)
				.done();

		FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

		// Run a one-pass encode
		executor.createJob(builder).run();
		result = outVideo;
		System.out.println("Finish processVideo function");
		return result;
	}

	private String convertVideo(String inputVideo, String outVideo)
	{
		System.out.println("Beginning convertVideo function");
		String result = null;
		FFmpegBuilder builder = new FFmpegBuilder()
				.setInput(inputVideo)
				.overrideOutputFiles(true)
				.addOutput(outVideo)
				.addExtraArgs("-c", "copy")
				.addExtraArgs("-bsf:v", "h264_mp4toannexb")
				.addExtraArgs("-f", "mpegts")
				.done();

		FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

		// Run a one-pass encode
		executor.createJob(builder).run();
		result = outVideo;
		System.out.println("Finish convertVideo function");
		return result;
	}

	private String concastVideo(String vIntro, String vMain, String vOutro, String vOutput)
	{
		System.out.println("Beginning concastVideo function");
		String result = null;
		FFmpegBuilder builder = new FFmpegBuilder()
				.setInput("concat:" + vIntro + "|" + vMain + "|" +  vOutro + "")
				.overrideOutputFiles(true)
				.addOutput(vOutput)
				.addExtraArgs("-c", "copy")
				.addExtraArgs("-bsf:a", "aac_adtstoasc")
				.done();
		FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

		// Run a one-pass encode
		executor.createJob(builder).run();
		result = vOutput;
		System.out.println("Finish concastVideo function");
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