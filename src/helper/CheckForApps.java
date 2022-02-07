/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import exceptions.HasAppointmentsException;
import exceptions.HasOverlapExcetption;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import static java.util.Objects.hash;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.AllContacts;
import model.Appointment;
import model.Contact;
import model.allAppointments;

/**
 *
 * @author tamic
 */
public class CheckForApps {
    public static void check(int customerId) throws HasOverlapExcetption, HasAppointmentsException{
        for(Appointment app : allAppointments.getAllAppointments()){
            if(app.getCustomerId() == customerId){
                    throw new exceptions.HasAppointmentsException();
            }
        
    }
    
}
    // Lambda expression to load the app list once instead of for each iteration
    public static void withinFifteenMinutes() throws HasAppointmentsException {
        Function<Integer, Double> half = x -> x / 2.0;
        Timestamp dueWithin = Timestamp.valueOf(LocalDateTime.now().plusMinutes(15));
            for(Appointment app : allAppointments.getAllAppointments()){
            if((app.getStartDateTime().before(dueWithin)) & app.getStartDateTime().after(Timestamp.valueOf(LocalDateTime.now()))) {
//                allAppointments.appointmentList.forEach( (n) -> { allAppointments.addUpcomingApps(n);} );
                allAppointments.addUpcomingApps(app);
                }
            }
            if(allAppointments.appWithinFifteenList.size() >= 1){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setContentText("Upcoming Appointments");
                alert.setContentText(allAppointments.getAllUpcomingApps().toString());
                alert.showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setContentText("There are 0 appoinments starting within the next 15 minutes");
                alert.showAndWait();
            }
    }  
    
    public static List reportByMonth() throws IOException {
        HashMap<String, Integer> monthAndType = new HashMap<>();
        for(Appointment app : allAppointments.getAllAppointments()) {
            String monthType = app.getStartDateTime().toLocalDateTime().getMonth().toString() + "-" + app.getType();
            if(!monthAndType.containsKey(monthType)){
                monthAndType.put(monthType, 1);
            }
            else {
                Integer newValue = monthAndType.get(monthType) + 1;
                monthAndType.put(monthType, newValue);
            }
        }
        

        return Arrays.asList(monthAndType);
//            
        }
        
        public static List<HashMap<String, List<Appointment>>> reportByShift() {
        List<Appointment> firstShiftApps = new ArrayList<>();
        List<Appointment> secondShiftApps = new ArrayList<>();
        for(Appointment app : allAppointments.getAllAppointments()){
            LocalDate startDate = app.getStartDateTime().toLocalDateTime().toLocalDate();
            LocalTime startTime = app.getStartDateTime().toLocalDateTime().toLocalTime();
            LocalDate endDate = app.getEndDateTime().toLocalDateTime().toLocalDate();
            LocalTime endTime = app.getStartDateTime().toLocalDateTime().toLocalTime();
            Instant[]tzc = TimeZoneConversion.opsHours(startDate, startTime, endDate, endTime);
            Instant start = tzc[2];
            Instant firstShift = tzc[4];
            
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
        
//        System.out.print(firstShiftApps + "\n");
//        System.out.print(secondShiftApps + "\n");
        return Arrays.asList(shifts);

    }
        
        public static List reportByContact() {
            HashMap<Contact, List<Appointment>> schedule = new HashMap<>();
            for(Appointment app : allAppointments.getAllAppointments()){
                for(Contact ct : AllContacts.getAllContacts()){
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
        
}

