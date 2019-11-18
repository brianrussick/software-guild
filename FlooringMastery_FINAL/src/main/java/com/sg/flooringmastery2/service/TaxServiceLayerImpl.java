package com.sg.flooringmastery2.service;

import com.sg.flooringmastery2.dao.PersistenceException;
import com.sg.flooringmastery2.dto.Order;
import com.sg.flooringmastery2.dto.StateTaxRates;
import com.sg.flooringmastery2.dao.TaxDao;

/**
 *
 * @author brian russick
 */
public class TaxServiceLayerImpl implements TaxServiceLayer {
                                            // tax service layer implementation 
                                           // implements tax service layer
    private TaxDao taxDao;
    
    public TaxServiceLayerImpl(TaxDao taxDao) {
        this.taxDao = taxDao;             // use this instance
    }
    
    @Override
    public void calcOrderTax(Order o) throws PersistenceException, DataValidationException {
        StateTaxRates state = taxDao.getTaxByState(o.getState());
        
        if (state != null){               // if state exists then set tax rate
            o.setState(state.getState());
            o.setTaxRate(state.getTax());
        } else {                          // otherwise throw error
            throw new DataValidationException("\tINVALID STATE - NO ITEMS FOUND\n");
        }
    }
}