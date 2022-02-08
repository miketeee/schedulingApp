/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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
    
    public static void writeToFile(File file, List map) throws IOException {
        FileWriter fWriter = new FileWriter(file.getPath(), true);
        BufferedWriter bWriter = new BufferedWriter(fWriter);
            bWriter.write("\n" + map.get(map.size() - 1).toString());
        bWriter.close();
    }
    
}


