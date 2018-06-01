package com.spider.corba;

import org.omg.CosNaming.*;
import org.apache.log4j.Logger;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

import com.spider.main.UploadTimerManager;

import SpiderUploadApp.*;
import SpiderUploadApp.SpiderFootSidePackage.UploadInfo;

class UploadImpl extends SpiderFootSidePOA {
	private static final Logger logger = Logger.getLogger(UploadImpl.class);

	@Override
	public boolean createUploadJob(UploadInfo vInfo) {
		logger.info("createUploadJob:: jobId = " + vInfo.jobId);
		UploadTimerManager.qUploadJob.add(vInfo);
		return true;
	}

}

public class UploadCorbaServer {

	public final String COMPONENT_NAME = "Spider_Foot_Upload_Server";
	private static final Logger logger = Logger.getLogger(UploadCorbaServer.class);

	public boolean initCorba(String refStr) {
		boolean isSuccess = false;
		try 
		{
			String [] agrs = new String[] { "-ORBInitRef", refStr };
			// create and initialize the ORB
			ORB orb = ORB.init(agrs, null);

			// get reference to rootpoa & activate the POAManager
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			// create servant and register it with the ORB
			UploadImpl downloadImpl = new UploadImpl(); 

			// get object reference from the servant
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(downloadImpl);
			SpiderFootSide href = SpiderFootSideHelper.narrow(ref);

			// get the root naming context
			// NameService invokes the name service
			org.omg.CORBA.Object objRef =
					orb.resolve_initial_references("NameService");
			// Use NamingContextExt which is part of the Interoperable
			// Naming Service (INS) specification.
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// bind the Object Reference in Naming
			NameComponent path[] = ncRef.to_name( COMPONENT_NAME );
			ncRef.rebind(path, href);

			logger.info("Upload server ready and waiting ...");

			// wait for invocations from clients
			orb.run();
			logger.info("Upload server exiting ...");
			isSuccess = true;
		}
		catch (Exception e) 
		{
			logger.error("ERROR: " + e.toString());
		}
		return isSuccess;
	}
}
