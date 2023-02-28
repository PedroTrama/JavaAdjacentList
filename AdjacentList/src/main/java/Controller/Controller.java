package Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * <b>Controller</b> class is the mean of communication between all other classses in the program. 
 * @author Gabriel Antunes Tosi, 254221
 * @author Jorge Yudi Saito Maruyama, 254274
 * @author Luis Francisco Ribeiro Malaquias, 254289
 * @author Pedro Trama Fernandes Pereira, 254344
 * @author Victor Hugo Costa Osses, 254373
 * @since Sep 2022
 * @version 1.0
 */
public class Controller {
    
    private final FileManipulator fileM = new FileManipulator();
    private final TextManipulator textM = new TextManipulator();
    
    public Controller() {}

    /**
    * This method gets multiple inputs for the user
    * @return the user's input
    */
    public List<String> scanFilesName() throws Exception {
         try {
             return getFileM().scanFileNames();
         } catch (IOException e) {
             throw new Exception(e.getMessage());
         }

    }

    /**
    * This method gets files
    * @param fileNames contains a list of file names
    * @return files
    */
    public ArrayList<File> getFiles(List fileNames) throws Exception {
       try {
            return getFileM().getFiles(fileNames);
       } catch (Exception e) {
            throw new Exception(e.getMessage());
        } 
    }
    
    /**
    * This method gets files text
    * @param files contains a list of files
    * @return text of files
    */
    public ArrayList<String> getFilesText (ArrayList<File> files) throws Exception {
        try {
          return getFileM().getFilesText(files);
        } catch (FileNotFoundException e) {
          throw new Exception(e.getMessage());
        } 
    }

    /**
    * This method creates the files in .csv
    * @param wordsCorrelation contains a list of adjacent words
    */  
    public void createFiles (ArrayList<TreeMap<String, ArrayList<String>>> wordsCorrelation) throws Exception {
        try {
            for(int index = 0; index < wordsCorrelation.size(); index++) {
                  FileManipulator fileM = getFileM();
                  File file = fileM.createFiles(index + 1);

                  if(file.isFile()) {
                    fileM.writeToFile(wordsCorrelation.get(index), file);
                  } 
            }
         } catch (ArrayIndexOutOfBoundsException e) {
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            throw new Exception (e.getMessage());
        }
    }
    
    /**
    * This method builds a list of adjacent words
    * @param filesTexts contains a list of files texts
    * @return list of adjacent words
    */  
    public ArrayList<TreeMap<String, ArrayList<String>>> textAnalyzer(ArrayList<String> filesTexts) throws Exception {
        try {
         return getTextM().textAnalyzer(filesTexts);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
    * This method gets an object of type TextManipulator
    * @return An object of type TextManipulator
    */
    public TextManipulator getTextM() {
        return textM;
    }
    
    /**
    * This method gets an object of type FileManipulator
    * @return An object of type FileManipulator
    */
    public FileManipulator getFileM() {
        return fileM;
    }  
}