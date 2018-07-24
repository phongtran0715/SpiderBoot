package com.spider.corba;

import org.apache.log4j.Logger;
import org.omg.CORBA.IntHolder;
import org.omg.CORBA.ORB;
import org.omg.CORBA.StringHolder;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import com.google.api.services.samples.youtube.cmdline.data.Search;
import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.spider.agent.YoutubeDataController;

import SpiderCorba.YoutubeAgent;
import SpiderCorba.YoutubeAgentHelper;
import SpiderCorba.YoutubeAgentPOA;

class YoutubeAgentImpl extends YoutubeAgentPOA {
	@Override
	public void getChannelInfo(String channelId, StringHolder channelName, IntHolder videoNumber, 
			IntHolder viewNUmber, IntHolder subcriber, IntHolder dateCreated, IntHolder status) {
		// TODO Auto-generated method stub
		ChannelListResponse response = Search.getInstance().getChannelInfo(channelId, 
				YoutubeDataController.getInstance().getYtAgentCfg().apiKey);
		if(response != null)
		{
			java.util.List<Channel> channels = response.getItems();
			for (Channel channel : channels) {
				channelName.value = channel.getSnippet().getTitle();
				videoNumber.value = channel.getStatistics().getVideoCount().intValue();
				viewNUmber.value = channel.getStatistics().getViewCount().intValue();
				subcriber.value = channel.getStatistics().getSubscriberCount().intValue();
				dateCreated.value = 0;
				status.value = 1;
			}
		}
	}
}

public class CorbaServer {
	private final Logger logger = Logger.getLogger(CorbaServer.class);
	private static CorbaServer instance = null;

	public CorbaServer getInstance() {
		if(instance == null)
		{
			instance = new CorbaServer();
		}
		return instance;
	}

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
			YoutubeAgentImpl serverHelperImpl = new YoutubeAgentImpl(); 

			// get object reference from the servant
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(serverHelperImpl);
			YoutubeAgent href = YoutubeAgentHelper.narrow(ref);

			// get the root naming context
			// NameService invokes the name service
			org.omg.CORBA.Object objRef =
					orb.resolve_initial_references("NameService");
			// Use NamingContextExt which is part of the Interoperable
			// Naming Service (INS) specification.
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// bind the Object Reference in Naming
			String contextName = "YoutubeAgent";
			logger.info("Corba context name = " + contextName);
			NameComponent path[] = ncRef.to_name(contextName);
			ncRef.rebind(path, href);
			System.out.println("Corba server ready and waiting ...");
			logger.info("Corba server ready and waiting ...");

			// wait for invocations from clients
			orb.run();
			logger.info("Corba server Exiting ...");
			isSuccess = true;
		}
		catch (Exception e) 
		{
			logger.error("ERROR: " + e.toString());
			e.printStackTrace();
		}
		return isSuccess;
	}
}
