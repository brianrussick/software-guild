package com.sg.vendingmachinespringmvc.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author brian russick
 */
public class Snack {
                                       // snack object with getters and setters
    private BigDecimal snackCost;
    private String snackName;
    private int snackIdNum;
    private int snackStockNum;

    public BigDecimal getSnackCost() {
        return snackCost;
    }

    public void setSnackCost(BigDecimal snackCost) {
        this.snackCost = snackCost;
    }    

    public String getSnackName() {
        return snackName;
    }

    public void setSnackName(String snackName) {
        this.snackName = snackName;
    }
    
    public int getSnackIdNum() {
        return snackIdNum;
    }

    public void setSnackIdNum(int snackIdNum) {
        this.snackIdNum = snackIdNum;
    }

    public int getSnackStockNum() {
        return snackStockNum;
    }

    public void setSnackStockNum(int amountInStock) {
        this.snackStockNum = amountInStock;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.snackCost);
        hash = 83 * hash + Objects.hashCode(this.snackName);
        hash = 83 * hash + this.snackIdNum;
        hash = 83 * hash + this.snackStockNum;
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
        final Snack other = (Snack) obj;
        if (this.snackIdNum != other.snackIdNum) {
            return false;
        }
        if (this.snackStockNum != other.snackStockNum) {
            return false;
        }
        if (!Objects.equals(this.snackName, other.snackName)) {
            return false;
        }
        if (!Objects.equals(this.snackCost, other.snackCost)) {
            return false;
        }
        return true;
    }
}