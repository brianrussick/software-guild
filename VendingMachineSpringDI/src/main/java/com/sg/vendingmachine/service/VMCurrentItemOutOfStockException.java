package com.sg.vendingmachine.service;

/**
 *
 * @author brian russick
 */                                                 // Item Out of Stock error
public class VMCurrentItemOutOfStockException extends Exception {
    
    public VMCurrentItemOutOfStockException(String message) {
        super(message);
    }
    
    public VMCurrentItemOutOfStockException(String message, Throwable cause) {
        super(message, cause);
    }
    
}