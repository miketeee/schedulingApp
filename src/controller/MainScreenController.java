/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import exceptions.HasAppointmentsException;
import exceptions.HasOverlapExcetption;
import helper.CheckForApps;
import helper.DeleteAppointment;
import helper.DeleteCustomer;
import static helper.DeleteCustomer.deleteCustomer;
import helper.HandleFile;
import helper.LoadAppointments;
import helper.LoadContacts;
import helper.LoadCustomers;
import helper.LoadUsers;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.AppointmentTime;
import model.Customer;
import model.Type;
import model.allAppointments;
import model.allCustomers;


/** 
 * The class that controls the main screen
 @author Tamicheal Wills
 */
public class MainScreenController implements Initializable {

    Stage stage;
    Parent scene;
    
    @FXML
    TableView<Customer> customerTableView;
    
    @FXML
    private TableView<Customer> customersTableView;
    
   @FXML
   private RadioButton reportMonthTotal;
 
   @FXML
   private RadioButton reportTypeTotal;
   
   @FXML
   private RadioButton reportByShift;
   
   @FXML
   private RadioButton resetAppsRadioBtn;
   
   @FXML
   private RadioButton reportSchedules;
   
   @FXML
   private RadioButton filterByMonth;
   
   @FXML
   private RadioButton filterByWeek;
    
    /** This is the exit button on the main screen. */
    
    @FXML
    public Label userIdMainLabel;
    
    @FXML
    private Button btnExit; 
    
    @FXML
    private Button btnAddCustomer;

    @FXML
    private Button btnDeleteCustomer;

    @FXML
    private Button btnModifyCustomer;
    
    @FXML
    private TableColumn<Customer, String> cAddressCol;

    @FXML
    private TableColumn<Customer, String> cDateCol;

    @FXML
    private TableColumn<Customer, Integer> cDivisionCol;

    @FXML
    private TableColumn<Customer, String> cNameCol;

    @FXML
    private TableColumn<Customer, String> cPhoneCol;

    @FXML
    private TableColumn<Customer, String> cTimeCol;

    @FXML
    private TableColumn<Customer, String> cUpdatedCol;

    @FXML
    private TableColumn<Customer, String> cUpdatorCol;

    @FXML
    private TableColumn<Customer, String> cZipCol;

    @FXML
    private TableColumn<Customer, Integer> cidCol;
    
    @FXML
    private TableColumn<Customer, Integer> cCreatorCol;
    
    @FXML
    private Button btnAddAppointment;

    @FXML
    private Button btnDeleteAppointment;

    @FXML
    private Button btnModifyAppointment;
    
    @FXML
    private TableColumn<Appointment, String> appTitleCol;

    @FXML
    private TableColumn<Appointment, String> appDescriptionCol;

    @FXML
    private TableColumn<Appointment, String> appLocationCol;

    @FXML
    private TableColumn<Appointment, String> appContactCol;

    @FXML
    private TableColumn<Appointment, String> appTypeCol;

    @FXML
    private TableColumn<Appointment, String> appStartCol;


    @FXML
    private TableColumn<Appointment, String> appEndCol;

    
    @FXML
    private TableColumn<Appointment, String> appCreatedCol;

    
    @FXML
    private TableColumn<Appointment, String> appCreatedByCol;
    
    @FXML
    private TableColumn<Appointment, String> appLastUpdateCol;
    
    @FXML
    private TableColumn<Appointment, String> appUpdatedByCol;

    @FXML
    private TableColumn<Appointment, Integer> appIdCol;
    
    @FXML
    private TableColumn<Appointment, Integer> appCustomerIdCol;
    
    @FXML
    private TableColumn<Appointment, Integer> appUserIdCol;
    
    @FXML
    private TableColumn<Appointment, Integer> appContactIdCol;
        

    @FXML
    private TableView<Appointment> appointmentsTableView;
    

