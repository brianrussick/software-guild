package com.sg.vendingmachinespringmvc.model;

import java.math.BigDecimal;
import java.util.Objects;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author brian russick
 */
public class UserSelection {                 // user forms getters and setters

    private BigDecimal userTotal = BigDecimal.ZERO;
    @NotEmpty(message="") 
    private String snackChoice;
    private String change = "";
    private String message = "";

    public BigDecimal getUserTotal() {
        return userTotal;
    }

    public void setUserTotal(BigDecimal userTotal) {
        this.userTotal = userTotal;
    }  
    
    public String getSnackChoice() {
        return snackChoice;
    }

    public void setSnackChoice(String snackChoice) {
        this.snackChoice = snackChoice;
    }    
    
    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.userTotal);
        hash = 13 * hash + Objects.hashCode(this.snackChoice);
        hash = 13 * hash + Objects.hashCode(this.change);
        hash = 13 * hash + Objects.hashCode(this.message);
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
        final UserSelection other = (UserSelection) obj;
        if (!Objects.equals(this.snackChoice, other.snackChoice)) {
            return false;
        }
        if (!Objects.equals(this.change, other.change)) {
            return false;
        }
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        if (!Objects.equals(this.userTotal, other.userTotal)) {
            return false;
        }
        return true;
    }
}