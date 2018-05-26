package org.spider.client;

public class ClusterObject {
	String clusterId;
	String clusterName;
	String ipAddress;
	int port;
	
	public ClusterObject() {
	}

	public ClusterObject(String clusterId, 
			String clusterName, String ipAddress, int port) 
	{
		super();
		this.clusterId = clusterId;
		this.clusterName = clusterName;
		this.ipAddress = ipAddress;
		this.port = port;
	}

	public String getClusterId() {
		return clusterId;
	}

	public void setClusterId(String clusterId) {
		this.clusterId = clusterId;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}