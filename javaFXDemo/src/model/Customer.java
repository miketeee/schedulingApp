/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author tamic
 */
public class Customer {
    private int id;
    private String name;
    private String address;
    private String zip;
    private String phone;
    private java.sql.Timestamp createdDateTime;
    private String createdBy;
    private java.sql.Timestamp updatedDateTime;
    private String updatedBy;
    private int division;
    public Customer(int id, String name, String address, String zip, String phone, 
            java.sql.Timestamp createdDateTime, String creator, 
            java.sql.Timestamp updatedDateTime, String updatedBy, int division) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.zip = zip;
        this.phone = phone;
        this.createdDateTime = createdDateTime;
        this.name = name;
        this.createdBy = creator;
        this.updatedDateTime = updatedDateTime;
        this.updatedBy = updatedBy;
        this.division = division;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the date
     */
    public java.sql.Timestamp getCreatedDateTime() {
        return createdDateTime;
    }

    /**
     * @param createdDateTime the date to set
     */
    public void setCreatedDateTime(java.sql.Timestamp createdDateTime) {
        this.createdDateTime = createdDateTime;
    }


    /**
     * @return createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the updated
     */
    public java.sql.Timestamp getUpdatedDateTime() {
        return updatedDateTime;
    }

    /**
     * @param updatedDateTime the updated to set
     */
    public void setUpdatedDateTime(java.sql.Timestamp updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }

    /**
     * @return updatedBy
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * @param updatedBy the updatedBy to set
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * @return the division
     */
    public int getDivision() {
        return division;
    }

    /**
     * @param division the division to set
     */
    public void setDivision(int division) {
        this.division = division;
    }
    
@Override
public String toString(){
    return("#" + Integer.toString(id) + " " + name);
}
}


