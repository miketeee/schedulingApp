/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import exceptions.HasAppointmentsException;
import exceptions.HasOverlapExcetption;
import helper.Schedule;
import database.DeleteAppointment;
import database.DeleteCustomer;
import helper.FileIO;
import database.ReadAppointments;
import database.ReadContacts;
import database.ReadCustomers;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Customer;
import collections.Appointments;
import collections.Contacts;
import collections.Customers;
import helper.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import model.Contact;


/** 
 * The class that controls the main screen
 @author 
 */
public class MainScreenController implements Initializable {

    Stage stage;
    Parent scene;
    
    @FXML
    TableView<Customer> customerTableView;
    
    @FXML
    private TableView<Customer> customersTableView;
    
//    @FXML
//    private TableView<String> reportOneListView;
    
    
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
    
    @FXML
    public Label userIdMainLabel;
    
    @FXML
    private Button btnExit; 
    
    @FXML
    private Button btnAddCustomer;

    @FXML
    private Button btnDeleteCustomer;

    @FXML
    private Button btnUpdateCustomer;
    
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
    private Button btnUpdateAppointment;
    
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
    
    @FXML
    private ListView<Map.Entry<String, Integer>> reportOneListView;
    
    @FXML
    private ListView<Map.Entry<Contact, List<Appointment>>> scheduleOneListView;
    
    @FXML
    private ListView<Map.Entry<String, List<Appointment>>> shiftScheduleListView;
    
    
    

    
    
    /** This method generates a report of appointments
     * filtered by month and type. Then it set the reportOne list view.
    @param event Radio Button selected
    */

    @FXML
    void onActionShowMonthAndTypeReport(ActionEvent event) throws IOException{
        reportOneListView.getItems().clear();
        Map<String, Integer> monthAndType = new HashMap<>();
        for(Appointment app : Appointments.getAllAppointments()) {
            String monthType = app.getStartDateTime().toLocalDateTime()
                    .getMonth().toString() + "-" + app.getAppointmentType();
            if(!monthAndType.containsKey(monthType)){
                monthAndType.put(monthType, 1);
            }
            else {
                Integer newValue = monthAndType.get(monthType) + 1;
                monthAndType.put(monthType, newValue);
            }
        }

        ObservableList<Map.Entry<String, Integer>> report = FXCollections
                .observableArrayList(monthAndType.entrySet().stream()
                        .collect(Collectors.toList()));
        reportOneListView.setItems(report);
    }
    
