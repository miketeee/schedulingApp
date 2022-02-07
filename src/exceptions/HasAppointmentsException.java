/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import javafx.scene.control.Alert;

/**
 *
 * @author tamic
 */

/** This class generates a HasPartsException*/
public class HasAppointmentsException extends Exception{
   
    /**This is the HasPartsException constructor. 
     This exception prevents a product from being deleted if it has associated parts. 
     */
    public HasAppointmentsException(){
        Alert saveAlert = new Alert(Alert.AlertType.ERROR);  
        saveAlert.setTitle("Error Dialog");
        saveAlert.setContentText("Delete this customers appointments first");
        saveAlert.showAndWait();
    }

}
