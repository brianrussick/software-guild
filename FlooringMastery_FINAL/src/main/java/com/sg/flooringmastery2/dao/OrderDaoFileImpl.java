package com.sg.flooringmastery2.dao;

import com.sg.flooringmastery2.dto.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author brian russick
 */
public class OrderDaoFileImpl implements OrderDao {
                  // implementation of orderDao
                 // load, create, write, retrieve, edit, remove, save orders..
    
                                // connect localDate map with orders list map
    private Map<LocalDate, List<Order>> oMap = new HashMap<>();
    private int mostRecentOrdNum; 
    private static final String DELIMITER = ",";   
    public static final String DIRECTORY = "orders/";
    
    private List<Order> loadOrderFile(LocalDate oDate) throws PersistenceException {
        Scanner sc = null;                         // scanner is uninitialized
        String fileName = oDate.format(DateTimeFormatter.ofPattern("MMddyyyy"));
                                     // create new order file in our directory
        File f = new File(String.format(DIRECTORY + "Orders_%s.txt", fileName));
                                    // create list to save orders
        List<Order> oList = new ArrayList<>();
         
        if (f.isFile()) {
            try {                  // create new scanner to read in order file
                sc = new Scanner(new BufferedReader (new FileReader(f)));
            } catch (FileNotFoundException e) { // if file not found throw error
                throw new PersistenceException ( 
                    "\tERROR - UNABLE TO LOAD FILE!\n", e);
                }
            String currentLine;        // holds most recent line read from file
            String[] currentTokens;       // holds split pieces of current line 
                                         // from delimiter
            while (sc.hasNextLine()) {               // lines left to read in ?
                currentLine = sc.nextLine();               // read in next line 
                currentTokens = currentLine.split(DELIMITER);// tokenize scanner
                if (currentTokens.length == 12) {
                Order o = new Order();                      // create new order
                                                           // set order details 
                o.setOrderNum(Integer.parseInt(currentTokens[0]));
                o.setCustomer(currentTokens[1]);
                o.setState(currentTokens[2]);
                o.setTaxRate(new BigDecimal(currentTokens[3]));
                o.setProduct(currentTokens[4]);
                o.setCostPerSqFt(new BigDecimal(currentTokens[5]));
                o.setLabCostPerSqFt(new BigDecimal(currentTokens[6]));
                o.setArea(new BigDecimal(currentTokens[7]));
                o.setMaterialCost(new BigDecimal(currentTokens[8]));
                o.setLabCost(new BigDecimal(currentTokens[9]));
                o.setTax(new BigDecimal(currentTokens[10]));
                o.setTotalCost(new BigDecimal(currentTokens[11]));

                o.setDate(LocalDate.parse(fileName,
                        DateTimeFormatter.ofPattern("MMddyyyy")));
                oList.add(o);                     // add order to list
                oMap.put(o.getDate(), oList);    // put into order map by date      
                } else {
                }  
            }
            sc.close(); // close scanner
        }
        return oList;
    }  

