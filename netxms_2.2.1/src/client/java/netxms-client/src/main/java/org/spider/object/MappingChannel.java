package org.spider.object;

import java.util.Date;

public class MappingChannel {
	int id;
	String homeId;
	String monitorId;
	long timeIntervalSync;
	int statusSync;
	int action;
	Date lastSyncTime;
	String downloadClusterId;
	String renderClusterId;
	String uploadClusterId;
	
	public MappingChannel() {
	}

	public MappingChannel(int id, String homeId, String monitorId,
			long timeIntervalSync, int statusSync, int action,
			Date lastSyncTime, String downloadClusterId,
			String renderClusterId, String uploadClusterId) {
		super();
		this.id = id;
		this.homeId = homeId;
		this.monitorId = monitorId;
		this.timeIntervalSync = timeIntervalSync;
		this.statusSync = statusSync;
		this.action = action;
		this.lastSyncTime = lastSyncTime;
		this.downloadClusterId = downloadClusterId;
		this.renderClusterId = renderClusterId;
		this.uploadClusterId = uploadClusterId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHomeId() {
		return homeId;
	}

	public void setHomeId(String homeId) {
		this.homeId = homeId;
	}

	public String getMonitorId() {
		return monitorId;
	}

	public void setMonitorId(String monitorId) {
		this.monitorId = monitorId;
	}

	public long getTimeIntervalSync() {
		return timeIntervalSync;
	}

	public void setTimeIntervalSync(long timeIntervalSync) {
		this.timeIntervalSync = timeIntervalSync;
	}

	public int getStatusSync() {
		return statusSync;
	}

	public void setStatusSync(int statusSync) {
		this.statusSync = statusSync;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public Date getLastSyncTime() {
		return lastSyncTime;
	}

	public void setLastSyncTime(Date lastSyncTime) {
		this.lastSyncTime = lastSyncTime;
	}

	public String getDownloadClusterId() {
		return downloadClusterId;
	}

	public void setDownloadClusterId(String downloadClusterId) {
		this.downloadClusterId = downloadClusterId;
	}

	public String getRenderClusterId() {
		return renderClusterId;
	}

	public void setRenderClusterId(String renderClusterId) {
		this.renderClusterId = renderClusterId;
	}

	public String getUploadClusterId() {
		return uploadClusterId;
	}

	public void setUploadClusterId(String uploadClusterId) {
		this.uploadClusterId = uploadClusterId;
	}
}
