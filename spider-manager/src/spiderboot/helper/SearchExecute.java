package spiderboot.helper;

import java.util.Iterator;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.transaction.Status;

import com.google.api.services.samples.youtube.cmdline.data.Search;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

public class SearchExecute implements Runnable{
	String keyWork;
	private volatile Status status;
	public SearchExecute(String keyWork) {
		// TODO Auto-generated constructor stub
		this.keyWork = keyWork;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Search search = new Search();
		List<SearchResult> searchResult = search.executeQuery(keyWork);
		if(searchResult != null){
			prettyPrint(searchResult.iterator(), keyWork);
			System.out.println("Search completed!");	
		}else{
			System.out.println("Search result is null");
		}
	}

	private void prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query) {
		System.out.println("\n=============================================================");
		System.out.println("   First " + 25 + " videos for search on \"" + query + "\".");
		System.out.println("\n=============================================================");
		if (!iteratorSearchResults.hasNext()) {
			System.out.println(" There aren't any results for your query.");
		}
		while (iteratorSearchResults.hasNext()) {

			SearchResult singleVideo = iteratorSearchResults.next();
			ResourceId rId = singleVideo.getId();

			// Confirm that the result represents a video. Otherwise, the
			// item will not contain a video ID.
			if (rId.getKind().equals("youtube#video")) {
				Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();
				System.out.println(" Video Id : " + rId.getVideoId());
				System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
				System.out.println(" Thumbnail: " + thumbnail.getUrl());
				System.out.println("\n-------------------------------------------------------------");
			}
		}
	}

	//TODO: handle to update search result table
	public void updateGUI(Status status) {
		setStatus(status);
		SwingUtilities.invokeLater(this);
	}

	private synchronized void setStatus(Status status) {
		this.status = status;
	}
}
