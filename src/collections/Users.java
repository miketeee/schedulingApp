/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;

/**
 *
 * @author tamic
 */
public class Users {
    
        /**This list contains all parts in the inventory. */
    public static ObservableList<User>userList = FXCollections.observableArrayList();
    
    public static void addUser(User user)
    {
        userList.add(user);        
    }
    
    public static ObservableList<User> getAllUsers()
    {
    return userList;   
    }
    
    
}
