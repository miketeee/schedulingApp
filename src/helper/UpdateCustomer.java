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
import javafx.scene.control.Alert;
import model.Division;

/**
 *
 * @author tamic
 */
public class UpdateCustomer {
    
    public static void updateCustomer(int customerID, String name, String address, String postalCode, String phone, int divId) throws SQLException{              
        
        
        LocalDateTime date = LocalDateTime.now();
        String updateStatement = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?";
        
        DBquery.setPreparedStatement(conn, updateStatement); // Create prepared statement
        
        PreparedStatement ps = helper.DBquery.getPreparedStatement();
        
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



//        
//        PreparedStatement ps = DBquery.getPreparedStatement();
//        
//        String Country, newCountry, Created_By;
//        String Create_Date = "2022-01-12";
//        String Created_By = "admin";
//        String Last_Update = "2022-01-13";
//        String Last_Upadatd_By = "admin";
        
//        //Get Keyboard input
//        Scanner keyboard = new Scanner(System.in);
//        System.out.print("Enter a country to update: ");
//        Country = keyboard.nextLine();
//        
//        System.out.print("Enter a new country: ");
//        newCountry = keyboard.nextLine();
//        
//         System.out.print("Enter a user: ");
//        Created_By = keyboard.nextLine();
//        
//        //Key value mapping
//        ps.setString(1, newCountry);
//        ps.setString(2, Created_By);
//        ps.setString(3, Country);
////        ps.setString(4, Last_Update);
////        ps.setString(5, Last_Upadatd_By);
//        
//        ps.execute(); // Execute PreparedStatement
//        
//        // Check rows affected
//        if(ps.getUpdateCount() > 0)
//            System.out.println(ps.getUpdateCount() + " rows(s) affected");
//        else
//            System.out.println("No Change!");

