/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import static database.JDBC.conn;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 *
 * @author tamic
 */
public class DeleteAppointment {
    public static void deleteAppointment(int appID) throws SQLException{
        
        String deleteStatement = "DELETE FROM appointments WHERE Appointment_ID = ?";
        
        DBquery.setPreparedStatement(conn, deleteStatement); 
        
        PreparedStatement ps = database.DBquery.getPreparedStatement();
        
        int Appointment_ID = appID;

        //Key value mapping
        ps.setInt(1, Appointment_ID);
      
        ps.execute(); // Execute PreparedStatement
        
        // Check rows affected
        if(ps.getUpdateCount() > 0)
            System.out.println(ps.getUpdateCount() + " rows(s) affected");
        else
            System.out.println("No Change!");
  
    }
    
}
