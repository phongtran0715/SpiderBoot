package processApp.processApp;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.TimerTask;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

public class ProcessExecuteTimer extends TimerTask{

	boolean isComplete = true;
	String timerId;
	String vIntro = "";
	String vOutro = "";
	String vInput = "";
	String logo = "";
	String BASE_PATH = "/home/phongtran0715/Downloads/Document/test_ffmpeg";
	FFmpeg ffmpeg;
	FFprobe ffprobe;
	private Statement stmt;
	private ResultSet rs;

	public ProcessExecuteTimer(String timerId) {
		// TODO Auto-generated constructor stub
		this.timerId = timerId;
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
		System.out.println("Timer task started at:"+new Date());
		completeTask();
		System.out.println("Timer task finished at:"+new Date());
	}
	
	private void completeTask() {
//		if(isComplete){
//			isComplete = false;
//			String query = "SELECT Id, VideoLocation, ChannelMappingId FROM video_container WHERE ProcessStatus = '1';";
//			System.out.println(query);
//			try {
//				stmt = MySqlAccess.getInstance().connect.createStatement();
//				if(stmt != null) {
//					System.out.println("Create stm successful");
//				}
//				rs = stmt.executeQuery(query);
//				while (rs.next()) {
//					System.out.println("Detect new video to process");
//					String vLocation = rs.getString("VideoLocation");
//					int cMappingId = rs.getInt("ChannelMappingId");
//					vIntro = getStringAtrr(cMappingId, "VideoIntro");
//					vOutro = getStringAtrr(cMappingId, "VideoOutro");
//					logo  = getStringAtrr(cMappingId, "Logo");
//					vInput = vLocation;
//					processVideo(vInput, BASE_PATH + "/tmp_output1.mp4");
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			isComplete = true;
//		}
//		else{
//			//do nothing
//			System.out.println("Process timer task is still running ...");
//		}
	}

	private String getStringAtrr(int cMappingId, String key)
	{
		String result = null;
		Statement stmt;
		String query = "SELECT " + key + " FROM home_channel_list WHERE ChannelId = "
				+ "(SELECT HomeChannelId FROM home_monitor_channel_mapping WHERE Id = "+ cMappingId +")";
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
				.addInput(inputVideo)
				.setComplexFilter("movie="+ logo +" [watermark]; [0:v]scale=1280:-2[bg];[1:v]scale=760:470[fl]"+
						";[bg][fl]overlay=260:64:shortest=1[out1];[out1][watermark]overlay=main_w-overlay_w-10:10,"+
						"format=yuv420p[v];[1:a]pan=stereo|c0<0*c0+c1|c1<0*c0+c1,aeval=-val(0)|val(1) [a]")
				.overrideOutputFiles(true)
				.addOutput(outVideo)
				.addExtraArgs("-map", "[v]")
				.addExtraArgs("-map", "[a]")
				.addExtraArgs("-movflags")
				.addExtraArgs("+faststart")
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
		FFmpegBuilder builder = new FFmpegBuilder();
		builder.setInput(inputVideo);
		builder.overrideOutputFiles(true);
		builder.addOutput(outVideo);
		builder.addExtraArgs("-c", "copy");
		builder.addExtraArgs("-bsf:v", "h264_mp4toannexb");
		builder.addExtraArgs("-f", "mpegts");

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
		FFmpegBuilder builder = new FFmpegBuilder();
		builder.setInput("concat:" + vIntro+ "|" + vMain + "|" + vOutro + "");
		builder.overrideOutputFiles(true);
		builder.addOutput(vOutput);
		builder.addExtraArgs("-c", "copy");
		builder.addExtraArgs("-bsf:a", "aac_adtstoasc");
		FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

		// Run a one-pass encode
		executor.createJob(builder).run();
		result = vOutput;
		System.out.println("Finish concastVideo function");
		return result;
	}

}
