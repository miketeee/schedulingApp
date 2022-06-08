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
 * This class creates a list of users,
 * adds users and returns all users.
 */
public class Users {
    
    /**This list contains all available users. */
    public static ObservableList<User>userList = FXCollections.observableArrayList();
    
    
     /** This method adds a user to the list of 
     * users.
     * @param user Object of type user
     */
    public static void addUser(User user)
    {
        userList.add(user);        
    }
    
    /** This method returns all users that exist.
    * @return Returns list of users
    */
    public static ObservableList<User> getAllUsers()
    {
    return userList;   
    }
    
    
}
