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
import static java.time.ZoneOffset.UTC;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import java.util.function.Supplier;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/** This class contains methods that allow for the formatting
 * of time to a string, local date, local time, and convert to 
 * UTC.
 */ 
public class Time {
    
    /** This method takes in a timestamp, formats the
     * timestamp to a string, then returns the string
     * @param tStamp Timestamp to format
     * @return Returns tStamp as string
     */ 
    public static String formatTimestampToString(java.sql.Timestamp tStamp) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-YYYY HH:mm");
        String formattedTime = dtf.format(tStamp.toLocalDateTime());
        return formattedTime;
    }
    
        /** This method takes in a string from the date picker object.
         * Then it converts the string into a local date.
         * @param datePicker DatePicker date to format
         * @return datePicker as string
         */
    
        public static LocalDate formatStringToLocalDate(DatePicker datePicker){
            String date = datePicker.getValue().toString();
            int year = Integer.parseInt(date.subSequence(0, 4).toString());
            int month = Integer.parseInt(date.subSequence(5, 7).toString());
            int day = Integer.parseInt(date.subSequence(8, 10).toString());
            LocalDate datePickerDate = LocalDate.of(year, month, day); 
        return datePickerDate;
        }
        
        /** This method takes in a string from the time input text fields, 
         * converts the string into a local time, then returns the string.
         * @param textField TextField time to format
         * @return Returns textField as Local Time
         */
        public static LocalTime formatStringToLocalTime(TextField textField){
        String timeOfDate = textField.getText();
        int timeOfHour = Integer.parseInt(timeOfDate.subSequence(0, 2).toString());
        int timeOfMin = Integer.parseInt(timeOfDate.subSequence(3, 5).toString());
        LocalTime localTime = LocalTime.of(timeOfHour, timeOfMin);
        return localTime;
        }

    /** This method uses the appointment times entered along with
    the predefined variables  of the method to convert the business
    hours to UTC along with the appointments start and end time.
    @param startDate Start date of appointment
    @param startTime Start time of appointment
    @param endDate End date of appointment
    @param endTime End time of appointment
    @return Returns Instants of open, close,  app start, app end, and first shift end
     */
    public static Instant[] convertTimeToUTC(LocalDate startDate, LocalTime startTime, 
            LocalDate endDate, LocalTime endTime) {
        LocalTime openTime = LocalTime.of(8, 0);
        LocalTime closeTime = LocalTime.of(22, 0);
        LocalTime firstShiftEndTime = LocalTime.of(15, 0);
        ZoneId bizOpsZoneId = ZoneId.of("Etc/GMT+5");
        ZoneId LocalZoneId = ZoneId.of(TimeZone.getDefault().getID());
        Instant firstShiftToUTC = ZonedDateTime.of(startDate, firstShiftEndTime, 
                LocalZoneId).toInstant();
        Instant openTimeToUTC = ZonedDateTime.of(startDate, openTime, bizOpsZoneId)
                .toInstant();
        Instant closeTimeToUTC = ZonedDateTime.of(startDate, closeTime, bizOpsZoneId)
                .toInstant();
        Instant appSttartToUTC = ZonedDateTime.of(startDate, startTime, LocalZoneId)
                .toInstant();
        Instant appEndToUTC = ZonedDateTime.of(endDate, endTime, LocalZoneId)
                .toInstant();
        Instant[] instants = {openTimeToUTC, closeTimeToUTC, appSttartToUTC, appEndToUTC,
            firstShiftToUTC};
        return instants;
    }
}
