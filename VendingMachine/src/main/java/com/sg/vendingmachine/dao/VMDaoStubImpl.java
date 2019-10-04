package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Candy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author brian russick
 */
public class VMDaoStubImpl implements VMDao {
    
    Candy candy;
    List<Candy> candyList = new ArrayList<>();


    @Override                  // edit candy in inventory if candy name matches
    public Candy editCandyInventory(String candyName, Candy candy) throws VMPersistenceException {
        if (candyName.equals(candy.getCandyName())) {
            return candy;
        } else {
            return null;
        }
    }

    @Override                 // get candy from inventory if candy name matches
    public Candy getCandyInventory(String candyName) throws VMPersistenceException {
        if (candyName.equals(candy.getCandyName())) {
            return candy;
        } else {
            return null;
        }
    }

    @Override                   // get only candy in stock
    public List<Candy> getOnlyCandyInStock() throws VMPersistenceException {
        return Collections.emptyList();
    }
    
        @Override                   // get all inventory
    public List<Candy> getAllCandyInventory() throws VMPersistenceException {
        return candyList;
    }
}