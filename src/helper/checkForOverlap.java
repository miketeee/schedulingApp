/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import exceptions.HasOverlapExcetption;
import java.time.Instant;
import model.Appointment;
import model.allAppointments;

/**
 * This method 
 * @author tamic
 */
public class CheckForOverlap {
    public static void checkForOverlap(int customerId, Instant startTime, 
            Instant endTime, int appId) 
            throws HasOverlapExcetption{
        
        for(Appointment app : allAppointments.getAllAppointments()){
            if(app.getCustomerId() == customerId){
                if((!startTime.isBefore(app.getStartDateTime().toInstant()) 
                        && !startTime.isBefore(app.getEndDateTime().toInstant())) 
                        || (!endTime.isAfter(app.getStartDateTime().toInstant()) 
                        && !endTime.isAfter(app.getEndDateTime().toInstant()))){
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
