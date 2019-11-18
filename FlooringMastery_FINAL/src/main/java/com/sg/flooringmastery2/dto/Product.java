package com.sg.flooringmastery2.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author brian russick
 */
public class Product {
    private String prodType;                            // product type
    private BigDecimal labCostPerSqFt;                 // labor cost per sq ft
    private BigDecimal prodCostPerSqFt;               // product cost per sq ft

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }
    
    public BigDecimal getLabCostPerSqFt() {
        return labCostPerSqFt;
    }

    public void setLabCostPerSqFt(BigDecimal LabCostPerSqFt) {
        this.labCostPerSqFt = LabCostPerSqFt;
    }
    public BigDecimal getProdCostPerSqFt() {
        return prodCostPerSqFt;
    }

    public void setProdCostPerSqFt(BigDecimal prodCostPerSqFt) {
        this.prodCostPerSqFt = prodCostPerSqFt;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.prodType);
        hash = 67 * hash + Objects.hashCode(this.labCostPerSqFt);
        hash = 67 * hash + Objects.hashCode(this.prodCostPerSqFt);
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
        final Product other = (Product) obj;
        if (!Objects.equals(this.prodType, other.prodType)) {
            return false;
        }
        if (!Objects.equals(this.labCostPerSqFt, other.labCostPerSqFt)) {
            return false;
        }
        if (!Objects.equals(this.prodCostPerSqFt, other.prodCostPerSqFt)) {
            return false;
        }
        return true;
    }
}