
package controller;

import exceptions.HasOverlapExcetption;
import exceptions.StartBeforeEndException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import model.Appointment;
import model.Contact;
import model.Customer;
import collections.Customers;
import helper.FormatTimeEntered;
import database.LoadAppointments;
import database.UpdateAppointment;
import helper.TimeZoneConversion;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.TimeZone;
import collections.Contacts;
import collections.AppTypes;
import collections.Appointments;
import helper.CheckForApps;
import java.util.stream.Collectors;

/**
 * The class that controls the update appointment form
 *
 * @author 
 */
public class UpdateAppointmentController implements Initializable {
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
    private DatePicker startDate;
    
    @FXML
    private DatePicker endDate;

    /** This method populates the update appointment screen with
     * the data that the user selected on the main screen.
     * @param selectedAppointment 
     */
    public void initData(Appointment selectedAppointment) throws SQLException {
        
        try{
            
            for(Customer c : Customers.getAllCustomers()){
                    if(c.getId() == selectedAppointment.getCustomerId()){
                        for(Contact ct : Contacts.getAllContacts())
                            if(ct.getId() == selectedAppointment.getContactId()){
                            appIdTxt.setText(String.valueOf(selectedAppointment
                                    .getId()));
                            appTitleTxt.setText(selectedAppointment.getTitle());
                            appDescriptionTxt.setText(selectedAppointment
                                    .getDescription());
                            appLocationTxt.setText(selectedAppointment.
                                    getLocation());
                            appTypeComboBox.setValue(selectedAppointment
                                    .getType());
                            appStartTimeTxt.setText(selectedAppointment
                                    .getStartDateTime().toLocalDateTime()
                                    .toLocalTime().toString());
                            
                            appEndTimeTxt.setText(selectedAppointment.
                                    getEndDateTime().toLocalDateTime().toLocalTime()
                                    .toString());
                            customerIdComboBox.setValue(c);
                            contactComboBox.setValue(ct);
                            userIdLabel.setText(String.valueOf(LoginScreenController
                                    .getUserID()));
                            startDate.setValue(selectedAppointment.getStartDateTime()
                                    .toLocalDateTime().toLocalDate());
                            endDate.setValue(selectedAppointment.getEndDateTime()
                                    .toLocalDateTime().toLocalDate());
                       }
                    }
       
                }
            }
         
        
            //Is thrown if no appointment is selected when update button is clicked
            catch(NullPointerException e)                
            {
                Alert saveAlert = new Alert(Alert.AlertType.WARNING);  
                saveAlert.setTitle("Warning Dialog");
                saveAlert.setContentText("No appointment was selected"
                        + "please select an appointment.");
                saveAlert.showAndWait();
            }

    }
    
    /** This method saves the data from the form fields then 
     * takes the user back to the main screen. Error will be 
     * thrown if any fields are blank.
     * @param event Save button is clicked
     */
    @FXML
    void onActionSaveAppointment (ActionEvent event) throws IOException, 
            SQLException, HasOverlapExcetption, StartBeforeEndException {
        
        /* Check to see if there are any empty fields*/
        if(appTitleTxt.getText().isEmpty() ||
           appDescriptionTxt.getText().isEmpty() ||
           appLocationTxt.getText().isEmpty() ||
           appTypeComboBox.getSelectionModel().isEmpty() ||
           startDate.getValue().toString().isEmpty() ||
           endDate.getValue().toString().isEmpty() ||
           appStartTimeTxt.getText().isEmpty() ||
           appEndTimeTxt.getText().isEmpty() ||
           customerIdComboBox.getSelectionModel().isEmpty() ||
           contactComboBox.getSelectionModel().isEmpty()
            ) 
        {
       
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("All fields must have a value");
            alert.showAndWait(); 
        
       
        }
        
        else {
        
        LocalTime startTime = FormatTimeEntered
                .formatStringToLocalTime(appStartTimeTxt);
        LocalTime endTime = FormatTimeEntered
                .formatStringToLocalTime(appEndTimeTxt);
        
        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
        Instant[]convertedTime = TimeZoneConversion.convertTimeToUTC(startDate.getValue(), 
                startTime, endDate.getValue(), endTime);
        Instant open = convertedTime[0];
        Instant close = convertedTime[1];
        Instant start = convertedTime[2];
        Instant end = convertedTime[3];
       
        if ((start.isAfter(close) || start.isBefore(open) || (end.isAfter(close) 
                || end.isBefore(open)))){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText("Appointment must be between " 
                        + open.atZone(localZoneId) + " and " 
                        + close.atZone(localZoneId));
                alert.showAndWait();
        }

        else if(start.isAfter(end)){
            throw new StartBeforeEndException();
        }
            
            CheckForApps.withOverlap(customerIdComboBox.getValue().getId(), start, 
                    end);
            UpdateAppointment.UpdateAppointment(Integer.parseInt(
                    appIdTxt.getText().toString()), 
                    appTitleTxt.getText().toString(), 
                    appDescriptionTxt.getText().toString(), 
                    appLocationTxt.getText().toString(), 
                    appTypeComboBox.getValue().toString(), 
                    startDate.getValue(), startTime, endDate.getValue(), 
                    endTime,customerIdComboBox.getValue().getId(), 
                    Integer.parseInt(userIdLabel.getText()), 
                    contactComboBox.getValue().getId());

            Appointments.appointmentList.clear();
            LoadAppointments.loadAppointments();
            
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }
 
    
    /**This method cancels the process of adding a updating an appointment
    * Then returns he user to the home screen.
    *@param event Cancel button clicked 
    */
    @FXML
    private void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    customerIdComboBox.setItems(Customers.getAllCustomers());
    contactComboBox.setItems(Contacts.getAllContacts());
    appTypeComboBox.setItems(AppTypes.getAllTypes());
    }    
    
}
