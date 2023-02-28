package Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * <b>FileManipulator</b> class is responsible for getting files from the user and creating new files with the modifications made through the <b>TextManipulator</b> class.
 * @author Gabriel Antunes Tosi, 254221
 * @author Jorge Yudi Saito Maruyama, 254274
 * @author Luis Francisco Ribeiro Malaquias, 254289
 * @author Pedro Trama Fernandes Pereira, 254344
 * @author Victor Hugo Costa Osses, 254373
 * @since Sep 2022
 * @version 1.0
 */
public class FileManipulator {
    
    /**
    * This is the classe's constructor method
    */
    public FileManipulator() {}
    
    /**
    * This method gets the user's inputs
    * @return Returns files' names
    */
     public List<String> scanFileNames() throws Exception { 
        try {
            List<String> fileNames = new ArrayList();
            System.out.println("Type the file paths (null for exit)");
            
            while(true) {
                Scanner scan = new Scanner(System.in);
                String str = scan.nextLine();
                
                if(!str.equals("")) fileNames.add(str); 
                else  break;
            }
            
          return fileNames;
        } catch (Exception e) {
            throw new Exception (e.getMessage());
        } 
    }
     
    /**
    * This method obtains files for every file name
    * @param fileNames is the name of the files
    * @return Returns files
    */
     public ArrayList<File> getFiles(List fileNames) throws Exception {
         try {
            ArrayList<File> files = new ArrayList<File>();
            
            for(int index = 0; index < fileNames.size(); index++) {
                File file = new File(fileNames.get(index).toString());
                files.add(file);
           }
            
           return files;
         } catch (Exception e) {
             throw new Exception(e.getMessage());
         } 
     }
     
    /**
    * This method reads the files' texts for every file
    * @param files is the files
    * @return the files' text
    */
    public  ArrayList<String>  getFilesText (ArrayList<File> files) throws Exception {
        try {
           ArrayList<String> fileTexts = new  ArrayList<String>();

           for(int index = 0; index < files.size(); index++) {
              String fileLines = "";

              Scanner scan = new Scanner(files.get(index));

              while (scan.hasNextLine()) {
                  String line = scan.nextLine();
                  if(!line.equals("")) {
                      line = line.replace("\n", "") + " ";
                      fileLines += line;
                  }
              }

              fileTexts.add(fileLines);
              scan.close();  
           }

          return fileTexts;
        }
        catch (FileNotFoundException e) {
            throw new Exception(e.getMessage(), e.getCause());
        } 
    }
    
    /**
    * This method creates and names files
    * @param index is the list of adjacent words text's index 
    * @return the user's input
    */
      public File createFiles(int index) throws Exception {
            try {
                 File file = new File("Texto0" + index + ".csv");
                 
                 if(!file.createNewFile()) {
                       System.out.println("File already exists");
                 }
                     
                 return file; 
             } catch (Exception e) {
                throw new Exception (e.getMessage());
             } 
      }
      
    /**
    * This method writes the list of adjacent words
    * @param text is the file's texts
    * @param file is the files
    */
      public void writeToFile(TreeMap<String, ArrayList<String>> text, File file) throws Exception {
            try {
                FileWriter fw = new FileWriter(file.getName());
                for(Map.Entry<String, ArrayList<String>> entry : text.entrySet()) {
                    String line = "";
                    for(String word : entry.getValue()) {
                        line += word + " ";
                    }
                    fw.write(entry.getKey() + " " + line.trim() + "\n");
                }
                
                fw.close();
              } 
              catch (IOException e) {
                  throw new Exception(e.getMessage());
              }
      }
    
}
