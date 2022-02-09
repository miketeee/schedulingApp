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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.time.LocalTime;

/**
* This class contains a method for updating appointments that
* exist in the database.
*/ 
public class UpdateAppointment {
    
    /**
    * This method updates an appointment based on the
    * the passed in appointment ID.
    * @param appId ID of appointment
    * @param title Title of appointment
    * @param description Description of appointment
    * @param location Location of appointment
    * @param appType Type of appointment
    * @param startDate Start date of appointment
    * @param startTime Start time of appointment
    * @param endDate End date of appointment
    * @param endTime End time of appointment
    * @param customerID Customer ID of appointment
    * @param userID User ID of appointment
    * @param contactID Contact ID of appointment
    */
    public static void UpdateAppointment(int appId, String title, String description, 
            String location, String appType, LocalDate startDate, LocalTime startTime, 
            LocalDate endDate, LocalTime endTime, int customerID, int userID, int contactID) 
            throws SQLException{
        
            LocalDateTime date = LocalDateTime.now();
            LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
            LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);
            String updateStatement = "UPDATE Appointments SET Title=?, "
                    + "Description=?, Location=?, Type=?,Start=?, End=?, "
                    + "Customer_ID=?, User_ID=?, Contact_ID=?, Last_Update=? "
                    + "WHERE Appointment_ID=?;";

            DBquery.setPreparedStatement(conn, updateStatement); // Create prepared statement

            PreparedStatement ps = database.DBquery.getPreparedStatement();

            String Title = title;
            String Description = description;
            String Location = location;
            String Type = appType;
            Timestamp Start = Timestamp.valueOf(startDateTime);
            Timestamp End = Timestamp.valueOf(endDateTime);
            Timestamp Create_Date = Timestamp.valueOf(date);
            String Created_By = LoginScreenController.getName();
    ;
            Timestamp Last_Update = Timestamp.valueOf(LocalDateTime.now());

            String Last_Upadatd_By =  LoginScreenController.getName();
            int Customer_ID = customerID;
            int User_ID = userID;
            int Contact_ID = contactID;
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
