package com.superherosightingsspringmvc.dto;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author brian russick
 */
public class SuperPower {
    private int id;
    private String name;
    private String description;
    private List<Integer> superHVIdList; 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getSuperHVIdList() {
        return superHVIdList;
    }

    public void setSuperHVIdList(List<Integer> superHVIdList) {
        this.superHVIdList = superHVIdList;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.description);
        hash = 67 * hash + Objects.hashCode(this.superHVIdList);
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
        final SuperPower other = (SuperPower) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.superHVIdList, other.superHVIdList)) {
            return false;
        }
        return true;
    } 
}