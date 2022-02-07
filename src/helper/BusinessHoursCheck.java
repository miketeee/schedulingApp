/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.TimeZone;
import java.util.function.BiPredicate;
import javafx.scene.control.Alert;

/**
 *
 * @author tamic
 */
public class BusinessHoursCheck {
    public static void BusinessHoursCheck(Instant start, Instant end, Instant close, Instant open){
        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
        boolean dayOfStartSat = start.atOffset(ZoneOffset.UTC).getDayOfWeek().equals(6);
        boolean dayOfEndSat = end.atOffset(ZoneOffset.UTC).getDayOfWeek().equals(6);
        boolean dayOfStartSun = start.atOffset(ZoneOffset.UTC).getDayOfWeek().equals(7);
        boolean dayOfEndSun = end.atOffset(ZoneOffset.UTC).getDayOfWeek().equals(7);
        
        BiPredicate<Instant, Instant> biPred = (x, y) -> x.isAfter(y);
        Boolean startToClose = biPred.test(start, close);
        
         if ((startToClose || start.isBefore(open) || (end.isAfter(close) || end.isBefore(open)))){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Appointment must be between " + open.atZone(localZoneId) + " and " + close.atZone(localZoneId));
            alert.showAndWait();
        }
        else if(dayOfStartSat || dayOfStartSun || dayOfEndSat || dayOfEndSun){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText("Appointment must be on a weekday");
                alert.showAndWait();
        }
        else if(start.isAfter(end)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText("Appointment start must be before end");
                alert.showAndWait();
        }

}
}
