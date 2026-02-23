//Author Developer
//Version 4
package com.pl3001.guessingapp;

import java.util.Scanner;

public class GuessingApp {

    public static void main(String[] args) throws InvalidInputException {

        GameConfig config = new GameConfig();
        Scanner scanner = new Scanner(System.in);

        int attempts = 0;
        int hintsUsed = 0;

        while (attempts < config.getMaxAttempts()) {

            System.out.print("Enter your guess: ");
            int guess = ValidationService.validateInput(scanner.nextLine());
            attempts++;

            String result = GuessValidator.validateGuess(
                    guess, config.getTargetNumber());

            if (!"CORRECT".equals(result)
                    && hintsUsed < config.getMaxHints()) {

                hintsUsed++;
                System.out.println(
                        HintService.generateHint(
                                config.getTargetNumber(),
                                hintsUsed));
            }

            System.out.println(result);

            if ("CORRECT".equals(result)) {
                break;
            }
        }

        scanner.close();
    }
}