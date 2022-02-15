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
import collections.Contacts;
import model.Contact;

/** This class contains a method reads all contacts stored
 * in the database.
 */
public class ReadContacts {
    
     /** This method queries the database and returns all contacts.
     * The returned contact data is used to instantiate contact
     * objects. Then the objects are added to a collection of contacts.
     */
    public static void readContacts() throws SQLException {
        
        
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
             
             Contact contact = new Contact(Contact_ID, Contact_Name, Email);
             Contacts.addContact(contact);
         }
    
}
    
}
