package com.sg.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author brian russick
 */
public class Candy {
    private BigDecimal cost;
    private int VMInventory;
    private String candyName;
                                                    // public getters & setters
    public Candy(String candyName) {
        this.candyName = candyName;
    }   

    public int getVMInventory() {
        return VMInventory;
    }

    public void setVMInventory(int inventory) {
        this.VMInventory = inventory;
    }
    
    public String getCandyName() {
        return candyName;
    }
    
    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    } 
}