package com.github.axet.vget;

import java.io.File;
import java.net.URL;

import com.github.axet.vget.info.VideoInfoUser;
import com.github.axet.vget.info.VideoInfo.VideoQuality;
import com.github.axet.vget.info.VideoInfo.VideoType;

/*------------------------------------------------------------------------------
** History

27-01-2018, [CR-009] phapnd
    Modify tra ra thong tin extentsion cua file videodownload
 */

public class DirectDownload {

//	public static void main() {
//		//download("JyhTTkypni0","C:\\Users\\phong.tran\\Downloads\\Video\\spider_video\\");
//		//download("J2-qgXKoeKw","/home/phongtran0715/Downloads", "phongtestvideo");
//	}
    public String download(String vId, String storeLocation) {
        String ext = null;
        try {
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

}
