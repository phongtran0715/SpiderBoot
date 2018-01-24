package com.github.axet.vget.vhs;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import com.github.axet.vget.info.VGetParser;
import com.github.axet.vget.info.VideoInfo;
import com.github.axet.vget.info.VideoInfo.States;
import com.github.axet.vget.info.VideoInfo.VideoQuality;
import com.github.axet.vget.info.VideoInfo.VideoType;
import com.github.axet.vget.info.VideoInfoUser;
import com.github.axet.wget.WGet;
import com.github.axet.wget.info.ex.DownloadError;

public class YouTubeParser extends VGetParser {

    public static class VideoUnavailablePlayer extends DownloadError {
        private static final long serialVersionUID = 10905065542230199L;

        public VideoUnavailablePlayer() {
            super("unavailable-player");
        }
    }

    public static class AgeException extends DownloadError {
        private static final long serialVersionUID = 1L;

        public AgeException() {
            super("Age restriction, account required");
        }
    }

    public static class PrivateVideoException extends DownloadError {
        private static final long serialVersionUID = 1L;

        public PrivateVideoException() {
            super("Private video");
        }

        public PrivateVideoException(String s) {
            super(s);
        }
    }

    public static class EmbeddingDisabled extends DownloadError {
        private static final long serialVersionUID = 1L;

        public EmbeddingDisabled(String msg) {
            super(msg);
        }
    }

    public static class VideoDeleted extends DownloadError {
        private static final long serialVersionUID = 1L;

        public VideoDeleted(String msg) {
            super(msg);
        }
    }

    List<VideoDownload> sNextVideoURL = new ArrayList<VideoDownload>();

    URL source;
    String outputName = null;

    public YouTubeParser(URL input) {
        this.source = input;
    }
    
    public YouTubeParser(URL input, String outputName) {
        this.source = input;
        this.outputName = outputName;
    }

    public static boolean probe(URL url) {
        return url.toString().contains("youtube.com");
    }

    void downloadone(VideoInfo info, AtomicBoolean stop, Runnable notify) throws Exception {
        try {
            extractEmbedded(info, stop, notify);
        } catch (EmbeddingDisabled e) {
            streamCpature(info, stop, notify);
        }
    }

    /**
     * do not allow to download age restricted videos
     * 
     * @param info
     * @param stop
     * @param notify
     * @throws Exception
     */
    void streamCpature(final VideoInfo info, final AtomicBoolean stop, final Runnable notify) throws Exception {
        String html;
        html = WGet.getHtml(info.getWeb(), new WGet.HtmlLoader() {
            @Override
            public void notifyRetry(int delay, Throwable e) {
                info.setDelay(delay, e);
                notify.run();
            }

            @Override
            public void notifyDownloading() {
                info.setState(States.DOWNLOADING);
                notify.run();
            }

            @Override
            public void notifyMoved() {
                info.setState(States.RETRYING);
                notify.run();
            }
        }, stop);
        extractHtmlInfo(info, html, stop, notify);
        extractIcon(info, html);
    }

    /**
     * Add resolution video for specific youtube link.
     * 
     * @param url
     *            download source url
     * @throws MalformedURLException
     */
    void addVideo(String itag, String url) throws MalformedURLException {
        Integer i = Integer.decode(itag);
        VideoQuality vd = itagMap.get(i);
        VideoType vt = vTypeMap.get(i);

        URL u = new URL(url);

        if (u != null)
            sNextVideoURL.add(new VideoDownload(vd, u, vt));
    }

    // http://en.wikipedia.org/wiki/YouTube#Quality_and_codecs

    static final Map<Integer, VideoQuality> itagMap = new HashMap<Integer, VideoInfo.VideoQuality>() {
        private static final long serialVersionUID = -6925194111122038477L;
        {
            put(120, VideoQuality.p720);
            put(102, VideoQuality.p720);
            put(101, VideoQuality.p360);
            put(100, VideoQuality.p360);
            put(85, VideoQuality.p520);
            put(84, VideoQuality.p720);
            put(83, VideoQuality.p240);
            put(82, VideoQuality.p360);
            put(46, VideoQuality.p1080);
            put(45, VideoQuality.p720);
            put(44, VideoQuality.p480);
            put(43, VideoQuality.p360);
            put(38, VideoQuality.p3072);
            put(37, VideoQuality.p1080);
            put(36, VideoQuality.p240);
            put(35, VideoQuality.p480);
            put(34, VideoQuality.p360);
            put(22, VideoQuality.p720);
            put(18, VideoQuality.p360);
            put(17, VideoQuality.p144);
            put(6, VideoQuality.p270);
            put(5, VideoQuality.p240);
            put(0, VideoQuality.pMaxQuality);
        }
    };
    