    /** This loads the AddCustomer form. 
    @param event Changes scene to AddCustomer screen.
    */
    @FXML
    void onActionAddCustomer (ActionEvent event) throws IOException {
        
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass()
                .getResource("/view/AddCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
        /** This method loads the ModifyPart screen. 
    @param event gets the selected part and sends it data to the ModifyPart 
    * screen. 
    */
    
    @FXML
    void onActionShowMonthAndTypeReport(ActionEvent event) throws IOException{
        File newFile = HandleFile.createFile("appTotalByMonth");
        List report = CheckForApps.reportByMonth();
        HandleFile.writeToFile(newFile, report);
    }
    
    @FXML
    void onActionShowByShiftReport(ActionEvent event) throws IOException{
        File newFile = HandleFile.createFile("appTotalByShift");
        List report = CheckForApps.reportByShift();
        HandleFile.writeToFile(newFile, report);
    }
    
    @FXML
    void onActionShowSchedules(ActionEvent event) throws IOException{
        File newFile = HandleFile.createFile("appSchedules");
        List report = CheckForApps.reportByContact();
        HandleFile.writeToFile(newFile, report);
    }
    
    public void clearTable(){
        appointmentsTableView.getItems().clear();
    }
    
    @FXML
    void onActionFilterMonth(ActionEvent event) {

        // Set result equal to a stream of all appointments that has been filtered
        // by the current month and collected
        // This makes the program more efficient by using a stream to filter appointments
        // rather than using a for loop to check if an appoinments month is equal to the current month
        // the code set the tableview without the need to create a seperate list to hold the values

        appointmentsTableView.setItems(allAppointments.getAllAppointments().stream().filter((x) ->
        x.getStartDateTime().toLocalDateTime().toLocalDate().getMonth() == LocalDateTime.now().toLocalDate().getMonth())
                .collect(Collectors.toCollection(FXCollections::observableArrayList)));
    }
    
    
    @FXML
    void onActionFilterWeek(ActionEvent event) {
        TimeZone LocalZoneId = TimeZone.getDefault();
        Calendar currentWeek = Calendar.getInstance(LocalZoneId);
        allAppointments.appsFilterWeek.clear();
        for(Appointment app : allAppointments.getAllAppointments()){
            Calendar appWeek = Calendar.getInstance();
            Date date = app.getStartDateTime();
            appWeek.setTime(date);
            if(appWeek.get(Calendar.WEEK_OF_YEAR) == currentWeek.get(Calendar.WEEK_OF_YEAR)){
                allAppointments.appsFilterWeek.add(app);
            }
        }
        appointmentsTableView.setItems(allAppointments.getAppsFilterWeek());
    }
    
    @FXML
    void onActionResetApps(ActionEvent event) {
        appointmentsTableView.setItems(allAppointments.getAllAppointments());
    }
    
    @FXML
    private void onActionModifyCustomer(ActionEvent event) throws SQLException {
        try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource(("/view/ModifyCustomer.fxml")));
            Parent root = loader.load();
           
            //Get modifyCustomer controller 
            UpdateCustomerController modifyCustomerController = loader
                    .getController();
            //Pass whatever data you want. You can have multiple method calls here
           
            //This sets the basic fields to those of whatever part is selected
            modifyCustomerController.initData(customersTableView
                    .getSelectionModel().getSelectedItem());
            
//            //This determines whether the selected part is inhouse or outsourced
//            modifyCustomerController.modPartType(customersTableView.getSelectionModel().getSelectedItem());

                
            
            customersTableView.getSelectionModel().getSelectedItem().getId();

            // Changes scene to ModifyPart screen
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass()
                    .getResource("/view/ModifyCustomer.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
         
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    /** This method deletes a part from the inventory. 
    @param event removes the selected part. 
    */
    @FXML
    void onActionDeleteCustomer (ActionEvent event) throws IOException, 
            SQLException, HasOverlapExcetption, HasAppointmentsException {
        CheckForApps.check(customersTableView.getSelectionModel()
                .getSelectedItem().getId());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will "
                + "delete the customer: " + customersTableView
                        .getSelectionModel().getSelectedItem().getName()
                        .toUpperCase() + ", do you want to continue?");
        
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.isPresent() && result.get() == ButtonType.OK)
        {
        DeleteCustomer.deleteCustomer(customersTableView.getSelectionModel()
                .getSelectedItem().getId());
        allCustomers.customerList.clear();
        LoadCustomers.loadCustomers();
        }
        
    }
    
    @FXML
    void onActionAddAppointment (ActionEvent event) throws IOException, SQLException {
        
//        LoadUsers.loadUser();
        LoadContacts.loadContacts();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("/view/AddAppointment.fxml")));
        Parent root = loader.load();
           
        
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddAppointment.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
    
