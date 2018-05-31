package com.spider.corba;

// SpiderDownloadServer.java
// Copyright and License 
import SpiderDownloadApp.*;
import spiderboot.data.DataController;

import org.omg.CosNaming.*;
import org.apache.log4j.Logger;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import com.spider.main.*;


class DownloadImpl extends SpiderFootSidePOA {
	private static final Logger logger = Logger.getLogger(DownloadImpl.class);

	@Override
	public boolean createMappingChannel(int timerId, String cHomeId, String cMonitorId, String downloadClusterId, int timerInterval) 
	{
		logger.info("DownloadImpl::createMappingChannel: >>> ");
		logger.info("timer id = " + timerId);
		logger.info("monitor id = " + cMonitorId);
		logger.info("time interval id = " + timerInterval);
		
		if(downloadClusterId.equals(DataController.getInstance().downloadConfig.appId))
		{
			DownloadTimerManager.getInstance().createDownloadTimer(timerId, cHomeId, cMonitorId, timerInterval);	
		}
		return false;
	}

	@Override
	public boolean modifyMappingChannel(int timerId, String cHomeId, String cMonitorId, String downloadClusterId, int timerInterval, int synStatus) 
	{
		logger.error("DownloadImpl::modifyMappingChannel : >>>");
		//check timer id
		DownloadTimerManager.getInstance().modifyMappingChannel(timerId, cHomeId, cMonitorId, downloadClusterId, timerInterval, synStatus);	
		return false;
	}

	@Override
	public boolean deleteMappingChannel(int timerId, String downloadClusterId) {
		logger.info("DownloadImpl::deleteMappingChannel : >>>");
		DownloadTimerManager.getInstance().deleteDownloadTimer(timerId, downloadClusterId);	
		return false;
	}
}


public class DownloadCorbaServer {

	public final String COMPONENT_NAME = "Spider_Foot_Download_Server";
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
