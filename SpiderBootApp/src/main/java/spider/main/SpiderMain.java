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
		if (spiderConfig.downloadDeploy)
			initDownloadApp();
		if (spiderConfig.renderDeploy)
			initRenderApp();
		if (spiderConfig.uploadDeploy)
			initUploadApp();
	}

	private static void initDownloadApp() {
		Thread downloadClientThread = new Thread() {
			public void run() {
				logger.info("[DOWNLOAD] : Beginning to init download corba client >>>");
				SpiderCorbaClient downloadClient = SpiderCorbaClient.getInstance();
				try {
					downloadClient.agentSide.onDownloadStartup(spiderConfig.dAppId);
				} catch (Exception e) {
					logger.error("[DOWNLOAD]" + e.toString());
				}
			}
		};

		downloadClientThread.start();
		logger.info("[DOWNLOAD] : Download app ID: " + spiderConfig.dAppId);
	}

	private static void initRenderApp() {
		Thread renderClientThread = new Thread() {
			public void run() {
				logger.info("[RENDER] : Beginning to init render corba client >>>");
				SpiderCorbaClient renderClient = SpiderCorbaClient.getInstance();
				try {
					renderClient.agentSide.onRenderStartup(spiderConfig.rAppId);
				} catch (Exception e) {
					logger.error("[RENDER] : " + e.toString());
				}
			}
		};

		renderClientThread.start();

		logger.info("[RENDER] : Render app ID : " + spiderConfig.rAppId);
		// create render timer task (only one timer task)
		RenderTimerManager.getInstance().initTimerTask();
	}

	private static void initUploadApp() {
		Thread clientThread = new Thread() {
			public void run() {
				logger.info("[UPLOAD] : Beginning to init upload corba client >>>");
				SpiderCorbaClient uploadClient = SpiderCorbaClient.getInstance();
				try {
					logger.info("[UPLOAD] : upload Id = " + spiderConfig.uAppId);
					uploadClient.agentSide.onUploadStartup(spiderConfig.uAppId);
				} catch (Exception e) {
					logger.error("[UPLOAD] : " + e.toString());
				}
			}
		};

		clientThread.start();
		logger.info("[UPLOAD] : Upload app ID : " + spiderConfig.uAppId);
	}
}
