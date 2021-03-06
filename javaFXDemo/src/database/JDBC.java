/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author tamic
 */
public abstract class JDBC {
//    private static final String protocol = "jdbc";
//    private static final String vendor = ":mysql:";
//    private static final String location = "//localhost/";
//    private static final String databaseName = "client_schedule";
//    private static final String jdbcUrl = protocol + vendor + location
//            + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
//    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
//    private static final String userName = "sqlUser"; // Username
//    private static String password = "Passw0rd!"; // Password
    private static final String url = "jdbc:sqlite:C:\\projects\\SQLlite_DBs\\sample.db";
    public static Connection conn = null;  // Connection Interface
    

    public static Connection openConnection() {
        try {
            Class.forName("org.sqlite.JDBC"); // Locate Driver
            conn = DriverManager.getConnection(url); // Reference Connection object
            System.out.println("Connection successful!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
        return conn;
    }
    
    //need to get the connection
    public static Connection getConnetion(){
        return conn;
    }

    public static void closeConnection() {
        try {
            conn.close();
            System.out.println("Connection closed!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }
}