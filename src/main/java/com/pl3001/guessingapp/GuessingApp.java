//Author Developer
//Version 5
package com.pl3001.guessingapp;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GuessingApp {

    public static void main(String[] args) throws InvalidInputException {

    Scanner scanner = new Scanner(System.in);

    System.out.println("===============================");
    System.out.println("Welcome to the Guessing App");
    System.out.println("===============================\n");

    /*
     * Player name is captured once
     * and stored along with game results.
     */
    System.out.print("Enter Player Name: ");
    String player = scanner.nextLine();

    GameConfig config = new GameConfig();
    config.showRules();

    int attempts = 0;
    int hintsUsed = 0;

    /*
     * Tracks whether the player
     * successfully guessed the number.
     */
    boolean win = false;
	


/*
 * Game loop runs until the player
 * exhausts the maximum attempts.
 */
while (attempts < config.getMaxAttempts()) {

  

    System.out.print("Enter your guess: ");

    int guess = ValidationService.validateInput(scanner.nextLine());
    attempts++;

    String result = GuessValidator.validateGuess(
            guess, config.getTargetNumber());

    if (!"CORRECT".equals(result) && hintsUsed < config.getMaxHints()) {
        hintsUsed++;
        System.out.println(
                HintService.generateHint(
                        config.getTargetNumber(), hintsUsed)
        );
    }

    System.out.println(result);

    if ("CORRECT".equals(result)) {
        
        break;
    }
}

StorageService.saveResult(player, attempts, win);

}
}