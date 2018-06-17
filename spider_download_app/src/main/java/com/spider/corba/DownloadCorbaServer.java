package com.spider.corba;

import spiderboot.data.DataController;

import org.omg.CosNaming.*;
import org.apache.log4j.Logger;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import com.spider.main.*;
import SpiderCorba.DownloadSide;
import SpiderCorba.DownloadSideHelper;
import SpiderCorba.DownloadSidePOA;


class DownloadImpl extends DownloadSidePOA {
	private static final Logger logger = Logger.getLogger(DownloadImpl.class);

	@Override
	public boolean createDownloadTimer(int timerId, int timerType,
			SpiderCorba.SpiderDefinePackage.DownloadConfig downloadCfg) {
		logger.info("Create new download timer id = " + timerId + " timer type = " + timerType);
		DownloadTimerManager.getInstance().createDownloadTimer(timerId, timerType, downloadCfg);	
		return true;
	}

	@Override
	public boolean modifyDownloadTimer(int timerId, int timerType,
			SpiderCorba.SpiderDefinePackage.DownloadConfig downloadCfg) {
		logger.info("Modify download timer id = " + timerId + " timer type = " + timerType);		
		DownloadTimerManager.getInstance().modifyDownloadTimer(timerId, timerType, downloadCfg);
		return true;
	}

	@Override
	public boolean deleteDowloadTimer(int timerId, int timerType) {
		logger.info("Delete download timer id = " + timerId + " timer type = " + timerType);
		DownloadTimerManager.getInstance().deleteDownloadTimer(timerId, timerType);
		return true;
	}

	@Override
	public boolean deleteDownloadedVideo(int jobId) {
		logger.info("Delete video by jobId = " + jobId);
		return true;
	}
}


public class DownloadCorbaServer {
	private static final Logger logger = Logger.getLogger(DownloadCorbaServer.class);

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
			DownloadImpl downloadImpl = new DownloadImpl(); 

			// get object reference from the servant
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(downloadImpl);
			DownloadSide href = DownloadSideHelper.narrow(ref);

			// get the root naming context
			// NameService invokes the name service
			org.omg.CORBA.Object objRef =
					orb.resolve_initial_references("NameService");
			// Use NamingContextExt which is part of the Interoperable
			// Naming Service (INS) specification.
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// bind the Object Reference in Naming
			String contextName =  DataController.getInstance().downloadConfig.appId;
			logger.info("Carba context name = " + contextName);
			NameComponent path[] = ncRef.to_name(contextName);
			ncRef.rebind(path, href);

			logger.info("Download server ready and waiting ...");

			// wait for invocations from clients
			orb.run();
			logger.info("Download server Exiting ...");
			isSuccess = true;
		}
		catch (Exception e) 
		{
			logger.error("ERROR: " + e.toString());
		}
		return isSuccess;
	}
}
