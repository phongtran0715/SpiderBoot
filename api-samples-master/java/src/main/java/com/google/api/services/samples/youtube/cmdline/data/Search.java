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

package com.google.api.services.samples.youtube.cmdline.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.util.DateTime;
import com.google.api.services.samples.youtube.cmdline.Auth;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;

/**
 * Print a list of videos matching a search term.
 *
 * @author Jeremy Walker
 */
public class Search {
	private static Search instance = null;

	/**
	 * Define a global variable that identifies the name of a file that
	 * contains the developer's API key.
	 */
	//private final String PROPERTIES_FILENAME = "youtube.properties";

	private final long NUMBER_OF_VIDEOS_RETURNED = 25;
	Properties properties;

	/**
	 * Define a global instance of a Youtube object, which will be used
	 * to make YouTube Data API requests.
	 */
	private YouTube youtube;

	/**
	 * Initialize a YouTube object to search for videos on YouTube. Then
	 * display the name and thumbnail image of each video in the result set.
	 *
	 * @param args command line args.
	 */
	public Search()
	{
		initialize();
	}

	public static Search getInstance() {
		if(instance == null){
			instance = new Search();
		}
		return instance;
	}

	private void initialize() {
		// Read the developer key from the properties file.
		/*
		properties = new Properties();
		try {
			InputStream in = Search.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
			properties.load(in);

		} catch (IOException e) {
			System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
			+ " : " + e.getMessage());
			System.exit(1);
		}
		*/
		// This object is used to make YouTube Data API requests. The last
		// argument is required, but since we don't need anything
		// initialized when the HttpRequest is initialized, we override
		// the interface and provide a no-op function.
		youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
			public void initialize(HttpRequest request) throws IOException {
			}
		}).setApplicationName("youtube-cmdline-search-sample").build();
	}

	public List<SearchResult> executeQuery(String queryTerm, String apiKey) {
		try {
			// Define the API request for retrieving search results.
			YouTube.Search.List search = youtube.search().list("id,snippet");

			// Set your developer key from the {{ Google Cloud Console }} for
			// non-authenticated requests. See:
			// {{ https://cloud.google.com/console }}
			//String apiKey = properties.getProperty("youtube.apikey");
			search.setKey(apiKey);
			search.setQ(queryTerm);

			// Restrict the search results to only include videos. See:
			// https://developers.google.com/youtube/v3/docs/search/list#type
			search.setType("video");

			// To increase efficiency, only retrieve the fields that the
			// application uses.
			search.setFields("items(etag,id/kind,id/videoId,snippet/title,snippet/description,"
					+ "snippet/thumbnails/default/url, contentDetails/licensedContent)");
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
			System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
					+ e.getDetails().getMessage());
		} catch (IOException e) {
			System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return null;
	}

	public List<SearchResult> getVideoByPublishDate(String channelId, DateTime publishAfter, 
			long MaxResult, String apiKey) {
		List<SearchResult> searchResult = new ArrayList<SearchResult>();
		try{
			// Define the API request for retrieving search results.
			YouTube.Search.List search = youtube.search().list("id,snippet");
//			String apiKey = properties.getProperty("youtube.apikey");
			search.setKey(apiKey);
			search.setType("video");
			search.setChannelId(channelId);
			search.setFields("items(id/kind,id/videoId,snippet/title,snippet/description,"
					+ "snippet/thumbnails/default/url)");
			search.setPublishedAfter(publishAfter);
			search.setMaxResults((long) MaxResult);
			search.setOrder("date");
			// Call the API and print results.
			SearchListResponse searchResponse = search.execute();
			searchResult = searchResponse.getItems();
			//prettyPrint(searchResult.iterator(), "");
		} catch (GoogleJsonResponseException e) {
			System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
					+ e.getDetails().getMessage());
		} catch (IOException e) {
			System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return searchResult;
	}

	public List<Video> getVideoInfo(String videoId, String key) {
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
			YouTube.Videos.List listVideosRequest = youtube.videos().list("snippet,contentDetails").setId(videoId);
			listVideosRequest.setKey(key);
			VideoListResponse listResponse = listVideosRequest.execute();

			videoList = listResponse.getItems();
		} catch (GoogleJsonResponseException e) {
			System.out.println("ERR_VIDEO INFO|There was a service error: " + e.getDetails().getCode() + " : "
					+ e.getDetails().getMessage() + ":" + e.toString());
			return videoList;
		} catch (IOException e) {
			System.out.println("ERR_VIDEO INFO|There was an IO error: " + e.getCause() + " : " + e.getMessage());
			return videoList;
		} catch (Exception ex) {
			System.out.println("ERR_VIDEO INFO|" + ex.toString());
			return videoList;
		}
		return videoList;
	}

	public List<Video> getVideoLicense(String videoId, String key) {
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
			YouTube.Videos.List listVideosRequest = youtube.videos().list("contentDetails").setId(videoId);
			listVideosRequest.setKey(key);
			VideoListResponse listResponse = listVideosRequest.execute();

			videoList = listResponse.getItems();
		} catch (GoogleJsonResponseException e) {
			System.out.println("ERR_VIDEO INFO|There was a service error: " + e.getDetails().getCode() + " : "
					+ e.getDetails().getMessage() + ":" + e.toString());
			return videoList;
		} catch (IOException e) {
			System.out.println("ERR_VIDEO INFO|There was an IO error: " + e.getCause() + " : " + e.getMessage());
			return videoList;
		} catch (Exception ex) {
			System.out.println("ERR_VIDEO INFO|" + ex.toString());
			return videoList;
		}
		return videoList;
	}

	public ChannelListResponse getChannelInfo(String channelId, String apiKey)
	{
		ChannelListResponse response = null;
		try {
			HashMap<String, String> parameters = new HashMap<>();
			parameters.put("part", "snippet,contentDetails,statistics");
			parameters.put("id", channelId);

			YouTube.Channels.List channelsListByIdRequest = youtube.channels().list(parameters.get("part").toString());
			channelsListByIdRequest.setKey(apiKey);
			if (parameters.containsKey("id") && parameters.get("id") != "") {
				channelsListByIdRequest.setId(parameters.get("id").toString());
			}

			response = channelsListByIdRequest.execute();
			//System.out.println(response);
		}catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
		return response;
	}
}
