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
public class StartBeforeEndException extends Exception{
   
    /**This is the HasPartsException constructor. 
     This exception prevents a product from being deleted if it has associated parts. 
     */
    public StartBeforeEndException(){
        Alert alert = new Alert(Alert.AlertType.ERROR);  
        alert.setTitle("Error Dialog");
        alert.setContentText("Appointment start must be before end");
        alert.showAndWait();
    }

}
