package com.sg.vendingmachinespringmvc.service;

/**
 *
 * @author brian russick
 */
public class InsufficientFundsException extends Exception {

    public InsufficientFundsException(String message) {
        super(message);
    }
}