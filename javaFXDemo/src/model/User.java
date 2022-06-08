/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/** This class represents the user model and is 
 * used to create user objects.
 */
public class User {
    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @return Returns the id
     */
    public int getId() {
        return id;
    }

    /** Sets the id*/
    public void setId(int id) {
        this.id = id;
    }

     /**
     * @return Returns the name
     */
    public String getName() {
        return name;
    }

    /** Sets the name*/
    public void setName(String name) {
        this.name = name;
    }

 
    /** This prints out the user object in string format*/
    @Override
    public String toString(){
        return("#" + Integer.toString(id) + " " + name);
    }
}

