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
import model.Customer;
import model.Division;
import collections.Divisions;

/**
 *
 * @author tamic
 */
public class LoadDivisions {
    
    public static void loadDivisions(int countryId) throws SQLException {
        
        String selectDivisions = "SELECT * FROM first_level_divisions WHERE Country_ID = ?";
        
        DBquery.setPreparedStatement(conn, selectDivisions); // Create prepared statement
        
        PreparedStatement ps = DBquery.getPreparedStatement();
  
        int countryID = countryId;
        
        ps.setInt(1, countryID);
        
        ps.execute(); // Execute PreparedStatement
        
        ResultSet rs = ps.getResultSet();
        
          // Forward scroll ResultSet
         while(rs.next())
         {
             int Division_ID = rs.getInt("Division_ID");
             String Division = rs.getString("Division");
             LocalDateTime dateTime = rs.getTimestamp("Create_Date").toLocalDateTime();
             LocalTime time = rs.getTimestamp("Create_Date").toLocalDateTime().toLocalTime();
             String Created_By = rs.getString("Created_By");
             LocalDateTime Last_Update = rs.getTimestamp("Last_Update").toLocalDateTime();
             String Last_Updated_By = rs.getString("Last_Updated_By");
             int Country_ID = rs.getInt("Country_ID");
             
             // Display Record
//             System.out.println(Division_ID + " | " + Division + " | " + dateTime + " | " + Created_By + " | " + Last_Update + " | " + Last_Updated_By + " | " + Country_ID + " | ");
  
             Division division = new Division(Division_ID ,Division ,dateTime ,Created_By ,Last_Update ,Last_Updated_By ,Country_ID);
             Divisions.addDivisions(division);
         }
         
    
}
    
        public static void loadDivisionsByID(int divisionId) throws SQLException {
        
        String selectDivisions = "SELECT * FROM first_level_divisions WHERE Division_ID = ?";
        
        DBquery.setPreparedStatement(conn, selectDivisions); // Create prepared statement
        
        PreparedStatement ps = DBquery.getPreparedStatement();
  
        int DivisionID = divisionId;
        
        ps.setInt(1, DivisionID);
        
        ps.execute(); // Execute PreparedStatement
        
        ResultSet rs = ps.getResultSet();
        
          // Forward scroll ResultSet
         while(rs.next())
         {
             int Division_ID = rs.getInt("Division_ID");
             String Division = rs.getString("Division");
             LocalDateTime dateTime = rs.getTimestamp("Create_Date").toLocalDateTime();
             LocalTime time = rs.getTimestamp("Create_Date").toLocalDateTime().toLocalTime();
             String Created_By = rs.getString("Created_By");
             LocalDateTime Last_Update = rs.getTimestamp("Last_Update").toLocalDateTime();
             String Last_Updated_By = rs.getString("Last_Updated_By");
             int Country_ID = rs.getInt("Country_ID");
             
             // Display Record
//             System.out.println(Division_ID + " | " + Division + " | " + dateTime + " | " + Created_By + " | " + Last_Update + " | " + Last_Updated_By + " | " + Country_ID + " | ");
             Division division = new Division(Division_ID ,Division ,dateTime ,Created_By ,Last_Update ,Last_Updated_By ,Country_ID);
//             Divisions.divisionList.clear();
             Divisions.addDivisions(division);
         }
         
    
}
    
    
}