    static final Map<Integer, VideoType> vTypeMap = new HashMap<Integer, VideoInfo.VideoType>() {
        private static final long serialVersionUID = -6925194111122038477L;
        {
            put(100, VideoType.WEBM);
            put(85, VideoType.MP4);
            put(84, VideoType.MP4);
            put(83, VideoType.MP4);
            put(82, VideoType.MP4);
            put(43, VideoType.WEBM);
            put(36, VideoType.THREEGP);
            put(22, VideoType.MP4);
            put(18, VideoType.MP4);
            put(17, VideoType.THREEGP);
            put(5, VideoType.FLV);
        }
    };

    public static String extractId(URL url) {
        {
            Pattern u = Pattern.compile("youtube.com/watch?.*v=([^&]*)");
            Matcher um = u.matcher(url.toString());
            if (um.find())
                return um.group(1);
        }

        {
            Pattern u = Pattern.compile("youtube.com/v/([^&]*)");
            Matcher um = u.matcher(url.toString());
            if (um.find())
                return um.group(1);
        }

        return null;
    }

    /**
     * allows to download age restricted videos
     * 
     * @param info
     * @param stop
     * @param notify
     * @throws Exception
     */
    void extractEmbedded(final VideoInfo info, final AtomicBoolean stop, final Runnable notify) throws Exception {
        String id = extractId(source);
        if (id == null) {
            throw new RuntimeException("unknown url");
        }

        info.setTitle(String.format("http://www.youtube.com/watch?v=%s", id));

        String get = String
                .format("http://www.youtube.com/get_video_info?video_id=%s&el=embedded&ps=default&eurl=", id);
        
        URL url = new URL(get);
        
        String qs = WGet.getHtml(url, new WGet.HtmlLoader() {
            @Override
            public void notifyRetry(int delay, Throwable e) {
                info.setDelay(delay, e);
                notify.run();
            }

            @Override
            public void notifyDownloading() {
                info.setState(States.DOWNLOADING);
                notify.run();
            }

            @Override
            public void notifyMoved() {
                info.setState(States.RETRYING);
                notify.run();
            }
        }, stop);

        Map<String, String> map = getQueryMap(qs);

        if (map.get("status").equals("fail")) {
            String r = URLDecoder.decode(map.get("reason"), "UTF-8");
            if (map.get("errorcode").equals("150"))
                throw new EmbeddingDisabled("error code 150");
            if (map.get("errorcode").equals("100"))
                throw new VideoDeleted("error code 100");

            throw new DownloadError(r);
            // throw new PrivateVideoException(r);
        }

        info.setTitle(URLDecoder.decode(map.get("title"), "UTF-8"));

        // String fmt_list = URLDecoder.decode(map.get("fmt_list"), "UTF-8");
        // String[] fmts = fmt_list.split(",");

        String url_encoded_fmt_stream_map = URLDecoder.decode(map.get("url_encoded_fmt_stream_map"), "UTF-8");

        extractUrlEncodedVideos(url_encoded_fmt_stream_map);

        // 'iurlmaxresæ or 'iurlsd' or 'thumbnail_url'
        String icon = map.get("thumbnail_url");
        icon = URLDecoder.decode(icon, "UTF-8");
        info.setIcon(new URL(icon));
        
        // get length of video
        String length_seconds = map.get("length_seconds");
        info.setLength(Integer.decode(length_seconds));
        
        // get author of video
        String author = map.get("author");
        info.setAuthor(author);
    }

