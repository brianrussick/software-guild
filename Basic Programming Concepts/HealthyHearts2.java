package com.tsguild.healthyhearts2;
import java.util.Scanner;

/**
 *
 * @author brian russick
 */
public class HealthyHearts2 {
    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);

	int age;
	int maxHeartRate;
	double bottomTarget; 
	double topTarget;

	System.out.print("What is your age? ");
	age = Integer.parseInt(sc.nextLine());
	maxHeartRate = 220 - age;
        bottomTarget = maxHeartRate * .5;
        topTarget = maxHeartRate * .85;
	System.out.println("Your maximum heart rate should be " + maxHeartRate + " beats per minute");
	System.out.println("Your target HR Zone is " + Math.round(bottomTarget) + " - " + Math.round(topTarget) + " beats per minute");
    }
}