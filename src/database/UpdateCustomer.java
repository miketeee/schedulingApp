/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import controller.LoginScreenController;
import static database.JDBC.conn;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.sql.Timestamp;

/* This class contains a method for updating customers that
* exist in the database.
*/ 
public class UpdateCustomer {
    
    /**
    * This method updates a customer based on the
    * the passed in customer ID.
    * @param customerID Customer ID of customer
    * @param name Name of customer
    * @param address Address of customer
    * @param postalCode Postal code of customer
    * @param phone Phone number of customer
    * @param divId Division id of customer
    */
    public static void updateCustomer(int customerID, String name, String address,
            String postalCode, String phone, int divId) throws SQLException{              
        
        
        LocalDateTime date = LocalDateTime.now();
        String updateStatement = "UPDATE customers SET Customer_Name = ?, "
                + "Address = ?, Postal_Code = ?, Phone = ?, Last_Update = ?, "
                + "Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?";
        
        DBquery.setPreparedStatement(conn, updateStatement); // Create prepared statement
        
        PreparedStatement ps = database.DBquery.getPreparedStatement();
        
        int Customer_ID = customerID;
        String Customer_Name = name;
        String Address = address;
        String Postal_Code = postalCode;
        String Phone = phone;
        Timestamp Last_Update = Timestamp.valueOf(date);
        String Last_Upadated_By = LoginScreenController.getName();;
        int Division_ID = divId;
        
        //Key value mapping
        ps.setString(1, Customer_Name);
        ps.setString(2, Address);
        ps.setString(3, Postal_Code);
        ps.setString(4, Phone);
        ps.setTimestamp(5, Last_Update);
        ps.setString(6, Last_Upadated_By);
        ps.setInt(7, Division_ID);
        ps.setInt(8, Customer_ID);
        
        ps.execute(); // Execute PreparedStatement
        
        // Check rows affected
        if(ps.getUpdateCount() > 0)
            System.out.println(ps.getUpdateCount() + " rows(s) affected");
        else
            System.out.println("No Change!");
  
    }
    
}