package spiderboot.render;
import java.io.IOException;
import org.apache.log4j.Logger;
import com.spider.corba.RenderCorbaClient;
import com.spider.corba.RenderCorbaServer;
import spiderboot.configuration.RenderProperty;
import spiderboot.data.DataController;

public class MainRender 
{
	private static final Logger logger = Logger.getLogger(MainRender.class);
	public static void main( String[] args ) throws IOException
	{
		if (args.length <= 0){
			logger.error("You must set config file for application");
			return;
		}
		//Load configuration file
		String configFile = args[0];
		final RenderProperty renderConfig = new RenderProperty(configFile);
		DataController.getInstance().setRenderConfigObj(renderConfig);

		//Init corba server

		Thread serverThread = new Thread(){
			public void run(){
				logger.info("Beginning to init render corba server >>>");
				RenderCorbaServer renderServer = new RenderCorbaServer();
				renderServer.initCorba(renderConfig.corbaRef);
			}
		};
		serverThread.start();

		//Init corba client 
		Thread clientThread = new Thread(){
			public void run(){
				logger.info("Beginning to init render corba client >>>");
				RenderCorbaClient renderClient = RenderCorbaClient.getInstance();
				try
				{
					renderClient.renderAppImpl.onRenderStartup(renderConfig.appId);	
				}catch (Exception e) {
					logger.error(e.toString());
				}
			}
		};

		clientThread.start();
		
		logger.info("Render app ID : " + renderConfig.appId);
		//create render timer task (only one timer task)
		RenderTimerManager.getInstance().initTimerTask();
	}
}
