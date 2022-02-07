/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import model.AllTypes;
import model.Type;

/**
 *
 * @author tamic
 */
public class LoadTypes {
    public static void loadTypes() {
        String type1 = new String("routine");
        String type2 = new String("Standard");
        String type3 = new String("Express");
        
        AllTypes.addType(type1);
        AllTypes.addType(type2);
        AllTypes.addType(type3);

    }
}
