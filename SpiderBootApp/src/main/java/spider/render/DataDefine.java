package spider.render;

import corba.variableDefinePackage.VideoInfo;

public class DataDefine {
	public class RenderJobData
	{
		public int jobId;
		public VideoInfo vInfo;
		public RenderJobData(int jobId, corba.variableDefinePackage.VideoInfo vInfo2) {
			super();
			this.jobId = jobId;
			this.vInfo = vInfo2;
		}
	}
}
