package com.sg.vendingmachine.ui;

/**
 *
 * @author brian russick
 */
public class VMNumberFormatException extends Exception {
    
    public VMNumberFormatException (String message) {
        super(message);
    }
    
    public VMNumberFormatException (String message, Throwable cause) {
        super(message, cause);
    }
}