package com.spider.corba;

// Copyright and License 
import SpiderAgentApp.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;

public class DownloadCorbaClient {

	public AgentSide downloadAppImpl;
	public final String COMPONENT_NAME = "AgentSide";
	
	public DownloadCorbaClient()
	{
		//default constructor
	}

	public boolean initCorba(String refStr) {
		boolean isSuccess = false;
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
			String name = "AgentSide";
			org.omg.CORBA.Object obj = ncRef.resolve_str(name);
			downloadAppImpl = AgentSideHelper.narrow(obj);

			System.out.println("Obtained a handle on server object: " + downloadAppImpl);
			isSuccess = true;

		} catch (Exception e) {
			System.out.println("ERROR : " + e) ;
			e.printStackTrace(System.out);
		}
		return isSuccess;
	}
}
