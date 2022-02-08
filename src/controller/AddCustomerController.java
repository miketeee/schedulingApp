/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import helper.UpdateCustomer;
import helper.LoadCustomers;
import static helper.LoadCustomers.loadCustomers;
import helper.LoadDivisions;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import model.allAppointments;
import model.allCustomers;
import model.allDivisions;
import java.lang.NullPointerException;
import javafx.collections.ObservableList;
import javafx.scene.control.RadioButton;
import model.Division;
import helper.LoadCountries;

/**
 * The class that controls the add customer form
 *
 * @author tamic
 */
public class AddCustomerController implements Initializable {
    
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
              
              allDivisions.divisionList.clear();
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
            
            customerDivision.setItems(allDivisions.getAllDivisions());
          
          }

    }
    
    @FXML
    void onActionClearComboBox(ActionEvent event) throws IOException{
        allDivisions.divisionList.clear();
        customerDivision.setValue(null);
        customerDivision.setPromptText("Click on Search");

   
    }
    
    @FXML
    void onActionSaveCustomer (ActionEvent event) throws IOException, SQLException {
        
        try {
        helper.AddCustomer.addCustomer(customerNameTxt.getText(), customerAddressTxt.getText(), customerPostalTxt.getText(), customerPhoneTxt.getText(), customerDivision.getValue().getId());
        
        allCustomers.customerList.clear();
        LoadCustomers.loadCustomers();
    
        
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
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
       
    }

    
}
