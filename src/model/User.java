/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.time.LocalTime;


/**
 *
 * @author tamic
 */
public class User {
    private int id;
    private String name;



    public User(int id, String name) {
        this.id = id;
        this.name = name;
  

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

 
@Override
public String toString(){
    return("#" + Integer.toString(id) + " " + name);
    }
}

