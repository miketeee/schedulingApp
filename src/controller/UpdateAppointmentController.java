
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
import helper.Time;
import database.ReadAppointments;
import database.UpdateAppointment;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.TimeZone;
import collections.Contacts;
import collections.AppointmentTypes;
import collections.Appointments;
import collections.Users;
import helper.Schedule;
import java.util.Optional;
import model.User;

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
    private ComboBox<User> userComboBox;
    @FXML
    private DatePicker startDate;
    
    @FXML
    private DatePicker endDate;
    
    

    /** This method populates the update appointment screen with
     * the data that the user selected on the main screen.
     * @param selectedAppointment 
     */
    public void initData(Appointment selectedAppointment) throws SQLException {
        
        Optional<Customer> customer = Customers.getAllCustomers().stream()
        .filter(x -> x.getId() == selectedAppointment.getCustomerId()).findAny();
        
        Optional<Contact> contact = Contacts.getAllContacts().stream()
                .filter(x -> x.getId() == selectedAppointment.getContactId()).findAny();
        
        Optional<User> user = Users.getAllUsers().stream()
        .filter(x -> x.getId() == selectedAppointment.getUserId()).findAny();
        
        try{
            appIdTxt.setText(String.valueOf(selectedAppointment
                    .getId()));
            appTitleTxt.setText(selectedAppointment.getTitle());
            appDescriptionTxt.setText(selectedAppointment
                    .getDescription());
            appLocationTxt.setText(selectedAppointment.
                    getLocation());
            appTypeComboBox.setValue(selectedAppointment
                    .getAppointmentType());
            appStartTimeTxt.setText(selectedAppointment
                    .getStartDateTime().toLocalDateTime()
                    .toLocalTime().toString());

            appEndTimeTxt.setText(selectedAppointment.
                    getEndDateTime().toLocalDateTime().toLocalTime()
                    .toString());
            customerIdComboBox.setValue(customer.get());
            contactComboBox.setValue(contact.get());
            userComboBox.setValue(user.get());
            startDate.setValue(selectedAppointment.getStartDateTime()
                    .toLocalDateTime().toLocalDate());
            endDate.setValue(selectedAppointment.getEndDateTime()
                    .toLocalDateTime().toLocalDate());
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
    private void onActionSaveAppointment (ActionEvent event) throws IOException, 
            SQLException, HasOverlapExcetption, StartBeforeEndException {
        
        /* Check to see if there are any empty fields*/
        if(appTitleTxt.getText().isEmpty() ||
           appDescriptionTxt.getText().isEmpty() ||
           appLocationTxt.getText().isEmpty() ||
           startDate.getValue().toString().isEmpty() ||
           endDate.getValue().toString().isEmpty() ||
           appStartTimeTxt.getText().isEmpty() ||
           appEndTimeTxt.getText().isEmpty())
        {
       
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("All fields must have a value."
                    + "Make sure to click on combo box values");
            alert.showAndWait(); 
        }
        
        else {
        
        LocalTime startTime = Time
                .formatStringToLocalTime(appStartTimeTxt);
        LocalTime endTime = Time
                .formatStringToLocalTime(appEndTimeTxt);
        
        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
        Instant[]convertedTime = Time.convertTimeToUTC(startDate.getValue(), 
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
                        + open + " and " 
                        + close);
                alert.showAndWait();
        }

        else if(start.isAfter(end)){
            throw new StartBeforeEndException();
        }
        
        else {
            
            Schedule.checkForOverlap(Integer.parseInt(appIdTxt.getText().toString()),
                    customerIdComboBox.getValue().getId(), start, end);
            UpdateAppointment.UpdateAppointment(Integer.parseInt(
                    appIdTxt.getText().toString()), 
                    appTitleTxt.getText().toString(), 
                    appDescriptionTxt.getText().toString(), 
                    appLocationTxt.getText().toString(), 
                    appTypeComboBox.getValue().toString(), 
                    startDate.getValue(), startTime, endDate.getValue(), 
                    endTime,customerIdComboBox.getValue().getId(), 
                    userComboBox.getValue().getId(), 
                    contactComboBox.getValue().getId());

            Appointments.appointmentList.clear();
            ReadAppointments.readAppointments();
            
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        }
    }
 
    
    /**This method cancels the process of updating an appointment
    * then returns he user to the home screen.
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
    appTypeComboBox.setItems(AppointmentTypes.getAllTypes());
    userComboBox.setItems(Users.getAllUsers());
    }    
    
}
