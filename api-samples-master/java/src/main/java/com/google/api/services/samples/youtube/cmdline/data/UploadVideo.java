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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.googleapis.media.MediaHttpUploaderProgressListener;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.samples.youtube.cmdline.Auth;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoSnippet;
import com.google.api.services.youtube.model.VideoStatus;
import com.google.common.collect.Lists;

/**
 * Upload a video to the authenticated user's channel. Use OAuth 2.0 to
 * authorize the request. Note that you must add your video files to the
 * project folder to upload them with this application.
 *
 * @author Jeremy Walker
 */
public class UploadVideo {

	/**
	 * Define a global instance of a Youtube object, which will be used
	 * to make YouTube Data API requests.
	 */
	private static YouTube youtube;

	/**
	 * Auth class
	 */
	Auth auth = new Auth();

	private static String storeFile = null;
	private static String clientSecretsFile = null;

	/**
	 * Define a global variable that specifies the MIME type of the video
	 * being uploaded.
	 */
	private static final String VIDEO_FILE_FORMAT = "video/*";

	public static void setStoreFile(String filePath) {
		storeFile = filePath;
	}

	public static void setclientSecretsFile(String filePath) {
		clientSecretsFile = filePath;
	}
	/**
	 * Upload the user-selected video to the user's YouTube channel. The code
	 * looks for the video in the application's project folder and uses OAuth
	 * 2.0 to authorize the API request.
	 *
	 * @param args command line args (not used).
	 */
	public static boolean execute(String title, String description, String etags, 
			String vLocation, String privacyStatus) {
		boolean isSuccess = false;

		// This OAuth 2.0 access scope allows an application to upload files
		// to the authenticated user's YouTube channel, but doesn't allow
		// other types of access.
		List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube.upload");

		try {
			// Authorize the request.
			Credential credential;
			if(storeFile == null)
			{
				System.out.println("Can not found store file to get upload auth!");
				return isSuccess;
			}
			else
			{
				System.out.println("Function UploadVideo::execute :" + clientSecretsFile );
				Auth.setClientSecrect(clientSecretsFile);
				credential = Auth.authorize(scopes, storeFile); 
			}

			// This object is used to make YouTube Data API requests.
			youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, credential).setApplicationName(
					"youtube-cmdline-uploadvideo-sample").build();

			// Add extra information to the video before uploading.
			Video videoObjectDefiningMetadata = new Video();

			// Set the video to be publicly visible. This is the default
			// setting. Other supporting settings are "unlisted" and "private."
			VideoStatus status = new VideoStatus();
			status.setPrivacyStatus(privacyStatus);
			videoObjectDefiningMetadata.setStatus(status);

			// Most of the video's metadata is set on the VideoSnippet object.
			VideoSnippet snippet = new VideoSnippet();

			// This code uses a Calendar instance to create a unique name and
			// description for test purposes so that you can easily upload
			// multiple files. You should remove this code from your project
			// and use your own standard names instead.
			Calendar cal = Calendar.getInstance();
			snippet.setTitle(title);
			snippet.setDescription(description);

			// Set the keyword tags that you want to associate with the video.

			List<String> tags = new ArrayList<String>();
			String[] partStrs = etags.split("\\r?\\n");
			for (String partStr : partStrs) {
				tags.add(partStr);
			}
			snippet.setTags(tags);

			// Add the completed snippet object to the video resource.
			videoObjectDefiningMetadata.setSnippet(snippet);

			//            InputStreamContent mediaContent = new InputStreamContent(VIDEO_FILE_FORMAT,
			//                    UploadVideo.class.getResourceAsStream("/sample-video.mp4"));

			File initialFile = new File(vLocation);
			InputStream targetStream = new FileInputStream(initialFile);
			InputStreamContent mediaContent = new InputStreamContent(VIDEO_FILE_FORMAT,targetStream);

			// Insert the video. The command sends three arguments. The first
			// specifies which information the API request is setting and which
			// information the API response should return. The second argument
			// is the video resource that contains metadata about the new video.
			// The third argument is the actual video content.
			YouTube.Videos.Insert videoInsert = youtube.videos()
					.insert("snippet,statistics,status", videoObjectDefiningMetadata, mediaContent);

			// Set the upload type and add an event listener.
			MediaHttpUploader uploader = videoInsert.getMediaHttpUploader();

			// Indicate whether direct media upload is enabled. A value of
			// "True" indicates that direct media upload is enabled and that
			// the entire media content will be uploaded in a single request.
			// A value of "False," which is the default, indicates that the
			// request will use the resumable media upload protocol, which
			// supports the ability to resume an upload operation after a
			// network interruption or other transmission failure, saving
			// time and bandwidth in the event of network failures.
			uploader.setDirectUploadEnabled(false);

			MediaHttpUploaderProgressListener progressListener = new MediaHttpUploaderProgressListener() {
				public void progressChanged(MediaHttpUploader uploader) throws IOException {
					switch (uploader.getUploadState()) {
					case INITIATION_STARTED:
						System.out.println("Initiation Started");
						break;
					case INITIATION_COMPLETE:
						System.out.println("Initiation Completed");
						break;
					case MEDIA_IN_PROGRESS:
						System.out.println("Upload in progress");
						System.out.println("Upload percentage: " + uploader.getNumBytesUploaded());
						break;
					case MEDIA_COMPLETE:
						System.out.println("Upload Completed!");
						break;
					case NOT_STARTED:
						System.out.println("Upload Not Started!");
						break;
					}
				}
			};
			uploader.setProgressListener(progressListener);

			// Call the API and upload the video.
			Video returnedVideo = videoInsert.execute();

			// Print data about the newly inserted video from the API response.
			System.out.println("\n================== Uploaded Video ==================\n");
			System.out.println("  - Id: " + returnedVideo.getId());
			System.out.println("  - Title: " + returnedVideo.getSnippet().getTitle());
			System.out.println("  - Tags: " + returnedVideo.getSnippet().getTags());
			System.out.println("  - Privacy Status: " + returnedVideo.getStatus().getPrivacyStatus());
			System.out.println("  - Video Count: " + returnedVideo.getStatistics().getViewCount());
			isSuccess = true;

		} catch (GoogleJsonResponseException e) {
			System.err.println("GoogleJsonResponseException code: " + e.getDetails().getCode() + " : "
					+ e.getDetails().getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
			e.printStackTrace();
		} catch (Throwable t) {
			System.err.println("Throwable: " + t.getMessage());
			t.printStackTrace();
		}
		return isSuccess;
	}
}
