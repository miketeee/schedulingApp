/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 *
 * @author tamic
 */
public class Time {
    public static String formatTime(java.sql.Timestamp tStamp) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-YYYY HH:mm");
        String formattedTime = dtf.format(tStamp.toLocalDateTime());
        return formattedTime;
    }
    
        public static LocalDate formatStringToLocalDate(DatePicker dateString){
            String aDate = dateString.getValue().toString();
            int dateYear = Integer.parseInt(aDate.subSequence(0, 4).toString());
            int dateMonth = Integer.parseInt(aDate.subSequence(5, 7).toString());
            int dateDay = Integer.parseInt(aDate.subSequence(8, 10).toString());
            LocalDate datePickerDate = LocalDate.of(dateYear, dateMonth, dateDay); 
        return datePickerDate;
        }
        
        public static LocalTime formatStringToLocalTime(TextField timeString){
        String timeOfDate = timeString.getText();
        int timeOfHour = Integer.parseInt(timeOfDate.subSequence(0, 2).toString());
        int timeOfMin = Integer.parseInt(timeOfDate.subSequence(3, 5).toString());
        LocalTime localTimeOfDate = LocalTime.of(timeOfHour, timeOfMin);
        return localTimeOfDate;
        }

    /** This method uses the appointment times entered along with
    the predefined variables  of the method to convert the business
    hours to UTC along with the appointments start and end time.
    @param startDate Start date of appointment
    @param startTime Start time of appointment
    @param endDate End date of appointment
    @param endTime End time of appointment
    @return Instants of open, close,  app start, app end, and first shift end
     */
    public static Instant[] convertTimeToUTC(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        LocalTime openTime = LocalTime.of(8, 0);
        LocalTime closeTime = LocalTime.of(22, 0);
        LocalTime firstShiftEndTime = LocalTime.of(15, 0);
        ZoneId bizOpsZoneId = ZoneId.of("America/New_York");
        ZoneId LocalZoneId = ZoneId.of(TimeZone.getDefault().getID());
        Instant firstShiftToUTC = ZonedDateTime.of(startDate, firstShiftEndTime, LocalZoneId).toInstant();
        Instant openTimeToUTC = ZonedDateTime.of(startDate, openTime, bizOpsZoneId).toInstant();
        Instant closeTimeToUTC = ZonedDateTime.of(startDate, closeTime, bizOpsZoneId).toInstant();
        Instant appSttartToUTC = ZonedDateTime.of(startDate, startTime, LocalZoneId).toInstant();
        Instant appEndToUTC = ZonedDateTime.of(endDate, endTime, LocalZoneId).toInstant();
        Instant[] instants = {openTimeToUTC, closeTimeToUTC, appSttartToUTC, appEndToUTC, firstShiftToUTC};
        return instants;
    }
}
