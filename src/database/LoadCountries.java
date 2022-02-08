/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.DBquery;
import static database.JDBC.conn;
import database.LoadDivisions;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author tamic
 */
public class LoadCountries {
    
    public static void loadCountries(String chosenCountry) throws SQLException {
        
        String selectCountries = "SELECT * FROM countries WHERE Country = ?";
        
        DBquery.setPreparedStatement(conn, selectCountries); // Create prepared statement
        
        PreparedStatement ps = DBquery.getPreparedStatement();
        
        String selectedCountry = chosenCountry;
        
        ps.setString(1, selectedCountry);
        
        ps.execute(); // Execute PreparedStatement
        
        ResultSet rs = ps.getResultSet();
        
          // Forward scroll ResultSet
         while(rs.next())
         {
             int Country_ID = rs.getInt("Country_ID");
             String Country = rs.getString("Country");
             LoadDivisions.loadDivisions(Country_ID);

         }
         
    
}
    
}