    private void writeOrderFile(List<Order> oList, LocalDate oDate) throws PersistenceException {       
        DateTimeFormatter ft = DateTimeFormatter.ofPattern("MMddyyyy");
        String newDate = oDate.format(ft);
                        // write using specific format in our orders directory
        File f = new File("orders/Orders_" + newDate + ".txt");
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(f));
        } catch (IOException e) {                     // catch throw new error 
            throw new PersistenceException (
                "\tERROR - UNABLE TO SAVE ORDER!\n", e);
        }            
        oList = this.getAllByDate(oDate);                 // use this instance
        oList = oMap.get(oDate);      // retrieve order by date from order map
           
        for (Order o : oList) {         // for every order put into order list
                                                        // print order details
                out.println(o.getOrderNum() + DELIMITER
                        + o.getCustomer() + DELIMITER
                        + o.getState() + DELIMITER
                        + o.getTaxRate() + DELIMITER
                        + o.getProduct() + DELIMITER
                        + o.getCostPerSqFt() + DELIMITER
                        + o.getLabCostPerSqFt() + DELIMITER
                        + o.getArea() + DELIMITER
                        + o.getMaterialCost() + DELIMITER
                        + o.getLabCost() + DELIMITER
                        + o.getTax() + DELIMITER
                        + o.getTotalCost());

                out.flush();            // forces PrintWriter to write to file
            }
        out.close();                    // close and flush stream
    }
    
    private void scanRecentOrdNum() throws PersistenceException {
        Scanner sc;

        try {                               
            sc = new Scanner(    // create new scanner to read in order # file
                    new BufferedReader(
                        new FileReader(DIRECTORY + "upToDateOrderNum.txt")));
        } catch (FileNotFoundException e) {
                                                      // catch throw new error 
            throw new PersistenceException (
                "\tERROR - UNABLE TO LOAD ORDER NUMBER!\n", e);
        }

        int upToDateOrdNum = Integer.parseInt(sc.nextLine()); // parse order #

        this.mostRecentOrdNum = upToDateOrdNum; // use this instance as the 
                                               // up to date order # in txt file
        sc.close(); // close scanner
    }

    private void writeRecentOrdNum(int mostRecentOrdNum) throws PersistenceException {
        PrintWriter out;

        try {
        out = new PrintWriter(new FileWriter(DIRECTORY + "upToDateOrderNum.txt"));
        } catch (IOException e) {
            throw new PersistenceException (  // catch throw new error 
                "\tERROR - UNABLE TO SAVE ORDER NUMBER!\n", e);
            }
        out.println(mostRecentOrdNum);   // print out most recent order number
        out.flush();                    // forces PrintWriter to write to file
        out.close();                   // close and flush stream
    }    
    
    @Override
    public Order createOrder(Order o) throws PersistenceException {
        scanRecentOrdNum();           // locate most recent order number
        mostRecentOrdNum++;          // increment most recent order number by 1
        o.setOrderNum(mostRecentOrdNum); // set most recent order number
        writeRecentOrdNum(mostRecentOrdNum); // write most recent order number
        
        List<Order> oList = oMap.get(o.getDate());
        if (oList == null){           // if uninitialized then 
        oList = new ArrayList<>() ;  // create new list
        }
        oList.add(o);              // add order & get date for order list
        oMap.put(o.getDate(),oList);

        return o;
    }
  
    @Override
    public List<Order> getAllByDate(LocalDate oDate) throws PersistenceException{ 
        loadOrderFile(oDate);                   // retrieve all orders by date
        List<Order> oList = oMap.get(oDate);
            
        return oList;
    }
  
    @Override
    public Order getOrder(Order o) throws PersistenceException {
        List<Order> oList = oMap.get(o.getDate());           // retrieve order
        for (Order currentOrd : oList){
            int oNum = o.getOrderNum();                     // by order number
                if (oNum == currentOrd.getOrderNum()){
                    o = currentOrd;
                    }
                }
        return o;    
    }

    @Override
    public List<Order> getAllOrders() throws PersistenceException {
        return new ArrayList(oMap.values());            // retrieve all orders
    }  
    
    @Override
    public Order removeOrder(Order o) throws PersistenceException {
        List<Order> oList = oMap.get(o.getDate());
        oList.remove(o);                                       // remove order
        oMap.put(o.getDate(),oList);
                 
        return o;
    }
        
    @Override
    public Order editOrder(Order o) throws PersistenceException{
        List<Order> oList = oMap.get(o.getDate());               // edit order
        oMap.put(o.getDate(), oList);

        return o;
    }   
  
    @Override
    public void saveToFile() throws PersistenceException {
        for (LocalDate oDate:oMap.keySet()){
            List<Order> oList = oMap.get(oDate);
            writeOrderFile (oList,oDate);                        // save order
        }
   }
}