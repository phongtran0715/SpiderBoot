package com.spider.corba;

import org.omg.CosNaming.*;
import org.apache.log4j.Logger;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

import com.spider.main.DataDefine;
import com.spider.main.UploadTimerManager;

import SpiderCorba.UploadSide;
import SpiderCorba.UploadSideHelper;
import SpiderCorba.UploadSidePOA;
import SpiderCorba.SpiderDefinePackage.VideoInfo;
import SpiderCorba.UploadSidePackage.UploadConfig;
import spiderboot.data.DataController;

class UploadImpl extends UploadSidePOA {
	private static final Logger logger = Logger.getLogger(UploadImpl.class);

	@Override
	public boolean createUploadJob(int jobId, VideoInfo vInfo, UploadConfig uploadCfg) {
		logger.info("createUploadJob:: jobId = " + jobId);
		//DataDefine.UploadJobData jobData = new DataDefine().new UploadJobData(jobId, vInfo, uploadCfg);
		//UploadTimerManager.qUploadJob.add(jobData);
		return false;
	}

	@Override
	public boolean deleteUploadJob(int jobId, String uploadClusterId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createUploadTimer(String timerId, String uploadClusterId) {
		// TODO Auto-generated method stub
		logger.info("Create new upload timer id = " + timerId + "upload cluster id = " + uploadClusterId);
		UploadTimerManager.getInstance().createUploadTimer(timerId, uploadClusterId);
		return true;
	}

	@Override
	public boolean modifyUploadTimer(String timerId, String uploadClusterId, int synStatus, UploadConfig uploadCfg) {
		// TODO Auto-generated method stub
		logger.info("Modify upload timer id = " + timerId + "upload cluster id = " + uploadClusterId);
		UploadTimerManager.getInstance().modifyUploadTimer(timerId, uploadClusterId, synStatus, uploadCfg);
		return false;
	}
	
	@Override
	public boolean deleteUploadTimer(String timerId, String uploadClusterId) {
		// TODO Auto-generated method stub
		logger.info("Delete upload timer id = " + timerId + "upload cluster id = " + uploadClusterId);
		UploadTimerManager.getInstance().deleteUploadTimer(timerId, uploadClusterId);
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
			UploadImpl downloadImpl = new UploadImpl(); 

			// get object reference from the servant
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(downloadImpl);
			UploadSide href = UploadSideHelper.narrow(ref);

			// get the root naming context
			// NameService invokes the name service
			org.omg.CORBA.Object objRef =
					orb.resolve_initial_references("NameService");
			// Use NamingContextExt which is part of the Interoperable
			// Naming Service (INS) specification.
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// bind the Object Reference in Naming
			String contextName = DataController.getInstance().renderConfig.appId;
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