    /** This method generates a report of appointments
     * filtered based on which of the two shifts they occur
     * during. Appointments before 3pm EST are first shift. 
     * Appointments after 3pm are second shift. The report is 
     * displayed in a list view.
    @param event Radio Button selected
    */
    @FXML
    void onActionShowByShiftReport(ActionEvent event) throws IOException{
   
        List<Appointment> firstShiftApps = new ArrayList<>();
        List<Appointment> secondShiftApps = new ArrayList<>();
        for(Appointment app : Appointments.getAllAppointments()){
            LocalDate startDate = app.getStartDateTime().toLocalDateTime().toLocalDate();
            LocalTime startTime = app.getStartDateTime().toLocalDateTime().toLocalTime();
            LocalDate endDate = app.getEndDateTime().toLocalDateTime().toLocalDate();
            LocalTime endTime = app.getStartDateTime().toLocalDateTime().toLocalTime();
            Instant[]conversion = Time.convertTimeToUTC(startDate, startTime, endDate, endTime);
            Instant start = conversion[2];
            Instant firstShift = conversion[4];
            
            if(start.isBefore(firstShift)){
            firstShiftApps.add(app);
            }
            else {
                secondShiftApps.add(app);
            }
        }
        HashMap<String, List<Appointment>> shifts = new HashMap<>();
        shifts.put("First Shift",firstShiftApps);
        shifts.put("Second Shift", secondShiftApps);
        ObservableList<Map.Entry<String, List<Appointment>>> shiftSchedule = FXCollections
        .observableArrayList(shifts.entrySet().stream()
        .collect(Collectors.toList()));
        
        shiftScheduleListView.setItems(shiftSchedule);
    }
    
    
    /** This method generates a report for each contact 
     * that only includes appointments where they are listed
     * as the contact. The report is displayed in a list view.
     * @param event Radio Button selected
     */
    @FXML
    void onActionShowSchedules(ActionEvent event) throws IOException{
          HashMap<Contact, List<Appointment>> schedule = new HashMap<>();
            for(Appointment app : Appointments.getAllAppointments()){
                for(Contact ct : Contacts.getAllContacts()){
                if(!schedule.containsKey(ct))
                    schedule.put(ct, new ArrayList<>());
                    if(app.getContactId() == ct.getId()){
                        List cApps = schedule.get(ct);
                        cApps.add(app);
                        schedule.put(ct, cApps);
                    }
                else {
                continue;
                }
            }
        }
        ObservableList<Map.Entry<Contact, List<Appointment>>> scheduleOne = FXCollections
            .observableArrayList(schedule.entrySet().stream()
            .collect(Collectors.toList()));
        
        scheduleOneListView.setItems(scheduleOne);
    }
    
    
    /** This method filters the appointment table to
     * show only the appointments that occur during 
     * the current month.
     * Lambda - The included expression makes the program more efficient by 
         filtering a stream of appointments rather than using a traditional for loop to check.
         The code assumes what to do rather than me having to explicitly tell it
         The code allows the appointment table view to be set without the need to 
          create new variables. The code sets the table view to a list derived
         from a stream of all current appointments that has been filtered to only show the 
         appointments where the current month of the appointment is equal to 
         the current month of current month of which the program is running
         based off of the current date.
     * @param event Radio Button selected
     */
    @FXML
    void onActionFilterMonth(ActionEvent event) {

        appointmentsTableView.setItems(Appointments.getAllAppointments().stream().filter((x) ->
        x.getStartDateTime().toLocalDateTime().toLocalDate().getMonth() == LocalDateTime.now().toLocalDate().getMonth())
                .collect(Collectors.toCollection(FXCollections::observableArrayList)));
    }
    
    /** This method filters the appointment table view to only show
     * appointments that occur during the current week based of the 
     * current date.
     * @param event Radio Button selected
     */
    @FXML
    void onActionFilterWeek(ActionEvent event) {
        TimeZone LocalZoneId = TimeZone.getDefault();
        Calendar currentWeek = Calendar.getInstance(LocalZoneId);
        Appointments.appsFilterWeek.clear();
        for(Appointment app : Appointments.getAllAppointments()){
            Calendar appWeek = Calendar.getInstance();
            Date date = app.getStartDateTime();
            appWeek.setTime(date);
            if(appWeek.get(Calendar.WEEK_OF_YEAR) 
                    == currentWeek.get(Calendar.WEEK_OF_YEAR)){
                Appointments.appsFilterWeek.add(app);
            }
        }
        appointmentsTableView.setItems(Appointments.getAppsFilterWeek());
    }
    
    
    /** This method resets the appointment table view to 
     * show all appointments.
     * @param event Radio Button selected
     */
    @FXML
    void onActionResetApps(ActionEvent event) {
        appointmentsTableView.setItems(Appointments.getAllAppointments());
    }
    
