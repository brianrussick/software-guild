package com.sg.dvdlibrary.dao;
import com.sg.dvdlibrary.dto.Dvd;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author brian russick
 */
public class DvdLibraryDaoFileImpl implements DvdLibraryDao {  
    public static final String DVD_FILE = "DvdList.txt";  // specify library
    public static final String DELIMITER = "::";    // set delimiter 
    private final Map<String, Dvd> dvds = new HashMap<>();  // create HashMap
    
    @Override                                 // Implement Override Annotations
    public Dvd getDvd(String title)           // find dvd by title
        throws DvdLibraryDaoException {
        loadDvd();
        return dvds.get(title);
    }
    @Override
    public List<Dvd> listAllDvds()            // display dvd list from HashMap
       throws DvdLibraryDaoException {
        loadDvd();
        return new ArrayList<Dvd>(dvds.values());
    }
    @Override                              
    public Dvd addDvd(String title, Dvd dvd)  // add dvd to HashMap
        throws DvdLibraryDaoException {
        Dvd newDvd = dvds.put(title, dvd);
        writeDvd();
        return newDvd;
    }
    @Override
    public Dvd editDvd(String title, Dvd dvd) // edit dvd if it exists
        throws DvdLibraryDaoException {
        Dvd editDvd = dvds.replace(title, dvd);
        writeDvd();
        return editDvd;
        
    }
    @Override
    public Dvd deleteDvd(String title)        // remove dvd from HashMap
        throws DvdLibraryDaoException {
        Dvd deleteDvd = dvds.remove(title);
        writeDvd();
        return deleteDvd;
    }
       
    	public void loadDvd() throws DvdLibraryDaoException {
	    Scanner sc;
	    
	    try {
	        sc = new Scanner(                    // create scanner
	                new BufferedReader(          // create readers
	                new FileReader(DVD_FILE)));  
	    } catch (FileNotFoundException e) {
	        throw new DvdLibraryDaoException(    // create custom error
	                "ERROR: FILE READER LOAD FAILURE!", e);
	    }

            // holds most recent line read from the file
	    String currentLine;    
	    
            // holds each of the parts of the currentLine after it has been 
            // split on our DELIMITER
            String[] currentTokens; 
	  
	    // process while we have more lines in the file
            while (sc.hasNextLine()) { 
	        currentLine = sc.nextLine();  // get the next line in the file
	        
            // tokenize scanner line by line
                currentTokens = currentLine.split(DELIMITER); 
                
	    // create a new Dvd and put it into the HashMap
                Dvd currentDvd = new Dvd(currentTokens[0]);
                
            // set the remaining vlaues on currentDvd manually
	        currentDvd.setReleaseDate(currentTokens[1]);
	        currentDvd.setRating(currentTokens[2]);
	        currentDvd.setDirectorName(currentTokens[3]);
                currentDvd.setStudio(currentTokens[4]);
                currentDvd.setUserReview(currentTokens[5]);
                
            // add current Dvd to HashMap and associate it with its title
	        dvds.put(currentDvd.getTitle(), currentDvd);
	    }	  
	    sc.close(); // close scanner to free resources / deallocate memory
	}
        
        public void writeDvd() throws DvdLibraryDaoException {
	    PrintWriter out;
	    
	    try {
            // write out to the dvd file    
	        out = new PrintWriter(new FileWriter(DVD_FILE));
	    } catch (IOException e) {                   
	        throw new DvdLibraryDaoException(   // create custom error
                        "ERROR: UNABLE TO SAVE FILE!!", e);
            }
            
	    List<Dvd> DvdList = listAllDvds(); // get dvd list
	    for (Dvd currentDvd : DvdList) {
                
	    // write the Dvd to the file
	        out.println(currentDvd.getTitle() + DELIMITER
	                + currentDvd.getReleaseDate() + DELIMITER 
	                + currentDvd.getRating() + DELIMITER
	                + currentDvd.getDirectorName() + DELIMITER 
                        + currentDvd.getStudio() + DELIMITER
                        + currentDvd.getUserReview());
	        
	    out.flush(); } // force PrintWriter to write line to the file      
            out.close();                 // close PrintWriter output stream
    }
}