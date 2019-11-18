package com.sg.flooringmastery2;

import com.sg.flooringmastery2.controller.Controller;
import com.sg.flooringmastery2.service.DataValidationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author brian russick
 */
public class App {                       //*************************************
                                        // logic that configures, instantiates 
                                       // and assembles the classes using 
                                      // spring dependency injection
                                     //*****************************
    public static void main(String[] args) throws DataValidationException {    
                            
    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        Controller controller = ctx.getBean("controller", Controller.class);
        
        controller.run();
    }
}