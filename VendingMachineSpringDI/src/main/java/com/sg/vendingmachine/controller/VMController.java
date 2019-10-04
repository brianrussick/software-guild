package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VMPersistenceException;
import com.sg.vendingmachine.dto.Candy;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.NoItemInventoryException;
import com.sg.vendingmachine.service.VMCurrentItemOutOfStockException;
import com.sg.vendingmachine.ui.VMNumberFormatException;
import com.sg.vendingmachine.ui.VMView;
import java.math.BigDecimal;
import java.util.List;
import com.sg.vendingmachine.service.VMServiceLayer;

/**
 *
 * @author brian russick
 */
public class VMController {
    VMServiceLayer service;                 // initialize service and view
    VMView view;
    
    public VMController(VMServiceLayer service, VMView view) {
        this.service = service;      // use this instance for service and view
        this.view = view;
    }
    
    public void run() {
            // boolean set to true will continue to display menu even if 
            // the user types an incorrect entry
        boolean keepGoing = true;
        displayMenuBanner();
        try {
           while (keepGoing) {
            
                displayMenu(); // lists VM inventory if it is currently in stock
                String userEntry = continueExitBanner(); 
                                                            // quit or continue
                if (userEntry.equals("Exit")){
                        keepGoing = false;
                        break;       
                    }
                else if (userEntry.equals("exit")){
                        keepGoing = false;
                        break;       
                    }                          // load user's cash into machine
                BigDecimal userCash = depositUserCash(); 
                Candy candyChoice = null;    // uninitialized 
                String userChoice = "";     // asks for user's choice of candy
                
                do {
                    userChoice = getCandyChoice(); // get user's choice of candy
                    
                    try {         // get candy from inventory if it is in stock
                        candyChoice = getCandyInventory(userChoice);
                        inStockStatus(candyChoice.getCandyName());
                    } catch (NoItemInventoryException e) { // catch error
                        displayTryAgainBanner();        // and try again
                        view.displayErrorBanner(e.getMessage());
                    } catch (VMCurrentItemOutOfStockException e) {
                        candyChoice = null;    // if out of stock display error
                        view.displayErrorBanner(e.getMessage());
                    }
                } while (candyChoice == null);
        // buys candy if it is in the inventory and if the user has enough cash
                    try {               
                       buyCandyInventory(candyChoice, userCash); 
                    } catch (InsufficientFundsException e) { // catch error if
                        view.displayErrorBanner(e.getMessage()); // funds insuff
                }
            } 
        } catch (VMPersistenceException e) { // catch Persistence error
            view.displayErrorBanner(e.getMessage());
        }
        displayExitBanner(); // exit
    }
                                             // display only the candy in stock
    private void displayMenu()  throws VMPersistenceException {
        List<Candy> candyInStock = service.getOnlyCandyInStock();
        
        view.displayInventoryEntireList(candyInStock);  
    }
    
                                          // BigDecimal conversion for deposit
    private BigDecimal depositUserCash() { 
        BigDecimal userCash = null;
        boolean numberFormatError = false;
        do {       
            try {
                userCash = view.depositUserCash(); // deposit user cash if 
                numberFormatError = false;        // # input is in proper format
            } catch (VMNumberFormatException e) {
                numberFormatError = true;    // throw error if incorrect format
                view.displayErrorBanner(e.getMessage());
                }
        } while (numberFormatError);
        return userCash;
    }
                                                // Banners etc.
    private String continueExitBanner() {
        return view.continueExitBanner();
    }    
    
    private String getCandyChoice() {
        return view.getCandyChoice();
    }
    
    private Candy getCandyInventory(String candy) throws VMPersistenceException, NoItemInventoryException {      
         return service.getCandyInventory(candy);
    }
    
    private void displayTryAgainBanner() {
        view.displayTryAgainBanner();
    }
    
    private void inStockStatus(String candyName) throws VMCurrentItemOutOfStockException, VMPersistenceException {
        service.inStockStatus(candyName);
    }
    
    private void buyCandyInventory(Candy candy, BigDecimal userCash) throws VMPersistenceException, InsufficientFundsException {
        
        String usersChange = service.buyCandyInventory(candy, userCash); 
        view.displayChangeBanner(candy, usersChange);
        view.displayContinueBanner();    
    }
    
    private void displayMenuBanner() {
        view.displayMenuBanner();
    }
    
    private void displayExitBanner() {
        view.displayExitBanner();
    }
}    