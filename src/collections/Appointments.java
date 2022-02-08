/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

/**
 *
 * @author tamic
 */
public class Appointments {
    
        /**This list contains all parts in the inventory. */
    public static ObservableList<Appointment>appointmentList = FXCollections
            .observableArrayList();
    
    public static ObservableList<Appointment>appWithinFifteenList = FXCollections
            .observableArrayList();
    
    public static ObservableList<Appointment>appsFilterMonth = FXCollections
            .observableArrayList();
    
    public static ObservableList<Appointment>appsFilterWeek = FXCollections
            .observableArrayList();

    public static ObservableList<Appointment> getAppsFilterMonth() {
        return appsFilterMonth;
    }

    public static void setAppsFilterMonth(ObservableList<Appointment> appsFilterMonth) {
        Appointments.appsFilterMonth = appsFilterMonth;
    }

    public static void setAppsFilterWeek(ObservableList<Appointment> appsFilterWeek) {
        Appointments.appsFilterWeek = appsFilterWeek;
    }

    public static ObservableList<Appointment> getAppsFilterWeek() {
        return appsFilterWeek;
    }
    
    public static void addAppointmentsThisWeek(Appointment appointment)
    {
        appointmentList.add(appointment);        
    }
    
    public static void addAppointmentsThisMonth(Appointment appointment)
    {
        appointmentList.add(appointment);        
    }
        
    public static void addAppointments(Appointment appointment)
    {
        appointmentList.add(appointment);        
    }
    
        public static void addUpcomingApps(Appointment appointment)
    {
        appWithinFifteenList.add(appointment);        
    }
    
    public static ObservableList<Appointment> getAllAppointments()
    {
    return appointmentList;   
    }
    
    public static ObservableList<Appointment> getAllUpcomingApps()
    {
    return appWithinFifteenList;   
    }
    
}
