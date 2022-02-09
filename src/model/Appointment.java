/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import helper.Time;
import java.sql.SQLException;
import model.AppointmentType;

/**
 *
 * @author tamic
 */
public class Appointment {
    
    private int id;
    private String title;
    private String description;
    private String location;
    private java.sql.Timestamp startDateTime;
    private java.sql.Timestamp endDateTime;
    private java.sql.Timestamp createdDateTime;
    private String createdBy;
    private java.sql.Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int customerId;
    private int userId;
    private int contactId;
    private String appointmentType;
    public Appointment(int id, String title, String description, String location, 
            java.sql.Timestamp startDateTime, java.sql.Timestamp endDateTime, 
            java.sql.Timestamp createdDateTime, String createdBy, 
            java.sql.Timestamp lastUpdate,
            String lastUpdatedBy, int customerId, int userId, int contactId, 
            String appointmentType) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.createdDateTime = createdDateTime;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;  
        this.appointmentType = appointmentType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) throws SQLException {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public java.sql.Timestamp getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(java.sql.Timestamp startDateTime) {
        this.startDateTime = startDateTime;
    }

    public java.sql.Timestamp getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(java.sql.Timestamp endDateTime) {
        this.endDateTime = endDateTime;
    }

    public java.sql.Timestamp getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(java.sql.Timestamp createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public java.sql.Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(java.sql.Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

  @Override
public String toString(){
    String appTime = Time.formatTime(startDateTime);
    return("#" + Integer.toString(id) + " " + title + " " + " " + appTime + " " 
            + Integer.toHexString(customerId));
 }
}


