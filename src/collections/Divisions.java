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
 *
 * @author tamic
 */
public class Divisions {
    
    /**This list contains all parts in the inventory. */
    public static ObservableList<Division>divisionList = FXCollections.observableArrayList();
    
    public static void addDivisions(Division division)
    {
        divisionList.add(division);        
    }
    
    public static ObservableList<Division> getAllDivisions()
    {
    return divisionList;   
    }
    
}
