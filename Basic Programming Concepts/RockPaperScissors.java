package com.tsguild.rockpaperscissors;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author brian russick
 */

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        
        String retryGame = " ";
        String calculateWinner = " ";
        int playerOneWins = 0;
        int playerTwoWins = 0;
        int playerOnePicks;
        int playerTwoPicks;
        int tieGame = 0;
        int roundsNum = 0;
        
        // integer variables to hold user and computer inputs 
        // 1 = Rock 
        // 2 = Paper 
        // 3 = Scissors
        
        do {
            System.out.print("Please enter the number of rounds (1 - 10) of Rock Paper Scissors you would like to play: ");
            roundsNum = sc.nextInt();
            sc.nextLine();
            if (roundsNum < 1 || roundsNum > 10){
                System.out.println("Invalid Input!! | Number must be between '1 - 10' ");
            } else { 
                for (int i = 0; i < roundsNum; i++) { // game will continue until user specificed # of rounds is met
                    do { 
                        playerOnePicks = Pick();
                        } while (playerOnePicks < 1 || playerOnePicks > 3);
                        playerTwoPicks = random.nextInt(3)+ 1; // shift # range '0 - 2' to '1 - 3'
                        Pick2(playerTwoPicks);
                        if (playerOnePicks == playerTwoPicks){
                        System.out.println("It's a TIE! ");
                        tieGame++;
                    } else if (playerOnePicks == 1) {
                        if (playerTwoPicks == 2) {
                            System.out.println("Computer wins!");
                            playerTwoWins++;
                        } else {
                            System.out.println("You won!");
                            playerOneWins++;
                        }
                    } else if (playerOnePicks == 2) {
                        if (playerTwoPicks == 3) {
                            System.out.println("Computer wins!");
                            playerTwoWins++;
                        } else {
                            System.out.println("You won!");
                            playerOneWins++;
                        }
                            } else {
                        if (playerTwoPicks == 1) {
                            System.out.println("Computer wins!");
                            playerTwoWins++;
                        } else {
                            System.out.println("You won!");
                            playerOneWins++;
                        }
                    }
                }
                System.out.println("There were " + tieGame + " rounds tied.");
                System.out.println("You won " + playerOneWins + " rounds.");
                System.out.println("The Computer won " + playerTwoWins + " rounds.");
                calculateWinner = calculateWinLossTie (playerOneWins, playerTwoWins);
                System.out.println(calculateWinner); 
                }
                System.out.print("Do you want to play again? (y/n) ");
                retryGame = sc.nextLine();
        } while (retryGame.equals("y"));
        System.out.println("\nThanks for playing!");  
    }
    public static String calculateWinLossTie (int playerOne, int playerTwo) {
        String finalOutome = " ";
        if (playerOne == playerTwo) {
            finalOutome = "Tie Game! How Frustrating!!!";
        } else if (playerOne > playerTwo) {
            finalOutome = "You are the WINNER!";
        } else {
            finalOutome = "You lost sorry! :( ";
            }
        return finalOutome;   
        }
    public static int Pick(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Decide between rock, paper, or scissors: " );
        String Pick = sc.nextLine();
        int pick = 0;
        switch (Pick) {
            case "rock": pick = 1; break;
            case "paper": pick = 2; break;
            case "scissors": pick = 3; break;
        }
        return pick;    
    }
    public static void Pick2 (int playerTwoPicks) {
       String pick= " ";
       
         switch (playerTwoPicks) {
            case 1: pick = "rock"; break;
            case 2: pick = "paper"; break;
            case 3: pick = "scissors"; break;
        }
        System.out.println("Computer picks " + pick + "." );
    }
}