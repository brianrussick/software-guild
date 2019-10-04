package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VMPersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Candy;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import com.sg.vendingmachine.dao.VMDao;

/**
 *
 * @author brian russick
 */
public class VMServiceLayerImpl implements VMServiceLayer{
    
    VMDao dao;
    Change userChange = new Change();
    
    public VMServiceLayerImpl(VMDao dao) {
        this.dao = dao;
    }

    @Override
    public String buyCandyInventory(Candy candy, BigDecimal userCash) throws VMPersistenceException, InsufficientFundsException {
                   
            // user cash must be greater than candy cost, otherwise throw error
        if (candy.getCost().compareTo(userCash) > 0) { 
            throw new InsufficientFundsException("YOU NEED MORE MONEY FOR THAT CANDY!");
        }
                        // update the current inventory based on user purchase 
        int currentInventory = candy.getVMInventory();
        
        candy.setVMInventory(currentInventory - 1);   // subtract 1 to set the
                                                     // updated inventory level
        dao.editCandyInventory(candy.getCandyName(), candy); // edit inventory
                                                            // by candy name
        userChange.getChange(candy.getCost(), userCash);
        
                                                            // Change logic
        String str;
        if (userChange.getQuarter() > 0) {
          str = "Your candy has been delivered!\nPlease take your"
                  + " remaining change:\n" + userChange.getQuarter() 
                  + " quarters & " + userChange.getDime() + " dimes & " 
                  + userChange.getNickel() + " nickels &\n"
                  + userChange.getPenny() + " pennies";
          
        } else if (userChange.getDime() > 0) {
            str = "Your candy has been delivered! Change remaining: "
                  + userChange.getDime() + " dimes & " 
                  + userChange.getNickel() + " nickels &\n"
                  + userChange.getPenny() + " pennies";
            
        } else if (userChange.getNickel() > 0) {
            str = "Your candy has been delivered! Change remaining: " 
                  + userChange.getNickel() + " nickels &\n"
                  + userChange.getPenny() + " pennies";
            
        } else if (userChange.getPenny() > 0) {
            str = "Your candy has been delivered! Change remaining: "
                  + userChange.getPenny() + " pennies";
            
        } else {
            str = "NO CHANGE LEFT OVER - TRANSACTION COMPLETE!";
        }
            return str;
    }
    
    @Override
    public List<Candy> getOnlyCandyInStock() throws VMPersistenceException {
        return dao.getOnlyCandyInStock();
    }
    
    @Override
    public List<Candy> getAllCandyInventory() throws VMPersistenceException {
        return dao.getAllCandyInventory();
    }

    @Override
    public void inStockStatus(String candyName) throws VMPersistenceException, VMCurrentItemOutOfStockException {
   
        List<Candy> inStock = dao.getOnlyCandyInStock().stream()
                .filter(i -> i.getCandyName().equals(candyName))
                .collect(Collectors.toList());
        
        if (inStock.isEmpty()) {
            throw new VMCurrentItemOutOfStockException("\tCANDY IS OUT OF STOCK!");
        }
    }
    
    @Override
    public Candy getCandyInventory(String candyName) throws VMPersistenceException, NoItemInventoryException {
        
        Candy candy = dao.getCandyInventory(candyName);
        
        if (candy == null) {
            throw new NoItemInventoryException("UNABLE TO LOCATE CANDY IN INVENTORY!");
        }
        return candy;
    }
}