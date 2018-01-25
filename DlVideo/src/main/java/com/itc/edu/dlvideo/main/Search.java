/*
 * Copyright (c) 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.itc.edu.dlvideo.main;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.util.DateTime;
import com.google.api.services.samples.youtube.cmdline.Auth;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;

import com.itc.edu.dlvideo.util.Config;
import com.itc.edu.dlvideo.util.Constant;
import java.io.File;
import java.io.FileInputStream;
import org.apache.log4j.Logger;

/*------------------------------------------------------------------------------
** History
25-01-2018, [CR-006] phapnd
    Cap nhat them ham get Video information

 */
/**
 * Print a list of videos matching a search term.
 *
 * @author Jeremy Walker
 */
public class Search {

    private static final Logger logger = Logger.getLogger(Search.class);
    private static Search instance = null;

    /**
     * Define a global variable that identifies the name of a file that contains
     * the developer's API key.
     */
    private final String PROPERTIES_FILENAME = "youtube.properties";

    private final long NUMBER_OF_VIDEOS_RETURNED = Config.noVideoReturn;
    Properties properties;

    /**
     * Define a global instance of a Youtube object, which will be used to make
     * YouTube Data API requests.
     */
    private YouTube youtube;

    /**
     * Initialize a YouTube object to search for videos on YouTube. Then display
     * the name and thumbnail image of each video in the result set.
     *
     * @param args command line args.
     */
    public Search() {
        initialize();
    }

    public static Search getInstance() {
        if (instance == null) {
            instance = new Search();
        }
        return instance;
    }

    private void initialize() {
        // Read the developer key from the properties file.
        properties = new Properties();
        try {
            InputStream in = Search.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
            properties.load(in);

        } catch (IOException e) {
            logger.error("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
                    + " : " + e.getMessage());
            System.exit(1);
        }
        // This object is used to make YouTube Data API requests. The last
        // argument is required, but since we don't need anything
        // initialized when the HttpRequest is initialized, we override
        // the interface and provide a no-op function.
        youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {
            }
        }).setApplicationName("youtube-cmdline-search-sample").build();
    }

    public List<SearchResult> executeQuery(String queryTerm) {
        try {
            // Define the API request for retrieving search results.
            YouTube.Search.List search = youtube.search().list("id,snippet");

            // Set your developer key from the {{ Google Cloud Console }} for
            // non-authenticated requests. See:
            // {{ https://cloud.google.com/console }}
            String apiKey = properties.getProperty("youtube.apikey");
            search.setKey(apiKey);
            search.setQ(queryTerm);

            // Restrict the search results to only include videos. See:
            // https://developers.google.com/youtube/v3/docs/search/list#type
            search.setType("video");

            // To increase efficiency, only retrieve the fields that the
            // application uses.
            search.setFields("items(etag,id/kind,id/videoId,snippet/title,snippet/description,"
                    + "snippet/thumbnails/default/url)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

            // Call the API and print results.
            SearchListResponse searchResponse = search.execute();
            List<SearchResult> searchResultList = searchResponse.getItems();
            if (searchResultList != null) {
                if (searchResultList != null) {
                    //prettyPrint(searchResultList.iterator(), queryTerm);
                    return searchResultList;
                }
            }
        } catch (GoogleJsonResponseException e) {
            logger.error("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            logger.error("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }

    public List<SearchResult> getVideoByPublishDate(String channelId, DateTime publishAfter) {
        List<SearchResult> searchResult = new ArrayList<SearchResult>();
        try {
            // Define the API request for retrieving search results.
            YouTube.Search.List search = youtube.search().list("id,snippet");
            String apiKey = properties.getProperty("youtube.apikey");
            search.setKey(apiKey);
            search.setType("video");
            search.setChannelId(channelId);
            search.setFields("items(id/kind,id/videoId)");
            search.setPublishedAfter(publishAfter);
            search.setMaxResults((long) 10);
            // Call the API and print results.
            SearchListResponse searchResponse = search.execute();
            searchResult = searchResponse.getItems();
            //prettyPrint(searchResult.iterator(), "");
        } catch (GoogleJsonResponseException e) {
            logger.error("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            logger.error("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            logger.error("ERR_getVideoByPublishDate|" + t);
        }
        return searchResult;
    }

    /*
	 * Prints out all results in the Iterator. For each result, print the
	 * title, video ID, and thumbnail.
	 *
	 * @param iteratorSearchResults Iterator of SearchResults to print
	 *
	 * @param query Search query (String)
     */
    private void prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query) {
        logger.info("\n=============================================================");
        logger.info(
                "   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\".");
        logger.info("=============================================================\n");

        if (!iteratorSearchResults.hasNext()) {
            logger.info(" There aren't any results for your query.");
        }

        while (iteratorSearchResults.hasNext()) {

            SearchResult singleVideo = iteratorSearchResults.next();
            ResourceId rId = singleVideo.getId();

            // Confirm that the result represents a video. Otherwise, the
            // item will not contain a video ID.
            if (rId.getKind().equals("youtube#video")) {
                Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();

                logger.info(" Video Id" + rId.getVideoId());
                logger.info(" Title: " + singleVideo.getSnippet().getTitle());
                logger.info(" Thumbnail: " + thumbnail.getUrl());
                logger.info("\n-------------------------------------------------------------\n");
            }
        }
    }

    public List<Video> getVideoInfo(String srcFile) {
        List<Video> videoList = null;
        try {
            // This object is used to make YouTube Data API requests. The last
            // argument is required, but since we don't need anything
            // initialized when the HttpRequest is initialized, we override
            // the interface and provide a no-op function.
            youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
                @Override
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("getInfo").build();
            //Joiner stringJoiner = Joiner.on(',');
            //String videoId = stringJoiner.join(videoIds);
            // Call the YouTube Data API's youtube.videos.list method to
            // retrieve the resources that represent the specified videos.
            //YouTube.Videos.List listVideosRequest = youtube.videos().list("snippet, recordingDetails").setId(videoId);
            YouTube.Videos.List listVideosRequest = youtube.videos().list("snippet,contentDetails").setId(srcFile);
            listVideosRequest.setKey(Config.youtubeKey);
            VideoListResponse listResponse = listVideosRequest.execute();

            videoList = listResponse.getItems();
            Iterator<Video> iteratorSearchResults = videoList.iterator();
            //logger.info("VIDEO INFO|" + videoList);
        } catch (GoogleJsonResponseException e) {
            logger.error("ERR_VIDEO INFO|There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage() + ":" + e);
            return videoList;
        } catch (IOException e) {
            logger.error("ERR_VIDEO INFO|There was an IO error: " + e.getCause() + " : " + e.getMessage());
            return videoList;
        } catch (Exception ex) {
            logger.error("ERR_VIDEO INFO|" + ex);
            return videoList;
        }
        return videoList;
    }

}
