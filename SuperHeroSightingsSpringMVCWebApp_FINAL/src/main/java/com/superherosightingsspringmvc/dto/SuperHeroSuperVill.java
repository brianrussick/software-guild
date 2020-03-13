package com.superherosightingsspringmvc.dto;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author brian russick
 */
public class SuperHeroSuperVill {  
    private int id;
    private String name;
    private String description;
    private String image;
    private List<Integer> superPowerIdList;
    private List<Integer> orgIdList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public List<Integer> getSuperPowerIdList() {
        return superPowerIdList;
    }

    public void setSuperPowerIdList(List<Integer> superPowerIdList) {
        this.superPowerIdList = superPowerIdList;
    }

    public List<Integer> getOrgIdList() {
        return orgIdList;
    }

    public void setOrgIdList(List<Integer> orgIdList) {
        this.orgIdList = orgIdList;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.description);
        hash = 29 * hash + Objects.hashCode(this.image);
        hash = 29 * hash + Objects.hashCode(this.superPowerIdList);
        hash = 29 * hash + Objects.hashCode(this.orgIdList);
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
        final SuperHeroSuperVill other = (SuperHeroSuperVill) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.superPowerIdList, other.superPowerIdList)) {
            return false;
        }
        if (!Objects.equals(this.orgIdList, other.orgIdList)) {
            return false;
        }
        return true;
    }    
}