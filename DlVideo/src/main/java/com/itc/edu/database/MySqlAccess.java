package com.itc.edu.database;

import com.itc.edu.dlvideo.util.Config;
import com.itc.edu.dlvideo.util.Constant;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/*------------------------------------------------------------------------------
** History
21-01-2018, [CR-001] phapnd
    Cap nhat lay thong tin ket noi MySqlAccess tu file cau hinh $HOME/etc/app.properties

25-01-2018, [CR-006] phapnd
    Modify de ho tro ket noi db UTF-8

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
                    .getConnection("jdbc:mysql://" + dbServer + "/" + dbName + "?zeroDateTimeBehavior=convertToNull&useUnicode=yes&characterEncoding=UTF-8", dbUserName, dbPassword);

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
                    .getConnection("jdbc:mysql://" + server + "/" + dbname + "?useUnicode=yes&characterEncoding=UTF-8", user, password);

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
