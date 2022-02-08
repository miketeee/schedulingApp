/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import javafx.scene.control.Alert;

/** This class generates a HasPartsException*/
public class HasAppointmentsException extends Exception{
   
    /**This is the HasAppointmentsException constructor. 
     *This exception prevents a customer from being deleted if the customer
     * id is associated with an existing appointment.
     */
    public HasAppointmentsException(){
        Alert saveAlert = new Alert(Alert.AlertType.ERROR);  
        saveAlert.setTitle("Error Dialog");
        saveAlert.setContentText("Delete this customers appointments first");
        saveAlert.showAndWait();
    }

}
