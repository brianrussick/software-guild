package com.superherosightingsspringmvc.controller;

import com.superherosightingsspringmvc.dto.Org;
import com.superherosightingsspringmvc.dto.SuperHeroSuperVill;
import com.superherosightingsspringmvc.service.ServiceLayer;
import java.util.ArrayList;
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
public class OrgController {    
    ServiceLayer service;

    @Inject
    public OrgController(ServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = "/createOrganization", method = RequestMethod.POST)
    public String createOrganization(HttpServletRequest request, Model model, String[] superHVIdList) {      
        Org org = new Org();
        org.setName(request.getParameter("name"));
        org.setDescription(request.getParameter("description"));
        org.setAddress(request.getParameter("address"));
        org.setPhoneNumber(request.getParameter("phoneNumber"));
      
        List<Integer> superHVIdList2 = new ArrayList<>();
      
        if (superHVIdList == null) {
        } else {
            for (String currentSuperId : superHVIdList) {
                int currentId = Integer.parseInt(currentSuperId);
                superHVIdList2.add(currentId);
            }
        }     
        org.setSuperHVIdList(superHVIdList2);
        service.addOrg(org); // saves organization into database
        return "redirect:orgs";    
    }

    @RequestMapping(value = "/displayEditOrganization", method = RequestMethod.GET)
    public String displayEditOrganization(HttpServletRequest request, Model model) {
        String organizationIdParameter = request.getParameter("organizationId");

        int orgId = Integer.parseInt(organizationIdParameter);
        Org org = service.getOrg(orgId);
        List<SuperHeroSuperVill> superList = service.getSuperHeroSuperVillAll();
        model.addAttribute("superHVList", superList);
        model.addAttribute("organization", org);
        return "orgEdit";
    }

    @RequestMapping(value = "/orgEdit", method = RequestMethod.POST)
    public String editOrganization(@ModelAttribute("organization") Org org) {
        service.updateOrg(org);
        return "redirect:orgs";
    }

    @RequestMapping(value = "/orgs", method = RequestMethod.GET)
    public String displayOrganizationsPage(Model model) {
        List<Org> organizationList = service.getOrgAll();
        List<SuperHeroSuperVill> superList = service.getSuperHeroSuperVillAll();
        model.addAttribute("orgList", organizationList);
        model.addAttribute("superHVList", superList);
        return "orgs";
    }

    @RequestMapping(value = "/orgInfo", method = RequestMethod.GET)
    public String displayOrganizationDetails(HttpServletRequest request, Model model) {
        String organizationIdParameter = request.getParameter("organizationId");

        int orgId = Integer.parseInt(organizationIdParameter);
        Org org = service.getOrg(orgId);       
        List<Integer> superHVIdList = org.getSuperHVIdList();       
        List<SuperHeroSuperVill> superList = new ArrayList<>();       
        superHVIdList.stream().map((currentId) -> service.getSuperHeroSuperVill(currentId)).forEachOrdered((superHV) -> {
            superList.add(superHV);
        });
        model.addAttribute("organization", org);       
        model.addAttribute("superHVList",superList);
        return "orgInfo";
    }

    @RequestMapping(value = "/deleteOrganization", method = RequestMethod.GET)
    public String deleteOrganization(HttpServletRequest request) {
        String organizationIdParameter = request.getParameter("organizationId");

        int orgId = Integer.parseInt(organizationIdParameter);
        service.removeOrg(orgId);
        return "redirect:orgs";
    }
}