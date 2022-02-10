/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import exceptions.HasOverlapExcetption;
import exceptions.StartBeforeEndException;
import helper.Time;
import database.ReadAppointments;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.TimeZone;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import collections.Contacts;
import collections.AppointmentTypes;
import model.Contact;
import model.Customer;
import collections.Appointments;
import collections.Customers;
import collections.Users;
import helper.Schedule;
import model.User;

/**
 * The class that controls the add appointment form
 *
 * @author 
 */
public class AddAppointmentController implements Initializable {
    
    Stage stage;
    Parent scene;
    
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField appIdTxt;
    @FXML
    private TextField appTitleTxt;
    @FXML
    private TextField appDescriptionTxt;
    @FXML
    private TextField appLocationTxt;
    @FXML
    private ComboBox<String> appTypeComboBox;
    @FXML
    private TextField appStartTimeTxt;
    @FXML
    private TextField appEndTimeTxt;
    @FXML
    private TextField appCustomerId;
    @FXML
    public Label userIdLabel;
    @FXML
    private ComboBox<Customer> customerIdComboBox;
    @FXML
    private ComboBox<Contact> contactComboBox;
    @FXML
    private ComboBox<User> userComboBox;
    
    @FXML
    private DatePicker startDate;
    
    @FXML
    private DatePicker endDate;
    
    
    /** This method saves the appointment information
     * entered into the add appointment form.
     * @param event Saved button clicked
     */
    
    @FXML
    private void onActionSaveAppointment (ActionEvent event) throws IOException, 
            SQLException, HasOverlapExcetption, StartBeforeEndException {
                LocalDate appStartDate = Time
                        .formatStringToLocalDate(startDate);
                LocalDate appEndDate = Time
                        .formatStringToLocalDate(endDate);
                LocalTime appStartTime = Time
                        .formatStringToLocalTime(appStartTimeTxt);
                LocalTime appEndTime = Time
                        .formatStringToLocalTime(appEndTimeTxt);
                ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
                Instant[]conversion = Time
                        .convertTimeToUTC(appStartDate, appStartTime, appEndDate, appEndTime);
                Instant open = conversion[0];
                Instant close = conversion[1];
                Instant appStart = conversion[2];
                Instant appEnd = conversion[3];
        
        try {
            if ((appStart.isAfter(close) || appStart.isBefore(open) 
                    || (appEnd.isAfter(close) 
                    || appEnd.isBefore(open)))){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText("Appointment must be between " 
                    + open.atZone(localZoneId) + " and " 
                    + close.atZone(localZoneId));
                alert.showAndWait();
            }

            else if(appStart.isAfter(appEnd)){
                throw new StartBeforeEndException();
            }
            else{
                Schedule.checkForOverlap(customerIdComboBox.getValue()
                        .getId(),
                        appStart, appEnd);
                database.AddAppointment.addAppointment(appTitleTxt.getText(), 
                        appDescriptionTxt.getText(), appLocationTxt.getText(), 
                        appTypeComboBox.getValue(), appStartDate, appStartTime, 
                        appEndDate, appEndTime, 
                        customerIdComboBox.getValue().getId(), 
                        userComboBox.getValue().getId(), 
                        contactComboBox.getValue().getId());
                
                Appointments.appointmentList.clear();
                ReadAppointments.readAppointments();
                stage = (Stage) ((Button)event.getSource()).getScene()
                        .getWindow();
                scene = FXMLLoader.load(getClass()
                        .getResource("/view/MainScreen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
                }
            }
        
        catch(NullPointerException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText("All fields must have a value");
                alert.showAndWait();                    
            }
 
    }
    
    /**This method cancels the process of adding a new appointment. Then returns
    * the user to the home screen.
    *@param event cancel button clicked 
    */
    @FXML
    private void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass()
                .getResource("/view/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
 
        
        appTypeComboBox.setItems(AppointmentTypes.getAllTypes());
        appTypeComboBox.setPromptText("Appointment type");
        
        customerIdComboBox.setItems(Customers.getAllCustomers());
        customerIdComboBox.setPromptText("Select a customer");
        
        contactComboBox.setItems(Contacts.getAllContacts());
        contactComboBox.setPromptText("Select a contact");
        
        userComboBox.setItems(Users.getAllUsers());
        userComboBox.setPromptText("Select a user");
        
        
    }    
    
    
}


