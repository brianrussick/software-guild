package com.superherosightingsspringmvc.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author brian russick
 */
public class SuperSighting {   
    private int id;
    private LocalDate date;
    private int superHVId;
    private int locaId;
    private String heroName;
    private String heroImage;

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getHeroImage() {
        return heroImage;
    }

    public void setHeroImage(String heroImage) {
        this.heroImage = heroImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getSuperHVId() {
        return superHVId;
    }

    public void setSuperHVId(int superHVId) {
        this.superHVId = superHVId;
    }

    public int getLocaId() {
        return locaId;
    }

    public void setLocaId(int locaId) {
        this.locaId = locaId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
        hash = 83 * hash + Objects.hashCode(this.date);
        hash = 83 * hash + this.superHVId;
        hash = 83 * hash + this.locaId;
        hash = 83 * hash + Objects.hashCode(this.heroName);
        hash = 83 * hash + Objects.hashCode(this.heroImage);
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
        final SuperSighting other = (SuperSighting) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.superHVId != other.superHVId) {
            return false;
        }
        if (this.locaId != other.locaId) {
            return false;
        }
        if (!Objects.equals(this.heroName, other.heroName)) {
            return false;
        }
        if (!Objects.equals(this.heroImage, other.heroImage)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }   
}