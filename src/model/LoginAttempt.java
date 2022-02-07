/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import helper.FormatTimeEntered;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author tamic
 */
public class LoginAttempt {
    public String name;
    public Instant timeNow;
    public String status;

    public LoginAttempt(String name, Instant timeNow, String status) {
        this.name = name;
        this.timeNow = timeNow;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getTimeNow() {
        return timeNow;
    }

    public void setTimeNow(Instant timeNow) {
        this.timeNow = timeNow;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString(){
    return(name + " " + timeNow + ": UTC" + " " + status);
}
    
}
