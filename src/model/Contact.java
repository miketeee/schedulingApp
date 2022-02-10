/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/** This class represents the contact model and is 
 * used to create contact objects.
 */
public class Contact {
    private int id;
    private String name;
    private String email;
    public Contact(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    /**
     * @return Returns the id
     */
    public int getId() {
        return id;
    }

    /** Sets the id
     * @param id ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

     /**
     * @return Returns the name
     */
    public String getName() {
        return name;
    }

    /** Sets the name
     * @param name Name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Returns the email
     */
    public String getEmail() {
        return email;
    }

    /** Sets the email
     * @param email Email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /** This prints out the contact object in string format*/
    @Override
    public String toString(){
        return("#" + Integer.toString(id) + " " + name);
    }
}


