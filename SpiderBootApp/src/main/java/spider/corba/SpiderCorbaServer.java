package spider.corba;

import spider.download.DownloadTimerManager;
import spider.main.DataController;
import spider.render.DataDefine;
import spider.render.RenderTimerManager;
import spider.render.DataDefine.RenderJobData;
import spider.upload.UploadTimerManager;

import org.omg.CosNaming.*;
import org.apache.log4j.Logger;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

import corba.SpiderBootSide;
import corba.SpiderBootSideHelper;
import corba.SpiderBootSidePOA;
import corba.variableDefinePackage.VideoInfo;;


class SpiderCorbaImpl extends SpiderBootSidePOA {

	private static final Logger logger = Logger.getLogger(SpiderCorbaImpl.class);
	//Download function
	@Override
	public boolean createDownloadTimer(int timerId, int timerInterval) {
		DownloadTimerManager.getInstance().createDownloadTimer(timerId, timerInterval);	
		return true;
	}

	@Override
	public boolean modifyDownloadTimer(int timerId, int timerInterval, int syncStatus) {
		DownloadTimerManager.getInstance().modifyDownloadTimer(timerId, timerInterval, syncStatus);
		return true;
	}

	@Override
	public boolean deleteDowloadTimer(int timerId) {
		DownloadTimerManager.getInstance().deleteDownloadTimer(timerId);
		return true;
	}

	@Override
	public boolean deleteDownloadedVideo(int jobId) {
		return true;
	}
	
	//Render function
	@Override
	public boolean createRenderJob(int jobId, VideoInfo vInfo) {
		logger.info("createRenderJob:: jobId = " + jobId);
		DataDefine.RenderJobData jobData = new spider.render.DataDefine().new RenderJobData(jobId, vInfo);
		RenderTimerManager.getInstance().addJob(jobData);
		return true;
	}

	@Override
	public boolean deleteRenderJob(int jobId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteRenderdVideo(String vLocation) {
		// TODO Auto-generated method stub
		
	}

	//Upload function
	@Override
	public boolean createUploadTimer(String cHomeId) {
		UploadTimerManager.getInstance().createUploadTimer(cHomeId);
		return false;
	}

	@Override
	public boolean deleteUploadTimer(String cHomeId) {
		UploadTimerManager.getInstance().deleteUploadTimer(cHomeId);
		return true;
	}

	@Override
	public boolean createUploadJob(int jobId, VideoInfo vInfo, String cHomeId) {
		UploadTimerManager.getInstance().createUploadJob(jobId, vInfo, cHomeId);
		return true;
	}

	@Override
	public boolean deleteUploadJob(int jobId, String cHomeId) {
		// TODO Auto-generated method stub
		return false;
	}
}


public class SpiderCorbaServer {
	private final Logger logger = Logger.getLogger(SpiderCorbaServer.class);

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
			SpiderCorbaImpl downloadImpl = new SpiderCorbaImpl(); 

			// get object reference from the servant
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(downloadImpl);
			SpiderBootSide href = SpiderBootSideHelper.narrow(ref);

			// get the root naming context
			// NameService invokes the name service
			org.omg.CORBA.Object objRef =
					orb.resolve_initial_references("NameService");
			// Use NamingContextExt which is part of the Interoperable
			// Naming Service (INS) specification.
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// bind the Object Reference in Naming
			String contextName =  DataController.getInstance().spiderConfig.dAppId;
			logger.info("Corba context name = " + contextName);
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
