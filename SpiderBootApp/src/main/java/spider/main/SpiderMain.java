/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spider.main;

import org.apache.log4j.Logger;

import spider.corba.SpiderCorbaClient;
import spider.corba.SpiderCorbaServer;
import spider.config.SpiderBootProperty;
import spider.main.DataController;
import spider.render.RenderTimerManager;

public class SpiderMain {
	static final Logger logger = Logger.getLogger(SpiderMain.class);
	static SpiderBootProperty spiderConfig = null;

	public static void main(String[] args) {
		if (args.length <= 0) {
			logger.error("You must set config file for application");
			return;
		}
		// Load configuration file
		String configFile = args[0];
		spiderConfig = new SpiderBootProperty(configFile);
		DataController.getInstance().setDownloadConfigObj(spiderConfig);
		
		// Init corba server for download app
		Thread spiderCorbaServer = new Thread() {
			public void run() {
				logger.info("Beginning to init download corba server >>>");
				SpiderCorbaServer server = new SpiderCorbaServer();
				server.initCorba(spiderConfig.corbaRef);
			}
		};
		spiderCorbaServer.start();
		
		initDownloadApp();
		initRenderApp();
		initUploadApp();
	}

	private static void initDownloadApp() {
		// Init corba client for download app
		Thread downloadClientThread = new Thread() {
			public void run() {
				logger.info("Beginning to init download corba client >>>");
				SpiderCorbaClient downloadClient = SpiderCorbaClient.getInstance();
				try {
					downloadClient.agentSide.onDownloadStartup(spiderConfig.dAppId);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		};

		downloadClientThread.start();
		logger.info("Download app ID: " + spiderConfig.dAppId);
	}

	private static void initRenderApp() {
		// Init corba client for render
		Thread renderClientThread = new Thread() {
			public void run() {
				logger.info("Beginning to init render corba client >>>");
				SpiderCorbaClient renderClient = SpiderCorbaClient.getInstance();
				try {
					renderClient.agentSide.onRenderStartup(spiderConfig.rAppId);
				} catch (Exception e) {
					logger.error(e.toString());
				}
			}
		};

		renderClientThread.start();

		logger.info("Render app ID : " + spiderConfig.rAppId);
		// create render timer task (only one timer task)
		RenderTimerManager.getInstance().initTimerTask();
	}

	private static void initUploadApp() {
		// Init corba client
		Thread clientThread = new Thread() {
			public void run() {
				logger.info("Beginning to init upload corba client >>>");
				SpiderCorbaClient uploadClient = SpiderCorbaClient.getInstance();
				try {
					logger.info("upload Id = " + spiderConfig.uAppId);
					uploadClient.agentSide.onUploadStartup(spiderConfig.uAppId);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		};

		clientThread.start();
		logger.info("Upload app ID : " + spiderConfig.uAppId);
	}
}
