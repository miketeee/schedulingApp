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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import collections.Contacts;
import model.User;
import collections.Users;
import model.Contact;

/**
 *
 * @author tamic
 */
public class LoadContacts {
    
    public static void loadContacts() throws SQLException {
        
        
        String selectContacts = "SELECT * FROM Contacts";
        
        DBquery.setPreparedStatement(conn, selectContacts); // Create prepared statement
        
        PreparedStatement ps = DBquery.getPreparedStatement();
        
        ps.execute(); // Execute PreparedStatement
        
        ResultSet rs = ps.getResultSet();
        
          // Forward scroll ResultSet
         while(rs.next())
         {
             int Contact_ID = rs.getInt("Contact_ID");
             String Contact_Name = rs.getString("Contact_Name");
             String Email = rs.getString("Email");
             
            
             
             // Display Record
//             System.out.println(CustomerID + " | " + Customer_Name + " | " + Address + " | " + Postal_code + " | " + Phone + " | " + date + "" + time + " | " + Created_By + " | " + Last_Update + " | " + Last_Updated_By + " | " + Division + " | " );
             Contact contact = new Contact(Contact_ID, Contact_Name, Email);
             Contacts.addContact(contact);
         }
    
}
    
}
