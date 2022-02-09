/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.LoadDivisions;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.Customer;
import model.Division;
import collections.Divisions;
import database.LoadCountries;
import database.LoadCustomers;
import collections.Customers;

/**
 * The class that controls the update customer form
 *
 * @author tamic
 */


public class UpdateCustomerController implements Initializable {
    
    
    Stage stage;
    Parent scene;

    @FXML
    private TextField customerIdTxt;
    @FXML
    private TextField customerNameTxt;
    @FXML
    private TextField customerAddressTxt;
    @FXML
    private TextField customerPostalTxt;
    @FXML
    private TextField customerPhoneTxt;
    @FXML
    private Button fillComboBox;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private ComboBox<Division> customerDivision;
    @FXML
    private RadioButton canadaRadioBtn;
    @FXML
    private RadioButton ukRadioBtn;
    @FXML
    private RadioButton usRadioBtn;
    
    /** Toggles between country radio buttons. */
    @FXML
    public ToggleGroup countryTG;
    
    private int cId = 0;
    


    /** This method populates the update customer screen with
     * the data that the user selected on the main screen.
     * @param selectedCustomer 
     */
    public void initData(Customer selectedCustomer) throws SQLException {
        
        try{
        
        cId = selectedCustomer.getId();
        LoadDivisions.loadDivisionsByID(selectedCustomer.getDivision()); 
        LoadDivisions.loadDivisions(Divisions.getAllDivisions().get(0).getCountryId());
        setRadioBtn(Divisions.getAllDivisions().get(0).getCountryId());
        customerDivision.setValue(Divisions.getAllDivisions().get(0));
        Divisions.getAllDivisions().remove(0); 
        customerDivision.setItems(Divisions.getAllDivisions());
        customerNameTxt.setText(selectedCustomer.getName());
        customerAddressTxt.setText(selectedCustomer.getAddress());
        customerPostalTxt.setText(selectedCustomer.getZip());
        customerPhoneTxt.setText(selectedCustomer.getPhone());
        }
        
        //Is thrown if no part is selected when update button is clicked
        catch(NullPointerException e)                
        {
            Alert saveAlert = new Alert(Alert.AlertType.WARNING);  
            saveAlert.setTitle("Warning Dialog");
            saveAlert.setContentText("No customer was selected. Please select customer.");
            saveAlert.showAndWait();
        }

    }

    /** This method toggles the radio button of the country that
     * corresponds with the country id entered into the init method
     * @param countryId that is passed from the main screen controller
     */
   private void setRadioBtn(int countryId){
        switch(countryId){
            case 1:
                countryTG.selectToggle(usRadioBtn);
                break;
            case 2:
                countryTG.selectToggle(ukRadioBtn);
                break;
            case 3:
                countryTG.selectToggle(canadaRadioBtn);
                break;
        }
    }
    
   /** This method populates the division combo box based on 
    * which country radio button is selected.
    * @param event Search button clicked
    */
    @FXML
    private void onActionFillComboBox(ActionEvent event) throws SQLException {
        
          if (!usRadioBtn.isSelected() && !ukRadioBtn.isSelected() 
                  && !canadaRadioBtn.isSelected()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText("Select A country");
                alert.showAndWait();
          }
          else {
              
              Divisions.divisionList.clear();
              customerDivision.setPromptText("Make a selection");
              
              if (countryTG.getSelectedToggle().equals(ukRadioBtn)){
                  String clickedCountry = "UK";
                  LoadCountries.loadCountries(clickedCountry);
              }
              else if (countryTG.getSelectedToggle().equals(usRadioBtn)){
                  String clickedCountry = "U.S";
                  LoadCountries.loadCountries(clickedCountry);
              }
              else {
                 String clickedCountry = "Canada"; 
                 LoadCountries.loadCountries(clickedCountry);
              }
            
            customerDivision.setItems(Divisions.getAllDivisions());
          
          }

    }
    
    /** This method clears the divisions combo box when
     * a user selects the radio button for a different country.
     * @param event Radio button for country toggled
     */
    @FXML
    private void onActionClearComboBox(ActionEvent event) throws IOException{
        Divisions.divisionList.clear();
        customerDivision.setValue(null);
        customerDivision.setPromptText("Click on Search");

   
    }
     
    /** This method saves the data from the form fields then 
     * takes the user back to the main screen. Error will be 
     * thrown if any fields are blank.
     * @param event Save button is clicked
     */
    @FXML
    private void onActionSaveCustomer (ActionEvent event) throws IOException, SQLException {
        
        
        if( customerNameTxt.getText().isEmpty() || 
            customerAddressTxt.getText().isEmpty() ||
            customerPostalTxt.getText().isEmpty() ||
            customerPhoneTxt.getText().isEmpty() ||
            customerDivision.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("All fields must have a value");
            alert.showAndWait();                
        }
        else {
            database.UpdateCustomer.updateCustomer(cId, customerNameTxt.getText(), 
                    customerAddressTxt.getText(),  customerPostalTxt.getText(), 
                    customerPhoneTxt.getText(),customerDivision.getValue().getId());
            Customers.customerList.clear();
            LoadCustomers.loadCustomers();
            Divisions.divisionList.clear(); // Clear combo box
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    
    /**This method cancels the process of updating a customer
    * then returns he user to the home screen.
    *@param event Cancel button clicked 
    */
    @FXML
    private void onActionCancel(ActionEvent event) throws IOException {
        
        Divisions.divisionList.clear();
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
        
        
        
    }    
    
}
