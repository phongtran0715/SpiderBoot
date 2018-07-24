package com.spider.corba;

// Copyright and License 

import org.omg.CosNaming.*;

import SpiderCorba.AgentSide;
import SpiderCorba.AgentSideHelper;

import org.apache.log4j.Logger;
import org.omg.CORBA.*;

public class RenderCorbaClient {

	public AgentSide renderAppImpl;
	public final String COMPONENT_NAME = "AgentSide";
	private  final Logger logger = Logger.getLogger(RenderCorbaClient.class);
	private static RenderCorbaClient instance = null;
	public boolean isSuccess = false;
	
	public static RenderCorbaClient getInstance() {
		if (instance == null) {
			instance = new RenderCorbaClient();
		}
		return instance;
	}

	public boolean initCorba(String refStr) {
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
			renderAppImpl = AgentSideHelper.narrow(obj);
			isSuccess = true;

		} catch (Exception e) {
			logger.error("ERROR : " + e.toString());
		}
		return isSuccess;
	}
}
