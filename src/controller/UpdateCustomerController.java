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
import database.UpdateCustomer;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
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
    


     /**This method loads the scene with selectedPart's values.
     @param selectedCustomer is the part that is selected from the main screen. 
     * @throws java.sql.SQLException 
     */
    public void initData(Customer selectedCustomer) throws SQLException {
        
        try{
        
        cId = selectedCustomer.getId();
        LoadDivisions.loadDivisionsByID(selectedCustomer.getDivision()); // get customers division
        LoadDivisions.loadDivisions(Divisions.getAllDivisions().get(0).getCountryId()); // fill combo box with states/provinces
//        Divisions.getAllDivisions().get(0).getCountryId();
        setRadioBtn(Divisions.getAllDivisions().get(0).getCountryId());
        customerDivision.setValue(Divisions.getAllDivisions().get(0)); // set value of combo box to customers division
        Divisions.getAllDivisions().remove(0); // delete index 0 it is a duplicate
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
    
    
    //get selected customerid from mainscreen
   
    
    
    // Select
    public void setRadioBtn(int countryId){
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
    
    @FXML
    void onActionFillComboBox(ActionEvent event) throws SQLException {
        
//        LoadCountries.LoadCountries(countryTG.getSelectedToggle().toString());
          if (!usRadioBtn.isSelected() && !ukRadioBtn.isSelected() && !canadaRadioBtn.isSelected()){
//              System.out.println("Select A country");
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
    
    @FXML
    void onActionClearComboBox(ActionEvent event) throws IOException{
        Divisions.divisionList.clear();
        customerDivision.setValue(null);
        customerDivision.setPromptText("Click on Search");

   
    }
        
    @FXML
    void onActionSaveCustomer (ActionEvent event) throws IOException, SQLException {
        
        
        if((
                customerNameTxt.getText().isEmpty()) || 
                customerAddressTxt.getText().isEmpty() ||
                customerPostalTxt.getText().isEmpty() ||
                customerPhoneTxt.getText().isEmpty() ||
                customerDivision.getSelectionModel().isEmpty()
                )
                
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("All fields must have a value");
            alert.showAndWait();                
        }
        else {
       
        
//        System.out.print(customerDivision.getValue().getId());
        
        database.UpdateCustomer.updateCustomer(cId, customerNameTxt.getText(), customerAddressTxt.getText(),  customerPostalTxt.getText(), customerPhoneTxt.getText(),customerDivision.getValue().getId());
        Customers.customerList.clear();
        LoadCustomers.loadCustomers();
        Divisions.divisionList.clear(); // Clear combo box
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        }
    }

    @FXML
    private void onActionCancel(ActionEvent event) throws IOException {
        
        Divisions.divisionList.clear(); // Clear combo box
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
