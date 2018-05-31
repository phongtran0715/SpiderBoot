package spiderboot.render;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.spider.corba.RenderCorbaClient;

import SpiderRenderApp.SpiderFootSidePackage.RenderInfo;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import spiderboot.configuration.RenderConfig;
import spiderboot.data.DataController;
import spiderboot.util.Utility;

public class RenderExecuteTimer extends TimerTask{

	boolean isComplete = true;
	FFmpeg ffmpeg;
	FFprobe ffprobe;
	String outputFolder;
	static Utility util = new Utility();
	RenderConfig renderConfig;
	boolean isInitCorba = false;
	RenderCorbaClient renderClient;
	private static final Logger logger = Logger.getLogger(RenderExecuteTimer.class);

	public RenderExecuteTimer(String appId) {
		logger.info("Function RenderExecuteTimer >>>");
		renderConfig = DataController.getInstance().renderConfig;
		outputFolder = renderConfig.outputVideo;
		try {
			ffmpeg = new FFmpeg();
			ffprobe = new FFprobe();
		} catch (IOException e) {
			e.printStackTrace();
		}
		renderClient = new RenderCorbaClient();
		isInitCorba = renderClient.initCorba(renderConfig.corbaRef);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		logger.info("Timer task started at:" + new Date());
		completeTask();
		logger.info("Timer task finished at:" + new Date());
	}

	private void completeTask() {
		if(isComplete){
			isComplete = false;
			if(RenderTimerManager.qRenderJob.isEmpty() == false)
			{
				RenderInfo vInfo = RenderTimerManager.qRenderJob.poll();				
				//TODO: get render information
				util.createFolderIfNotExist(outputFolder);

				String vOutput = outputFolder + util.prefixOS() + vInfo.videoId + ".mp4";
				//process video
				processVideo(vInfo.vdownloadPath, outputFolder + util.prefixOS() + "video_tmp1.mp4", vInfo.vLogo);
				//convert video
				convertVideo(vInfo.vIntro, outputFolder + util.prefixOS() + "intro.ts");
				convertVideo(vInfo.vOutro, outputFolder + util.prefixOS() + "outro.ts");
				convertVideo(outputFolder + util.prefixOS() + "video_tmp1.mp4", 
						outputFolder + util.prefixOS() + "video_tmp1.ts");
				//concast video 
				concastVideo(outputFolder + util.prefixOS() + "intro.ts",
						outputFolder + util.prefixOS() + "video_tmp1.ts",
						outputFolder + util.prefixOS() + "outro.ts", 
						vOutput);
				//update rendered video information
				updateRenderedInfo(vInfo.jobId, 2, vOutput);
				//remove all temp file
				deleteTempFile(outputFolder + util.prefixOS() + "video_tmp1.mp4");
				deleteTempFile(outputFolder + util.prefixOS() + "intro.ts");
				deleteTempFile(outputFolder + util.prefixOS() + "outro.ts");
				deleteTempFile(outputFolder + util.prefixOS() + "video_tmp1.ts");
			}
			isComplete = true;
		}
		else{
			//do nothing
			System.out.println("Process timer task is still running ...");
		}
	}

	private String processVideo(String inputVideo, String outVideo, String logo)
	{
		logger.info("Beginning processVideo : " + inputVideo);
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
		logger.info("Finish processVideo : " + inputVideo);
		return result;
	}

	private String convertVideo(String inputVideo, String outVideo)
	{
		logger.info("Beginning convertVideo video : " + inputVideo);
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
		logger.info("Finish convertVideo : " + inputVideo);
		return result;
	}

	private String concastVideo(String vIntro, String vMain, String vOutro, String vOutput)
	{
		logger.info("Beginning concastVideo >>> ");
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
		logger.info("Finish concastVideo <<< Output video : " + vOutput);
		return result;
	}

	private void updateRenderedInfo(int jobId, int processStatus, String videoRendered)
	{
		logger.info(">>> Function [updateRenderedInfo] : job Id = " + jobId);
		isInitCorba = renderClient.initCorba(renderConfig.corbaRef);
		if(isInitCorba)
		{
			if(renderClient.renderAppImpl != null)
			{
				try {
					renderClient.renderAppImpl.updateRenderedVideo(jobId, processStatus, videoRendered);
				}catch (Exception e) {
					System.out.println(e.toString());
				}
			}else {
				logger.error("Render client implementation is NULL");
			}
		}else {
			logger.error("Init corba client FALSE");
		}
	}
	
	private void deleteTempFile(String filePath)
	{
		File file = new File(filePath);
        if(file.delete() == false)
        {
        	logger.error("Failed to delete the file : " + filePath);
        }
	}
}