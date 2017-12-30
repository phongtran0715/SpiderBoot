package spiderboot.ffmpeg;

import java.io.IOException;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

public class FfmpegWrapper {
	FFmpeg ffmpeg = null;
	FFprobe ffprobe = null;
	FFmpegBuilder builder = null;
	private void FfmpegWrapper() {
		try {
			ffmpeg = new FFmpeg();
			ffprobe = new FFprobe();
			builder = new FFmpegBuilder();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
