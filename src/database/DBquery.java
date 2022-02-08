/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

/**
 *
 * @author tamic
 */
public class DBquery {
    
    private static PreparedStatement statement;
    
   // Create statement object
   // setStatement will recieve a connection reference
    public static void setPreparedStatement(Connection conn, String sqlStatement)
            throws SQLException
    {
       statement =  conn.prepareStatement(sqlStatement);
    }
    
    // Return statement object
    public static PreparedStatement getPreparedStatement()
    {
        return statement;
    }
    
    
}
