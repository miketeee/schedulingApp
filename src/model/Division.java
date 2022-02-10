/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDateTime;


/** This class represents the division model and is 
 * used to create division objects.
 */
public class Division {
    private int id;
    private String name;
    private LocalDateTime created;
    private String createdBy;
    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;
    private int countryId;
    public Division(int id, String name, LocalDateTime created, String createdBy, 
            LocalDateTime lastUpdated, String lastUpdatedBy, int countryId){
        this.id = id;
        this.name = name;
        this.created = created;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryId = countryId;
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
     * @return Returns the created date time
     */
    public LocalDateTime getCreated() {
        return created;
    }

    /** Sets the created date time
     * @param created Created date time to set
     */
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

     /**
     * @return Returns the created by
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /** Sets the created by
     * @param createdBy Created by name to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

     /**
     * @return Returns the last updated by
     */
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    /** Sets the last updated time
     * @param lastUpdated Last updated time to set
     */
    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * @return Returns the last updated by
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /** Sets the last updated by
     * @param lastUpdatedBy Last updated by time to set
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * @return Returns the country id
     */
    public int getCountryId() {
        return countryId;
    }

    /** Sets the country id.
     * @param countryId Country ID to set
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
    
    /** This prints out the division object in string format*/
    @Override
    public String toString(){
        return("#" + Integer.toString(id) + " " + name);
    }
}
