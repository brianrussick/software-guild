package com.sg.vendingmachinespringmvc.service;

/**
 *
 * @author brian russick
 */
public class NoInventoryException extends Exception {

    public NoInventoryException(String message) {
        super(message);
    }
}