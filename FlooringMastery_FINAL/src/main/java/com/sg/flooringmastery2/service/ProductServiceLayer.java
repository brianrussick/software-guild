package com.sg.flooringmastery2.service;

import com.sg.flooringmastery2.dao.PersistenceException;
import com.sg.flooringmastery2.dto.Order;

/**
 *
 * @author brian russick
 */
public interface ProductServiceLayer {
    public void loadProduct(Order o) throws PersistenceException, DataValidationException;   
}