/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author tamic
 */
public class CurrentUser extends User{
    public int currentId;

    public CurrentUser(int id, String name, int currentId) {
        super(id, name);
    }

    public int getCurrentId() {
        return currentId;
    }

    public void setCurrentId(int currentId) {
        this.currentId = currentId;
    }

          
    }
    

