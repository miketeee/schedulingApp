/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 *
 * @author tamic
 */
public class FormatTimeEntered {
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
}
