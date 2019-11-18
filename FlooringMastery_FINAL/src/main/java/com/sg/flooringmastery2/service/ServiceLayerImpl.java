package com.sg.flooringmastery2.service;

import com.sg.flooringmastery2.dao.PersistenceException;
import com.sg.flooringmastery2.dto.Order;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.sg.flooringmastery2.dao.AuditDao;
import com.sg.flooringmastery2.dao.OrderDao;

/**
 *
 * @author brian russick
 */
public class ServiceLayerImpl implements ServiceLayer {    
    private OrderDao dao;  
    private AuditDao auditDao;
  
    public ServiceLayerImpl(OrderDao dao, AuditDao auditDao) {
        this.dao = dao;               // use this instance of dao and auditDao
        this.auditDao = auditDao;
    }
    
    @Override
    public void createOrder(Order o) throws PersistenceException, DataValidationException {    
        dao.createOrder(o);                  // add new order
    }  

    @Override
    public Order editOrder(Order o) throws PersistenceException, DataValidationException {
        Order orderWithEdits;         
        orderWithEdits = dao.editOrder(o);   // edit order
            return orderWithEdits;     
    }

    @Override
    public Order removeOrder(Order o)throws PersistenceException, DataValidationException {   
        Order deletedO;
        deletedO = dao.removeOrder(o);       // delete order
            return deletedO;
    }

    @Override
    public Order getOrder(Order o) throws PersistenceException, DataValidationException {
        if (dao.getOrder(o) != null) {       // retrieve order
            return  dao.getOrder(o);} else {
            throw new DataValidationException("\tTHE ORDER DOES NOT EXIST!\n");
        }
    }

    @Override
    public void saveToFile()throws PersistenceException, DataValidationException {
           dao.saveToFile();                 // save order to file
    }
    
    @Override
    public void verifyOrderDetails(Order o) throws DataValidationException {
                            // if order details are empty or null throw error
        String str = ""; 
        if (o.getCustomer().trim().isEmpty()) {
            str += "\tYOU MUST ENTER A NAME\n";
        } else if (o.getCustomer() == null) {
            str += "\tYOU MUST ENTER A NAME\n";
        }
        if (o.getState().trim().isEmpty()) {
            str += "\tYOU MUST ENTER A STATE\n";
        } else if (o.getState() == null) {
            str += "\tYOU MUST ENTER A STATE\n";
        }
        if (o.getProduct().trim().isEmpty()) {
            str += "\tYOU MUST ENTER A PRODUCT\n";
        } else if (o.getProduct() == null) {
            str += "\tYOU MUST ENTER A PRODUCT\n";
        }
        if (o.getArea().compareTo(BigDecimal.ZERO) == 0) {
            str += "\tYOU MUST ENTER AN AREA SQ FT";
        } else if (o.getArea() == null) {
            str += "\tYOU MUST ENTER AN AREA SQ FT";
        }
        if (str.isEmpty()) {
        } else {
            throw new DataValidationException(str);
        }
    }
    
    @Override                                    // calculate the order logic
    public void calcOrderTotal(Order o) {   
        o.setMaterialCost(o.getCostPerSqFt().multiply(o.getArea())
                .setScale(2, RoundingMode.HALF_UP));
        o.setLabCost(o.getLabCostPerSqFt().multiply(o.getArea())
                .setScale(2, RoundingMode.HALF_UP));
        o.setTax(o.getTaxRate().divide(new BigDecimal("100.00"))
                .multiply((o.getMaterialCost().add(o.getLabCost())))
                .setScale(2, RoundingMode.HALF_UP));
        o.setTotalCost(o.getMaterialCost().add(o.getLabCost()).add(o.getTax()));
    }

    @Override
    public List<Order> getOrderDateSpecific(LocalDate oDate) throws PersistenceException,DataValidationException {
        List<Order>oList = new ArrayList<>();
        oList = dao.getAllByDate(oDate);            // get orders by date
        
        if (oList != null) {} 
        else {                         // throw error if order date not found
            throw new DataValidationException("\tTHE ORDER DOES NOT EXIST!\n");
        }
        return oList;
    }
    
    @Override
    public void verifyMode(String userModeChoice) throws PersistenceException {
        ApplicationContext ctx =          // pick training or production mode
        new ClassPathXmlApplicationContext("applicationContext.xml");
     
        if (!userModeChoice.equalsIgnoreCase("training")) {
        if (userModeChoice.equalsIgnoreCase("production")) {  // run production     
            dao = ctx.getBean("orderDao", OrderDao.class);   // mode            
            }}
        else { // otherwise run training mode
            dao = ctx.getBean("trainingMode", OrderDao.class);          
        }
    }
}