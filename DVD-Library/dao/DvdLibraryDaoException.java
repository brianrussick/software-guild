package com.sg.dvdlibrary.dao;

/**
 *
 * @author brian russick
 */
public class DvdLibraryDaoException extends Exception{ // error class for app
	    
    public DvdLibraryDaoException(String message) {
	super(message);
	}
	    
    public DvdLibraryDaoException(String message, Throwable cause) {
	super(message, cause);
	}
}