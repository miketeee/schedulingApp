/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author tamic
 */
public class HandleFile {
    
    /**
     * This method creates text file
     * @param fileName - The application predefined file name
     * @return Returns new text file
     * @throws IOException 
     */
    public static File createFile(String fileName) throws IOException{
        String fileType = ".txt";
        String fileLocation = "src\\reports\\";
        File newFile = new File(fileLocation + fileName + fileType);
        if (newFile.createNewFile()){
            System.out.print("File created: " + newFile.getName());
        } else {
            System.out.print("File already exists");
        }
        return newFile;
    }
     
//    public static ArrayList readFromFile(File file) throws FileNotFoundException, IOException {
//            
//            List<String> readLogins = Files.;
//            
//            System.out.print(readLogins);
////            List attempts = readLogins.collect(Collectors.toList());
//  
////            System.out.print(logins[0]);
//            
//            
////            previousLogins = readLogins
////                    .map(x -> x.split("="))
////                    .collect(Collectors.toMap(
////                            x -> x[0], 
////                            x -> x[1]));
////                        readLogins.close();
////            System.out.print(readLogins);
//            
////        for(Object entry : map.entrySet()){
////            Stream previous = Stream.of(map);
////            System.out.print(previous);
////        }
// 
//        return (ArrayList) readLogins;
//    }
    
    public static void writeToFile(File file, List map) throws IOException {
        FileWriter fWriter = new FileWriter(file.getPath(), true);
        BufferedWriter bWriter = new BufferedWriter(fWriter);
 
        String nextLine = System.lineSeparator();
//            theWriter.append(nextLine + entry.toString());
            bWriter.write("\n" + map.get(map.size() - 1).toString());
//            theWriter.write("\n" + item.toString());

//        theWriter.write("\n" + map);
        bWriter.close();
    }
    
}


