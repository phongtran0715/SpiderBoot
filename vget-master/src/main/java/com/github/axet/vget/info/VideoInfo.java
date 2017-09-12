package com.github.axet.vget.info;

import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;

import com.github.axet.vget.vhs.VimeoParser;
import com.github.axet.vget.vhs.YouTubeParser;
import com.github.axet.wget.info.DownloadInfo;
import com.github.axet.wget.info.ex.DownloadInterruptedError;

public class VideoInfo {

    // keep it in order hi->lo
    public enum VideoQuality {
        p3072, p2304, p1080, p720, p520, p480, p360, p270, p240, p224, p144, pMaxQuality
    }

    // phongtn add
    public enum VideoType {
    	MP4,MKV,WEBM,FLV,THREEGP	
    }
    
    public enum States {
        QUEUE, EXTRACTING, EXTRACTING_DONE, DOWNLOADING, RETRYING, DONE, ERROR, STOP
    }

    // user friendly url (not direct video stream url)
    private URL web;

    private VideoQuality vq;
    private DownloadInfo info;
    private String title;
    private URL icon;
    private long length;
    private String author;

	// states, three variables
    private States state;
    private Throwable exception;
    private int delay;

    /**
     * 
     * @param vq
     *            max video quality to download
     * @param web
     *            user firendly url
     * @param video
     *            video stream url
     * @param title
     *            video title
     */
    public VideoInfo(URL web) {
        this.setWeb(web);

        reset();
    }

    /**
     * check if we have call extract()
     * 
     * @return true - if extract() already been called
     */
    public boolean empty() {
        return info == null;
    }

    /**
     * reset videoinfo state. make it simialar as after calling constructor
     */
    public void reset() {
        setState(States.QUEUE);

        info = null;
        vq = null;
        title = null;
        icon = null;
        exception = null;
        delay = 0;
        length = 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DownloadInfo getInfo() {
        return info;
    }

    public void setInfo(DownloadInfo info) {
        this.info = info;
    }

    /**
     * get current video quality. holds actual videoquality ready for download
     * 
     * @return videoquality of requested URL
     */
    public VideoQuality getVideoQuality() {
        return vq;
    }

    /**
     * 
     * @param vq
     *            video quality
     */
    public void setVideoQuality(VideoQuality vq) {
        this.vq = vq;
    }

    public URL getWeb() {
        return web;
    }

    public void setWeb(URL source) {
        this.web = source;
    }

    public void extract(VideoInfoUser user, AtomicBoolean stop, Runnable notify) {
        VGetParser ei = null;

        if (YouTubeParser.probe(web))
            ei = new YouTubeParser(web);

        if (VimeoParser.probe(web))
            ei = new VimeoParser(web);

        if (ei == null){
        	System.out.println("ei = " + null);;
//        	throw new RuntimeException("unsupported web site");
        }
//            

        try {
            ei.extract(this, user, stop, notify);

            info.setReferer(web);

            info.extract(stop, notify);
        } catch (DownloadInterruptedError e) {
            setState(States.STOP, e);

            //throw e;
        } catch (RuntimeException e) {
            setState(States.ERROR, e);
//            throw e;
        }
    }

    public States getState() {
        return state;
    }

    public void setState(States state) {
        this.state = state;
        this.exception = null;
        this.delay = 0;
    }

    public void setState(States state, Throwable e) {
        this.state = state;
        this.exception = e;
        this.delay = 0;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay, Throwable e) {
        this.delay = delay;
        this.exception = e;
        this.state = States.RETRYING;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }

    public URL getIcon() {
        return icon;
    }

    public void setIcon(URL icon) {
        this.icon = icon;
    }

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}
	
    public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}