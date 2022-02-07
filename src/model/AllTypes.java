/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author tamic
 */
public class AllTypes {
    
        /**This list contains all parts in the inventory. */
    private static ObservableList<String>typetList = FXCollections.observableArrayList();
    
    public static void addType(String type)
    {
        typetList.add(type);        
    }
    
    public static ObservableList<String> getAllTypes()
    {
    return typetList;   
    }
    
}