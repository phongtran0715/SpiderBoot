package com.spider.corba;

// Copyright and License 
import org.omg.CosNaming.*;

import SpiderCorba.AgentSide;
import SpiderCorba.AgentSideHelper;
import spiderboot.configuration.DownloadProperty;
import spiderboot.data.DataController;

import org.apache.log4j.Logger;
import org.omg.CORBA.*;

public class DownloadCorbaClient {

	public AgentSide downloadAppImpl = null;
	public final String COMPONENT_NAME = "AgentSide";
	Logger logger = Logger.getLogger(DownloadCorbaClient.class);
	static DownloadCorbaClient instance = null;
	ORB orb = null;

	public DownloadCorbaClient()
	{
		DownloadProperty appProperty = DataController.getInstance().downloadConfig;
		initCorba(appProperty.corbaRef);
	}

	public static DownloadCorbaClient getInstance() {
		if (instance == null) {
			instance = new DownloadCorbaClient();
		}
		return instance;
	}

	public void initCorba(String refStr) {
		try{
			// create and initialize the ORB
			String [] args = new String[] { "-ORBInitRef", refStr };
			orb = ORB.init(args, null);

			// get the root naming context
			org.omg.CORBA.Object objRef = 
					orb.resolve_initial_references("NameService");
			// Use NamingContextExt instead of NamingContext. This is 
			// part of the Interoperable naming Service.  
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// lookup name
			org.omg.CORBA.Object obj = ncRef.resolve_str(COMPONENT_NAME);
			downloadAppImpl = AgentSideHelper.narrow(obj);

		} catch (Exception e) {
			logger.error("ERROR : " + e.toString());
		}
	}
	
	public void resolveAgain() {
		try {
			org.omg.CORBA.Object objRef = 
					orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			org.omg.CORBA.Object obj = ncRef.resolve_str(COMPONENT_NAME);
			downloadAppImpl = AgentSideHelper.narrow(obj);

		}catch (Exception e) {
			logger.error("ERROR : " + e.toString());
		}
	}
}
