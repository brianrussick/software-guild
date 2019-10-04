package com.sg.dvdlibrary;
import com.sg.dvdlibrary.controller.DvdLibraryController;
import com.sg.dvdlibrary.dao.DvdLibraryDaoFileImpl;
import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.ui.DvdLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;

/**
 *
 * @author brian russick
 */
public class App {                      // logic that configures, instantiates, 
                                        // and assembles the classes in our app
    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImpl();
        DvdLibraryDao dao = new DvdLibraryDaoFileImpl();
        DvdLibraryView view = new DvdLibraryView(io);
        DvdLibraryController controller = new DvdLibraryController(dao, view);
        controller.run();
    }
}