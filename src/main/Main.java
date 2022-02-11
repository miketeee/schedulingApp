
package main;

import collections.AppointmentTypes;
import database.JDBC;
import database.ReadAppointments;
import database.ReadContacts;
import database.ReadCustomers;
import database.ReadUsers;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.SQLException;



/**This class creates and app that functions as an scheduling app that allows
   for the creating, reading, updating, and deleting of customers and appointments*/
public class Main extends Application {

    /**
     * @param args the command line arguments
     */
    /**This is the main method. This is the first method that gets called when the program is ran*/
    public static void main(String[] args) throws IOException, SQLException {
        
        
        // ********
        // LAMDBDA EXPRESSION LOCATIONS
        // src/helper/schedule/ line 31
        // src/controller/MainScreenController/ line 242
        
        
        
        
        
        
        
        
       
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

        ReadCustomers.readCustomers();
         
        ReadAppointments.readAppointments();
        
        ReadContacts.readContacts();
        
        ReadUsers.readUsers();
        
        // Create appointment types
        AppointmentTypes.addType(new String("routine"));
        AppointmentTypes.addType(new String("Standard"));
        AppointmentTypes.addType(new String("Express"));
        
        
        
       
        
        
        
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