package com.tsguild.doggenetics;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author brian russick
 */
public class DogGenetics {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        
        String dogName;
        int bernard;
        int chihuahua;
        int pug;
        int cur;
        int doberman;

        System.out.print("What is your dog's name? ");
        dogName = sc.nextLine();
        System.out.println("Well then, I have this highly reliable report on " + dogName + "'s prestigious background right here.\n");
        System.out.println(dogName + " is: \n");
        
        doberman = random.nextInt(20);
        cur = random.nextInt(20);
        pug = random.nextInt(20);
        chihuahua = random.nextInt(20);
        bernard = random.nextInt(1) + 100 - chihuahua - pug - cur - doberman;
        
        System.out.println(bernard + "% St. Bernard");
        System.out.println(chihuahua + "% Chihuahua");
        System.out.println(pug + "% Dramatic RedNosed Asian Pug");
        System.out.println(cur + "% Common Cur");
        System.out.println(doberman + "% King Doberman\n");
        System.out.print("Wow, that's QUITE the dog!");
    }
}