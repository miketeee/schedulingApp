/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import model.*;
import helper.DBquery;
import static helper.JDBC.conn;
import helper.LoadDivisions;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
             System.out.println(Country);
             System.out.println(Country_ID);
//             String Division = rs.getString("Division");
//             LocalDateTime dateTime = rs.getTimestamp("Create_Date").toLocalDateTime();
//             LocalTime time = rs.getTimestamp("Create_Date").toLocalDateTime().toLocalTime();
//             String Created_By = rs.getString("Created_By");
//             LocalDateTime Last_Update = rs.getTimestamp("Last_Update").toLocalDateTime();
//             String Last_Updated_By = rs.getString("Last_Updated_By");
//             int Country_ID = rs.getInt("Country_ID");
             
             // Display Record
//             System.out.println(Division_ID + " | " + Division + " | " + dateTime + " | " + Created_By + " | " + Last_Update + " | " + Last_Updated_By + " | " + Country_ID + " | ");
//             Division division = new Division(Division_ID ,Division ,dateTime ,Created_By ,Last_Update ,Last_Updated_By ,Country_ID);
//             allDivisions.addDivisions(division);
         }
         
    
}
    
}