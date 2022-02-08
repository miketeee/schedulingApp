/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import static database.JDBC.conn;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author tamic
 */
public class DeleteCustomer {
    public static void deleteCustomer(int customerID) throws SQLException{
        
        LocalDateTime date = LocalDateTime.now();
        String deleteStatement = "DELETE FROM customers WHERE Customer_ID = ?";
        
        DBquery.setPreparedStatement(conn, deleteStatement); // Create prepared statement
        
        PreparedStatement ps = database.DBquery.getPreparedStatement();
        
        int Customer_ID = customerID;

        //Key value mapping
        ps.setInt(1, Customer_ID);
      
        ps.execute(); // Execute PreparedStatement
        
        // Check rows affected
        if(ps.getUpdateCount() > 0)
            System.out.println(ps.getUpdateCount() + " rows(s) affected");
        else
            System.out.println("No Change!");
  
    }
    
}
