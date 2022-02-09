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
import java.time.format.DateTimeFormatter;
import model.AppointmentUniversal;
import collections.Appointments;
import helper.Time;

/** This class contains a method reads all appointments stored
 * in the database.
 */
public class ReadAppointments {
        
        /** This method queries the database and returns all appointments.
         * The returned appointment data is used to instantiate appointment
         * objects. Then the objects are added to a collection of appointments.
         */
        public static void readAppointments() throws SQLException {
            String selectAppointments = "SELECT * FROM Appointments";

            DBquery.setPreparedStatement(conn, selectAppointments); // Create prepared statement

            PreparedStatement psa = DBquery.getPreparedStatement();

            psa.execute(); // Execute PreparedStatement

            ResultSet rsa = psa.getResultSet();
        
          // Forward scroll ResultSet
         while(rsa.next())
         {
             
             int Appointment_ID = rsa.getInt("Appointment_ID");
             String Title = rsa.getString("Title");
             String Description = rsa.getString("Description"); 
             String Location = rsa.getString("Location");
             String Type = rsa.getString("Type"); 
             java.sql.Timestamp startDateTime = rsa.getTimestamp("Start");
             java.sql.Timestamp endDateTime = rsa.getTimestamp("End");
             java.sql.Timestamp createdDateTime = rsa.getTimestamp("Create_Date");
             String Created_By = rsa.getString("Created_By");
             java.sql.Timestamp lastUpdateDateTime = rsa.getTimestamp("Last_Update");
             String Last_Updated_By = rsa.getString("Last_Updated_By");
             int Customer_ID = rsa.getInt("Customer_ID");
             int User_ID = rsa.getInt("User_ID");
             int Contact_ID = rsa.getInt("Contact_ID");
          
             String startDT = Time.formatTimestampToString(startDateTime);
             String endDT = Time.formatTimestampToString(endDateTime);
             String createdDT = Time.formatTimestampToString(createdDateTime);
             String updatedDT = Time.formatTimestampToString(lastUpdateDateTime);
             
             // Create object of type appoinmentUniversal
             AppointmentUniversal appointment = new AppointmentUniversal(startDT, 
                     endDT, createdDT, updatedDT, Appointment_ID, Title,
                     Description ,Location, startDateTime, endDateTime, 
                     createdDateTime, Created_By, lastUpdateDateTime, 
                     Last_Updated_By, Customer_ID, User_ID, Contact_ID, Type);
             
             // Add appointment to collection
             Appointments.addAppointments(appointment);
         }
    
}
}
