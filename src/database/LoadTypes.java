/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import collections.AppTypes;
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
        
        AppTypes.addType(type1);
        AppTypes.addType(type2);
        AppTypes.addType(type3);

    }
}
