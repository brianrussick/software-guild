package com.superherosightingsspringmvc.service;

import com.superherosightingsspringmvc.Commons.CustomConstants;
import com.superherosightingsspringmvc.dao.SuperHeroSightingsDao;
import com.superherosightingsspringmvc.dto.Location;
import com.superherosightingsspringmvc.dto.Org;
import com.superherosightingsspringmvc.dto.SuperPower;
import com.superherosightingsspringmvc.dto.SuperSighting;
import com.superherosightingsspringmvc.dto.SuperHeroSuperVill;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author brian russick
 */
@Service
public class ServiceLayerImpl implements ServiceLayer {   
    SuperHeroSightingsDao dao;
    
    @Inject
    public ServiceLayerImpl(SuperHeroSightingsDao dao) {
        this.dao = dao;
    }

    @Override
    public void addOrg(Org org) {
        dao.addOrg(org);
    }
    
    @Override
    public Org getOrg(int orgId) {
        return dao.getOrg(orgId);
    }
    
    @Override
    public List<Org> getOrgAll() {
        return dao.getOrgAll();
    }
    
    @Override
    public void updateOrg(Org org) {
        dao.updateOrg(org);
    }
    
    @Override
    public void removeOrg(int orgId) {
        dao.removeOrg(orgId);
    }
    
    @Override
    public void addLocation(Location loca) {
        dao.addLocation(loca);
    }
    
    @Override
    public Location getLocation(int locaId) {
        return dao.getLocation(locaId);
    }
    
    @Override
    public List<Location> getLocationAll() {
        return dao.getLocationAll();
    }

    @Override
    public void updateLocation(Location loca) {
        dao.updateLocation(loca);
    }
    
    @Override
    public void removeLocation(int locaId) {
        dao.removeLocation(locaId);
    }
    
    @Override
    public void addSuperPower(SuperPower superP) {
        dao.addSuperPower(superP);
    }
    
    @Override
    public SuperPower getSuperPower(int superPowerId) {
        return dao.getSuperPower(superPowerId);
    }
    
    @Override
    public List<SuperPower> getSuperPowerAll() {
        return dao.getSuperPowerAll();
    }

    @Override
    public void updateSuperPower(SuperPower superP) {
        dao.updateSuperPower(superP);
    }

    @Override
    public void removeSuperPower(int superPowerId) {
        dao.removeSuperPower(superPowerId);
    }
    
    @Override
    public void addSuperSighting(SuperSighting sighting) {
        dao.addSuperSighting(sighting);
    }
    
    @Override
    public SuperSighting getSuperSighting(int superSightingId) {
        return dao.getSuperSighting(superSightingId);
    }
    
    @Override
    public List<SuperSighting> getNewSuperSighting() {
        return dao.getNewSuperSighting();
    }
    
    @Override
    public List<SuperSighting> getSuperSightingsAll() {
        return dao.getSuperSightingsAll();
    }
    
    @Override
    public List<SuperSighting> getTopSuperSightings(){
        return dao.getTopSuperSightings();
    }

    @Override
    public List<SuperHeroSuperVill> getTopSuperHeros(){
        return dao.getTopSuperHeros();
    }
    
    @Override
    public void updateSuperSighting(SuperSighting sighting) {
        dao.updateSuperSighting(sighting);
    }
    
    @Override
    public void removeSuperSighting(int superSightingId) {
        dao.removeSuperSighting(superSightingId);
    }
    
    @Override
    public void addSuperHeroSuperVill(SuperHeroSuperVill superHV) {
        dao.addSuper(superHV);
    }
    
    @Override
    public SuperHeroSuperVill getSuperHeroSuperVill(int superHVId) {
        return dao.getSuperHeroSuperVill(superHVId);
    }

    @Override
    public List<SuperHeroSuperVill> getSuperHeroSuperVillAll() {
        return dao.getSuperHeroSuperVillAll();
    }

    @Override
    public void removeSuperHeroSuperVill(int superHVId) {
        dao.removeSuperHeroSuperVill(superHVId);
    }

    @Override
    public void updateSuperHeroSuperVill(SuperHeroSuperVill superHV) {
        dao.updateSuperHeroSuperVill(superHV);
    }

    @Override
    public ResponseEntity<InputStreamResource> getHeroImage(String filename) throws IOException {

        String filepath = CustomConstants.UploadedFolder+filename;

        File f = new File(filepath);
        Resource file = new UrlResource(f.toURI());
        String mediaType = file.getURL().openConnection().getContentType();
        System.out.println(mediaType);
        return ResponseEntity
                .ok()
                .contentLength(file.contentLength())
                .contentType(
                        MediaType.parseMediaType("image/JPG"))
                .body(new InputStreamResource(file.getInputStream()));
    }
}