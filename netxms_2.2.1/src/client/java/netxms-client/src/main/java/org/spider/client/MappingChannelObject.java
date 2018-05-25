package org.spider.client;

public class MappingChannelObject {
	int id;
	String homeId;
	String homeName;
	String monitorId;
	String monitorName;
	long timeIntervalSync;
	int statusSync;
	String lastSyncTime;
	String downloadClusterId;
	String renderClusterId;
	String uploadClusterId;
	
	public MappingChannelObject() {
	}

	public MappingChannelObject(int id, String homeId, 
			String homeName, String monitorId, String monitorName,
			long timeIntervalSync, int statusSync,
			String lastSyncTime, String downloadClusterId,
			String renderClusterId, String uploadClusterId) {
		super();
		this.id = id;
		this.homeId = homeId;
		this.homeName = homeName;
		this.monitorId = monitorId;
		this.monitorName = monitorName;
		this.timeIntervalSync = timeIntervalSync;
		this.statusSync = statusSync;
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
	public String getHomeName() {
		return homeName;
	}

	public void setHomeName(String homeName) {
		this.homeName = homeName;
	}

	public String getMonitorName() {
		return monitorName;
	}

	public void setMonitorName(String monitorName) {
		this.monitorName = monitorName;
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

	public String getLastSyncTime() {
		return lastSyncTime;
	}

	public void setLastSyncTime(String lastSyncTime) {
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
