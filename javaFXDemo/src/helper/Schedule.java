/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import exceptions.HasAppointmentsException;
import exceptions.HasOverlapExcetption;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import javafx.scene.control.Alert;
import model.Appointment;
import collections.Appointments;
import java.util.stream.Collectors;


/**
 * This class contains methods for checking for appointment conflict,
 * appointment alerts, and generates reports.
 */
public class Schedule {
    
    /** This method takes in a customer ID that is used to 
     * check and see if a customer has an existing appointment
     * that would prevent the customer from being deleted.
     * Lambda - The included expression enhances the program by alleviating
        the need to declare any variables. The lambda expression allows
        the program to stream the existing appointments and filter
        the appointments to a list based on if their customer id field
        has a value that matches the passed in customer. The code
        will throw an exception if the size of the list has a value
        greater than or equal to zero. 
     * @param customerId Customer ID to reference
     */
    
    public static void checkForExistingAppointments(int customerId) throws HasOverlapExcetption, 
            HasAppointmentsException{
        
       if(Appointments.getAllAppointments().stream()
               .filter(x -> x.getCustomerId() == customerId)
               .collect(Collectors.toList()).size() >= 1){
           throw new exceptions.HasAppointmentsException();
       }
    }
    
    /** This method check for appointments that occur within fifteen
     * minutes of a user logging in. If there is an appointment  an 
     * alert will state this. If there are no appointments that will
     * be stated also.
     */
    public static void checkForUpcomingAppointments() throws HasAppointmentsException {
        Timestamp fifteenMinutesFromLoginTime = Timestamp.valueOf(LocalDateTime.now()
                .plusMinutes(15));
                
            for(Appointment app : Appointments.getAllAppointments()){
            if((app.getStartDateTime().before(fifteenMinutesFromLoginTime)) 
                    & app.getStartDateTime().after(Timestamp
                            .valueOf(LocalDateTime.now()))) {
                Appointments.addUpcomingApps(app);
                }
            }
            if(Appointments.appsWithinFifteenMinutes.size() >= 1){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setContentText("Upcoming Appointments");
                alert.setContentText(Appointments.getAllUpcomingApps().toString());
                alert.showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setContentText("There are 0 appoinments starting within "
                        + "the next 15 minutes");
                alert.showAndWait();
            }
    }  
        
    /** This method takes in a potential appointment time for
     * a specific customer ID. Then the existing appointments are
     * referenced to check if the passed in customer id has any 
     * appointments that are scheduled during the potential appointment
     * time.
     * @param customerId Customer ID to check for scheduling conflicts
     * @param enteredAppStartTime Appointment start time to check
     * @param enteredAppEndTime Appointment end time to check
     */
    public static void checkForOverlap(int appointmentId, int customerId, Instant enteredAppStartTime, 
            Instant enteredAppEndTime) throws HasOverlapExcetption {
        
        for (Appointment existingAppToComapre : Appointments.getAllAppointments()) {
            if (existingAppToComapre.getCustomerId() == customerId) {
                if ((!enteredAppStartTime.isBefore(existingAppToComapre.getStartDateTime().toInstant()) 
                        && !enteredAppStartTime.isBefore(existingAppToComapre.getEndDateTime().toInstant())) 
                        || (!enteredAppEndTime.isAfter(existingAppToComapre.getStartDateTime().toInstant()) 
                        && !enteredAppEndTime.isAfter(existingAppToComapre.getEndDateTime().toInstant()))) {
                    continue;
                } else if (appointmentId == existingAppToComapre.getId()) {
                    continue;
                }
                    else {
                    throw new HasOverlapExcetption();
                }
            }
        }
    }
        
}

