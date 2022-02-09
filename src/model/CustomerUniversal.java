/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import helper.Time;
import java.sql.Timestamp;

/**
 *
 * @author tamic
 */
public class CustomerUniversal extends Customer {
    public String formattedCreatedTime;
    public String formattedUpdatedTime;

    public CustomerUniversal(int id, String name, String address, String zip, 
            String phone, Timestamp createdDateTime, String creator, 
            Timestamp updatedDateTime, String updator, int division, 
            String formattedCreatedTime, String formattedUpdatedTime) {
        super(id, name, address, zip, phone, createdDateTime, creator, 
                updatedDateTime, updator, division);
        this.formattedCreatedTime = Time
                .formatTimestampToString(createdDateTime);
        this.formattedUpdatedTime = Time
                .formatTimestampToString(updatedDateTime);
    }

    public String getFormattedUpdatedTime() {
        return formattedUpdatedTime;
    }

    public void setFormattedUpdatedTime(String formattedUpdatedTime) {
        this.formattedUpdatedTime = formattedUpdatedTime;
    }

    public String getFormattedCreatedTime() {
        return formattedCreatedTime;
    }

    public void setFormattedCreatedTime(String formattedCreatedTime) {
        this.formattedCreatedTime = formattedCreatedTime;
    }
    

    
}
