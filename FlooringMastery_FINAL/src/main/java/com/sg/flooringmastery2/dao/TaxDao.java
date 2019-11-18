package com.sg.flooringmastery2.dao;

import com.sg.flooringmastery2.dto.StateTaxRates;

/**
 *
 * @author brian russick
 */
public interface TaxDao {
                                                             // load all taxes
    public void loadAllStateTaxRates() throws PersistenceException; 
                                                     // get tax rates by state
    public StateTaxRates getTaxByState (String state) throws PersistenceException;  
}