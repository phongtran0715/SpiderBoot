package com.spider.corba;

// Copyright and License 
import org.omg.CosNaming.*;

import SpiderCorba.AgentSide;
import SpiderCorba.AgentSideHelper;
import spiderboot.configuration.UploadProperty;
import spiderboot.data.DataController;

import org.apache.log4j.Logger;
import org.omg.CORBA.*;

public class UploadCorbaClient {

	public AgentSide uploadAppImpl;
	public final String COMPONENT_NAME = "AgentSide";
	Logger logger = Logger.getLogger(UploadCorbaClient.class);
	static UploadCorbaClient instance = null;
	ORB orb = null;
	
	public UploadCorbaClient()
	{
		UploadProperty uploadProperty = DataController.getInstance().uploadConfig;
		initCorba(uploadProperty.corbaRef);
	}
	
	public static UploadCorbaClient getInstance() {
		if (instance == null) {
			instance = new UploadCorbaClient();
		}
		return instance;
	}

	public void initCorba(String refStr) {
		logger.info("Function initCorba with refStr = " + refStr);
		try{
			// create and initialize the ORB
			String [] args = new String[] { "-ORBInitRef", refStr };
			ORB orb = ORB.init(args, null);

			// get the root naming context
			org.omg.CORBA.Object objRef = 
					orb.resolve_initial_references("NameService");
			// Use NamingContextExt instead of NamingContext. This is 
			// part of the Interoperable naming Service.  
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// lookup name
			org.omg.CORBA.Object obj = ncRef.resolve_str(COMPONENT_NAME);
			uploadAppImpl = AgentSideHelper.narrow(obj);

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
			uploadAppImpl = AgentSideHelper.narrow(obj);

		}catch (Exception e) {
			logger.error("ERROR : " + e.toString());
		}
	}
}
