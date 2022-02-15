/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;

/**
 * This class creates a collection of contacts,
 * adds contacts and returns all contacts.
 */
public class Contacts {
    
    /**This list contains all available contacts. */
    private static ObservableList<Contact>contactList = FXCollections.observableArrayList();
    
    
    /** This method adds a contact to the list of 
     * contacts.
     * @param contact Object of type contact
     */
    public static void addContact(Contact contact)
    {
        contactList.add(contact);        
    }
   
    /** This method returns all contacts that exist.
    * @return Returns list of contacts
    */
    public static ObservableList<Contact> getAllContacts()
    {
    return contactList;   
    }
    
}
