package org.spider.client;

public class SpiderDefine {
	public class MappingConfig
	{
		public int mappingId;
		public String cHomeId; 
		public String cMonitorId;  
		public long    timeSync;
		public int statusSync;
		public String downloadClusterId;
		public String renderClusterId;
		public String uploadClusterId;
		
		public MappingConfig() {
			// TODO Auto-generated constructor stub
		}

		public MappingConfig(int mappingId, String cHomeId, String cMonitorId, 
				long timeSync, int statusSync, String downloadClusterId,
				String renderClusterId, String uploadClusterId) {
			this.mappingId = mappingId;
			this.cHomeId = cHomeId;
			this.cMonitorId = cMonitorId;
			this.timeSync = timeSync;
			this.statusSync = statusSync;
			this.downloadClusterId = downloadClusterId;
			this.renderClusterId = renderClusterId;
			this.uploadClusterId = uploadClusterId;
		}
	}

	public class RenderConfig
	{
		public String vIntro;
		public String vOutro;
		public String vLogo;
		public boolean enableIntro = false;
		public boolean enableOutro = false;
		public boolean enableLogo = false;
		
		public RenderConfig() {
			super();
			// TODO Auto-generated constructor stub
		}

		public RenderConfig(String vIntro, String vOutro, String vLogo,
				boolean enableIntro, boolean enableOutro, boolean enableLogo) {
			super();
			this.vIntro = vIntro;
			this.vOutro = vOutro;
			this.vLogo = vLogo;
			this.enableIntro = enableIntro;
			this.enableOutro = enableOutro;
			this.enableLogo = enableLogo;
		}
	}

	public class UploadConfig
	{
		public String titleTemp;
		public String descTemp;
		public String tagTemp;
		public boolean enableTitle = false;
		public boolean enableDesc = false;
		public boolean enableTag = false;
		
		public UploadConfig() {
			super();
			// TODO Auto-generated constructor stub
		}

		public UploadConfig(String titleTemp, String descTemp, String tagTemp,
				boolean enableTitle, boolean enableDesc, boolean enableTag) {
			super();
			this.titleTemp = titleTemp;
			this.descTemp = descTemp;
			this.tagTemp = tagTemp;
			this.enableTitle = enableTitle;
			this.enableDesc = enableDesc;
			this.enableTag = enableTag;
		}
	}
}
