package org.spider.object;

public class HomeChannel {
	int id;
	String channelId;
	String channelName;
	String googleAccount;
	String vIntro;
	String vOutro;
	String logo;
	String descTemp;
	String titleTemp;
	
	public HomeChannel()
	{
		
	}
	
	public HomeChannel(int id, String channelId, String channelName, String googleAccount, 
			String vIntro, String vOutro, String logo, String desctemp, String titletemp)
	{
		this.id = id;
		this.channelId = channelId;
		this.channelName = channelName;
		this.googleAccount = googleAccount;
		this.vIntro = vIntro;
		this.vOutro = vOutro;
		this.logo = logo;
		this.descTemp = desctemp;
		this.titleTemp = titletemp;
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

	public String getGoogleAccount() {
		return googleAccount;
	}

	public void setGoogleAccount(String googleAccount) {
		this.googleAccount = googleAccount;
	}

	public String getvIntro() {
		return vIntro;
	}

	public void setvIntro(String vIntro) {
		this.vIntro = vIntro;
	}

	public String getvOutro() {
		return vOutro;
	}

	public void setvOutro(String vOutro) {
		this.vOutro = vOutro;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getDescTemp() {
		return descTemp;
	}

	public void setDescTemp(String descTemp) {
		this.descTemp = descTemp;
	}

	public String getTitleTemp() {
		return titleTemp;
	}

	public void setTitleTemp(String titleTemp) {
		this.titleTemp = titleTemp;
	}
	
}
