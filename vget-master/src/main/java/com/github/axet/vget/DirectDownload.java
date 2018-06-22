package com.github.axet.vget;

import java.io.File;
import java.net.URL;

import com.github.axet.vget.info.VideoInfoUser;
import com.github.axet.vget.info.VideoInfo.VideoQuality;
import com.github.axet.vget.info.VideoInfo.VideoType;

public class DirectDownload {

    public String download(String vId, String storeLocation) {
        String ext = null;
        try {
        	deleteFileIdExisted(storeLocation + "/" + vId + ".mp4");
            System.out.println("Begining download video id : " + vId);
            String[] vUlrs = {"https://www.youtube.com/watch?v=" + vId};
            for (String vUrl : vUlrs) {
                VGet v = new VGet(new URL(vUrl), new File(storeLocation), vId);
                VideoInfoUser user = new VideoInfoUser();
                user.setUserQuality(VideoQuality.pMaxQuality);
                user.setUserType(VideoType.MP4);
                ext = v.download(user);
            }
            System.out.println("Download completed video id: " + vId);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return ext;
    }
    
	private void deleteFileIdExisted(String filePath)
	{
		File file = new File(filePath);
		if(file.exists())
		{
			if(file.delete() == false)
			{
				System.out.println("Failed to delete the file : " + filePath);
			}	
		}
	}

}
