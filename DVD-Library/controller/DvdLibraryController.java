package com.sg.dvdlibrary.controller;
import com.sg.dvdlibrary.dto.Dvd;
import com.sg.dvdlibrary.dao.DvdLibraryDaoException;
import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.ui.DvdLibraryView;
import java.util.List;

/**
 *
 * @author brian russick
 */
public class DvdLibraryController {
    
    DvdLibraryView view;
    DvdLibraryDao dao;

    public void run() {
	int menuSelection;
        boolean keepGoing = true;
	
        try {                   // test for errors while running menu
	while (keepGoing) {
	            
	    menuSelection = getMenuSelection();

	    switch (menuSelection) {        // switch statement for menu
	        case 1:
	            findDvd();
	            break;
	        case 2:
	            listDvds();
	            break;
	        case 3:
	            addDvd();
	            break;
	        case 4:
	            editDvd();
	            break;
	        case 5:
	            deleteDvd();
	            break;
                case 6: 
                    keepGoing = false;
                    break;
	        default:
	            unknownCommand();
	    }
	}
	exit();
    } catch (DvdLibraryDaoException e) {      // catch error and display banner
        view.displayErrorMessageBanner(e.getMessage());
    }
}
            
                                                    // dvd methods       
    private int getMenuSelection() {
        return view.getMenuSelection();
    }

// view displays the Add Dvd banner and asks the user for all of the Dvd
// properties to be added.  Then we ask the DAO to add the Dvd and finally we
// ask the view to display the Add Dvd Success banner
    
    private void addDvd() throws DvdLibraryDaoException {
        view.displayAddDvdBanner();
        Dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getTitle(), newDvd);
        view.displayAddDvdSuccessBanner();
    }
    
// view displays the Dvd List banner and gets the Dvd list from the HashMap.  
// Then we ask the view to display the Dvd list    
    
    private void listDvds() throws DvdLibraryDaoException {
        view.displayDvdListBanner();
        List<Dvd> dvdList = dao.listAllDvds();
        view.displayDvdList(dvdList);
    }
    
// view displays the Get Dvd banner and asks the user for the title of the
// Dvd to locate.  Then we ask the DAO to get the Dvd and finally we
// ask the view to display the Dvd    
    
    private void findDvd() throws DvdLibraryDaoException {
        view.displayGetDvdBanner();
        String title = view.getDvdName();
        Dvd dvd = dao.getDvd(title);
        view.displayDvd(dvd);
    }
    
// view displays the Delete Dvd banner and asks the user for the title of the
// Dvd to be deleted.  Then we ask the DAO to delete the Dvd and finally we
// ask the view to display the Delete Dvd Success banner
    
    private void deleteDvd() throws DvdLibraryDaoException {
        view.displayDeleteDvdBanner();
        String title = view.getDvdName();
        dao.deleteDvd(title);
        view.displayDeleteDvdSuccessBanner();
    }
    
// view displays the Edit Dvd banner and asks the user for all of the Dvd
// properties to be added. Then we ask the DAO to edit the Dvd info if the title 
// matches and finally we ask the view to display the Edit Dvd Success banner
 
    private void editDvd() throws DvdLibraryDaoException {
        view.displayEditDvdBanner();
        Dvd editDvd = view.getNewDvdInfo();
        dao.editDvd(editDvd.getTitle(),editDvd);
        view.displayEditDvdSuccessBanner();
    }

// view displays the Unknown Command banner
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
// view displays the Exit banner    
    private void exit() {
        view.displayExitBanner();
    }

// constructor that initializes dao and view using their current instance      
    public DvdLibraryController(DvdLibraryDao dao, DvdLibraryView view) {
        this.dao = dao; 
        this.view = view;   
    }
}