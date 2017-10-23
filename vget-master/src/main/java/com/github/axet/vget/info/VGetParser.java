package com.github.axet.vget.info;

import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.github.axet.vget.info.VideoInfo.VideoQuality;
import com.github.axet.vget.info.VideoInfo.VideoType;
import com.github.axet.wget.info.DownloadInfo;
import com.github.axet.wget.info.ex.DownloadError;
import com.github.axet.wget.info.ex.DownloadRetry;

public abstract class VGetParser {

	static public class VideoDownload {
		public VideoQuality vq;
		public URL url;
		public VideoType vt;

		public VideoDownload(VideoQuality vq, URL u, VideoType vt) {
			this.vq = vq;
			this.url = u;
			this.vt = vt;
		}
	}

	static public class VideoContentFirst implements Comparator<VideoDownload> {

		@Override
		public int compare(VideoDownload o1, VideoDownload o2) {
			Integer i1 = o1.vq.ordinal();
			Integer i2 = o2.vq.ordinal();
			Integer ic = i1.compareTo(i2);

			return ic;
		}

	}

	abstract public void extract(VideoInfo info, VideoInfoUser user, AtomicBoolean stop, Runnable notify);

	public void getVideo(VideoInfo vvi, VideoInfoUser user, List<VideoDownload> sNextVideoURL) {
		if (sNextVideoURL.size() == 0) {
			// rare error:
			//
			// The live recording you're trying to play is still being processed
			// and will be available soon. Sorry, please try again later.
			//
			// retry. since youtube may already rendrered propertly quality.
			throw new DownloadRetry("no video with required quality found,"
					+ " wait until youtube will process the video");
		}

		Collections.sort(sNextVideoURL, new VideoContentFirst());

		for (int i = 0; i < sNextVideoURL.size(); i++) {
			VideoDownload v = sNextVideoURL.get(i);
			boolean found = true;
			if(user.getUserQuality().equals(VideoQuality.pMaxQuality)){
				if(user.getUserType().equals(v.vt)){
					found = true;
				}
			}else{
				if (user.getUserQuality() != null)
					found &= user.getUserQuality().equals(v.vq) && user.getUserType().equals(v.vt);	
			}

			if (found) {
				vvi.setVideoQuality(v.vq);
				DownloadInfo info = new DownloadInfo(v.url);
				vvi.setInfo(info);
				System.out.println("Downloading video : " + vvi.getTitle());
				System.out.println("Quality : " + vvi.getVideoQuality().toString() + 
									" - Author : " + vvi.getAuthor() + " - Length : " + vvi.getLength() + " s");
				return;
			}
		}

		// throw download stop if user choice not maximum quality and we have no
		// video rendered by youtube
		throw new DownloadError("no video with required quality found,"
				+ " increace VideoInfo.setVq to the maximum and retry download");
	}
}
