package com.sg.vendingmachine.ui;

/**
 *
 * @author brian russick
 */
public interface UserIO {   // defines the methods that must be implemented by 
                            // any class that wants to interact with the ui
    void print(String msg); 
    double readDouble(String prompt);
    double readDouble(String prompt, double min, double max);
    float readFloat(String prompt);
    float readFloat(String prompt, float min, float max);
    int readInt(String prompt);
    int readInt(String prompt, int min, int max);
    long readLong(String prompt);
    String readString(String prompt);
}