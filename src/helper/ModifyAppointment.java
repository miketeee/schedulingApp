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
import java.time.LocalTime;
import model.Division;

/**
 *
 * @author tamic
 */
public class ModifyAppointment {
    
    public static void ModifyAppointment(int appId, String title, String descript, String location, String type, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, int cId, int uId, int contactId) throws SQLException{
        
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
        LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);
        String updateStatement = "UPDATE Appointments SET Title=?, Description=?, Location=?, Type=?,Start=?, End=?, Customer_ID=?, User_ID=?, Contact_ID=?, Last_Update=? WHERE Appointment_ID=?;";
        
        DBquery.setPreparedStatement(conn, updateStatement); // Create prepared statement
        
        PreparedStatement ps = helper.DBquery.getPreparedStatement();
        
        String Title = title;
        String Description = descript;
        String Location = location;
        String Type = type;
        Timestamp Start = Timestamp.valueOf(startDateTime);
        Timestamp End = Timestamp.valueOf(endDateTime);
        Timestamp Create_Date = Timestamp.valueOf(date);
        String Created_By = LoginScreenController.getName();
;
        Timestamp Last_Update = Timestamp.valueOf(LocalDateTime.now());
      
        String Last_Upadatd_By =  LoginScreenController.getName();
        int Customer_ID = cId;
        int User_ID = uId;
        int Contact_ID = contactId;
        int Appointment_ID = appId;
        
        //Key value mapping
        ps.setString(1, Title);
        ps.setString(2, Description);
        ps.setString(3, Location);
        ps.setString(4, Type);
        ps.setTimestamp(5, Start);
        ps.setTimestamp(6, End);
        ps.setInt(7, Customer_ID);
        ps.setInt(8, User_ID);
        ps.setInt(9, Contact_ID);
        ps.setTimestamp(10, Last_Update);
        ps.setInt(11, Appointment_ID);
        
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

