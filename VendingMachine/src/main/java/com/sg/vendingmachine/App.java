package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VMController;
import com.sg.vendingmachine.dao.VMDaoFileImpl;
import com.sg.vendingmachine.service.VMServiceLayerImpl;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VMView;
import com.sg.vendingmachine.dao.VMDao;
import com.sg.vendingmachine.service.VMServiceLayer;

/**
 *
 * @author brian russick                 // logic that configures, instantiates, 
                                        // and assembles the classes
 */
public class App {
    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImpl();
        VMView view = new VMView(io);
        VMDao dao = new VMDaoFileImpl();
        VMServiceLayer service = new VMServiceLayerImpl(dao);
        VMController controller = new VMController(service, view);
        controller.run(); // start app
    }
}