package com.sg.vendingmachine.service;

/**
 *
 * @author brian russick
 */                                        // No Item Found in Inventory error
public class NoItemInventoryException extends Exception {
    
    public NoItemInventoryException(String message) {
        super(message);
    }
    
    public NoItemInventoryException(String message, Throwable cause) {
        super(message, cause);
    }
}