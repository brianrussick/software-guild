package com.sg.flooringmastery2.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author brian russick
 */
public class UserIOConsoleImpl implements UserIO {      //implements interface

    Scanner sc = new Scanner(System.in);
                       // implement override annotations parsing scanner input
    @Override
    public void print(String prompt) {
        System.out.println(prompt);
    }

    @Override
    public double readDouble(String prompt) {
        print(prompt);
        String input = sc.next();
        double inputDouble = Double.parseDouble(input);
        
        return inputDouble;
         
    }
    
    @Override
    public double readDouble(String prompt, double min, double max) {
        
        double valueDouble = Double.parseDouble(prompt);
        while (true) {
            if (valueDouble < min || valueDouble > max) {
                System.out.println("Please enter a value between " + min + " - " + max + ".");
                prompt = sc.next();
                valueDouble = Double.parseDouble(prompt);
            } else if (valueDouble > min && valueDouble < max) {
                return valueDouble;
            }
        }
    }

    @Override
    public float readFloat(String prompt) {
        float valueFloat = Float.parseFloat(prompt);
        return valueFloat;
    }
    
    @Override
    public float readFloat(String prompt, float min, float max) {
        float valueFloat = Float.parseFloat(prompt);
        while (true) {
            if (valueFloat < min || valueFloat > max) {
                System.out.println("Please enter a value between " + min + " - " + max + ".");
                prompt = sc.next();
                valueFloat = Float.parseFloat(prompt);
            } else if (valueFloat > min && valueFloat < max) {
                return valueFloat;
            }
        }
    }    
    
@Override
    public int readInt(String prompt) {
        int value = 0;
        prompt = sc.nextLine();
        value = Integer.parseInt(prompt);
        return value;
    }
    
    @Override
    public int readInt(String prompt, int min, int max) {
        System.out.println(prompt);
        String inputInt = sc.nextLine();
        int valueInt = Integer.parseInt(inputInt);
        
        while (true) {
            if (valueInt >= min && valueInt <= max) {
                return valueInt;
            }
        }
    }  

    @Override  
    public long readLong(String prompt) {
        long valueLong = Long.parseLong(prompt);
        return valueLong;
    }    

    @Override
    public long readLong(String prompt, long min, long max) {
        System.out.println(prompt);
        String inputInt = sc.nextLine();
        int valueLong = Integer.parseInt(inputInt);
        
        while (true) {
            if (valueLong >= min && valueLong <= max) {
                return valueLong;
            }
        }
    }
    
    @Override
    public BigDecimal readBigDecimal(String prompt) {
        System.out.print(prompt);
        String str = sc.nextLine();
        return new BigDecimal(str);
    }
    
    @Override
    public LocalDate readDate(String prompt) {
        boolean bool = false;
        LocalDate date = LocalDate.now();
        while (!bool) {
            try {
                System.out.print(prompt);
                date = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("MMddyyyy"));
                bool = true;
            } catch (DateTimeParseException e) {
                System.out.print("INVALID DATE FORMAT - PLEASE USE (MMDDYYYY)\n");
                bool = false;
            }
        }
        return date;
    }
    
    @Override
    public String readString(String prompt) {
        System.out.print(prompt);
        String str = sc.nextLine();
        return str;
    }
}