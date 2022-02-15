/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import javafx.scene.control.Alert;

/** This class generates a HasPartsException*/
public class StartBeforeEndException extends Exception{
   
    /**This is the StartBeforeEndException constructor. 
     This exception prevents an appointment from being created if the start
     * time is before the end time.
     */
    public static void StartBeforeEndException(){
        Alert alert = new Alert(Alert.AlertType.ERROR);  
        alert.setTitle("Error Dialog");
        alert.setContentText("Appointment start must be before end");
        alert.showAndWait();
    }

}
