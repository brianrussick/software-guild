package com.sg.flooringmastery2.controller;

import com.sg.flooringmastery2.dao.PersistenceException;
import com.sg.flooringmastery2.dto.Order;
import com.sg.flooringmastery2.service.DataValidationException;
import com.sg.flooringmastery2.ui.View;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.sg.flooringmastery2.service.ServiceLayer;
import com.sg.flooringmastery2.service.TaxServiceLayer;
import com.sg.flooringmastery2.service.ProductServiceLayer;

/**
 *
 * @author brian russick
 */
public class Controller {
    
    private View view;
    private ServiceLayer service;
    private ProductServiceLayer product;
    private TaxServiceLayer tax;
        
         // initialize and use this instance of view, product, tax, and service
    public Controller(View view, ProductServiceLayer product, TaxServiceLayer tax, ServiceLayer service){
        this.view = view;
        this.product = product;
        this.tax = tax;
        this.service = service;
    }
    
    public void run() throws DataValidationException {
                  // boolean set to true will continue to display menu even if 
                 // the user types an incorrect entry         
        boolean keepGoing = true;
        int menuSelection = 0;
        
        try {   // test for errors, decide between training OR production mode
            
            boolean trainingModeActive = view.verifyMode();
            if (trainingModeActive == true) {       // training mode
                service.verifyMode("Training");
                view.displayTrainingModeBanner();
            } else {
                service.verifyMode("Production");         // production mode
                view.displayProductionModeBanner();
                }
            while (keepGoing) {
                menuSelection = getMenuSelection();
                switch (menuSelection) {        // switch statement for menu
                    case 1:
                        displayOrders();
                        break;
                    case 2:
                        createOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        if (trainingModeActive) {
                            view.displayInvalidActionBanner(); // no saving in 
                        } else {                              // training mode
                            saveToFile(); // if production mode active then save
                        }
                        break;
                    case 6:
                        if (trainingModeActive) {
                            keepGoing = false;
                            break;
                        } else {
                            keepGoing = false;
                        break;
                    }
                }
            }
            displayThankYouQuitBanner(); // exit 
        } catch (PersistenceException | DataValidationException e) {
            view.displayErrorBanner(e.getMessage());
        }
    }
    
    private int getMenuSelection() {
        return view.getMenuSelection();
    }    
    
    private void saveToFile() throws PersistenceException, DataValidationException {
     
            try {
            service.saveToFile();    // write memory to disk from current session
            view.displaySaveWorkBanner(); // saved work successfully
            } catch (PersistenceException | DataValidationException e) {
            view.displayErrorBanner(e.getMessage());    // catch errors & 
        }                                              // display banner
   } 

    private Order calc (Order o) throws PersistenceException, DataValidationException {
                                           //******************************
        service.verifyOrderDetails(o);    // verify order in service layer
        product.loadProduct(o);          // load / calculate material
        tax.calcOrderTax(o);            // tax
        service.calcOrderTotal(o);          // & order total
        return o;                     // return order
    }                                //**************
    
    private void displayOrders() {
        view.displayOrdersBanner();
        LocalDate oDate = view.userDateSelection(); // ask user for order date &
        try {                                      // send to service layer
        List<Order> oDateSpecific = service.getOrderDateSpecific(oDate); 
        view.getEntireOrderList(oDateSpecific);  // print order(s) if valid 
                                               
        } catch (PersistenceException | DataValidationException e) {
        view.displayErrorBanner(e.getMessage());// catch errors & display banner
    }
}
    
    private void createOrder() throws PersistenceException, DataValidationException {
        view.displayCreateOrderBanner();
        try {                                     
        Order o = view.getOrderDetailsNew(); // ask user for order details
        Order addOrder = calc(o);      // perform order calculations
        view.getAddOrder(addOrder);             // view new order summary
        
                                          // list containing all order details
        List<Order> oList = new ArrayList<>(); 
        oList.add(addOrder);               // add new order to orders list
        
        if (view.saveOrder() == true){          // save order to memory (y/n) 
            oList.stream()                
        .forEach(ord -> {                       // iterate through order stream
                try {
                service.createOrder(ord);    // send new order to service layer
                } catch (PersistenceException | DataValidationException e) {
                view.displayErrorBanner(e.getMessage());}
                });
            }
        } catch (PersistenceException | DataValidationException e){
            view.displayErrorBanner(e.getMessage());
        }                                      // catch errors & display banner
    }
    
    private void editOrder() throws PersistenceException, DataValidationException {
        view.displayEditBanner();
        LocalDate oDate = view.getUserDate();  // ask user for order date
        
        try {                     // check service layer for order date match
            List<Order> oList = service.getOrderDateSpecific(oDate);
            view.getEntireOrderList(oList);     // view order details if match
            String idNum = view.confirmOrderNum();  // confirm desired order #
                                                    
            int oNum = Integer.parseInt(idNum);         // parse order number
            for (Order orderMatch:oList){               // into integer     
            
            if (oNum == orderMatch.getOrderNum()){   // if order exists 
            service.getOrder(orderMatch);             // get from service layer
            Order edit = view.getEditOrder(orderMatch); // enter new info
            edit = calc(edit);            // calculate order changes
            service.editOrder(edit);      // send changes to service layer
            }                 // changes are saved to memory if (y) is selected
            }
            view.displayEditingCompleteBanner();
           
        } catch (PersistenceException | DataValidationException e) {
            view.displayErrorBanner(e.getMessage());
        }}                                    // catch errors & display banner

    private void removeOrder() throws PersistenceException, DataValidationException {
        view.displayRemoveOrderBanner();
        LocalDate oDate = view.getUserDate();   // ask user for order date
        
        try {                              // send order date to service layer
            List<Order> oList = service.getOrderDateSpecific(oDate);
            view.getEntireOrderList(oList); // print order summary for date       
            
            int oNum = view.getOrderNum();                  // confirm order #
                                               // "Confirm REMOVE order? (y/n)" 
            if (view.askUserIfWantToRemoveTheOrder() == true) {
                                                 // iterate through orders list
            Iterator<Order> iterator = oList.iterator(); 
            while (iterator.hasNext()) { // iterate orders in forward direction
            Order o = iterator.next();
            if (oNum == o.getOrderNum()) {  // if order number matches remove
                iterator.remove();                  // the order from memory
                }
            }
            }
            view.displayRemovalCompleteBanner();
                  
            } catch (PersistenceException | DataValidationException e) {
                view.displayErrorBanner(e.getMessage());  }
        } 

    private void displayThankYouQuitBanner() {   // display banner 'Goodbye!!!'
        view.displayThankYouQuitBanner();
    }  
}