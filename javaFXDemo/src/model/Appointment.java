/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import helper.Time;
import java.sql.SQLException;

/** This class represents the appointment model and is 
 * used to create appointment objects.
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

    /**
     * @return Returns the id
     */
    public int getId() {
        return id;
    }

    /** Sets the id*/
    public void setId(int id) throws SQLException {
        this.id = id;
    }

    /**
     * @return Returns the title
     */    
    public String getTitle() {
        return title;
    }

    /** Sets the title*/
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * @return Returns the description
     */
    public String getDescription() {
        return description;
    }

    /** Sets the description*/
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * @return Returns the location
     */
    public String getLocation() {
        return location;
    }

    /** Sets the location*/
    public void setLocation(String location) {
        this.location = location;
    }

     /**
     * @return Returns the start date time
     */
    public java.sql.Timestamp getStartDateTime() {
        return startDateTime;
    }

    /** Sets the start date time*/
    public void setStartDateTime(java.sql.Timestamp startDateTime) {
        this.startDateTime = startDateTime;
    }

     /**
     * @return Returns the end date time
     */
    public java.sql.Timestamp getEndDateTime() {
        return endDateTime;
    }

    /** Sets the end date time*/
    public void setEndDateTime(java.sql.Timestamp endDateTime) {
        this.endDateTime = endDateTime;
    }

    /**
     * @return Returns the created date time
     */
    public java.sql.Timestamp getCreatedDateTime() {
        return createdDateTime;
    }

    /** Sets the created date time*/
    public void setCreatedDateTime(java.sql.Timestamp createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

     /**
     * @return Returns created by
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /** Sets the created by*/
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    /**
     * @return Returns last update time
     */
    public java.sql.Timestamp getLastUpdate() {
        return lastUpdate;
    }

    /** Sets the last update time*/
    public void setLastUpdate(java.sql.Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    /**
     * @return Returns last updated by
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /** Sets the last updated by*/
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
    /**
     * @return Returns the customer id
     */
    public int getCustomerId() {
        return customerId;
    }

    /** Sets the customer id*/
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    /**
     * @return Returns the user id
     */
    public int getUserId() {
        return userId;
    }

    /** Sets the user id*/
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    /**
     * @return Returns the contact id
     */
    public int getContactId() {
        return contactId;
    }

    /** Sets the contact id*/
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    
     /**
     * @return Returns the appointment type
     */   
    public String getAppointmentType() {
        return appointmentType;
    }

    /** Sets the appointment type*/
    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    /** This prints out the appointment object in string format*/
    @Override
    public String toString(){
        String appontmentStartTime = Time.formatTimestampToString(startDateTime);
        String appointmentEndTime = Time.formatTimestampToString(endDateTime);
        return("#" + Integer.toString(id) + " " + title + " " +  " " + appointmentType + " " +
                 " " + description + " " + appontmentStartTime + " to " 
                + appointmentEndTime + " " + customerId);
     }
}
