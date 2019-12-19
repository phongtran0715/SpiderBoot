package com.spider.agent;

import com.spider.config.YoutubeAgentProperty;

public class YoutubeDataController {
	private static YoutubeDataController instance = null;
	private YoutubeAgentProperty ytAgentCfg = null;
	
	public static YoutubeDataController getInstance() {
		if(instance == null)
		{
			instance = new YoutubeDataController();
		}
		return instance;
	}
	
	public YoutubeAgentProperty getYtAgentCfg() {
		return ytAgentCfg;
	}
	public void setYtAgentCfg(YoutubeAgentProperty ytAgentCfg) {
		this.ytAgentCfg = ytAgentCfg;
	}
	
}
