package com.itc.edu.database;

import com.itc.edu.ulvideo.util.Config;
import com.itc.edu.ulvideo.util.Constant;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/*------------------------------------------------------------------------------
** History


*/

public class MySqlAccess {

    private static final Logger logger = Logger.getLogger(MySqlAccess.class);
    private static MySqlAccess instance = null;
    public Connection connect = null;
    private final String dbServer;
    private final String dbName;
    private final String dbUserName;
    private final String dbPassword;

    private MySqlAccess() {
        //default constructor
        dbServer = Config.dbServer;
        dbName = Config.dbName;
        dbUserName = Config.dbUserName;
        dbPassword = Config.dbPassword;
    }

    public static MySqlAccess getInstance() {
        if (instance == null) {
            instance = new MySqlAccess();
        }
        return instance;
    }

    public int DBConnect() {
        // This will load the MySQL driver, each DB has its own driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.info("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return Constant.ERR_DRIVER;
        }
        logger.info("MySQL JDBC Driver Registered!");
        // Setup the connection with the DB
        try {
            connect = DriverManager
                    .getConnection("jdbc:mysql://" + dbServer + "/" + dbName + "?zeroDateTimeBehavior=convertToNull", dbUserName, dbPassword);

        } catch (SQLException e) {
            logger.info("Connection Failed! Check output console");
            e.printStackTrace();
            return Constant.ERR_OTHER;
        }
        if (connect != null) {
            logger.info("You made it, take control your database now!");
        } else {
            logger.info("Failed to make connection!");
        }
        return Constant.SUCCESS;
    }

    public int DBConnect(String server, String dbname, String user, String password) {
        // This will load the MySQL driver, each DB has its own driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("Where is your MySQL JDBC Driver?" + e);
            return Constant.ERR_DRIVER;
        }
        logger.info("MySQL JDBC Driver Registered!");
        // Setup the connection with the DB
        try {
            connect = DriverManager
                    .getConnection("jdbc:mysql://" + server + "/" + dbname, user, password);

        } catch (SQLException e) {
            logger.error("Connection Failed! Check output console" + e);
            return Constant.ERR_OTHER;
        }
        if (connect != null) {
            logger.warn("You made it, take control your database now!");
        } else {
            logger.error("Failed to make connection!");
        }
        return Constant.SUCCESS;
    }

    void DBDisconnect() {
        try {
            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {
            logger.error("Can not close connection");
            logger.error(e);
        }
    }

    void DBReconnect() {

    }

}