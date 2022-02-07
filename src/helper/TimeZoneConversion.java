/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

/**
 *
 * @author tamic
 */
public class TimeZoneConversion {
    public static Instant[] opsHours(LocalDate lds, LocalTime lts, LocalDate lde, LocalTime lte){
        LocalTime bizOpen = LocalTime.of(8, 00);
        LocalTime bizClose = LocalTime.of(22, 00);
        LocalTime firstShift = LocalTime.of(15,00);
        LocalDateTime start = LocalDateTime.of(lds, lts);
        LocalDateTime end = LocalDateTime.of(lde, lte);
        LocalDateTime bizHoursOpen = LocalDateTime.of(lds, bizOpen);
        LocalDateTime bizHoursClose = LocalDateTime.of(lds, bizClose);
        LocalDateTime bizHoursFirstSift = LocalDateTime.of(lds, firstShift);
        
       ZoneId bizOpsZoneId = ZoneId.of("America/New_York");
       ZoneId LocalZoneId = ZoneId.of(TimeZone.getDefault().getID());
       ZonedDateTime bizOpsZDT = ZonedDateTime.of(lds, bizOpen, LocalZoneId);
       ZonedDateTime firstShiftZDT = ZonedDateTime.of(lds, firstShift, LocalZoneId);
       ZonedDateTime bizOpsOpenZDT = ZonedDateTime.of(lds, bizOpen, bizOpsZoneId);
       ZonedDateTime bizOpsCloseZDT = ZonedDateTime.of(lds, bizClose, bizOpsZoneId);
       ZonedDateTime appStartZDT = ZonedDateTime.of(lds, lts, LocalZoneId);
       ZonedDateTime appEndZDT = ZonedDateTime.of(lde, lte, LocalZoneId);
       LocalDateTime bizOpsOpenLDT = bizOpsOpenZDT.toLocalDateTime();
       LocalDateTime bizOpsCloseLDT = bizOpsCloseZDT.toLocalDateTime();
       LocalDateTime appStartLDT = appStartZDT.toLocalDateTime();
       LocalDateTime appEndLDT = appEndZDT.toLocalDateTime();
       
       Instant firstShiftToGMT = firstShiftZDT.toInstant();
       Instant openToGMT = bizOpsOpenZDT.toInstant();
       Instant closeToGMT = bizOpsCloseZDT.toInstant();
       Instant startToGMT = appStartZDT.toInstant();
       Instant endToGMT = appEndZDT.toInstant();
       
       Instant[] instants = {openToGMT, closeToGMT, startToGMT, endToGMT, firstShiftToGMT};
       
       
 
//       System.out.print(openToGMT+ "\n");
//       System.out.print(closeToGMT+ "\n");
//       System.out.print(startToGMT+ "\n");
//       System.out.print(endToGMT+ "\n");
        return instants;
    }
}
