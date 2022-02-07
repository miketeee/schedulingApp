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

/** This class generates an MinException*/
public class MinException extends Exception{
    
    /**This is the MinException constructor.
     This exception prevents a part or product from being saved if the min value is greater than the max value. 
     */
    public MinException(){
        super("");
    }
    
    /*
    public MinException(String message){
        super(message);
    }
*/
}
