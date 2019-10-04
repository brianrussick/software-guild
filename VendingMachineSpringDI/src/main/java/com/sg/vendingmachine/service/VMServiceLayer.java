package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VMPersistenceException;
import com.sg.vendingmachine.dto.Candy;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author brian russick
 */
public interface VMServiceLayer {
                                        // get all candy from inventory
    List<Candy> getAllCandyInventory()throws VMPersistenceException;
                                        // get candy only if it is in stock
    List<Candy> getOnlyCandyInStock() throws VMPersistenceException;
                                        // purchase candy from inventory
    String buyCandyInventory(Candy candy, BigDecimal userCash) throws InsufficientFundsException, VMPersistenceException;    
                                        // get candy from inventory
    Candy getCandyInventory(String candyName) throws NoItemInventoryException, VMPersistenceException;
                                        // check if candy is in stock
    void inStockStatus(String candyName) throws VMCurrentItemOutOfStockException, VMPersistenceException;
}