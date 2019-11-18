package com.sg.flooringmastery2.service;

import com.sg.flooringmastery2.dao.PersistenceException;
import com.sg.flooringmastery2.dto.Order;

/**
 *
 * @author brian russick
 */
public interface TaxServiceLayer {
    public void calcOrderTax(Order o) throws PersistenceException, DataValidationException;   
}
