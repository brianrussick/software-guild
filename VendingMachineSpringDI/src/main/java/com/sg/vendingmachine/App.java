package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VMController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author brian russick                 // logic that configures, instantiates, 
                                        // and assembles the classes
 */
public class App {
    public static void main(String[] args) {
    //    UserIO io = new UserIOConsoleImpl();
    //    VMView view = new VMView(io);
    //    VMDao dao = new VMDaoFileImpl();
    //    VMServiceLayer service = new VMServiceLayerImpl(dao);
    //    VMController controller = new VMController(service, view);
    //    controller.run(); // start app
    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        VMController controller = ctx.getBean("controller", VMController.class);
        
        controller.run();
    }
}