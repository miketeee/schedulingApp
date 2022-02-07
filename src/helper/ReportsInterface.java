/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.time.Instant;
import java.util.Collections;
import java.util.Map;
import java.util.function.BiPredicate;
import model.Appointment;

/**
 *
 * @author tamic
 */
public interface ReportsInterface {
    // int value returning abstract method
    
    // Sort by month
    BiPredicate<Instant, Instant> biPredicate = (s1, s2) -> s1.isAfter(s2);
}
