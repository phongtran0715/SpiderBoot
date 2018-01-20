package com.itc.edu.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class MySqlAccess {
	private static final Logger logger = Logger.getLogger(MySqlAccess.class);
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
			logger.info("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return 1;
		}
		logger.info("MySQL JDBC Driver Registered!");
		// Setup the connection with the DB
		try {
			connect = DriverManager
					.getConnection("jdbc:mysql://" + dbServer + "/" + dbName + "?zeroDateTimeBehavior=convertToNull", dbUserName, dbPassword);

		} catch (SQLException e) {
			logger.info("Connection Failed! Check output console");
			e.printStackTrace();
			return 2;
		}
		if (connect != null) {
			logger.info("You made it, take control your database now!");
		} else {
			logger.info("Failed to make connection!");
		}
		return errCode;
	}
	
	public int DBConnect(String server, String dbname, String user, String password){
		int errCode = 0;
		// This will load the MySQL driver, each DB has its own driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			logger.error("Where is your MySQL JDBC Driver?" + e);
			return 1;
		}
		logger.info("MySQL JDBC Driver Registered!");
		// Setup the connection with the DB
		try {
			connect = DriverManager
					.getConnection("jdbc:mysql://" + server + "/" + dbname, user, password);

		} catch (SQLException e) {
			logger.error("Connection Failed! Check output console" + e);
			return 2;
		}
		if (connect != null) {
			logger.warn("You made it, take control your database now!");
		} else {
			logger.error("Failed to make connection!");
		}
		return errCode;
	}

	void DBDisconnect(){
		try{
			if(connect != null){
				connect.close();
			}
		}catch(Exception e){
			logger.error("Can not close connection");
			logger.error(e);
		}
	}
	
	void DBReconnect(){
		
	}
	
}

