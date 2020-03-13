package com.superherosightingsspringmvc.controller;

import com.superherosightingsspringmvc.dto.SuperSighting;
import com.superherosightingsspringmvc.service.ServiceLayer;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author brian russick
 */
@Controller
public class IndexController {  
    ServiceLayer service;
    
    // instantiate Spring service Layer
    public IndexController(ServiceLayer service) { 
        this.service = service;             
    } 

    // display the top ten most recent super sightings
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String displayIndexPage(Model model) {
        List<SuperSighting> sightingList = service.getTopSuperSightings();
        model.addAttribute("sightingList", sightingList);
        return "index"; // returns jsp
    }
}