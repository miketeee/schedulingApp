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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.function.Predicate;
import collections.Users;
import model.User;

/**
 *
 * @author tamic
 */
public class AddAppointment {
      
    public static void addAppointment(String title, String descript, String location, String type, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, int cId, int uId, int contactId) throws SQLException {
            
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
        LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);
        String insertStatement = "INSERT INTO Appointments(Title, Description, Location, Type,Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        DBquery.setPreparedStatement(conn, insertStatement); // Create prepared statement
        
        PreparedStatement ps = database.DBquery.getPreparedStatement();
        
        String Title = title;
        String Description = descript;
        String Location = location;
        String Type = type;
        Timestamp Start = Timestamp.valueOf(startDateTime);
        Timestamp End = Timestamp.valueOf(endDateTime);
        Timestamp Create_Date = Timestamp.valueOf(date);
        String Created_By = LoginScreenController.getName();
;
        Timestamp Last_Update = Timestamp.valueOf(date);
      
        String Last_Upadatd_By =  LoginScreenController.getName();
        int Customer_ID = cId;
        int User_ID = uId;
        int Contact_ID = contactId;
        
        //Key value mapping
        ps.setString(1, Title);
        ps.setString(2, Description);
        ps.setString(3, Location);
        ps.setString(4, Type);
        ps.setTimestamp(5, Start);
        ps.setTimestamp(6, End);
        ps.setTimestamp(7, Create_Date);
        ps.setString(8, Created_By);
        ps.setTimestamp(9, Last_Update);
        ps.setString(10, Last_Upadatd_By);
        ps.setInt(11, Customer_ID);
        ps.setInt(12, User_ID);
        ps.setInt(13, Contact_ID);
        
        ps.execute(); // Execute PreparedStatement
        
        // Check rows affected
        if(ps.getUpdateCount() > 0)
            System.out.println(ps.getUpdateCount() + " rows(s) affected");
        else
            System.out.println("No Change!");
}
    
}
