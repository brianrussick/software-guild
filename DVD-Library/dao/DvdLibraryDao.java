package com.sg.dvdlibrary.dao;
import com.sg.dvdlibrary.dto.Dvd;
import java.util.List;

/**
 *
 * @author brian russick
 */
public interface DvdLibraryDao {        
    Dvd addDvd(String title, Dvd dvd)   // add dvd to library and attach title
     throws DvdLibraryDaoException;
                                  
    List<Dvd> listAllDvds()             // view entire dvd list
     throws DvdLibraryDaoException;
                                    
    Dvd getDvd(String title)            // retrieve dvd if title matches
     throws DvdLibraryDaoException;
                                   
    Dvd deleteDvd(String title)         // remove dvd from library
     throws DvdLibraryDaoException;
     
    Dvd editDvd(String title, Dvd dvd)  // edit dvd if title exists in library
     throws DvdLibraryDaoException;   
}