/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import exceptions.HasAppointmentsException;
import helper.Schedule;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.SQLException;
import database.DBquery;
import helper.FileIO;
import database.JDBC;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.TimeZone;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import model.LoginAttempt;

/**
 *The class that controls the login form.
 * @author tamic
 */
public class LoginScreenController implements Initializable {
    Stage stage;
    Parent scene;
    
    @FXML
    private Button btnLogin;

    @FXML
    private TextField password;

    @FXML
    private Label userLanguage;

    @FXML
    private Label userLocation;

    @FXML
    private TextField username;
    
    List attempts = new ArrayList();
    
    File loginActivity ;
    
    /*Initialize the logged in user's id to 0.*/
    public static int userID = 0;
    
    /*Initialize the logged in user's id to 0.*/
    public static String name;

    /**
     * This method returns the user name.
     * @return Returns user name
     */
    public static String getName() {
        return name;
    }

    /**
     * This method sets the user name.
     */
    public static void setName(String name) {
        LoginScreenController.name = name;
    }
    
    public LoginScreenController() throws IOException {
        this.loginActivity = FileIO.createFile("login_activity");
    }
    /**
     * This method returns the user id
     * @return Returns user id
     */
    public static int getUserID() {
        return userID;
    }
    
    /**
     * This method sets the user id.
     */
    public static void setUserID(int userID) {
        LoginScreenController.userID = userID;
    }

    
    /**
     * This method checks if the entered user name and password combination
     * is correct. Then logs whether the login attempt was successful or not.
     * @param event Login button clicked
     */
    @FXML
    void onActionLogin(ActionEvent event) throws SQLException, IOException, 
            HasAppointmentsException {
        String userName = username.getText();
        String userPass = password.getText();
        Instant loginTime = Instant.now();
        
        Connection conn = JDBC.openConnection();
       
        String selectStatement = 
                "SELECT * FROM client_schedule.users WHERE User_Name = '" 
                + userName+ "' and Password = '" + userPass + "'";
        
        DBquery.setPreparedStatement(conn, selectStatement);
        
        PreparedStatement ps = DBquery.getPreparedStatement();
        
        ps.execute(); // Execute PreparedStatement
        
        ResultSet rs = ps.getResultSet();
         
         // Forward scroll ResultSet
         if(rs.next())
         {

            int User_ID = rs.getInt("User_ID");
            String User_Name = rs.getString("User_Name");
            LoginAttempt loginAttempt = new LoginAttempt(User_Name, 
                loginTime, "success");
            attempts.add(loginAttempt);
            FileIO.writeToFile(loginActivity, attempts);

            LoginScreenController.setUserID(User_ID);
            LoginScreenController.setName(User_Name);
            
            Schedule.checkForUpcomingAppointments();
            
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass()
                    .getResource("/view/MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        else {
            LoginAttempt loginAttempt = new LoginAttempt(userName, 
                        loginTime, "failure");
                attempts.add(loginAttempt);
                FileIO.writeToFile(loginActivity, attempts);

             ResourceBundle rb = ResourceBundle.getBundle("rb/Nat", 
                     Locale.getDefault());
             if(Locale.getDefault().getLanguage().equals("fr")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("boîte de dialogue d'erreur");
                alert.setContentText(
                        "la combinaison nom d'utilisateur et mot de passe "
                                + "n'existe pas");
                alert.showAndWait();
             }
             else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText(
                        "Username and Password combination doesn't exist");
                alert.showAndWait();
             }

         }

    }

     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rbs) {
        //TODO

        
       ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
       String zId = String.valueOf(localZoneId);
       userLocation.setText(zId);
       try {
            ResourceBundle rb = ResourceBundle.getBundle("rb/Nat", 
                    Locale.getDefault());

            if(Locale.getDefault().getLanguage().equals("fr"))
                btnLogin.setText(rb.getString("Login"));
                username.setPromptText(rb.getString("Username"));
                password.setPromptText(rb.getString("Password"));
                userLanguage.setText("French");
                
                
                
       }
       
       catch(MissingResourceException e) {
           System.out.println(
                   "Can't find bundle for base name rb/Nat, locale en_US");
       }
    }



}
