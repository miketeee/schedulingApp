/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author tamic
 */
public class AppointmentUniversal extends Appointment{
     public String formattedStartDateTime;
     public String formattedEndDateTime;
     public String formattedCreatedDateTime;
     public String formattedUpdatedDateTime;

    public AppointmentUniversal(String formattedStartDateTime, 
            String formattedEndDateTime, String formattedCreatedDateTime, 
            String  formattedUpdatedDateTime, int id, String title, 
            String description, String location, Timestamp startDateTime, 
            Timestamp endDateTime, Timestamp createdDateTime, String createdBy, 
            Timestamp lastUpdate, String lastUpdatedBy, int customerId, int userId, 
            int contactId, String appointmentType) {
        super(id, title, description, location, startDateTime, endDateTime, 
                createdDateTime, createdBy, lastUpdate, lastUpdatedBy, customerId, 
                userId, contactId, appointmentType);
        this.formattedStartDateTime = formattedStartDateTime;
        this.formattedEndDateTime = formattedEndDateTime;
        this.formattedCreatedDateTime = formattedCreatedDateTime;
        this.formattedUpdatedDateTime = formattedUpdatedDateTime;
    }
 
    public String getFormattedStartDateTime() {
        return formattedStartDateTime;
    }

    public void setFormattedStartDateTime(String formattedStartDateTime) {
        this.formattedStartDateTime = formattedStartDateTime;
    }

    public String getFormattedEndDateTime() {
        return formattedEndDateTime;
    }

    public void setFormattedEndDateTime(String formattedEndDateTime) {
        this.formattedEndDateTime = formattedEndDateTime;
    }

    public String getFormattedCreatedDateTime() {
        return formattedCreatedDateTime;
    }

    public void setFormattedCreatedDateTime(String formattedCreatedDateTime) {
        this.formattedCreatedDateTime = formattedCreatedDateTime;
    }

    public String getFormattedUpdatedDateTime() {
        return formattedUpdatedDateTime;
    }

    public void setFormattedUpdatedDateTime(String formattedUpdatedDateTime) {
        this.formattedUpdatedDateTime = formattedUpdatedDateTime;
    }

    
}
