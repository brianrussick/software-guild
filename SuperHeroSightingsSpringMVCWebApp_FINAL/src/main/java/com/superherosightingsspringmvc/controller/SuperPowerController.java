package com.superherosightingsspringmvc.controller;

import com.superherosightingsspringmvc.dto.SuperPower;
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
public class SuperPowerController {
    ServiceLayer service;

    @Inject
    public SuperPowerController(ServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = "/createPower", method = RequestMethod.POST)
    public String createPower(HttpServletRequest request, Model model, String[] superHVIdList) {
        SuperPower power = new SuperPower();
        power.setName(request.getParameter("name"));
        power.setDescription(request.getParameter("description"));
        List<Integer> superHVIdList2 = new ArrayList<>();

        if (superHVIdList == null) {
        } else {
            for (String currentSuperHVId : superHVIdList) {
                int currentId = Integer.parseInt(currentSuperHVId);
                superHVIdList2.add(currentId);
            }
        }
        power.setSuperHVIdList(superHVIdList2);
        service.addSuperPower(power);
        return "redirect:superPowers";
    }

    @RequestMapping(value = "/superPowers", method = RequestMethod.GET)
    public String displayPowersPage(Model model) {

        List<SuperPower> powerList = service.getSuperPowerAll();
        List<SuperHeroSuperVill> superList = service.getSuperHeroSuperVillAll();
        model.addAttribute("superPowerList", powerList);
        model.addAttribute("superHVList", superList);
        return "superPowers";
    }

    @RequestMapping(value = "/displayEditPower", method = RequestMethod.GET)
    public String displayEditPower(HttpServletRequest request, Model model) {
        String powerIdParameter = request.getParameter("powerId");

        int powerId = Integer.parseInt(powerIdParameter);
        SuperPower power = service.getSuperPower(powerId);
        List<SuperHeroSuperVill> superList = service.getSuperHeroSuperVillAll();
        model.addAttribute("superHVList", superList);
        model.addAttribute("Power", power);
        return "superPowerEdit";
    }

    @RequestMapping(value = "/superPowerEdit", method = RequestMethod.POST)
    public String editPower(@ModelAttribute("power") SuperPower power) {
        service.updateSuperPower(power);
        return "redirect:superPowers";
    }

    @RequestMapping(value = "/superPowerInfo", method = RequestMethod.GET)
    public String displayPowerDetails(HttpServletRequest request, Model model) {
        String powerIdParameter = request.getParameter("powerId");

        int powerId = Integer.parseInt(powerIdParameter);
        SuperPower power = service.getSuperPower(powerId);
        List<Integer> superHVIdList = power.getSuperHVIdList();
        List<SuperHeroSuperVill> superList = new ArrayList<>();
        superHVIdList.stream().map((currentId) -> service.getSuperHeroSuperVill(currentId)).forEachOrdered((superHV) -> {
            superList.add(superHV);
        });
        model.addAttribute("superPower", power);
        model.addAttribute("superHVList", superList);
        return "superPowerInfo";
    }

    @RequestMapping(value = "/deletePower", method = RequestMethod.GET)
    public String deletePower(HttpServletRequest request) {
        String powerIdParameter = request.getParameter("powerId");
        int powerId = Integer.parseInt(powerIdParameter);
        service.removeSuperPower(powerId);
        return "redirect:superPowers";
    }
}