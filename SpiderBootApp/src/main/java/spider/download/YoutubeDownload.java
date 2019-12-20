package spider.download;

import org.apache.log4j.Logger;

import com.sapher.youtubedl.YoutubeDL;
import com.sapher.youtubedl.YoutubeDLException;
import com.sapher.youtubedl.YoutubeDLRequest;
import com.sapher.youtubedl.YoutubeDLResponse;

public class YoutubeDownload {
	private static final Logger logger = Logger.getLogger(YoutubeDownload.class);
	public int execute(final String videoId, String directory) {
		int exitCode = -1;
		String videoUrl = "https://www.youtube.com/watch?v=" + videoId;
		YoutubeDLRequest request = new YoutubeDLRequest(videoUrl, directory);
		request.setOption("ignore-errors");		// --ignore-errors
		request.setOption("output", "%(id)s");	// --output "%(id)s"
		request.setOption("retries", 10);		// --retries 10
		//request.setOption("format", "bestvideo[ext=mp4]+bestaudio[ext=m4a]/best[ext=mp4]/best");	// --video format
		// Make request and return response
		try {
			YoutubeDLResponse response = YoutubeDL.execute(request);
			exitCode = response.getExitCode();
			logger.info("[DOWNLOAD] : Download video : " + videoId + " completed!");
			logger.info(response.getDirectory());
		} catch (YoutubeDLException e) {
			e.printStackTrace();
			logger.error("[DOWNLOAD] : " + e.toString());
		}
		return exitCode;
	}
}
