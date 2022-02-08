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
import java.time.LocalTime;
import collections.Users;
import model.User;

/**
 *
 * @author tamic
 */

//Delete this file no need to load users if the user cant set the userid since it is set by login details
public class LoadUsers{
    
    public static void loadUser() throws SQLException {
        
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
             // Display Record
//             System.out.println(CustomerID + " | " + Customer_Name + " | " + Address + " | " + Postal_code + " | " + Phone + " | " + date + "" + time + " | " + Created_By + " | " + Last_Update + " | " + Last_Updated_By + " | " + Division + " | " );
             User user = new User(User_ID, User_Name);
             Users.addUser(user);
         }
}

    
}

