package com.superherosightingsspringmvc.service;

import com.superherosightingsspringmvc.dto.Location;
import com.superherosightingsspringmvc.dto.Org;
import com.superherosightingsspringmvc.dto.SuperPower;
import com.superherosightingsspringmvc.dto.SuperSighting;
import com.superherosightingsspringmvc.dto.SuperHeroSuperVill;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author brian russick
 */
public interface ServiceLayer {
    
    public void addOrg(Org org);
    
    public Org getOrg(int orgId);
    
    public List<Org> getOrgAll();
    
    public void updateOrg(Org org);
    
    public void removeOrg(int orgId);
    
    public void addLocation(Location loca);
    
    public Location getLocation(int locaId);
    
    public List<Location> getLocationAll();
    
    public void updateLocation(Location loca);
    
    public void removeLocation(int locaId);
    
    public void addSuperPower(SuperPower superP);
    
    public SuperPower getSuperPower(int superPowerId);
    
    public List<SuperPower> getSuperPowerAll();
    
    public void updateSuperPower(SuperPower superP);
    
    public void removeSuperPower(int superPowerId);
    
    public void addSuperSighting(SuperSighting sighting);
    
    public SuperSighting getSuperSighting(int superSightingId);
    
    public List<SuperSighting> getNewSuperSighting();
    
    public List<SuperSighting> getSuperSightingsAll();

    public List<SuperSighting> getTopSuperSightings();
    
    public List<SuperHeroSuperVill> getTopSuperHeros();
    
    public void updateSuperSighting(SuperSighting sighting);
    
    public void removeSuperSighting(int superSightingId);

    public void addSuperHeroSuperVill(SuperHeroSuperVill superHV);
    
    public SuperHeroSuperVill getSuperHeroSuperVill(int superHVId);
    
    public List<SuperHeroSuperVill> getSuperHeroSuperVillAll();
    
    public void removeSuperHeroSuperVill(int superHVId);
    
    public void updateSuperHeroSuperVill(SuperHeroSuperVill superHV);
    
    public ResponseEntity<InputStreamResource> getHeroImage(String filename) throws IOException;
}