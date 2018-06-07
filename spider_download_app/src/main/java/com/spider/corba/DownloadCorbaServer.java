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
import SpiderCorba.DownloadSidePackage.DownloadConfig;


class DownloadImpl extends DownloadSidePOA {
	private static final Logger logger = Logger.getLogger(DownloadImpl.class);

	@Override
	public boolean createDownloadJob(int jobId, DownloadConfig downloadCfg) {
		// TODO Auto-generated method stub
		logger.info("DownloadImpl::createMappingChannel: >>> ");
		logger.info("timer id = " + jobId);
		logger.info("monitor id = " + downloadCfg.cMonitorId);
		logger.info("time interval id = " + downloadCfg.timerInterval);

		if(downloadCfg.downloadClusterId.equals(DataController.getInstance().downloadConfig.appId))
		{
			DownloadTimerManager.getInstance().createDownloadTimer(jobId, downloadCfg.cHomeId, 
					downloadCfg.cMonitorId, downloadCfg.timerInterval);	
		}
		return true;
	}

	@Override
	public boolean modifyDownloadJob(int jobId, DownloadConfig downloadCfg) {
		// TODO Auto-generated method stub
		logger.error("DownloadImpl::modifyMappingChannel : >>>");
		//check timer id
		DownloadTimerManager.getInstance().modifyDownloadTimer(jobId, downloadCfg.cHomeId, downloadCfg.cMonitorId,
				downloadCfg.downloadClusterId, downloadCfg.timerInterval, downloadCfg.synStatus);	
		return true;
	}

	@Override
	public boolean deleteDownloadJob(int jobId, String downloadClusterId) {
		// TODO Auto-generated method stub
		logger.info("DownloadImpl::deleteMappingChannel : >>>");
		DownloadTimerManager.getInstance().deleteDownloadTimer(jobId, downloadClusterId);	
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
			DownloadSide href = DownloadSideHelper.narrow(ref);

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
