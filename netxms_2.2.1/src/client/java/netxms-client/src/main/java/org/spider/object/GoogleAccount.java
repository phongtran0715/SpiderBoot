package org.spider.object;

public class GoogleAccount {
	private int id;
	private String userName;
	private String api;
	private String clientSecret;
	private String accountType;
	private String appName;
	
	public GoogleAccount()
	{
		
	}
	
	public GoogleAccount(int id, String userName, String api, String clientSecret, String accountType, String appName)
	{
		this.id = id;
		this.userName = userName;
		this.api =  api;
		this.clientSecret = clientSecret;
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

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
	
}
