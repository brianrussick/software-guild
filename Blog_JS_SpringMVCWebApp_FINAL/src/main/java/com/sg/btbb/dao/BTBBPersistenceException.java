package com.sg.btbb.dao;

/**
 *
 * @author brian russick
 */
public class BTBBPersistenceException extends Exception {

    public BTBBPersistenceException(String message) {
        super(message);
    }

    public BTBBPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }    
}