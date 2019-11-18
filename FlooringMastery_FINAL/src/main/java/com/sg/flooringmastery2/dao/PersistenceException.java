package com.sg.flooringmastery2.dao;

/**
 *
 * @author brian russick
 */                                                       // Persistence error
public class PersistenceException extends Exception {

    public PersistenceException(String message) {
        super(message);
    }

    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    } 
}