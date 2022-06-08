/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.DBquery;
import static database.JDBC.conn;
import database.ReadDivisions;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class contains a method reads a specific country
 * in the database.
 */
public class ReadCountries {
    
     /** This method queries the database for a match to the 
      * passed in country name. The ID of that country is then
      * passed into the readDivisionsByCountryID method.
      */
    public static void readCountries(String chosenCountry) throws SQLException {
        
        String selectCountries = "SELECT * FROM countries WHERE Country = ?";
        
        DBquery.setPreparedStatement(conn, selectCountries); // Create prepared statement
        
        PreparedStatement ps = DBquery.getPreparedStatement();
        
        ps.setString(1, chosenCountry);
        
        ps.execute(); // Execute PreparedStatement
        
        ResultSet rs = ps.getResultSet();
        
          // Forward scroll ResultSet
         while(rs.next())
         {
             int Country_ID = rs.getInt("Country_ID");
             ReadDivisions.readDivisionsByCountryID(Country_ID);

         }
         
    
}
    
}