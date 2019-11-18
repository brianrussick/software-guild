package com.sg.flooringmastery2.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author brian russick
 */
public class Order {
    
    private int orderNum;
    private String customer;
    private String state;
    private BigDecimal taxRate;
    private String product;
    private BigDecimal costPerSqFt;
    private BigDecimal labCostPerSqFt;
    private BigDecimal area;
    private BigDecimal materialCost;
    private BigDecimal labCost;
    private BigDecimal tax;
    private BigDecimal totalCost;
    private LocalDate oDate;
    
    public LocalDate getDate(){
        return oDate;
    }
    public void setDate(LocalDate oDate){
        this.oDate = oDate;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public BigDecimal getCostPerSqFt() {
        return costPerSqFt;
    }

    public void setCostPerSqFt(BigDecimal costPerSqFt) {
        this.costPerSqFt = costPerSqFt;
    }

    public BigDecimal getLabCostPerSqFt() {
        return labCostPerSqFt;
    }

    public void setLabCostPerSqFt(BigDecimal laborCostPerSqFt) {
        this.labCostPerSqFt = laborCostPerSqFt;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }
    
    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getLabCost() {
        return labCost;
    }

    public void setLabCost(BigDecimal labCost) {
        this.labCost = labCost;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost (BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

   @Override
    public String toString() {
        return "Order #: " + orderNum + " | Name: " + customer;
    } 

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.orderNum;
        hash = 83 * hash + Objects.hashCode(this.customer);
        hash = 83 * hash + Objects.hashCode(this.state);
        hash = 83 * hash + Objects.hashCode(this.taxRate);
        hash = 83 * hash + Objects.hashCode(this.product);
        hash = 83 * hash + Objects.hashCode(this.costPerSqFt);
        hash = 83 * hash + Objects.hashCode(this.labCostPerSqFt);
        hash = 83 * hash + Objects.hashCode(this.area);
        hash = 83 * hash + Objects.hashCode(this.materialCost);
        hash = 83 * hash + Objects.hashCode(this.labCost);
        hash = 83 * hash + Objects.hashCode(this.tax);
        hash = 83 * hash + Objects.hashCode(this.totalCost);
        hash = 83 * hash + Objects.hashCode(this.oDate);
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
        final Order other = (Order) obj;
        if (this.orderNum != other.orderNum) {
            return false;
        }
        if (!Objects.equals(this.customer, other.customer)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        if (!Objects.equals(this.taxRate, other.taxRate)) {
            return false;
        }
        if (!Objects.equals(this.costPerSqFt, other.costPerSqFt)) {
            return false;
        }
        if (!Objects.equals(this.labCostPerSqFt, other.labCostPerSqFt)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.materialCost, other.materialCost)) {
            return false;
        }
        if (!Objects.equals(this.labCost, other.labCost)) {
            return false;
        }
        if (!Objects.equals(this.tax, other.tax)) {
            return false;
        }
        if (!Objects.equals(this.totalCost, other.totalCost)) {
            return false;
        }
        if (!Objects.equals(this.oDate, other.oDate)) {
            return false;
        }
        return true;
    }
}