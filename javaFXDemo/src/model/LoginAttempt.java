/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.Instant;

/** This class represents the loginAttempt model and is 
 * used to create loginAttempt objects.
 */
public class LoginAttempt {
    public String name;
    public Instant currentTime;
    public String loginResult;

    public LoginAttempt(String name, Instant timeNow, String status) {
        this.name = name;
        this.currentTime = timeNow;
        this.loginResult = status;
    }

    /**
     * @return Returns the id
     */
    public String getName() {
        return name;
    }

    /** Sets the name*/
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Returns the current time
     */
    public Instant getCurrentTime() {
        return currentTime;
    }

    /** Sets the current time*/
    public void setCurrentTime(Instant currentTime) {
        this.currentTime = currentTime;
    }

    /**
     * @return Returns the login result
     */
    public String getLoginResult() {
        return loginResult;
    }

    /** Sets the login result*/
    public void setLoginResult(String loginResult) {
        this.loginResult = loginResult;
    }
    
    /** This prints out the loginAttempt object in string format*/
    @Override
    public String toString(){
        return(name + " " + currentTime + ": UTC" + " " + loginResult);
    }
    
}