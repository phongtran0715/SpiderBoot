package spiderboot.view;

import java.awt.Dialog.ModalityType;

import spiderboot.configuration.ConfigProperties;
import spiderboot.databaseconnection.MySqlAccess;
import spiderboot.helper.Util;


public class MainSpiderBoot {
	static int SUCCESSFUL							= 	0;
	static int ERR_LOAD_CONFIG_PROPERTIES_FALSE		=	1;
	static int ERR_LOAD_DATABASE_FALSE				=	2;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		int errCode  = preLoad();
		if(errCode == SUCCESSFUL){
			boolean isRunning  = Util.checkIfRunning();
			if(!isRunning){
				HomeForm window = new HomeForm();
				window.setVisible(true);
//				LoginForm loginFrm = new LoginForm();
//				loginFrm.setVisible(true);	
			}	
		}else if(errCode == ERR_LOAD_CONFIG_PROPERTIES_FALSE){
			//TODO: Handle while loading config file false
		}else if(errCode == ERR_LOAD_DATABASE_FALSE){
			DatabaseConfig dbConfigFrm = new DatabaseConfig();
			dbConfigFrm.setModalityType(ModalityType.APPLICATION_MODAL);
			dbConfigFrm.setVisible(true);
		}else{
			//do nothing
		}
	}

	private static int preLoad() {
		int errorCode = -1;
		//load configuration file
//		boolean configResult = ConfigProperties.getInstance().loadConfigFile();
//		if(!configResult){
//			System.out.println("Can not load configuration file");
//			errorCode = 1;
//			return errorCode;
//		}
		//open database connection
		int dbResult = MySqlAccess.getInstance().DBConnect();
		if(dbResult == 0){
			errorCode = 0;
			System.out.println("Open connection successful");
		}else{
			System.out.println("Open connection false.");
			errorCode = 2;
		}
		return errorCode;
	}
}
