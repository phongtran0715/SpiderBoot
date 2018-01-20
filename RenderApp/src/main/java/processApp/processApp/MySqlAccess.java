package processApp.processApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlAccess {
	private static MySqlAccess instance = null;
	public Connection connect = null;
	private String dbServer;
	private String dbName;
	private String dbUserName;
	private String dbPassword;

	private MySqlAccess(){
		//default constructor
		dbServer = "localhost:3306";
		dbName = "spiderboot";
		dbUserName = "root";
		dbPassword = "123456";
	}

	public static MySqlAccess getInstance(){
		if(instance == null){
			instance = new MySqlAccess();
		}
		return instance;
	}

	public int DBConnect(){
		int errCode = 0;
		// This will load the MySQL driver, each DB has its own driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return 1;
		}
		System.out.println("MySQL JDBC Driver Registered!");
		// Setup the connection with the DB
		try {
			connect = DriverManager
					.getConnection("jdbc:mysql://" + dbServer + "/" + dbName + "?zeroDateTimeBehavior=convertToNull", dbUserName, dbPassword);

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return 2;
		}
		if (connect != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
		return errCode;
	}
	
	public int DBConnect(String server, String dbname, String user, String password){
		int errCode = 0;
		// This will load the MySQL driver, each DB has its own driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return 1;
		}
		System.out.println("MySQL JDBC Driver Registered!");
		// Setup the connection with the DB
		try {
			connect = DriverManager
					.getConnection("jdbc:mysql://" + server + "/" + dbname, user, password);

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return 2;
		}
		if (connect != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
		return errCode;
	}

	void DBDisconnect(){
		try{
			if(connect != null){
				connect.close();
			}
		}catch(Exception e){
			System.out.println("Can not close connection");
			e.printStackTrace();
		}
	}
	
	void DBReconnect(){
		
	}
	
}

