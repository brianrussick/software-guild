package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Candy;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author brian russick
 */
public class VMView {
    private UserIO io;
    
    public VMView(UserIO io) {
        this.io = io;
    }                                         
                                               // stream for inventory menu
    public void displayInventoryEntireList(List<Candy> inventory) {
        inventory.stream()
                .forEach(i -> io.print(i.getCandyName() +
                        "  -  $" + i.getCost()));
    }
                                           // BigDecimal conversion for deposit
    public BigDecimal depositUserCash() throws VMNumberFormatException {
        BigDecimal userCash = null;
        try {
            userCash = new BigDecimal
    (io.readString("Deposit Money (# format: 5, 10.50, 25.00, 55.75, etc): ")); 
        } catch (NumberFormatException e) { // catch error
            throw new VMNumberFormatException("\tMUST ENTER NUMBERS!", e);  
            }
        return userCash; 
    }
     
    public String getCandyChoice() {
        return io.readString("\nEnter Candy Choice: ");
    }                                                       // display banners
    
    public String continueExitBanner() {
        return io.readString("\nPlease hit 'Enter' to add money\n"
                + "============= OR ==============\n"
                + "Type Exit to quit the program");
    }
 
    public void displayMenuBanner() {
        io.print("\n==== Vending Machine Menu ====");

    }     
    
    public void displayChangeBanner(Candy candy, String usersChange) {
        io.print(candy.getCandyName() + " has been selected");
        io.print(usersChange);
    }

    public void displayErrorBanner(String error) {
        io.print("\n===========     ERROR     ===========\n");
        io.print(error);
        io.print("\n===========     ERROR     ===========\n");
    }
    
    public void displayContinueBanner() {
        io.print("\n===============================\n");
        io.readString("Please hit 'Enter' to continue");
        io.print("===============================\n");
    }
    
    public void displayTryAgainBanner() {
        io.print("\n=== TRY AGAIN! Selection Invalid ===\n");
    }
    
    public void displayExitBanner() {
        io.print("\n===============================");
        io.print("===============================");
        io.print("= Goodbye!!!  &  Thank You!!! =");
        io.print("===============================");
        io.print("===============================\n");
    }    
}