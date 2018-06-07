package spiderboot.render;

import SpiderCorba.RenderSidePackage.RenderConfig;
import SpiderCorba.SpiderDefinePackage.VideoInfo;

public class DataDefine {
	public class RenderJobData
	{
		public int jobId;
		public VideoInfo vInfo;
		public RenderConfig renderCfg;
		public RenderJobData(int jobId, VideoInfo vInfo, RenderConfig renderCfg) {
			super();
			this.jobId = jobId;
			this.vInfo = vInfo;
			this.renderCfg = renderCfg;
		}
	}
}
