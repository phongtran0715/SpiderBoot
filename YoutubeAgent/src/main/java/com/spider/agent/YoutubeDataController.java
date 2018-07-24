package com.spider.agent;

import spiderboot.configuration.YoutubeAgentConfig;

public class YoutubeDataController {
	private static YoutubeDataController instance = null;
	private YoutubeAgentConfig ytAgentCfg = null;
	
	public static YoutubeDataController getInstance() {
		if(instance == null)
		{
			instance = new YoutubeDataController();
		}
		return instance;
	}
	
	public YoutubeAgentConfig getYtAgentCfg() {
		return ytAgentCfg;
	}
	public void setYtAgentCfg(YoutubeAgentConfig ytAgentCfg) {
		this.ytAgentCfg = ytAgentCfg;
	}
	
}
