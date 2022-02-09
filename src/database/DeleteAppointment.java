/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import static database.JDBC.conn;
import java.sql.PreparedStatement;
import java.sql.SQLException;

    /**
    * This class contains a method for deleting appointments from the database.
    */ 
public class DeleteAppointment {
    
    /** This method uses the passed in appointment id to find and
     * delete a specified appointment.
     * @param appID Appointment ID to delete
     */
    
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
