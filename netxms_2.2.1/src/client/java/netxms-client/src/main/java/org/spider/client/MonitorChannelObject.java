package org.spider.client;

public class MonitorChannelObject {
	int stt;
	int id;
	String channelId;
	String channelName;
	
	public MonitorChannelObject()
	{
		
	}
	
	public MonitorChannelObject(int stt, int id, String channelId, String channelName) {
		super();
		this.stt = stt;
		this.id = id;
		this.channelId = channelId;
		this.channelName = channelName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public int getStt() {
		return stt;
	}

	public void setStt(int stt) {
		this.stt = stt;
	}
	
}
