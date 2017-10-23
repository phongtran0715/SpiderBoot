package com.github.axet.vget.info;

import com.github.axet.vget.info.VideoInfo.VideoQuality;
import com.github.axet.vget.info.VideoInfo.VideoType;

public class VideoInfoUser {
    private VideoQuality userQuality;
    private VideoType userType;

    public VideoQuality getUserQuality() {
        return userQuality;
    }
    
    public VideoType getUserType(){
    	return userType;
    }

    /**
     * limit maximum quality, or do not call this function if you wish maximum
     * quality available. if youtube does not have video with requested quality,
     * program will raise an exception
     * 
     * @param userQuality
     */
    public void setUserQuality(VideoQuality userQuality) {
        this.userQuality = userQuality;
    }
    
    /**
     * set type of output video 
     * @author phongtn
     * @param userType
     */
    public void setUserType(VideoType userType){
    	this.userType =userType;
    }

}