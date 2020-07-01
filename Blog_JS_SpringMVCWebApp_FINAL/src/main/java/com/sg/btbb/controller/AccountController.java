package com.sg.btbb.controller;

import com.sg.btbb.dao.BTBBdao;
import com.sg.btbb.dao.BTBBPersistenceException;
import com.sg.btbb.model.DesignationRole;
import com.sg.btbb.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author brian russick
 */
@Controller
public class AccountController {    
    @Inject 
    private BTBBdao dao;
    @Inject
    private PasswordEncoder encoder;
    
    @PostMapping("/user")
    public String createUser(HttpServletRequest request) throws BTBBPersistenceException {
        String clearPw = request.getParameter("password");
        String hashPw = encoder.encode(clearPw);

        User newUser = new User();
        newUser.setPassword(hashPw);
        
        DesignationRole defaultDesignationRole = dao.getRoleById(1);
        List<DesignationRole> designationRoles = new ArrayList<>();
        designationRoles.add(defaultDesignationRole);
        newUser.setDesignationRoles(designationRoles);
        
        newUser.setName(request.getParameter("name"));
        newUser.setEmail(request.getParameter("email"));
        newUser.setUserName(request.getParameter("userName"));
        dao.addUser(newUser);
        
        return "loginPage";
    }    
}