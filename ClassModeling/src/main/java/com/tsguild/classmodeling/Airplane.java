package com.tsguild.classmodeling;

/**
 *
 * @author brian russick
 */
public class Airplane {
    private String communicationSystem;
    private String acquisition;
    private String airplaneTracking;
    private String route;
    private String pathDetermining;
    private String acknowledgmentReceiving;
    private boolean landingApproved;

public Airplane(String communicationSystem, String acquisition){
        this.communicationSystem = communicationSystem;
        this.acquisition = acquisition;
}

public void landingApproved(boolean landingApproved){
        
        System.out.println("All set to land pilot, welcome home!"); //boolean = true
        
        System.out.println("YOU ARE NOT CLEAR .. REPEAT .. NOT CLEAR FOR LANDING PILOT!"); //boolean = false
    } 
}