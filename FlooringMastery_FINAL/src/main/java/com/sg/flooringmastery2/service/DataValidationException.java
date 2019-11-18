package com.sg.flooringmastery2.service;

/**
 *
 * @author brian russick
 */                                                   // data validation error
public class DataValidationException extends Exception {
    
    public DataValidationException(String message) {
        super(message);
    }

    public DataValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}