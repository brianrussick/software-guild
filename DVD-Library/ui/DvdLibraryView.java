package com.sg.dvdlibrary.ui;
import com.sg.dvdlibrary.dto.Dvd;
import java.util.List;

/**
 *
 * @author brian russick
 */
public class DvdLibraryView {
    
    private final UserIO io;        // initialize userIO
    
    public DvdLibraryView(UserIO io) {
        this.io = io;   // constructor that initializes current instance of io 
    }
    
    public int getMenuSelection(){
        io.print("\n====================");
        io.print("--------Menu--------");
        io.print("====================\n");
        io.print("1: Find a DVD");
        io.print("2. View Entire DVD List");
        io.print("3. Add a DVD");
        io.print("4. Edit a DVD");
        io.print("5. Delete a DVD");
        io.print("\n====================");
        io.print("6.---Exit Program---");
        io.print("====================\n");
                                                        // only takes int 1-6
        return io.readInt("Select option (1 - 6)", 1, 6); 
    }
     
    public Dvd getNewDvdInfo() {           // read in Strings to create new Dvd
        String title = io.readString("Enter DVD title: ");
        String releaseDate = io.readString("Enter DVD release date: ");
        String ratingMpaa = io.readString("Enter DVD MPAA rating: ");
        String directorName = io.readString("Enter DVD director name: ");
        String studio = io.readString("Enter DVD studio name: ");
        String userReview = io.readString("Enter briefly worded user review: ");
        Dvd newDvd = new Dvd(title);
        newDvd.setReleaseDate(releaseDate);
        newDvd.setRating(ratingMpaa);
        newDvd.setDirectorName(directorName);
        newDvd.setStudio(studio);
        newDvd.setUserReview(userReview);
        return newDvd;
    }
                                                    // Print Banner Methods
     public void displayGetDvdBanner() {
        io.print("\n====================");
        io.print("-----Find a DVD-----");
        io.print("====================\n");
    }   
    
    public void displayDvdListBanner() {
        io.print("\n====================");
        io.print("----View DVD List---");
        io.print("====================\n");
    }     
     
    public void displayAddDvdBanner() {
        io.print("\n====================");
        io.print("------Add a DVD-----");
        io.print("====================\n");
    }
    
    public void displayEditDvdBanner() {
        io.print("\n====================");
        io.print("-----Edit a DVD-----");
        io.print("====================\n");
    }    
    
    public void displayDeleteDvdBanner() {
        io.print("\n====================");
        io.print("----Remove a DVD----");
        io.print("====================\n");
        
    }    
    
    public void displayExitBanner() {
        io.print("\n====================");
        io.print("--Have a great day--");
        io.print("====================\n");
        io.print("====================");
        io.print("-See you next time!-");
        io.print("====================\n");
    }    
    
    public void displayAddDvdSuccessBanner() {
        io.print("\n====================");
        io.print("DVD Added to Library");
        io.print("====================\n");
    }
 
    public void displayDeleteDvdSuccessBanner() {
        
        io.print("\n====================");
        io.print("DVD Removal Complete");
        io.print("====================\n");
    }
       
    public void displayEditDvdSuccessBanner() {
        io.print("\n====================");
        io.print("DVD Editing Complete");
        io.print("====================\n");
    }
    
    public void displayUnknownCommandBanner() {
        io.print("-----ERROR: UNKNOWN COMMAND-----");
    }
    
    public void displayErrorMessageBanner(String error) {
        io.print("-----ERROR-----");
        io.print(error);
    }    

//***is there an advantage to using functional operations vs a for loop below?
    public void displayDvdList(List<Dvd> DvdList) {
        for (Dvd currentDvd : DvdList) { 
            io.print(currentDvd.getTitle() + ":  " + currentDvd.getReleaseDate()
            + " | " + currentDvd.getRating() + " | " + currentDvd.getDirectorName()
            + " | " + currentDvd.getStudio() + " | " + currentDvd.getUserReview());
        }
    }

    public String getDvdName() {
        return io.readString("Enter DVD title: ");
    }
    
    public void displayDvd(Dvd dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle() + ":  " + dvd.getReleaseDate()
            + " | " + dvd.getRating() + " | " + dvd.getDirectorName()
            + " | " + dvd.getStudio() + " | " + " | " + dvd.getUserReview());
        } else {
            io.print("\nERROR: DVD DOES NOT EXIST IN LIBRARY!");
        }
    }
}