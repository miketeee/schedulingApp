/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import exceptions.HasOverlapExcetption;
import exceptions.StartBeforeEndException;
import helper.LoadDivisions;
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
import model.AppointmentTime;
import model.Contact;
import model.Customer;
import model.Type;
import model.allCustomers;
import model.allDivisions;
import helper.FormatTimeEntered;
import helper.LoadAppointments;
import helper.ModifyAppointment;
import helper.TimeZoneConversion;
import helper.checkForOverlap;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.TimeZone;
import javafx.scene.control.TextFormatter;
import model.AllContacts;
import model.AllTypes;
import model.allAppointments;

/**
 * FXML Controller class
 *
 * @author tamic
 */
public class ModifyAppointmentController implements Initializable {
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
    
    //value returning Lambda Expression
   
//    CustomerInterface appCID = a -> {
//       String name = allCustomers.getAllCustomers(); 
//    };
    
            /**This method loads the scene with selectedPart's values.
     @param selectedCustomer is the part that is selected from the main screen. 
//     */
    public void initData(Appointment selectedAppointment) throws SQLException {
        
        try{
            for(Customer c : allCustomers.getAllCustomers()){
                    if(c.getId() == selectedAppointment.getCustomerId()){
                        for(Contact ct : AllContacts.getAllContacts())
                            if(ct.getId() == selectedAppointment.getContactId()){
                            appIdTxt.setText(String.valueOf(selectedAppointment.getId()));
                            appTitleTxt.setText(selectedAppointment.getTitle());
                            appDescriptionTxt.setText(selectedAppointment.getDescription());
                            appLocationTxt.setText(selectedAppointment.getLocation());
                            appTypeComboBox.setValue(selectedAppointment.getType());
                            appStartTimeTxt.setText(selectedAppointment.getStartDateTime().toLocalDateTime().toLocalTime().toString());
                            
                            appEndTimeTxt.setText(selectedAppointment.getEndDateTime().toLocalDateTime().toLocalTime().toString());
                            customerIdComboBox.setValue(c);
                            contactComboBox.setValue(ct);
                            userIdLabel.setText(String.valueOf(LoginScreenController.getUserID()));
                            startDate.setValue(selectedAppointment.getStartDateTime().toLocalDateTime().toLocalDate());
                            endDate.setValue(selectedAppointment.getEndDateTime().toLocalDateTime().toLocalDate());

                    }
       
                    }
                    }
        }
        
        //Is thrown if no part is selected when modify button is clicked
        catch(NullPointerException e)                
        {
            Alert saveAlert = new Alert(Alert.AlertType.WARNING);  
            saveAlert.setTitle("Warning Dialog");
            saveAlert.setContentText("No customer was selected. Please select customer.");
            saveAlert.showAndWait();
        }

    }
    
    @FXML
    void onActionSaveAppointment (ActionEvent event) throws IOException, SQLException, HasOverlapExcetption, StartBeforeEndException {
        
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
                        
            ) {
       
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("All fields must have a value");
            alert.showAndWait(); 
        
       
        }
        else{
        LocalTime startTime = FormatTimeEntered.formatStringToLocalTime(appStartTimeTxt);
        LocalTime endTime = FormatTimeEntered.formatStringToLocalTime(appEndTimeTxt);
        
        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
        Instant[]tzc = TimeZoneConversion.opsHours(startDate.getValue(), startTime, endDate.getValue(), endTime);
        Instant open = tzc[0];
        Instant close = tzc[1];
        Instant start = tzc[2];
        Instant end = tzc[3];
        
        int startDay = start.atOffset(ZoneOffset.UTC).getDayOfWeek().getValue();
        int endDay = end.atOffset(ZoneOffset.UTC).getDayOfWeek().getValue();
       
        if ((start.isAfter(close) || start.isBefore(open) || (end.isAfter(close) || end.isBefore(open)))){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText("Appointment must be between " + open.atZone(localZoneId) + " and " + close.atZone(localZoneId));
                alert.showAndWait();
        }

        else if(start.isAfter(end)){
            throw new StartBeforeEndException();
        }
            
            checkForOverlap.check(customerIdComboBox.getValue().getId(), start, end, Integer.parseInt(appIdTxt.getText().toString()));
            ModifyAppointment.ModifyAppointment(Integer.parseInt(appIdTxt.getText().toString()), appTitleTxt.getText().toString(), appDescriptionTxt.getText().toString(), appLocationTxt.getText().toString(), appTypeComboBox.getValue().toString(), startDate.getValue(), startTime, endDate.getValue(), endTime,customerIdComboBox.getValue().getId(), Integer.parseInt(userIdLabel.getText()), contactComboBox.getValue().getId());

            allAppointments.appointmentList.clear();
            LoadAppointments.loadAppointments();
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }
 
    

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
    customerIdComboBox.setItems(allCustomers.getAllCustomers());
    contactComboBox.setItems(AllContacts.getAllContacts());
    appTypeComboBox.setItems(AllTypes.getAllTypes());
    }    
    
}
