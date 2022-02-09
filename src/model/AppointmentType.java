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
public class AppointmentType {
    private String appointmentType;
    public AppointmentType(String appointmentType){
        this.appointmentType = appointmentType;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }


    @Override
    public String toString(){
        return(appointmentType);
    }
}