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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.samples.youtube.cmdline.Auth;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.common.collect.Lists;

/**
 * Print a list of videos uploaded to the authenticated user's YouTube channel.
 *
 * @author Jeremy Walker
 */
public class ChannelInfo {

    /**
     * Define a global instance of a Youtube object, which will be used
     * to make YouTube Data API requests.
     */
    private static YouTube youtube;

    /**
     * Authorize the user, call the youtube.channels.list method to retrieve
     * the playlist ID for the list of videos uploaded to the user's channel,
     * and then call the youtube.playlistItems.list method to retrieve the
     * list of videos in that playlist.
     *
     * @param args command line args (not used).
     */
    public List<Channel> getChannelInfor(String channelId) {

    	List<Channel> cResult = null;
        // This OAuth 2.0 access scope allows for read-only access to the
        // authenticated user's account, but not other types of account access.
        List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube.readonly");

        try {
            // Authorize the request.
            Credential credential = Auth.authorize(scopes, "myuploads");

            // This object is used to make YouTube Data API requests.
            youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, credential).setApplicationName(
                    "youtube-cmdline-myuploads-sample").build();

            // Call the API's channels.list method to retrieve the
            // resource that represents the authenticated user's channel.
            // In the API response, only include channel information needed for
            // this use case. The channel's contentDetails part contains
            // playlist IDs relevant to the channel, including the ID for the
            // list that contains videos uploaded to the channel.
            YouTube.Channels.List channelRequest = youtube.channels().list("contentDetails");
            channelRequest.setMine(true);
            channelRequest.setFields("items/contentDetails,nextPageToken,pageInfo");
            ChannelListResponse channelResult = channelRequest.execute();

            List<Channel> channelsList = channelResult.getItems();

            if (channelsList != null) {
                // The user's default channel is the first item in the list.
                // Extract the playlist ID for the channel's videos from the
                // API response.

                // Define a list to store items in the list of uploaded videos.
                List<Channel> channelItemList = new ArrayList<Channel>();
                
                HashMap<String, String> parameters = new HashMap<>();
                parameters.put("part", "snippet,contentDetails,statistics");
                parameters.put("id", channelId);
                YouTube.Channels.List channelsListByIdRequest = youtube.channels().list(parameters.get("part").toString());
                if (parameters.containsKey("id") && parameters.get("id") != "") {
                    channelsListByIdRequest.setId(parameters.get("id").toString());
                    String nextToken = "";

                    // Call the API one or more times to retrieve all items in the
                    // list. As long as the API response returns a nextPageToken,
                    // there are still more items to retrieve.
                    do {
                    	channelsListByIdRequest.setPageToken(nextToken);
                    	ChannelListResponse response = channelsListByIdRequest.execute();
                    	channelItemList.addAll(response.getItems());
                        nextToken = response.getNextPageToken();
                    } while (nextToken != null);

                    // Prints information about the results.
                    //prettyPrint(channelItemList.size(), channelItemList.iterator());
                    cResult = channelItemList;
                }
            }

        } catch (GoogleJsonResponseException e) {
            e.printStackTrace();
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());

        } catch (Throwable t) {
            t.printStackTrace();
        }
        return cResult;
    }

    /*
     * Print information about all of the items in the playlist.
     *
     * @param size size of list
     *
     * @param iterator of Playlist Items from uploaded Playlist
     */
    private static void prettyPrint(int size, Iterator<Channel> channelEntries) {
        System.out.println("=============================================================");
        System.out.println("\t\tTotal Videos Uploaded: " + size);
        System.out.println("=============================================================\n");

        while (channelEntries.hasNext()) {
            Channel channelItem = channelEntries.next();
            System.out.println(" channel name  = " + channelItem.getSnippet().getTitle());
            System.out.println(" thumbnail  = " + channelItem.getSnippet().getThumbnails().getDefault().getUrl());
            System.out.println(" video count  = " + channelItem.getStatistics().getVideoCount());
            System.out.println(" view count  = " + channelItem.getStatistics().getViewCount());
            
            System.out.println(" upload date = " + channelItem.getSnippet().getPublishedAt());
            System.out.println(" uploaded playlist = " + channelItem.getContentDetails().getRelatedPlaylists().getUploads());
            System.out.println("\n-------------------------------------------------------------\n");
        }
    }
}
