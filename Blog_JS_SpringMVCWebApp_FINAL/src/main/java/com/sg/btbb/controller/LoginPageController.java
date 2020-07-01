package com.sg.btbb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author brian russick
 */
@Controller
public class LoginPageController {
    
    @GetMapping("/login")
    public String loginForm() {
        return "loginPage";
    }
}