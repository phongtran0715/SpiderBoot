package org.spider.client;

public class GoogleAccountObject {
	private int id;
	private String userName;
	private String api;
	private String clientSecret;
	private String clientId;
	private int accountType;
	private String appName;
	
	public GoogleAccountObject()
	{
		
	}
	
	public GoogleAccountObject(int id, String userName, String api, 
			String clientSecret, String clientId, int accountType, String appName)
	{
		this.id = id;
		this.userName = userName;
		this.api =  api;
		this.clientSecret = clientSecret;
		this.clientId = clientId;
		this.accountType = accountType;
		this.appName = appName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}	
}
