/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import exceptions.HasOverlapExcetption;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.control.Alert;
import model.Appointment;
import model.allAppointments;

/**
 *
 * @author tamic
 */
public class checkForOverlap {
    public static void check(int customerId, Instant s1, Instant s2, int appId) 
            throws HasOverlapExcetption{
        
        for(Appointment app : allAppointments.getAllAppointments()){
            if(app.getCustomerId() == customerId){
                if((!s1.isBefore(app.getStartDateTime().toInstant()) && !s1.isBefore(app.getEndDateTime().toInstant())) || (!s2.isAfter(app.getStartDateTime().toInstant()) && !s2.isAfter(app.getEndDateTime().toInstant()))){
                    continue;
                }
                else if(app.getId() == appId){
                    continue;
                }
                else {
                    throw new exceptions.HasOverlapExcetption();
                }
            }
        }
        
    }
    
}
