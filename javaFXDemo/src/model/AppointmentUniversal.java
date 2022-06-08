/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;

/** This class represents the appointment universal model and is 
 * used to create appointment objects. This class extends the
 * appointment class.
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
 
    
    /**
     * @return Returns the formatted start date time
     */   
    public String getFormattedStartDateTime() {
        return formattedStartDateTime;
    }

    /** sets the formatted start date time*/
    public void setFormattedStartDateTime(String formattedStartDateTime) {
        this.formattedStartDateTime = formattedStartDateTime;
    }
    
    /**
     * @return Returns the formatted end date time
     */   
    public String getFormattedEndDateTime() {
        return formattedEndDateTime;
    }

    /** sets the formatted end date time*/
    public void setFormattedEndDateTime(String formattedEndDateTime) {
        this.formattedEndDateTime = formattedEndDateTime;
    }

    
    /**
     * @return Returns the formatted created date time
     */   
    public String getFormattedCreatedDateTime() {
        return formattedCreatedDateTime;
    }

    /** sets the formatted created date time*/
    public void setFormattedCreatedDateTime(String formattedCreatedDateTime) {
        this.formattedCreatedDateTime = formattedCreatedDateTime;
    }

    
    /**
     * @return Returns the formatted up date time
     */   
    public String getFormattedUpdatedDateTime() {
        return formattedUpdatedDateTime;
    }
    
    /** sets the formatted up date time*/
    public void setFormattedUpdatedDateTime(String formattedUpdatedDateTime) {
        this.formattedUpdatedDateTime = formattedUpdatedDateTime;
    }

    
}
