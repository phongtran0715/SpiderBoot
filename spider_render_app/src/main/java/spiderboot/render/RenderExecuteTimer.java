package spiderboot.render;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import com.spider.corba.RenderCorbaClient;

import SpiderCorba.SpiderDefinePackage.VideoInfo;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFmpegUtils;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.job.FFmpegJob;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import net.bramp.ffmpeg.progress.Progress;
import net.bramp.ffmpeg.progress.ProgressListener;
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
		try {
			completeTask();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.toString());
		}
	}

	private void completeTask() throws IOException {
		if(isComplete){
			isComplete = false;

			if(RenderTimerManager.qRenderJob.isEmpty() == false)
			{
				DataDefine.RenderJobData jobData = RenderTimerManager.qRenderJob.poll();
				VideoInfo vInfo = jobData.vInfo;
			
				logger.info("Render job ( id = )"  + jobData.jobId + " started at:" + new Date());
				logger.info("\n\n");
				logger.info("\n=================== BEGINNING RENDER VIDEO ===================");
				logger.info(" + Video ID :" + vInfo.videoId);
				logger.info(" + Title :" + vInfo.title);
				logger.info(" + Video location :" + vInfo.vDownloadPath);
				logger.info(" + Mapping ID :" + vInfo.mappingId);
				String extension = vInfo.vDownloadPath.substring(vInfo.vDownloadPath.lastIndexOf(".") + 1);
				if(extension.equals("mp4") == false)
				{
					logger.error("Video type is : " + extension + ". This video will be ignore");
					isComplete = true;
					return;
					
				}
				SpiderCorba.SpiderDefinePackage.RenderConfig renderCfg = null;
				try {
					renderCfg = getRenderConfig(vInfo.mappingId);	
				}catch (Exception e) {
					// TODO: handle exception
					logger.error(e.toString());
				}
				 
				if(renderCfg == null)
				{
					logger.error("Error! Can not get render config");
					isComplete = true;
					return;
				}
				File uploadFile = new File(vInfo.vDownloadPath);

				if (!uploadFile.exists()) {
					logger.error("Error! File " + vInfo.vDownloadPath + " not Exist");
					isComplete = true;
					return;
				}
				util.createFolderIfNotExist(outputFolder);
				//check file is existed or not
				String vOutput = outputFolder + util.prefixOS() + "video_" +vInfo.videoId + "_" + new Date().getTime() +   ".mp4";
				
				//process video
				String vProcessedInput = processVideo(renderCfg, vInfo.vDownloadPath);
				//convert video
				String tmpVIntro = convertVideo(renderCfg.vIntroTemp);	
				String tmpVOutro = convertVideo(renderCfg.vOutroTemp);	
				String tmpVProcess =  convertVideo(vProcessedInput);
				//concast video 
				concastVideo(tmpVIntro, tmpVProcess, tmpVOutro, 
						renderCfg.enableIntro, renderCfg.enableOutro, vOutput);
				
				//update rendered video information
				vInfo.vRenderPath = vOutput;
				vInfo.processStatus = 2;
				updateRenderedInfo(jobData.jobId, vInfo);
				//remove all temp file
				
				deleteTempFile(vProcessedInput);
				deleteTempFile(tmpVProcess);
				deleteTempFile(tmpVIntro);
				deleteTempFile(tmpVOutro);
				
				logger.info("\n=================== COMPLETE RENDER VIDEO ===================\n\n");
			}
			isComplete = true;
		}
		else{
		}
	}

	private SpiderCorba.SpiderDefinePackage.RenderConfig getRenderConfig(int mappingId)
	{
		SpiderCorba.SpiderDefinePackage.RenderConfig renderCfg = null;
		logger.info(">>> Function [getRenderConfig] :");
		if(isInitCorba == false)
		{
			isInitCorba = renderClient.initCorba(renderConfig.corbaRef);	
		}
		if(isInitCorba)
		{
			if(renderClient.renderAppImpl != null)
			{
				try {
					renderCfg = renderClient.renderAppImpl.getRenderConfig(mappingId);
				}catch (Exception e) {
					System.out.println(e.toString());
				}
			}else {
				logger.error("Render client implementation is NULL");
			}
		}else {
			logger.error("Init corba client FALSE");
		}
		return renderCfg;
	}
	private String processVideo(SpiderCorba.SpiderDefinePackage.RenderConfig renderCfg, String inputVideo) throws IOException 
	{
		logger.info("Beginning processVideo : " + inputVideo);
		String tmpOutput = "/tmp/" + new Date().getTime() + ".mp4";
		FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
		final FFmpegProbeResult in = ffprobe.probe(inputVideo);
		Double duration = in.getFormat().duration - 20;
		FFmpegBuilder builder =
				new FFmpegBuilder()
				.addExtraArgs("-ss", "10")
				.addExtraArgs("-t", Double.toString(duration))
				.setInput(inputVideo)
				.addInput(renderCfg.vLogoTemp)
				.setComplexFilter("overlay=main_w-overlay_w-5:5")
				.addOutput(tmpOutput)
				.setFormat("mp4")
				.addExtraArgs("-bufsize", "4000k")
				.addExtraArgs("-maxrate", "1000k")
				.setAudioCodec("copy")
				.setAudioSampleRate(FFmpeg.AUDIO_SAMPLE_44100)
				.setAudioBitRate(1_000_000)
				.addExtraArgs("-profile:v", "baseline")
				.setVideoCodec("libx264")
				.setVideoPixelFormat("yuv420p")
				.setVideoResolution(1280, 720)
				.setVideoBitRate(2_000_000)
				.setVideoFrameRate(30)
				.addExtraArgs("-deinterlace")
				.addExtraArgs("-preset", "medium")
				.addExtraArgs("-g", "30")
				.done();

		FFmpegJob job = executor.createJob(builder, new ProgressListener() {

			// Using the FFmpegProbeResult determine the duration of the input
			final double duration_ns = in.getFormat().duration * TimeUnit.SECONDS.toNanos(1);
			@Override
			public void progress(Progress progress) {
				double percentage = progress.out_time_ns / duration_ns;
				logger.info(String.format("[%.0f%%] status:%s frame:%d time:%s ms fps:%.0f speed:%.2fx", percentage * 100,
						progress.status,
						progress.frame,
						FFmpegUtils.toTimecode(progress.out_time_ns, TimeUnit.NANOSECONDS),
						progress.fps.doubleValue(),
						progress.speed));
			}
		});
		job.run();
		logger.info("Finish processVideo : ");
		return tmpOutput;
	}

	private String convertVideo(String inputVideo)
	{
		logger.info("Beginning convertVideo video : " + inputVideo);
		File file = new File(inputVideo);
		if(file.exists() == false)
		{
			logger.error("Input file does not exist!");
			return null;
		}
		String tmpOutput = "/tmp/" + new Date().getTime() + ".mp4";
		FFmpegBuilder builder = new FFmpegBuilder()
				.setInput(inputVideo)
				.overrideOutputFiles(true)
				.addOutput(tmpOutput)
				.addExtraArgs("-c", "copy")
				.addExtraArgs("-bsf:v", "h264_mp4toannexb")
				.addExtraArgs("-f", "mpegts")
				.done();

		FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

		// Run a one-pass encode
		executor.createJob(builder).run();

		logger.info("Finish convertVideo : " + inputVideo);
		return tmpOutput;
	}

	private String concastVideo(String vIntro, String vMain, String vOutro, 
			boolean enableIntro, boolean enableOutro, String outputFile)
	{
		logger.info("Beginning concastVideo >>> ");
		logger.info("input video " + vMain);
		String concast = "concat:";
		if(enableIntro && (vIntro != null))
		{
			concast += vIntro + "|";
		}
		concast += vMain;
		if(enableOutro && (vOutro != null))
		{
			concast += "|" + vOutro;
		}
		logger.info("concast string :" + concast);
		FFmpegBuilder builder = new FFmpegBuilder()
				.setInput(concast)
				.overrideOutputFiles(true)
				.addOutput(outputFile)
				.addExtraArgs("-c", "copy")
				.addExtraArgs("-bsf:a", "aac_adtstoasc")
				.done();
		FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

		// Run a one-pass encode
		executor.createJob(builder).run();
		logger.info("Finish concastVideo <<< Output video : " + outputFile);
		return outputFile;
	}

	private void updateRenderedInfo(int jobId, VideoInfo vInfo)
	{
		logger.info(">>> Function [updateRenderedInfo] : job Id = " + jobId);
		if(isInitCorba == false)
		{
			isInitCorba = renderClient.initCorba(renderConfig.corbaRef);	
		}
		if(isInitCorba)
		{
			if(renderClient.renderAppImpl != null)
			{
				try {
					renderClient.renderAppImpl.updateRenderedVideo(jobId, vInfo);
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
		try {
			File file = new File(filePath);
			if(file.exists())
			{
				if(file.delete() == false)
				{
					logger.error("Failed to delete the file : " + filePath);
				}	
			}	
		}catch (Exception e) {
			logger.error(e.toString());
		}

	}
}