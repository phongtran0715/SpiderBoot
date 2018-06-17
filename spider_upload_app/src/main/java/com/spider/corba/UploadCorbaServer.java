package com.spider.corba;

import org.omg.CosNaming.*;
import org.apache.log4j.Logger;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import com.spider.main.UploadTimerManager;
import SpiderCorba.UploadSide;
import SpiderCorba.UploadSideHelper;
import SpiderCorba.UploadSidePOA;
import SpiderCorba.SpiderDefinePackage.VideoInfo;
import spiderboot.data.DataController;

class UploadImpl extends UploadSidePOA {
	private static final Logger logger = Logger.getLogger(UploadImpl.class);

	@Override
	public boolean createUploadTimer(int timerId, int timerType) {
		logger.info("Create new upload timer id = " + timerId + " timer type = " + timerType);
		UploadTimerManager.getInstance().createUploadTimer(timerId, timerType);
		return false;
	}

	@Override
	public boolean deleteUploadTimer(int timerId, int timerType) {
		logger.info("Delete upload timer id = " + timerId + " timer type = " + timerType);
		UploadTimerManager.getInstance().deleteUploadTimer(timerId, timerType);
		return false;
	}

	@Override
	public boolean createUploadJob(int jobId, VideoInfo vInfo) {
		logger.info("Create upload jod id = " + jobId);
		UploadTimerManager.getInstance().createUploadJob(jobId, vInfo);
		return false;
	}
}

public class UploadCorbaServer {
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
			UploadImpl uploadImpl = new UploadImpl(); 

			// get object reference from the servant
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(uploadImpl);
			UploadSide href = UploadSideHelper.narrow(ref);

			// get the root naming context
			// NameService invokes the name service
			org.omg.CORBA.Object objRef =
					orb.resolve_initial_references("NameService");
			// Use NamingContextExt which is part of the Interoperable
			// Naming Service (INS) specification.
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			// bind the Object Reference in Naming
			String contextName = DataController.getInstance().uploadConfig.appId;
			logger.info("6");
			logger.info("Corba context name = " + contextName);
			NameComponent path[] = ncRef.to_name(contextName);
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