    /** This method takes the user to the the add customer form. 
    @param event Add button clicked.
    */
    @FXML
    void onActionAddCustomer (ActionEvent event) throws IOException {
        
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass()
                .getResource("/view/AddCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
    
    /** This method sends the information of the item that 
     * was selected to the update customer form.
     * @param event Update button clicked
     */
    @FXML
    private void onActionUpdateCustomer(ActionEvent event) throws SQLException {
        try {

            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource(("/view/UpdateCustomer.fxml")));
            Parent root = loader.load();
           
            UpdateCustomerController updateCustomerController = loader
                    .getController();

            updateCustomerController.initData(customersTableView
                    .getSelectionModel().getSelectedItem());
               
            customersTableView.getSelectionModel().getSelectedItem().getId();

            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass()
                    .getResource("/view/UpdateCustomer.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
         
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    /** This method deletes a customer from the database. 
    @param event Delete customer button clicked 
    */
    @FXML
    void onActionDeleteCustomer (ActionEvent event) throws IOException, 
            SQLException, HasOverlapExcetption, HasAppointmentsException {
        Schedule.checkForExistingAppointments(customersTableView.getSelectionModel()
                .getSelectedItem().getId());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will "
                + "delete the customer: " + customersTableView
                        .getSelectionModel().getSelectedItem().getName()
                        .toUpperCase() + ", do you want to continue?");
        
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.isPresent() && result.get() == ButtonType.OK) {
            DeleteCustomer.deleteCustomer(customersTableView.getSelectionModel()
                    .getSelectedItem().getId());
            Customers.customerList.clear();
            ReadCustomers.readCustomers();
        }
        
    }
    
    /** This method takes the user to the the add appointment form. 
    @param event Add button clicked.
    */
    @FXML
    void onActionAddAppointment (ActionEvent event) throws IOException, 
            SQLException {
        
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource(("/view/AddAppointment.fxml")));
        Parent root = loader.load();
        
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass()
                .getResource("/view/AddAppointment.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    /** This method sends the information of the item that 
     * was selected to the update appointment form.
     * @param event Update button clicked
     */
    @FXML
    private void onActionUpdateAppointment(ActionEvent event) throws IOException, 
            SQLException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource(("/view/UpdateAppointment.fxml")));
            Parent root = loader.load();
           
            UpdateAppointmentController updateAppointmentController 
                    = loader.getController();
           
            updateAppointmentController.initData(appointmentsTableView
                    .getSelectionModel().getSelectedItem());
            
            appointmentsTableView.getSelectionModel().getSelectedItem().getId();

            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass()
                    .getResource("/view/UpdateAppointment.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        }
         
        catch (IOException ex) {
            System.err.println(ex);
        }
    }
    
    /**This method shows an alert with appointment data 
     * after any appointment is deleted from the database.
     * @param app object
     */
    private void cancelledApp(Appointment app) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setContentText("Appointment ID: " + app.getId() + " Type: " 
                + app.getAppointmentType() +  " has been canceled");
        alert.showAndWait();
    }
    
    /** This method deletes the selected appointment from the database. 
    @param event Delete button clicked
    */
    @FXML
    private void onActionDeleteAppointment (ActionEvent event) throws IOException, 
            SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, 
                "This will delete the selected appointment do you want to continue?");
        
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.isPresent() && result.get() == ButtonType.OK)
        {
        cancelledApp(appointmentsTableView.getSelectionModel().getSelectedItem());
        DeleteAppointment.deleteAppointment(appointmentsTableView
                .getSelectionModel().getSelectedItem().getId());
        Appointments.appointmentList.clear();
        ReadAppointments.readAppointments();
    }
    }
    
    
    /** This method exits the program. 
    @param event Exit button clicked.
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
        // Set user id, customers table view, and appointments table view
  
     userIdMainLabel.setText(String.valueOf(LoginScreenController.getUserID()));

     customersTableView.setItems(Customers.getAllCustomers());
     cidCol.setCellValueFactory(new PropertyValueFactory<>("id"));
     cNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
     cAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
     cZipCol.setCellValueFactory(new PropertyValueFactory<>("zip"));
     cPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
     cDateCol.setCellValueFactory(new PropertyValueFactory<>("formattedCreatedTime"));
     cUpdatedCol.setCellValueFactory(new PropertyValueFactory<>("formattedUpdatedTime"));
     cUpdatorCol.setCellValueFactory(new PropertyValueFactory<>("updatedBy"));
     cDivisionCol.setCellValueFactory(new PropertyValueFactory<>("division"));
     cCreatorCol.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
     
     appointmentsTableView.setItems(Appointments.getAllAppointments());
     appIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
     appTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
     appDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
     appLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
     appTypeCol.setCellValueFactory(new PropertyValueFactory<>("AppointmentType"));
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
