package spiderboot.render;

import SpiderCorba.SpiderDefinePackage.VideoInfo;

public class DataDefine {
	public class RenderJobData
	{
		public int jobId;
		public VideoInfo vInfo;
		public RenderJobData(int jobId, VideoInfo vInfo) {
			super();
			this.jobId = jobId;
			this.vInfo = vInfo;
		}
	}
}
