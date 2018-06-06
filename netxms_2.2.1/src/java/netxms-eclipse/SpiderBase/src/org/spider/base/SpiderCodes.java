package org.spider.base;

public class SpiderCodes {
	public static final int CLUSTER_DOWNLOAD = 1;
	public static final int CLUSTER_RENDER	 = 2;
	public static final int CLUSTER_UPLOAD	 = 3;

	public static final int ACCOUNT_HELPER	 = 1;
	public static final int ACCOUNT_SEO		 = 2;
	public static final int ACCOUNT_ADSEND	 = 3;
	//Spider command codes
	public static final int CMD_SPIDER_START 			   = 0x2000;
	public static final int CMD_GET_GOOGLE_ACCOUNT 		   = 0x2001;
	public static final int CMD_GET_HOME_CHANNEL           = 0x2002;
	public static final int CMD_GET_MONITOR_CHANNEL        = 0x2003;
	public static final int CMD_GET_MAPPING_CHANNEL        = 0x2004;
	public static final int CMD_CREATE_GOOGLE_ACCOUNT      = 0x2005;
	public static final int CMD_CREATE_HOME_CHANNEL        = 0x2006;
	public static final int CMD_CREATE_MONITOR_CHANNEL     = 0x2007;
	public static final int CMD_CREATE_MAPPING_CHANNEL     = 0x2008;
	public static final int CMD_MOD_GOOGLE_ACCOUNT         = 0x2009;
	public static final int CMD_MOD_HOME_CHANNEL           = 0x200A;
	public static final int CMD_MOD_MONITOR_CHANNEL        = 0x200B;
	public static final int CMD_MOD_MAPPING_CHANNEL        = 0x200C;
	public static final int CMD_DEL_GOOGLE_ACCOUNT         = 0x200D;
	public static final int CMD_DEL_HOME_CHANNEL           = 0x200E;
	public static final int CMD_DEL_MONITOR_CHANNEL        = 0x200F;
	public static final int CMD_DEL_MAPPING_CHANNEL        = 0x2010;
	public static final int CMD_GET_CLUSTER       		   = 0x2011;
	public static final int CMD_MOD_CLUSTER       		   = 0x2012;
	public static final int CMD_DEL_CLUSTER       		   = 0x2013;
	public static final int CMD_CREATE_CLUSTER    		   = 0x2014;
	public static final int CMD_SPIDER_END		           = 0x2FFF;

	public static final long VID_SPIDER_START				= 1000;
	//google account variable
	public static final long VID_GOOGLE_START			    = 1001;
	public static final long VID_GOOGLE_RECORD_ID			= 1002;
	public static final long VID_GOOGLE_USER_NAME			= 1003;
	public static final long VID_GOOGLE_API					= 1004;
	public static final long VID_GOOGLE_CLIENT_SECRET		= 1005;
	public static final long VID_GOOGLE_ACCOUNT_TYPE		= 1006;
	public static final long VID_GOOGLE_APP_NAME			= 1007;
	public static final long VID_GOOGLE_CLIENT_ID			= 1008;
	public static final long VID_GOOGLE_END			    	= 1015;
	//end google account

	//home channel variable
	public static final long VID_HOME_CHANNEL_START			= 1016;
	public static final long VID_HOME_CHANNEL_RECORD_ID		= 1017;
	public static final long VID_HOME_CHANNEL_ID			= 1018;
	public static final long VID_HOME_CHANNEL_NAME			= 1019;
	public static final long VID_HOME_CHANNEL_GACCOUNT		= 1020;
	public static final long VID_HOME_CHANNEL_ACCOUNT_ID	= 1021;
	public static final long VID_HOME_CHANNEL_END			= 1030;
	//end home channel variable

	//monitor channel variable
	public static final long VID_MONITOR_CHANNEL_START		= 1031;
	public static final long VID_MONITOR_CHANNEL_ID			= 1032;
	public static final long VID_MONITOR_CHANNEL_NAME		= 1033;
	public static final long VID_MONITOR_CHANNEL_RECORD_ID	= 1034;
	public static final long VID_MONITOR_CHANNEL_END		= 1045;
	//end monitor variable

	//mapping channel variable
	public static final long VID_MAPPING_CHANNEL_START					= 1046;
	public static final long VID_MAPPING_CHANNEL_RECORD_ID				= 1047;
	public static final long VID_MAPPING_CHANNEL_HOME_ID				= 1048;
	public static final long VID_MAPPING_CHANNEL_MONITOR_ID				= 1049;
	public static final long VID_MAPPING_CHANNEL_TIME_SYNC				= 1050;
	public static final long VID_MAPPING_CHANNEL_STATUS_SYNC			= 1051;
	public static final long VID_MAPPING_CHANNEL_ACTION					= 1052;
	public static final long VID_MAPPING_CHANNEL_LAST_SYNC_TIME			= 1053;
	public static final long VID_MAPPING_CHANNEL_DOWNLOAD_CLUSTER_ID	= 1054;
	public static final long VID_MAPPING_CHANNEL_RENDER_CLUSTER_ID		= 1055;
	public static final long VID_MAPPING_CHANNEL_UPLOAD_CLUSTER_ID		= 1056;
	public static final long VID_VIDEO_INTRO							= 1057;
	public static final long VID_VIDEO_OUTRO							= 1058;
	public static final long VID_VIDEO_LOGO								= 1059;
	public static final long VID_VIDEO_TITLE_TEMPLATE					= 1060;
	public static final long VID_VIDEO_DESC_TEMPLATE					= 1061;
	public static final long VID_VIDEO_TAGS_TEMPLATE					= 1062;
	public static final long VID_ENABLE_VIDEO_INTRO						= 1063;
	public static final long VID_ENABLE_VIDEO_OUTRO						= 1064;
	public static final long VID_ENABLE_VIDEO_LOGO						= 1065;
	public static final long VID_ENABLE_TITLE_TEMPLATE					= 1066;
	public static final long VID_ENABLE_DESC_TEMPLATE					= 1067;
	public static final long VID_ENABLE_TAGS_TEMPLATE					= 1068;
	public static final long VID_MAPPING_CHANNEL_END					= 1100;
	//end mapping channel variable

	//cluster variable
	public static final long VID_SPIDER_CLUSTER_START					= 1101;
	public static final long VID_SPIDER_CLUSTER_RECORD_ID				= 1102;
	public static final long VID_SPIDER_CLUSTER_ID						= 1103;
	public static final long VID_SPIDER_CLUSTER_NAME					= 1104;
	public static final long VID_SPIDER_CLUSTER_IP_ADDRESS				= 1105;
	public static final long VID_SPIDER_CLUSTER_PORT					= 1106;
	public static final long VID_SPIDER_CLUSTER_TYPE					= 1107;
	public static final long VID_SPIDER_CLUSTER_END						= 1120;
	//end cluster variable

	public static final long VID_SPIDER_END								= 2000;
}
