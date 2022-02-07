/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author tamic
 */

/** This class generates an InvExcetion*/
public class InvException extends Exception{
    
    /**This is the InvException constructor.
     This exception prevents a part or product from being saved if the inventory value is NOT in between the min and max values.
     */
    public InvException(){
        super("");
    }
    
    /*
    public InvException(String message){
        super(message);
    }
*/
}
