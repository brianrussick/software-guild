package com.superherosightingsspringmvc.controller;

import com.superherosightingsspringmvc.Commons.CustomConstants;
import com.superherosightingsspringmvc.dto.Org;
import com.superherosightingsspringmvc.dto.SuperPower;
import com.superherosightingsspringmvc.dto.SuperHeroSuperVill;
import com.superherosightingsspringmvc.service.ServiceLayer;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author brian russick
 */
@Controller
public class SuperHVController { 
    ServiceLayer service;

    @Inject
    public SuperHVController(ServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = "/createSuper", method = RequestMethod.POST, consumes = "multipart/form-data")
    public String createSuper(HttpServletRequest request, Model model, String[] organizationIdList, String[] powerIdList,
        @RequestParam("image") MultipartFile image) {
        String unique = String.valueOf(new Timestamp(System.currentTimeMillis()).getTime());

        if (saveProductImage(image,unique)){
            SuperHeroSuperVill superHV = new SuperHeroSuperVill();
            superHV.setName(request.getParameter("name"));
            superHV.setDescription(request.getParameter("description"));
            superHV.setImage(CustomConstants.ImageUrl+unique+image.getOriginalFilename());

            List<Integer>
            orgIdList2 = new ArrayList<>();

            if (organizationIdList == null) {
            } else {
                for (String currentOrganizationId : organizationIdList) {
                    int currentId = Integer.parseInt(currentOrganizationId);
                    orgIdList2.add(currentId);
                }
            }
            superHV.setOrgIdList(orgIdList2);
            List<Integer> superPowerIdList2 = new ArrayList<>();
            if (powerIdList == null) {
            } else {
                for (String currentPowerId : powerIdList) {
                    int currentId = Integer.parseInt(currentPowerId);
                    superPowerIdList2.add(currentId);
                }
            }
            superHV.setSuperPowerIdList(superPowerIdList2);
            service.addSuperHeroSuperVill(superHV);
        }
        return "redirect:supersHV";
    }

    // save image method
    public Boolean saveProductImage(MultipartFile file, String unique  ){
        try {
            String UPLOADED_FOLDER_NEW = CustomConstants.UploadedFolder;
            File dir = new File(UPLOADED_FOLDER_NEW);
            dir.setExecutable(true);
            dir.setReadable(true);
            dir.setWritable(true);

            if (!dir.exists()){
                dir.mkdirs();
            }
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER_NEW + unique+ file.getOriginalFilename());
            Files.write(path, bytes);
        }
        catch (IOException e){
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/displayEditSuper", method = RequestMethod.GET)
    public String displayEditSuper(HttpServletRequest request, Model model) {
        String superHVIdParameter = request.getParameter("superHVId");

        int superHVId = Integer.parseInt(superHVIdParameter);
        SuperHeroSuperVill superHV = service.getSuperHeroSuperVill(superHVId);      
        List<Org> organizationList = service.getOrgAll();      
        List<SuperPower> powerList = service.getSuperPowerAll();
        model.addAttribute("super", superHV);       
        model.addAttribute("orgList", organizationList);
        model.addAttribute("superPowerList", powerList);
        return "superHVEdit";
    }

    @RequestMapping(value = "/superHVEdit", method = RequestMethod.POST)
    public String editSuper(@ModelAttribute("super") SuperHeroSuperVill superHV) {
        service.updateSuperHeroSuperVill(superHV);
        return "redirect:supersHV";
    }

    //get image call
    @RequestMapping(value = "/superheros/image/{filename:.*}", method = RequestMethod.GET)
    public  ResponseEntity<InputStreamResource> getImage(@PathVariable("filename") String filename)
            throws IOException {
        return service.getHeroImage(filename);
    }

    @RequestMapping(value = "/supersHV", method = RequestMethod.GET)
    public String displaySupersPage(Model model) {

        List<SuperHeroSuperVill> superList = service.getSuperHeroSuperVillAll();
        List<Org> organizationList = service.getOrgAll();
        List<SuperPower> powerList = service.getSuperPowerAll();
        model.addAttribute("superHVList", superList);
        model.addAttribute("orgList", organizationList);
        model.addAttribute("superPowerList", powerList);
        return "supersHV";
    }

    @RequestMapping(value = "/superHVInfo", method = RequestMethod.GET)
    public String displaySuperDetails(HttpServletRequest request, Model model) {
        String superHVIdParameter = request.getParameter("superHVId");

        int superHVId = Integer.parseInt(superHVIdParameter);
        SuperHeroSuperVill superHV = service.getSuperHeroSuperVill(superHVId);        
        List<Integer> orgIdList = superHV.getOrgIdList();       
        List<Org> organizationList = new ArrayList<>();        
        orgIdList.stream().map((currentId) -> service.getOrg(currentId)).forEachOrdered((org) -> {
            organizationList.add(org);
        });       
        List<Integer> superPowerIdList = superHV.getSuperPowerIdList();       
        List<SuperPower> powerList = new ArrayList<>();       
        superPowerIdList.stream().map((currentId) -> service.getSuperPower(currentId)).forEachOrdered((power) -> {
            powerList.add(power);
        });
        model.addAttribute("superHV", superHV);       
        model.addAttribute("orgList", organizationList);       
        model.addAttribute("superPowerList", powerList);
        return "superHVInfo";
    }

    @RequestMapping(value = "/deleteSuper", method = RequestMethod.GET)
    public String deleteSuper(HttpServletRequest request) {
        String superHVIdParameter = request.getParameter("superHVId");

        int superHVId = Integer.parseInt(superHVIdParameter);
        service.removeSuperHeroSuperVill(superHVId);
        return "redirect:supersHV";
    }  
}