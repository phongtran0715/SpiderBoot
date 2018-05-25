package com.spider.corba;

// SpiderDownloadServer.java
// Copyright and License 
import SpiderDownloadApp.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import com.spider.main.*;


class DownloadImpl extends SpiderFootSidePOA {
	private ORB orb;

	public void setORB(ORB orb_val) {
		orb = orb_val; 
	}
	
	@Override
	public boolean createMappingChannel(int timerId, String cHomeId, String cMonitorId, int timerInterval) 
	{
		System.out.println("DownloadImpl::createMappingChannel ");
		System.out.println("timer id = " + timerId);
		System.out.println("home channel id = " + cHomeId);
		System.out.println("monitor id = " + cMonitorId);
		System.out.println("time interval id = " + timerInterval);
		DownloadTimerManager.getInstance().createDownloadTimer(timerId, cHomeId, cMonitorId, timerInterval);
		return false;
	}

	@Override
	public boolean modifyMappingChannel(int timerId, String cHomeId, String cMonitorId, int timerInterval, int synStatus) 
	{
		System.out.println("DownloadImpl::modifyMappingChannel ");
		DownloadTimerManager.getInstance().modifyMappingChannel(timerId, cHomeId, cMonitorId, timerInterval, synStatus);
		return false;
	}

	@Override
	public boolean deleteMappingChannel(int timerId) {
		System.out.println("DownloadImpl::deleteMappingChannel ");
		DownloadTimerManager.getInstance().deleteDownloadTimer(timerId);
		return false;
	}
}


public class DownloadCorbaServer {

	public final String COMPONENT_NAME = "Spider_Foot_Download_Server";
	public final String PORT = "2809";
	public final String HOST = "localhost";
	public DownloadCorbaServer()
	{
		//default constructor
	}

	public boolean initCorba() {
		boolean isSuccess = false;
		try 
		{
			//String param = " -ORBInitRef NameService=corbaloc::localhost:2809/NameService";
			String [] agrs = new String[] { "-ORBInitRef", "NameService=corbaloc::localhost:2809/NameService" };
			// create and initialize the ORB
			ORB orb = ORB.init(agrs, null);

			// get reference to rootpoa & activate the POAManager
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			// create servant and register it with the ORB
			DownloadImpl downloadImpl = new DownloadImpl();
			downloadImpl.setORB(orb); 

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

			System.out.println("Download server ready and waiting ...");

			// wait for invocations from clients
			orb.run();
			System.out.println("Download server Exiting ...");
			isSuccess = true;
		}
		catch (Exception e) 
		{
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}
		return isSuccess;
	}
}
