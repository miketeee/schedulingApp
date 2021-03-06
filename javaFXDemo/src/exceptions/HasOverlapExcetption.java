/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import javafx.scene.control.Alert;

/** This class generates a HasOverlapExcetption*/
public class HasOverlapExcetption extends Exception{
   
    /**This is the HasOverlapExcetption constructor. 
     This exception prevents an appointment from being created if
     * the associated customer already has an appointment during this time. 
     */
    public HasOverlapExcetption(){
        Alert saveAlert = new Alert(Alert.AlertType.ERROR);  
        saveAlert.setTitle("Error Dialog");
        saveAlert.setContentText("The selected customer id has appointment\n at this time.");
        saveAlert.showAndWait();
    }

}
