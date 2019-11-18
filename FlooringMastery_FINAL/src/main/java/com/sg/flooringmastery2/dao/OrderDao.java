package com.sg.flooringmastery2.dao;

import com.sg.flooringmastery2.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author brian russick
 */
public interface OrderDao {
                                                // save
    public void saveToFile() throws PersistenceException;
                                                // retrieve all orders by date
    List<Order> getAllByDate(LocalDate oDate) throws PersistenceException;
                                                // retrieve entire orders list
    List<Order> getAllOrders() throws PersistenceException;
                                                // add new order
    Order createOrder(Order o) throws PersistenceException;
                                                // edit order
    Order editOrder(Order o) throws PersistenceException;
                                                // remove order
    Order removeOrder(Order o) throws PersistenceException;
                                                // retrieve order
    Order getOrder(Order o) throws PersistenceException;  
}