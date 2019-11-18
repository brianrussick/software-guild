package com.sg.flooringmastery2.dao;

import com.sg.flooringmastery2.dto.Product;

/**
 *
 * @author brian russick
 */
public interface ProductDao {
                                                         // load all products
    public void loadProdAllTypes() throws PersistenceException;   
                                                        // get product
    Product getProduct(String prodType) throws PersistenceException;
}