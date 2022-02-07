/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import exceptions.HasAppointmentsException;
import helper.CheckForApps;
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
import helper.DBquery;
import helper.FormatTimeEntered;
import helper.HandleFile;
import helper.JDBC;
import helper.LoadUsers;
import helper.LogActivity;
import helper.ReportsInterface;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.TimeZone;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Stream;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import model.AllUsers;
import model.CurrentUser;
import model.Customer;
import model.LoginAttempt;
import model.User;
import model.allCustomers;
/**
 *
 * @author tamic
 */
public class LoginScreenController implements Initializable {
    Stage stage;
    Parent scene;
    public static int userID = 0;
    public static String name;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        LoginScreenController.name = name;
    }

    public static int getUserID() {
        return userID;
    }

    public static void setUserID(int userID) {
        LoginScreenController.userID = userID;
    }

    @FXML
    private Button btnLogin;
    
    
    @FXML
    private Button btnLogin1;

    @FXML
    private TextField password;

    @FXML
    private Label userLanguage;

    @FXML
    private Label userLocation;

    @FXML
    private TextField username;
    
    
    List attempts = new ArrayList();
    File loginActivity;

    public LoginScreenController() throws IOException {
        this.loginActivity = HandleFile.createFile("login_activity");
    }
    
    @FXML
    void onActionLogin(ActionEvent event) throws SQLException, IOException, HasAppointmentsException {
        String userName = username.getText();
        String userPass = password.getText();
        Instant instant = Instant.now();
        
        
        
        Connection conn = JDBC.openConnection();
       
        String selectStatement = "SELECT * FROM client_schedule.users WHERE User_Name = '" + userName+ "' and Password = '" + userPass + "'";
        
        DBquery.setPreparedStatement(conn, selectStatement); // Create prepared statement
        
        PreparedStatement ps = DBquery.getPreparedStatement();
        
        ps.execute(); // Execute PreparedStatement
        
        ResultSet rs = ps.getResultSet();
         
         // Forward scroll ResultSet
         if(rs.next())
         {
             //call is good func
            //Load second scene
            int User_ID = rs.getInt("User_ID");
            String User_Name = rs.getString("User_Name");
                LoginAttempt loginAttempt = new LoginAttempt(User_Name, instant, "success");
                attempts.add(loginAttempt);
                HandleFile.writeToFile(loginActivity, attempts);

            LoginScreenController.setUserID(User_ID);
            LoginScreenController.setName(User_Name);
            
            
            CheckForApps.withinFifteenMinutes();
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
         }
         else {
                LoginAttempt loginAttempt = new LoginAttempt(userName, instant, "failure");
                attempts.add(loginAttempt);
                HandleFile.writeToFile(loginActivity, attempts);

             ResourceBundle rb = ResourceBundle.getBundle("rb/Nat", Locale.getDefault());
             if(Locale.getDefault().getLanguage().equals("fr")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("boîte de dialogue d'erreur");
                alert.setContentText("la combinaison nom d'utilisateur et mot de passe n'existe pas");
                alert.showAndWait();
             }
             else {
                 // print bad name password combo to list with req'd info
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText("Username and Password combination doesn't exist");
                alert.showAndWait();
             }

         }

    }

    @Override
    public void initialize(URL url, ResourceBundle rbs) {
        //TODO

        
       ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
       String zId = String.valueOf(localZoneId);
       userLocation.setText(zId);
       try {
            ResourceBundle rb = ResourceBundle.getBundle("rb/Nat", Locale.getDefault());

            if(Locale.getDefault().getLanguage().equals("fr"))
                btnLogin.setText(rb.getString("Login"));
                username.setPromptText(rb.getString("Username"));
                password.setPromptText(rb.getString("Password"));
                userLanguage.setText("French");
                
                
                
       }
       
       catch(MissingResourceException e) {
           System.out.println("Can't find bundle for base name rb/Nat, locale en_US");
       }
    }



}
