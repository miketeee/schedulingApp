/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import static database.JDBC.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import collections.Customers;
import helper.Time;
import model.CustomerUniversal;

/** This class contains a method that reads all customers stored
 * in the database.
 */
public class ReadCustomers {
    
    /** This method queries the database and returns all customers.
     * The returned customer data is used to instantiate customer
     * objects. Then the objects are added to a collection of customers.
     */
    public static void readCustomers() throws SQLException {
        
        String selectCustomers = "SELECT * FROM Customers";
        
        DBquery.setPreparedStatement(conn, selectCustomers); // Create prepared statement
        
        PreparedStatement ps = DBquery.getPreparedStatement();
        
        ps.execute(); // Execute PreparedStatement
        
        ResultSet rs = ps.getResultSet();
        
          // Forward scroll ResultSet
         while(rs.next())
         {
             int CustomerID = rs.getInt("Customer_ID");
             String Customer_Name = rs.getString("Customer_Name");
             String Address = rs.getString("Address"); 
             String Postal_code = rs.getString("Postal_code");
             String Phone = rs.getString("Phone"); 
             java.sql.Timestamp createdDateTime = rs.getTimestamp("Create_Date");
             java.sql.Timestamp lastUpdatedDateTime = rs.getTimestamp("Last_Update");
             String Created_By = rs.getString("Created_By");
             String Last_Updated_By = rs.getString("Last_Updated_By");
             int Division = rs.getInt("Division_ID");
             
             String createdDT = Time.formatTimestampToString(createdDateTime);
             String updatedDT = Time.formatTimestampToString(lastUpdatedDateTime);
             
            
             CustomerUniversal customer = new CustomerUniversal(CustomerID ,
                     Customer_Name ,Address ,Postal_code ,Phone ,createdDateTime,
                     Created_By ,lastUpdatedDateTime ,Last_Updated_By ,Division, 
                     createdDT, updatedDT);
           
             Customers.addCustomers(customer);
         }
    
}
    
}
