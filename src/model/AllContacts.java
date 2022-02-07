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
public class AllContacts {
    
        /**This list contains all parts in the inventory. */
    private static ObservableList<Contact>contactList = FXCollections.observableArrayList();
    
    public static void addContact(Contact contact)
    {
        contactList.add(contact);        
    }
    
    public static ObservableList<Contact> getAllContacts()
    {
    return contactList;   
    }
    
}
