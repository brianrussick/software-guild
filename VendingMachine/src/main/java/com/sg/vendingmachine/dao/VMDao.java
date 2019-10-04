package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Candy;
import java.util.List;

/**
 *
 * @author brian russick
 */
public interface VMDao {
                    // get candy only if it is in stock
    List<Candy> getOnlyCandyInStock() throws VMPersistenceException;   
                    // get all candy from inventory
    List<Candy> getAllCandyInventory() throws VMPersistenceException;
                    // edit inventory
    Candy editCandyInventory(String candyName, Candy candy) throws VMPersistenceException;
                    // get candy from inventory
    Candy getCandyInventory(String candyName) throws VMPersistenceException;
}