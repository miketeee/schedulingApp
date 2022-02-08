
package main;

import helper.DBquery;
import helper.JDBC;
import helper.LoadAppointments;
import helper.LoadContacts;
import helper.LoadCustomers;
import helper.LoadTypes;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.TimeZone;
import model.Customer;
import model.allCustomers;
import model.allAppointments;
import java.sql.PreparedStatement;
import java.time.format.DateTimeFormatter;
import model.Appointment;





/**
 *
 * @author Tamicheal Wills
 */

/**This class creates and app that functions as an scheduling app that allows
   for the creating, reading, updating, and deleting of customers and appointments*/
public class Main extends Application {

    /**
     * @param args the command line arguments
     */
    /**This is the main method. This is the first method that gets called when the program is ran*/
    public static void main(String[] args) throws IOException, SQLException {
       
//       Locale.setDefault(new Locale("fr"));
 
//       try {
//            ResourceBundle rb = ResourceBundle.getBundle("rb/Nat", Locale.getDefault());
//
//            if(Locale.getDefault().getLanguage().equals("fr"))
//                System.out.println(rb.getString("Hello") + "" + rb.getString("Login"));
//       }
//       
//       catch(MissingResourceException e) {
//           System.out.println("Can't find bundle for base name rb/Nat, locale en_US");
//       }
      
        
        


        Connection conn = JDBC.openConnection();
        

        LoadCustomers.loadCustomers();
         
        LoadAppointments.loadAppointments();
        
        LoadTypes.loadTypes();
        
        LoadContacts.loadContacts();
        
        
        
       
        
        
        
        launch(args); 
        
        JDBC.closeConnection();


    }

    /**This is the start method.*/
    @Override
    public void start(Stage stage) throws Exception {
      Parent root = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml")); 
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.setTitle("My JavaFX Menu");
      stage.show();
    }
}