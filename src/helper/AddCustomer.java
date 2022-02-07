/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import controller.LoginScreenController;
import static helper.JDBC.conn;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Timestamp;

/**
 *
 * @author tamic
 */
public class AddCustomer {
    
    public static void addCustomer(String name, String address, String postalCode, String phone, int divId) throws SQLException{
        
        LocalDateTime date = LocalDateTime.now();
        String insertStatement = "INSERT INTO Customers(Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES(?,?,?,?,?,?,?,?,?)";
        
        DBquery.setPreparedStatement(conn, insertStatement); // Create prepared statement
        
        PreparedStatement ps = helper.DBquery.getPreparedStatement();
        
        String Customer_Name = name;
        String Address = address;
        String Postal_Code = postalCode;
        String Phone = phone;
        Timestamp Create_Date = Timestamp.valueOf(date);
        String Created_By = LoginScreenController.getName();
        Timestamp Last_Update = Timestamp.valueOf(date);
        String Last_Upadatd_By = LoginScreenController.getName();;
        int Division_ID = divId;
        
        //Key value mapping
        ps.setString(1, Customer_Name);
        ps.setString(2, Address);
        ps.setString(3, Postal_Code);
        ps.setString(4, Phone);
        ps.setTimestamp(5, Create_Date);
        ps.setString(6, Created_By);
        ps.setTimestamp(7, Last_Update);
        ps.setString(8, Last_Upadatd_By);
        ps.setInt(9, Division_ID);
        
        ps.execute(); // Execute PreparedStatement
        
        // Check rows affected
        if(ps.getUpdateCount() > 0)
            System.out.println(ps.getUpdateCount() + " rows(s) affected");
        else
            System.out.println("No Change!");
  
    }
    
}
