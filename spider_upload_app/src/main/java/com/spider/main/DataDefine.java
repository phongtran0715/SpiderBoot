package com.spider.main;

import SpiderCorba.SpiderDefinePackage.VideoInfo;
import SpiderCorba.UploadSidePackage.UploadConfig;

public class DataDefine {
	public class UploadJobData
	{
		public int jobId;
		public VideoInfo vInfo;
		public UploadConfig uploadCfg;
		public UploadJobData(int jobId, VideoInfo vInfo, UploadConfig uploadCfg) {
			super();
			this.jobId = jobId;
			this.vInfo = vInfo;
			this.uploadCfg = uploadCfg;
		}
	}
}
