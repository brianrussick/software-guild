package com.superherosightingsspringmvc.dao;

import com.superherosightingsspringmvc.dto.Location;
import com.superherosightingsspringmvc.dto.Org;
import com.superherosightingsspringmvc.dto.SuperPower;
import com.superherosightingsspringmvc.dto.SuperSighting;
import com.superherosightingsspringmvc.dto.SuperHeroSuperVill;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author brian russick
 */
public interface SuperHeroSightingsDao {
    
    public void addOrg(Org org);
    
    public Org getOrg(int orgId);
    
    public List<Org> getOrgAll();
    
    public void updateOrg(Org org);
    
    public void removeOrg(int orgId);
    
    public List<Org> getOrgsBySuperHV(SuperHeroSuperVill superHV);
    
    public void addLocation(Location loca);
    
    public Location getLocation(int locaId);
    
    public List<Location> getLocationAll();
    
    public void updateLocation(Location loca);
    
    public void removeLocation(int locaId);
    
    public List<Location> getSuperHVLocations(int superHVId);
    
    public void addSuperPower(SuperPower superP);
    
    public SuperPower getSuperPower(int superPowerId);
    
    public List<SuperPower> getSuperPowerAll();
    
    public void updateSuperPower(SuperPower superP);
    
    public void removeSuperPower(int superPowerId);
    
    public void addSuperSighting(SuperSighting sighting);
    
    public SuperSighting getSuperSighting(int superSightingId);
    
    public List<SuperSighting> getSuperSightingsAll();

    public List<SuperSighting> getTopSuperSightings();

    public List<SuperHeroSuperVill> getTopSuperHeros();
    
    public void updateSuperSighting(SuperSighting sighting);
    
    public void removeSuperSighting(int superSightingId);
    
    public List<SuperSighting> getSuperSightingsByLocation(int locaId);
    
    public List<SuperSighting> getSuperSightingsByDate(LocalDate date);
    
    public void addSuper(SuperHeroSuperVill superHV);
    
    public SuperHeroSuperVill getSuperHeroSuperVill(int superHVId);
    
    public List<SuperHeroSuperVill> getSuperHeroSuperVillAll();
    
    public void updateSuperHeroSuperVill(SuperHeroSuperVill superHV);
    
    public void removeSuperHeroSuperVill(int superHVId);
    
    public List<SuperHeroSuperVill> getSupersHVInOrg(Org org);
    
    public void resetDatabase();

    public List<SuperSighting> getNewSuperSighting();      
}