/*
Runtime or Logical error:
Location: AddPartController line 204

I encountered a logical error when creating the function that determines
whether the Inventory value is a value in between the min and max values.
The program should have produced a value of true if the the inventory value 
was in between the max and min values. When i ran the program with numbers 
where the inventory value was not in between the min and max I recieved a 
value of true even though the values should have been false.. I realized that
i had used the wrong logical operater. In between the max and min values I used a
greater than symbol instead of a less than symbol. I changed the logical operator 
to a less than symbol and the program worked correctly.
*/

/*
compatible feature:

In the next version of this program I would implement a feature that
would allow the user to modify the data in the columns and rows of the 
table view. This would allow the user to update part and product information
faster.
 */
package twstock;

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

/**This class creates and app that functions as an Inventory Management System*/
public class TWStock extends Application {

    /**
     * @param args the command line arguments
     */
    /**This is the main method. This is the first method that gets called when the program is ran*/
    public static void main(String[] args) throws IOException, SQLException {
       
//       ZoneId.getAvailableZoneIds().stream().forEach(System.out::println);
//       ZoneId.getAvailableZoneIds().stream().filter(c -> c.contains("System")).forEach(System.out::println);

////

//
//       LocalDate userDate = LocalDate.now();
//     LocalTime parisTime = LocalTime.o
//       LocalTime userTime = LocalTime.now();
//       ZoneId userZoneId = ZoneId.of("Europe/Paris");
//       ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
//       ZonedDateTime userZDT = ZonedDateTime.of(userDate, userTime, localZoneId);

//       
//       Instant parisToGMTInstant = userZDT.toInstant();
//       ZonedDateTime parisToLocalZDT = userZDT.withZoneSameInstant(localZoneId);
//       ZonedDateTime gmtToLocalZDT = parisToGMTInstant.atZone(localZoneId);
//       
//       System.out.println("local: " + ZonedDateTime.now());
//       System.out.println("Paris: " + userZDT);
//       System.out.println("Paris->GMT: " + parisToGMTInstant);
//       System.out.println("GMT->Local: " + gmtToLocalZDT);
//       System.out.println("GMT->LocalDate: " + gmtToLocalZDT.toLocalDate());
//       System.out.println("GMT->LocalTime: " + gmtToLocalZDT.toLocalTime());
       
//       String datE = String.valueOf(gmtToLocalZDT.toLocalDate());
//       String timE = String.valueOf(gmtToLocalZDT.toLocalTime());
//       String dateTime = datE + " " + timE;
//       System.out.println(dateTime);
       
       
        

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