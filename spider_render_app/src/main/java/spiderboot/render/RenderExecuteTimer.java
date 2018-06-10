package spiderboot.render;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import com.spider.corba.RenderCorbaClient;

import SpiderCorba.AgentSidePackage.ClusterInfo;
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
				SpiderCorba.RenderSidePackage.RenderConfig renderCfg = jobData.renderCfg;
				logger.info("Render job ( id = )"  + jobData.jobId + " started at:" + new Date());
				logger.info("=================== Render video infor ===================");
				logger.info(" + Video ID :" + vInfo.videoId);
				logger.info(" + Title :" + vInfo.title);
				logger.info(" + Video location :" + vInfo.vDownloadPath);
				logger.info(" + Mapping ID :" + vInfo.mappingId);
				logger.info("==========================================================");
				//TODO: get render information
				File uploadFile = new File(vInfo.vDownloadPath);

				if (!uploadFile.exists()) {
					logger.error("File " + vInfo.vDownloadPath + " not Exist");
					logger.info("Timer task finished at:" + new Date());
					isComplete = true;
					return;
				}
				util.createFolderIfNotExist(outputFolder);
				//check file is existed or not
				String vOutput = outputFolder + util.prefixOS() + vInfo.videoId + "_" + new Date().getTime() +   ".mp4";
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
				updateRenderedInfo(jobData.jobId, 2, vOutput);
				//remove all temp file
				deleteTempFile(vProcessedInput);
				deleteTempFile(tmpVProcess);
				deleteTempFile(tmpVIntro);
				deleteTempFile(tmpVOutro);
				logger.info("Timer task finished at:" + new Date());
			}
			isComplete = true;
		}
		else{
			//do nothing
			System.out.println("Process timer task is still running ...");
		}
	}

	private String processVideo(SpiderCorba.RenderSidePackage.RenderConfig renderCfg, String inputVideo) throws IOException 
	{
		logger.info("Beginning processVideo : " + inputVideo);
		String tmpOutput = "/tmp/" + new Date().getTime() + ".mp4";
		FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
		final FFmpegProbeResult in = ffprobe.probe(inputVideo);

		FFmpegBuilder builder;
		builder = new FFmpegBuilder();
		builder = builder.setInput(inputVideo);
		if(renderCfg.enableLogo)
		{
			File file = new File(renderCfg.vLogoTemp);
			if(file.exists() == true)
			{
				builder = builder.addInput(renderCfg.vLogoTemp);
				builder = builder.setComplexFilter("overlay=main_w-overlay_w-10:10");
			}else {
			logger.error("Logo template file does not exist");	
			}
		}
		builder = builder.addExtraArgs("-r", "30");
		builder = builder.overrideOutputFiles(true);
		builder.addOutput(tmpOutput).done();

		FFmpegJob job = executor.createJob(builder, new ProgressListener() {

			// Using the FFmpegProbeResult determine the duration of the input
			final double duration_ns = in.getFormat().duration * TimeUnit.SECONDS.toNanos(1);

			@Override
			public void progress(Progress progress) {
				double percentage = progress.out_time_ns / duration_ns;

				// Print out interesting information about the progress
				System.out.println(String.format(
						"[%.0f%%] status:%s frame:%d time:%s ms fps:%.0f speed:%.2fx",
						percentage * 100,
						progress.status,
						progress.frame,
						FFmpegUtils.toTimecode(progress.out_time_ns, TimeUnit.NANOSECONDS),
						progress.fps.doubleValue(),
						progress.speed
						));
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

	private void updateRenderedInfo(int jobId, int processStatus, String videoRendered)
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