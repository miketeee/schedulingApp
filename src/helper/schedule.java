/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import exceptions.HasAppointmentsException;
import exceptions.HasOverlapExcetption;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javafx.scene.control.Alert;
import collections.Contacts;
import model.Appointment;
import model.Contact;
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
     * @param customerId Customer ID to reference
     */
    
    public static void checkForExistingAppointments(int customerId) throws HasOverlapExcetption, 
            HasAppointmentsException{
        
        // This lambda expression enhances the program by alleviating the need
        // to declare any variables. The lambda expression allows
        // the program to stream the existing appoinments and filter
        // the appointments to a list based on if their customer id field
        // has a value that matches the passed in customer. The code
        // will throw an exception if the size of the list has a value
        // greater than or equal to zero.
        
       if(Appointments.getAllAppointments().stream()
               .filter(x -> x.getCustomerId() == customerId)
               .collect(Collectors.toList()).size() >= 1);
       
           throw new exceptions.HasAppointmentsException();
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
    
    /** This method generates a report of appointments based 
     * on the month and type of the appointment. The report is 
     * written to a text file in the reports package.
     * @return Returns List of report data
     */
    public static List reportByMonthAndType() throws IOException {
        HashMap<String, Integer> monthAndType = new HashMap<>();
        for(Appointment app : Appointments.getAllAppointments()) {
            String monthType = app.getStartDateTime().toLocalDateTime()
                    .getMonth().toString() + "-" + app.getAppointmentType();
            if(!monthAndType.containsKey(monthType)){
                monthAndType.put(monthType, 1);
            }
            else {
                Integer newValue = monthAndType.get(monthType) + 1;
                monthAndType.put(monthType, newValue);
            }
        }
        

        return Arrays.asList(monthAndType);
  
        }
        
        /** This method generates a report of appointments based on 
         * whether they occur before or after first shift ends at 3pm EST. 
         * The appointments before will be categorized as first shift and
         * those after will be second shift.
         * @return Returns List of report data
         */
        public static List<HashMap<String, List<Appointment>>> reportByShift() {
        List<Appointment> firstShiftApps = new ArrayList<>();
        List<Appointment> secondShiftApps = new ArrayList<>();
        for(Appointment app : Appointments.getAllAppointments()){
            LocalDate startDate = app.getStartDateTime().toLocalDateTime().toLocalDate();
            LocalTime startTime = app.getStartDateTime().toLocalDateTime().toLocalTime();
            LocalDate endDate = app.getEndDateTime().toLocalDateTime().toLocalDate();
            LocalTime endTime = app.getStartDateTime().toLocalDateTime().toLocalTime();
            Instant[]conversion = Time.convertTimeToUTC(startDate, startTime, endDate, endTime);
            Instant start = conversion[2];
            Instant firstShift = conversion[4];
            
            if(start.isBefore(firstShift)){
            firstShiftApps.add(app);
            }
            else {
                secondShiftApps.add(app);
                
            }
        }
        HashMap<String, List<Appointment>> shifts = new HashMap<>();
        shifts.put("First Shift",firstShiftApps);
        shifts.put("Second Shift", secondShiftApps);
        return Arrays.asList(shifts);

    }
        
        /** This method generates a report for each contact 
         * that only includes appointments where they are listed
         * as the contact.
         * @return Returns List of report data
         */
        public static List reportByContact() {
            HashMap<Contact, List<Appointment>> schedule = new HashMap<>();
            for(Appointment app : Appointments.getAllAppointments()){
                for(Contact ct : Contacts.getAllContacts()){
                if(!schedule.containsKey(ct))
                    schedule.put(ct, new ArrayList<>());
                    if(app.getContactId() == ct.getId()){
                        List cApps = schedule.get(ct);
                        cApps.add(app);
                        schedule.put(ct, cApps);
                    }
                else {
                continue;
                }
            }
        }
        return Arrays.asList(schedule);

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
    public static void checkForOverlap(int customerId, Instant enteredAppStartTime, 
            Instant enteredAppEndTime) throws HasOverlapExcetption {
        
        for (Appointment existingAppToComapre : Appointments.getAllAppointments()) {
            if (existingAppToComapre.getCustomerId() == customerId) {
                if ((!enteredAppStartTime.isBefore(existingAppToComapre.getStartDateTime().toInstant()) 
                        && !enteredAppStartTime.isBefore(existingAppToComapre.getEndDateTime().toInstant())) 
                        || (!enteredAppEndTime.isAfter(existingAppToComapre.getStartDateTime().toInstant()) 
                        && !enteredAppEndTime.isAfter(existingAppToComapre.getEndDateTime().toInstant()))) {
                    continue;
                } else {
                    throw new HasOverlapExcetption();
                }
            }
        }
    }
        
}

