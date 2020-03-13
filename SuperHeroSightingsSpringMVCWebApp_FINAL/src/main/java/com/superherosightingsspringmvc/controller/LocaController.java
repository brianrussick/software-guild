package com.superherosightingsspringmvc.controller;

import com.superherosightingsspringmvc.dto.Location;
import com.superherosightingsspringmvc.service.ServiceLayer;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author brian russick
 */
@Controller
public class LocaController {
    ServiceLayer service;

    @Inject
    public LocaController(ServiceLayer service) {
        this.service = service;
    }
    
    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    public String displayLocationsPage(Model model) {
        List<Location> locationList = service.getLocationAll();
        model.addAttribute("locaList", locationList); // access on jsp
        return "locations"; // returns jsp
    }
    
    @RequestMapping(value = "/locationInfo", method = RequestMethod.GET)
    public String displayLocationDetails(HttpServletRequest request, Model model) {
        String locaIdParameter = request.getParameter("locaId");
        int locaId = Integer.parseInt(locaIdParameter);
        Location location = service.getLocation(locaId);
        model.addAttribute("location", location);
        return "locationInfo";
    }
    
    @RequestMapping(value = "/deleteLocation", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request) {      
        String locaIdParameter = request.getParameter("locationId");
        int locaId = Integer.parseInt(locaIdParameter);
        service.removeLocation(locaId);
        return "redirect:locations"; // refresh page to get current list
    }
    
    @RequestMapping(value = "/locationEdit", method = RequestMethod.POST)
    public String editLocation(@ModelAttribute("location") Location location) {
        service.updateLocation(location);
        return "redirect:locations";
    }
    
    @RequestMapping(value = "/createLocation", method = RequestMethod.POST)
    public String createLocation(HttpServletRequest request, Model model) {
        Boolean bool = true;
        String errMsg = "Must input valid #";        
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String address = request.getParameter("address");
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");
        
        Location loca = new Location();
        loca.setName(name);
        loca.setDescription(description);
        loca.setAddress(address);

        try {
            loca.setLatitude(Double.parseDouble(latitude));
        } catch (NumberFormatException e) {
            bool = false;
            model.addAttribute("latitude", errMsg);
            latitude = errMsg;
            model.addAttribute("name", name);
            model.addAttribute("description", description);
            model.addAttribute("address", address);
            model.addAttribute("longitude", longitude);
            }
        try {
            loca.setLongitude(Double.parseDouble(longitude));
        } catch (NumberFormatException e) {
            bool = false;
            model.addAttribute("longitude", errMsg);
            model.addAttribute("name", name);
            model.addAttribute("description", description);
            model.addAttribute("address", address);  
            model.addAttribute("latitude", latitude);
        }
        if (!bool) {
            List<Location> locationList = service.getLocationAll();
            model.addAttribute("locaList", locationList);
            return "locations"; //return jsp
        } else {
            service.addLocation(loca);
            return "redirect:locations";
        }
    }
    
    @RequestMapping(value = "/displayEditLocation", method = RequestMethod.GET)
    public String displayEditLocation(HttpServletRequest request, Model model) {      
        String locaIdParameter = request.getParameter("locationId");
        int locaId = Integer.parseInt(locaIdParameter);
        Location location = service.getLocation(locaId);
        model.addAttribute("location", location);
        return "locationEdit";
    }    
}