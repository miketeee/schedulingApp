/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import exceptions.HasOverlapExcetption;
import exceptions.StartBeforeEndException;
import helper.FormatTimeEntered;
import helper.HandleFile;
import helper.LoadAppointments;
import helper.LoadCustomers;
import helper.TimeZoneConversion;
import helper.checkForOverlap;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import static java.util.Calendar.SATURDAY;
import static java.util.Calendar.SUNDAY;
import java.util.ResourceBundle;
import java.util.TimeZone;
import static java.util.TimeZone.getDefault;
import java.util.function.BiPredicate;
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
import model.AllContacts;
import model.AllTypes;
import model.AllUsers;
import model.Contact;
import model.Customer;
import model.Type;
import model.User;
import model.allAppointments;
import model.allCustomers;

/**
 * FXML Controller class
 *
 * @author tamic
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
    private DatePicker startDate;
    
    @FXML
    private DatePicker endDate;
    
//    public void initUserInfo(int userInfo){
//     try{
//         userIdLabel.setText(String.valueOf(userInfo));
//     }
//     catch(IllegalArgumentException e) {
//         System.out.print(e);
//     }
//        }
    
    
    @FXML
    void onActionSaveAppointment (ActionEvent event) throws IOException, SQLException, HasOverlapExcetption, StartBeforeEndException {
        LocalDate sDate = FormatTimeEntered.formatStringToLocalDate(startDate);
        LocalDate eDate = FormatTimeEntered.formatStringToLocalDate(endDate);
        LocalTime sTime = FormatTimeEntered.formatStringToLocalTime(appStartTimeTxt);
        LocalTime eTime = FormatTimeEntered.formatStringToLocalTime(appEndTimeTxt);
        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
        Instant[]tzc = TimeZoneConversion.opsHours(sDate, sTime, eDate, eTime);
        Instant open = tzc[0];
        Instant close = tzc[1];
        Instant start = tzc[2];
        Instant end = tzc[3];
        int startDay = start.atOffset(ZoneOffset.UTC).getDayOfWeek().getValue();
        int endDay = end.atOffset(ZoneOffset.UTC).getDayOfWeek().getValue();
        
        try {
        
        
        BiPredicate<Instant, Instant> biPred = (x, y) -> x.isAfter(y);
        Boolean startBeforeClose = biPred.test(start, close);
       
        if ((startBeforeClose || start.isBefore(open) || (end.isAfter(close) || end.isBefore(open)))){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText("Appointment must be between " + open.atZone(localZoneId) + " and " + close.atZone(localZoneId));
                alert.showAndWait();
        }

        else if(start.isAfter(end)){
            throw new StartBeforeEndException();
        }
        else{
                checkForOverlap.check(customerIdComboBox.getValue().getId(), start, end, -1);
                helper.AddAppointment.addAppointment(appTitleTxt.getText(), appDescriptionTxt.getText(), appLocationTxt.getText(), appTypeComboBox.getValue(), sDate, sTime, eDate, eTime, customerIdComboBox.getValue().getId(), 
                Integer.parseInt(userIdLabel.getText()), contactComboBox.getValue().getId());
       
      
                
                allAppointments.appointmentList.clear();
                LoadAppointments.loadAppointments();
                stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
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
 
        
        userIdLabel.setText(String.valueOf(LoginScreenController.getUserID()));
        appTypeComboBox.setItems(AllTypes.getAllTypes());
        appTypeComboBox.setPromptText("Appointment type");
        
        customerIdComboBox.setItems(allCustomers.getAllCustomers());
        customerIdComboBox.setPromptText("Select a customer");
        
        contactComboBox.setItems(AllContacts.getAllContacts());
        contactComboBox.setPromptText("Select a Contact");
        
        
    }    
    
    
}


