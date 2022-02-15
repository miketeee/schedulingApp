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

/** This class contains methods that allow for the
 * creation of files and the ability to write to fails
 */
public class FileIO {
    
    /**
     * This method creates text file using the passed in parameter
     * as the file name.
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
            System.out.print("");
        }
        return newFile;
    }
    
    /** This method takes in a file  and a list. The contents of
     * the list are written to the passed in file.
     * @param file File to write to
     * @param list List of data to write
     */
    public static void writeToFile(File file, List list) throws IOException {
        FileWriter fWriter = new FileWriter(file.getPath(), true);
        BufferedWriter bWriter = new BufferedWriter(fWriter);
            bWriter.write("\n" + list.get(list.size() - 1).toString());
        bWriter.close();
    }
    
}


