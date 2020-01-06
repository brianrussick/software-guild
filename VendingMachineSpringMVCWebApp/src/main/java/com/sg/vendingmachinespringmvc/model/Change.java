package com.sg.vendingmachinespringmvc.model;

/**
 *
 * @author brian russick
 */
public class Change {
    private int quarter;
    private int dime;
    private int nickel;
    private int penny;

    public int getQuarter() {
        return quarter;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    public int getDime() {
        return dime;
    }

    public void setDime(int dime) {
        this.dime = dime;
    }

    public int getNickel() {
        return nickel;
    }

    public void setNickel(int nickel) {
        this.nickel = nickel;
    }

    public int getPenny() {
        return penny;
    }

    public void setPenny(int penny) {
        this.penny = penny;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.quarter;
        hash = 37 * hash + this.dime;
        hash = 37 * hash + this.nickel;
        hash = 37 * hash + this.penny;
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
        final Change other = (Change) obj;
        if (this.quarter != other.quarter) {
            return false;
        }
        if (this.dime != other.dime) {
            return false;
        }
        if (this.nickel != other.nickel) {
            return false;
        }
        if (this.penny != other.penny) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return quarter + " Quarter " + 
               dime + " Dime " + 
               nickel + " Nickel " + 
               penny + " Penny ";
    }
}