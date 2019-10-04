package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.cost);
        hash = 73 * hash + this.VMInventory;
        hash = 73 * hash + Objects.hashCode(this.candyName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Candy other = (Candy) obj;
        if (this.VMInventory != other.VMInventory) {
            return false;
        }
        if (!Objects.equals(this.candyName, other.candyName)) {
            return false;
        }
        if (!Objects.equals(this.cost, other.cost)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Candy : " + candyName + " : Money inserted : "; 
    }
}