package spiderboot.render;

import java.io.IOException;

import com.spider.corba.RenderCorbaClient;
import com.spider.corba.RenderCorbaServer;

import spiderboot.configuration.RenderConfig;
import spiderboot.data.DataController;

public class MainRender 
{
	public static void main( String[] args ) throws IOException
	{
		if (args.length <= 0){
			System.out.println("You must set config file for application");
			return;
		}
		//Load configuration file
		String configFile = args[0];
		final RenderConfig renderConfig = new RenderConfig(configFile);
		DataController.getInstance().setRenderConfigObj(renderConfig);

		//Init corba server

		Thread serverThread = new Thread(){
			public void run(){
				System.out.println("Beginning to init render corba server...");
				RenderCorbaServer renderServer = new RenderCorbaServer();
				renderServer.initCorba(renderConfig.corbaRef);
			}
		};
		serverThread.start();

		//Init corba client 
		Thread clientThread = new Thread(){
			public void run(){
				System.out.println("Beginning to init render corba client...");
				RenderCorbaClient renderClient = new RenderCorbaClient();
				boolean isInitClient = renderClient.initCorba(renderConfig.corbaRef);
				if(isInitClient == false)
				{
					System.out.println("Error! Can not init render corba client connection.");
				}else {
					System.out.println("Init render corba client connection successful!");

					System.out.println("Notify startup status to agent");
					try
					{
						System.out.println("appID = " + renderConfig.appId);
						renderClient.renderAppImpl.onRenderStartup(renderConfig.appId);	
					}catch (Exception e) {
						System.err.println(e.toString());
					}
				}
			}
		};

		clientThread.start();
		
		//create render timer task (only one timer task)
		RenderTimerManager.getInstance().initTimerTask();
	}
}
