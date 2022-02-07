/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import static helper.JDBC.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import model.Customer;
import model.allCustomers;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import model.CustomerTime;

/**
 *
 * @author tamic
 */
public class LoadCustomers {
    
    public static void loadCustomers() throws SQLException {
        
        String selectCustomers = "SELECT * FROM Customers";
        
        DBquery.setPreparedStatement(conn, selectCustomers); // Create prepared statement
        
        PreparedStatement ps = DBquery.getPreparedStatement();
        
        ps.execute(); // Execute PreparedStatement
        
        ResultSet rs = ps.getResultSet();
        
          // Forward scroll ResultSet
         while(rs.next())
         {
//             DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-YYYY HH:mm");
             
             
             int CustomerID = rs.getInt("Customer_ID");
             String Customer_Name = rs.getString("Customer_Name");
             String Address = rs.getString("Address"); 
            
             String Postal_code = rs.getString("Postal_code");
             String Phone = rs.getString("Phone"); 
             
             java.sql.Timestamp createdDateTime = rs.getTimestamp("Create_Date");
//             String createdTimeSliced = dtf.format(createdDateTime.toLocalDateTime());
             java.sql.Timestamp lastUpdatedDateTime = rs.getTimestamp("Last_Update");
//             String lastUpdatedTimeSliced = dtf.format(lastUpdatedDateTime.toLocalDateTime());
             
             String Created_By = rs.getString("Created_By");
             String Last_Updated_By = rs.getString("Last_Updated_By");
             int Division = rs.getInt("Division_ID");
             
             String createdDT = FormatTimeEntered.formatTime(createdDateTime);
             String updatedDT = FormatTimeEntered.formatTime(lastUpdatedDateTime);
             
             // Display Record
//             System.out.println(CustomerID + " | " + Customer_Name + " | " + Address + " | " + Postal_code + " | " + Phone + " | " + date + "" + time + " | " + Created_By + " | " + Last_Update + " | " + Last_Updated_By + " | " + Division + " | " );
             CustomerTime customer = new CustomerTime(CustomerID ,Customer_Name ,Address ,Postal_code ,Phone ,createdDateTime ,Created_By ,lastUpdatedDateTime ,Last_Updated_By ,Division, createdDT, updatedDT);
           
             allCustomers.addCustomers(customer);
         }
    
}
    
}
