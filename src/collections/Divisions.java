/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Division;

/**
 * This class creates a list of divisions,
 * adds divisions and returns all divisions.
. */
public class Divisions {
    
    /**This list contains all available divisions. */
    public static ObservableList<Division>divisionList = FXCollections.observableArrayList();
    
     /** This method adds a division to the list of 
     * divisions.
     * @param division Object of type division
     */
    public static void addDivisions(Division division)
    {
        divisionList.add(division);        
    }
    
    
    /** This method returns all divisions that exist.
    * @return Returns list of divisions
    */
    public static ObservableList<Division> getAllDivisions()
    {
    return divisionList;   
    }
    
}
