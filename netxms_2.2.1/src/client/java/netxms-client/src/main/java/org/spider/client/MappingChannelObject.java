package org.spider.client;

import org.spider.client.SpiderDefine.MappingConfig;
import org.spider.client.SpiderDefine.RenderConfig;
import org.spider.client.SpiderDefine.UploadConfig;

public class MappingChannelObject {
	MappingConfig mappingConfig;
	RenderConfig renderConfig;
	UploadConfig uploadConfig;

	public MappingChannelObject(MappingConfig mappingConfig, 
			RenderConfig renderConfig, UploadConfig uploadConfig) {
		super();
		this.mappingConfig = mappingConfig;
		this.renderConfig = renderConfig;
		this.uploadConfig = uploadConfig;
	}

	public MappingConfig getMappingConfig() {
		return mappingConfig;
	}

	public void setMappingConfig(MappingConfig mappingConfig) {
		this.mappingConfig = mappingConfig;
	}

	public RenderConfig getRenderConfig() {
		return renderConfig;
	}

	public void setRenderConfig(RenderConfig renderConfig) {
		this.renderConfig = renderConfig;
	}

	public UploadConfig getUploadConfig() {
		return uploadConfig;
	}

	public void setUploadConfig(UploadConfig uploadConfig) {
		this.uploadConfig = uploadConfig;
	}
}