        /** This method loads the ModifyPart screen. 
    @param event gets the selected part and sends it data to the ModifyPart screen. 
    */
    @FXML
    private void onActionModifyAppointment(ActionEvent event) throws IOException, SQLException {
        try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource(("/view/ModifyAppointment.fxml")));
            Parent root = loader.load();
           
            //Get modifyCustomer controller 
            UpdateAppointmentController modifyAppointmentController = loader.getController();
            //Pass whatever data you want. You can have multiple method calls here
           
            //This sets the basic fields to those of whatever part is selected
            modifyAppointmentController.initData(appointmentsTableView.getSelectionModel().getSelectedItem());
            
//            //This determines whether the selected part is inhouse or outsourced
//            modifyCustomerController.modPartType(customersTableView.getSelectionModel().getSelectedItem());

                
            
            appointmentsTableView.getSelectionModel().getSelectedItem().getId();

            // Changes scene to ModifyPart screen
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/ModifyAppointment.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        }
         
        catch (IOException ex) {
            System.err.println(ex);
        }
    }
    
    public void canxApp(Appointment app) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setContentText("Appointment ID: " + app.getId() + " Type: " + app.getType() +  " has been canceled");
        alert.showAndWait();
    }
    
    @FXML
    void onActionDeleteAppointment (ActionEvent event) throws IOException, SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the selected do you want to continue?");
        
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.isPresent() && result.get() == ButtonType.OK)
        {
        canxApp(appointmentsTableView.getSelectionModel().getSelectedItem());
        DeleteAppointment.deleteAppointment(appointmentsTableView.getSelectionModel().getSelectedItem().getId());
        allAppointments.appointmentList.clear();
        LoadAppointments.loadAppointments();
        CheckForApps.reportByMonth();
    }
    }
    
    
    /** This method exits the program. 
    @param event closes the program.
    */
    @FXML
    void onActionExit (ActionEvent event) throws IOException {
        
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }

    /** This is the initialize method. 
    @param url initializes url
    @param rb initializes rb
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//     CheckForApps.reportByMonth();
//     CheckForApps.reportByType();
//     CheckForApps.reportForFirstShift();
//     CheckForApps.reportByContact();
 
  
     userIdMainLabel.setText(String.valueOf(LoginScreenController.getUserID()));

     customersTableView.setItems(allCustomers.getAllCustomers());
     cidCol.setCellValueFactory(new PropertyValueFactory<>("id"));
     cNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
     cAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
     cZipCol.setCellValueFactory(new PropertyValueFactory<>("zip"));
     cPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
     cDateCol.setCellValueFactory(new PropertyValueFactory<>("formattedCreatedTime"));
     cUpdatedCol.setCellValueFactory(new PropertyValueFactory<>("formattedUpdatedTime"));
     cUpdatorCol.setCellValueFactory(new PropertyValueFactory<>("updator"));
     cDivisionCol.setCellValueFactory(new PropertyValueFactory<>("division"));
     cCreatorCol.setCellValueFactory(new PropertyValueFactory<>("creator"));
     
     appointmentsTableView.setItems(allAppointments.getAllAppointments());
     appIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
     appTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
     appDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
     appLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
     appTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
     appStartCol.setCellValueFactory(new PropertyValueFactory<>("formattedStartDateTime"));
     appEndCol.setCellValueFactory(new PropertyValueFactory<>("formattedEndDateTime"));
     appCreatedCol.setCellValueFactory(new PropertyValueFactory<>("formattedCreatedDateTime"));
     appCreatedByCol.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
     appLastUpdateCol.setCellValueFactory(new PropertyValueFactory<>("formattedUpdatedDateTime"));
     appUpdatedByCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
     appCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
     appUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
     appContactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
    }    
}
