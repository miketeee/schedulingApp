/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

/**
 * This class creates a list of customers,
 * adds customers and returns all customers.
 */
public class Customers {
    
    /**This list contains all available customers. */
    public static ObservableList<Customer>customerList = FXCollections.observableArrayList();
    
    
     /** This method adds a customer to the list of 
     * customers.
     * @param customer Object of type customer
     */
    public static void addCustomers(Customer customer)
    {
        customerList.add(customer);        
    }
    
    /** This method returns all customers that exist.
    * @return Returns list of customers
    */
    public static ObservableList<Customer> getAllCustomers()
    {
    return customerList;   
    }
    
}
