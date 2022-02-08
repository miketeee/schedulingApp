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
 *
 * @author tamic
 */
public class Customers {
    
    /**This list contains all parts in the inventory. */
    public static ObservableList<Customer>customerList = FXCollections.observableArrayList();
    
    public static void addCustomers(Customer customer)
    {
        customerList.add(customer);        
    }
    
    public static ObservableList<Customer> getAllCustomers()
    {
    return customerList;   
    }
    
}
