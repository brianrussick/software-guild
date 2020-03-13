package com.superherosightingsspringmvc.controller;

import com.superherosightingsspringmvc.dto.Location;
import com.superherosightingsspringmvc.dto.SuperSighting;
import com.superherosightingsspringmvc.dto.SuperHeroSuperVill;
import com.superherosightingsspringmvc.service.ServiceLayer;
import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author brian russick
 */
@Controller
public class SuperSightingController {
    
    ServiceLayer service;

    @Inject
    public SuperSightingController(ServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = "/createSighting", method = RequestMethod.POST)
    public String createSighting(HttpServletRequest request, Model model, String superHVId, String locationId) {       
        SuperSighting sighting = new SuperSighting();
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        sighting.setDate(LocalDate.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth()));
           
        int superHVId2 = Integer.parseInt(superHVId);                
        sighting.setSuperHVId(superHVId2);
      
        int locaId2 = Integer.parseInt(locationId);                 
        sighting.setLocaId(locaId2);
        service.addSuperSighting(sighting);
        return "redirect:superSightings";     
    }

    @RequestMapping(value = "/superSightings", method = RequestMethod.GET)
    public String displaySightingsPage(Model model) {
        List<SuperSighting> sightingList = service.getSuperSightingsAll();
        List<SuperHeroSuperVill> superList = service.getSuperHeroSuperVillAll();
        List<Location> locationList = service.getLocationAll();
        model.addAttribute("superSightingList", sightingList);
        model.addAttribute("superHVList", superList);
        model.addAttribute("locaList", locationList);
        return "superSightings";
    }


    @RequestMapping(value = "/superSightingInfo", method = RequestMethod.GET)
    public String displaySightingDetails(HttpServletRequest request, Model model) {
        String sightingIdParameter = request.getParameter("sightingId");

        int sightingId = Integer.parseInt(sightingIdParameter);
        SuperSighting sighting = service.getSuperSighting(sightingId);
        
        int superHVId = sighting.getSuperHVId();       
        SuperHeroSuperVill superHV = service.getSuperHeroSuperVill(superHVId);
        
        int locaId = sighting.getLocaId();       
        Location loca = service.getLocation(locaId);
        model.addAttribute("sighting", sighting);        
        model.addAttribute("superHV", superHV);       
        model.addAttribute("location", loca);
        return "superSightingInfo";
    }

    @RequestMapping(value = "/deleteSighting", method = RequestMethod.GET)
    public String deleteSighting(HttpServletRequest request) {
        String sightingIdParameter = request.getParameter("sightingId");

        int sightingId = Integer.parseInt(sightingIdParameter);
        service.removeSuperSighting(sightingId);
        return "redirect:superSightings";
    }

    @RequestMapping(value = "/displayEditSighting", method = RequestMethod.GET)
    public String displayEditSighting(HttpServletRequest request, Model model) {      
        String sightingIdParameter = request.getParameter("sightingId");

        int sightingId = Integer.parseInt(sightingIdParameter);
        SuperSighting sighting = service.getSuperSighting(sightingId);        
        List<SuperHeroSuperVill> superList = service.getSuperHeroSuperVillAll();       
        List<Location> locationList = service.getLocationAll();
        model.addAttribute("sighting", sighting);       
        model.addAttribute("superHVList", superList);       
        model.addAttribute("locaList", locationList);
        return "superSightingEdit";
    }

    @RequestMapping(value = "/superSightingEdit", method = RequestMethod.POST)
    public String editSighting(HttpServletRequest request, Model model, String superHVId, String locationId, String id) {
        SuperSighting sighting = new SuperSighting();
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        sighting.setDate(LocalDate.of(
                date.getYear(), 
                date.getMonthValue(), 
                date.getDayOfMonth()));

        int superHVId2 = Integer.parseInt(superHVId);
        sighting.setSuperHVId(superHVId2);

        int locaId2 = Integer.parseInt(locationId);
        sighting.setLocaId(locaId2);

        int sightingId = Integer.parseInt(id);
        sighting.setId(sightingId);

        service.updateSuperSighting(sighting);
        return "redirect:superSightings";
    }
}