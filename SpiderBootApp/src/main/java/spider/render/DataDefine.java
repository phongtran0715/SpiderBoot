package spider.render;

import SpiderCorba.SpiderDefinePackage.VideoInfo;

public class DataDefine {
	public class RenderJobData
	{
		public int jobId;
		public VideoInfo vInfo;
		public RenderJobData(int jobId, SpiderCorba.SpiderDefinePackage.VideoInfo vInfo2) {
			super();
			this.jobId = jobId;
			this.vInfo = vInfo2;
		}
	}
}
