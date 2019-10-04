package com.sg.vendingmachine.dao;

/**
 *
 * @author brian russick
 */                                                        // Persistence error
public class VMPersistenceException extends Exception {
    
    public VMPersistenceException(String message) {
        super(message);
    }
    
    public VMPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}