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
import collections.Users;
import model.Contact;
import model.User;

/** This class contains a method reads all users stored
 * in the database.
 */
public class ReadUsers {
    
     /** This method queries the database and returns all users.
     * The returned contact data is used to instantiate contact
     * objects. Then the objects are added to a collection of users.
     */
    public static void readUsers() throws SQLException {
        
        
        String selectUsers = "SELECT * FROM Users";
        
        DBquery.setPreparedStatement(conn, selectUsers); // Create prepared statement
        
        PreparedStatement ps = DBquery.getPreparedStatement();
        
        ps.execute(); // Execute PreparedStatement
        
        ResultSet rs = ps.getResultSet();
        
          // Forward scroll ResultSet
         while(rs.next())
         {
             int User_ID = rs.getInt("User_ID");
             String User_Name = rs.getString("User_Name");
        
             
             User user = new User(User_ID, User_Name);
             Users.addUser(user);
         }
    
}
    
}
