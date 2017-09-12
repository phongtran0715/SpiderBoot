package com.github.axet.vget;

import java.io.File;
import java.net.URL;

import com.github.axet.vget.info.VideoInfoUser;
import com.github.axet.vget.info.VideoInfo.VideoQuality;
import com.github.axet.vget.info.VideoInfo.VideoType;

public class DirectDownload {

    public static void main(String[] args) {
        try {
        	String path = "C:\\Users\\phong.tran\\Downloads\\Video";
        	System.out.println("Begining download...");
        	String [] vUlrs= {"https://www.youtube.com/watch?v=_Nn_vIef4Jk"};
        	for(String vUrl : vUlrs){
        		VGet v = new VGet(new URL(vUrl), new File(path));
                VideoInfoUser user = new VideoInfoUser();
                user.setUserQuality(VideoQuality.pMaxQuality);
                user.setUserType(VideoType.MP4);
                v.download(user);
        	}
            System.out.println("Download complete!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        	throw new RuntimeException(e);
        }
    }
}
