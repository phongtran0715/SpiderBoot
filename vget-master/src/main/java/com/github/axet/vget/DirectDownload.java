package com.github.axet.vget;

import java.io.File;
import java.net.URL;

import com.github.axet.vget.info.VideoInfoUser;
import com.github.axet.vget.info.VideoInfo.VideoQuality;
import com.github.axet.vget.info.VideoInfo.VideoType;

public class DirectDownload {
	
	public static void main() {
		//download("JyhTTkypni0","C:\\Users\\phong.tran\\Downloads\\Video\\spider_video\\");
	}
	
    public static void download(String vId, String storeLocation) {
        try {
        	System.out.println("Begining download video id : " + vId);
        	String [] vUlrs= {"https://www.youtube.com/watch?v=" + vId};
        	for(String vUrl : vUlrs){
        		VGet v = new VGet(new URL(vUrl), new File(storeLocation));
                VideoInfoUser user = new VideoInfoUser();
                user.setUserQuality(VideoQuality.pMaxQuality);
                user.setUserType(VideoType.MP4);
                v.download(user);
        	}
            System.out.println("Download completed video id: " + vId);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        	throw new RuntimeException(e);
        }
    }
}
