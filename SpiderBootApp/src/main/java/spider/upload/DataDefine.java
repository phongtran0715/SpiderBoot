package spider.upload;

import corba.variableDefinePackage.VideoInfo;

public class DataDefine {
	public class UploadJobData
	{
		public int jobId;
		public VideoInfo vInfo;
		public UploadJobData(int jobId, VideoInfo vInfo) {
			super();
			this.jobId = jobId;
			this.vInfo = vInfo;
		}
	}

	public final String[] IGNORE_KEYWORD = {
			"http", "https",
			"follow", "Follow", "FOLLOW",
			"subscribe", "Subscribe", "SUBSCIBE",
			"facebook", "Facebook", "FACEBOK",
			"fanpage", "Fanpage", "FANPAGE",
			"google+", "Google+", "GOOGLE+", 
			"instagram", "Instagram", "INSTAGRAM",
			"twitter", "Twitter", "TWITTER",
			"reup", "Reup", "REUP", "re-upload"	
	};
}
