/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class creates a collection of appointment types,
 * adds appointment types and returns all types.
 */
public class AppointmentTypes {
    
    /**This list contains all available appointment types. */
    private static ObservableList<String>typeOfAppointmentList = FXCollections.observableArrayList();
    
    /** This method adds an appointment type to the list of 
     * appointment types.
     * @param appType String value of appointment type
     */
    public static void addType(String appType)
    {
        typeOfAppointmentList.add(appType);        
    }
    
    /** This method returns all appointment types that exist.
     * @return Returns list of appointment types
     */
    public static ObservableList<String> getAllTypes()
    {
    return typeOfAppointmentList;   
    }
    
}