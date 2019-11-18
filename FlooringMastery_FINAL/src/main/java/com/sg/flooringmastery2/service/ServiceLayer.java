package com.sg.flooringmastery2.service;

import com.sg.flooringmastery2.dao.PersistenceException;
import com.sg.flooringmastery2.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author brian russick
 */
public interface ServiceLayer {

    public void verifyMode (String userModeChoice) throws PersistenceException;
    
    public void saveToFile() throws PersistenceException, DataValidationException;  

    public void verifyOrderDetails(Order o) throws DataValidationException;
    
    public void createOrder(Order o) throws PersistenceException, DataValidationException;
    
    public void calcOrderTotal(Order o);
    
    public List<Order> getOrderDateSpecific(LocalDate oDate) throws PersistenceException, DataValidationException;

    Order getOrder(Order o) throws PersistenceException, DataValidationException;

    Order editOrder(Order o) throws PersistenceException, DataValidationException;    
    
    Order removeOrder(Order o) throws PersistenceException, DataValidationException;
}