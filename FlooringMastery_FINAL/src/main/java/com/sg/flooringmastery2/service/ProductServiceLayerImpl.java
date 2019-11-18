package com.sg.flooringmastery2.service;

import com.sg.flooringmastery2.dao.PersistenceException;
import com.sg.flooringmastery2.dto.Order;
import com.sg.flooringmastery2.dto.Product;
import com.sg.flooringmastery2.dao.ProductDao;

/**
 *
 * @author brian russick
 */             
                                       // product service layer implementation
                                      // implements product service layer
public class ProductServiceLayerImpl implements ProductServiceLayer {
    
    ProductDao prodDao;
    
    public ProductServiceLayerImpl (ProductDao dao) {       
       this.prodDao = dao;                                // use this instance
    }
    
    @Override
    public void loadProduct(Order o) throws DataValidationException, PersistenceException {
        Product prod  = prodDao.getProduct(o.getProduct());
        if (prod != null) {                             // set product details
            o.setProduct(prod.getProdType());
            o.setLabCostPerSqFt(prod.getLabCostPerSqFt());
            o.setCostPerSqFt(prod.getProdCostPerSqFt());
        } else { // if (prod == null) throw error
            throw new DataValidationException("\tERROR - NO PRODUCT FOUND!\n");
        }
    }  
}