    void extractIcon(VideoInfo info, String html) {
        try {
            Pattern title = Pattern.compile("itemprop=\"thumbnailUrl\" href=\"(.*)\"");
            Matcher titleMatch = title.matcher(html);
            if (titleMatch.find()) {
                String sline = titleMatch.group(1);
                sline = StringEscapeUtils.unescapeHtml4(sline);
                info.setIcon(new URL(sline));
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, String> getQueryMap(String qs) {
        try {
            qs = qs.trim();
            List<NameValuePair> list;
            list = URLEncodedUtils.parse(new URI(null, null, null, -1, null, qs, null), "UTF-8");
            HashMap<String, String> map = new HashMap<String, String>();
            for (NameValuePair p : list) {
                map.put(p.getName(), p.getValue());
            }
            return map;
        } catch (URISyntaxException e) {
            throw new RuntimeException(qs, e);
        }
    }

    void extractHtmlInfo(VideoInfo info, String html, AtomicBoolean stop, Runnable notify) throws Exception {
        {
            Pattern age = Pattern.compile("(verify_age)");
            Matcher ageMatch = age.matcher(html);
            if (ageMatch.find())
                throw new AgeException();
        }

        {
            Pattern age = Pattern.compile("(unavailable-player)");
            Matcher ageMatch = age.matcher(html);
            if (ageMatch.find())
                throw new VideoUnavailablePlayer();
        }

        {
            Pattern urlencod = Pattern.compile("\"url_encoded_fmt_stream_map\": \"([^\"]*)\"");
            Matcher urlencodMatch = urlencod.matcher(html);
            if (urlencodMatch.find()) {
                String url_encoded_fmt_stream_map;
                url_encoded_fmt_stream_map = urlencodMatch.group(1);

                // normal embedded video, unable to grab age restricted videos
                Pattern encod = Pattern.compile("url=(.*)");
                Matcher encodMatch = encod.matcher(url_encoded_fmt_stream_map);
                if (encodMatch.find()) {
                    String sline = encodMatch.group(1);

                    extractUrlEncodedVideos(sline);
                }

                // stream video
                Pattern encodStream = Pattern.compile("stream=(.*)");
                Matcher encodStreamMatch = encodStream.matcher(url_encoded_fmt_stream_map);
                if (encodStreamMatch.find()) {
                    String sline = encodStreamMatch.group(1);

                    String[] urlStrings = sline.split("stream=");

                    for (String urlString : urlStrings) {
                        urlString = StringEscapeUtils.unescapeJava(urlString);

                        Pattern link = Pattern.compile("(sparams.*)&itag=(\\d+)&.*&conn=rtmpe(.*),");
                        Matcher linkMatch = link.matcher(urlString);
                        if (linkMatch.find()) {

                            String sparams = linkMatch.group(1);
                            String itag = linkMatch.group(2);
                            String url = linkMatch.group(3);

                            url = "http" + url + "?" + sparams;

                            url = URLDecoder.decode(url, "UTF-8");

                            addVideo(itag, url);
                        }
                    }
                }
            }
        }

        {
            Pattern title = Pattern.compile("<meta name=\"title\" content=(.*)");
            Matcher titleMatch = title.matcher(html);
            if (titleMatch.find()) {
                String sline = titleMatch.group(1);
                String name = sline.replaceFirst("<meta name=\"title\" content=", "").trim();
                name = StringUtils.strip(name, "\">");
                name = StringEscapeUtils.unescapeHtml4(name);
                info.setTitle(name);
            }
        }
    }

    void extractUrlEncodedVideos(String sline) throws Exception {
        String[] urlStrings = sline.split("url=");
        
        for (String urlString : urlStrings) {
            urlString = StringEscapeUtils.unescapeJava(urlString);
            // universal request
            {
                String url = null;
                {
                    Pattern link = Pattern.compile("([^&]*)&");
                    Matcher linkMatch = link.matcher(urlString);
                    if (linkMatch.find()) {
                        url = linkMatch.group(1);
                        url = URLDecoder.decode(url, "UTF-8");
                    }
                }
                String itag = null;
                {
                    Pattern link = Pattern.compile("itag=(\\d+)");
                    Matcher linkMatch = link.matcher(urlString);
                    if (linkMatch.find()) {
                        itag = linkMatch.group(1);
                    }
                }
                String sig = null;
                {
                    Pattern link = Pattern.compile("sig=([^&,]*)");
                    Matcher linkMatch = link.matcher(urlString);
                    if (linkMatch.find()) {
                        sig = linkMatch.group(1);
                    }
                }

                if (url != null && itag != null) {
                    try {
                        new URL(url);

                        if (sig != null)
                            url += "&signature=" + sig;

                        if (itag != null) {
                        	Integer i = Integer.decode(itag);
//                        	System.out.println("with count = " + count + " - itag = " + itag + " - video format add : + " + itagMap.get(i) + " " + vTypeMap.get(i));
                            addVideo(itag, url);
                            continue;
                        }
                    } catch (MalformedURLException e) {
                    	System.out.println("ignore bad urls : " + e.getMessage());
                        // ignore bad urls
                    }
                }else{
//                	System.out.println("count = " + count + "url = " + url);
//                	System.out.println("count = " + count + "itag = " + itag);
                }
            }
        }
    }

    @Override
    public void extract(VideoInfo info, VideoInfoUser user, AtomicBoolean stop, Runnable notify) {
        try {     	
        	
            downloadone(info, stop, notify);
            if(outputName != null)
            {
            	info.setTitle(outputName);
            }
            getVideo(info, user, sNextVideoURL);
        } catch (RuntimeException e) {
        	System.out.println(e.getMessage());
            //throw e;
        } catch (Exception e) {
            //throw new RuntimeException(e);
        	System.out.println(e.getMessage());
        }
    }

}
