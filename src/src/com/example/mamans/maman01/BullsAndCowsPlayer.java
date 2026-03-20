package com.example.mamans.maman01;

import java.util.Scanner;

public class BullsAndCowsPlayer {
    /*
    * The player code section of this Bulls-and-Cows game.
    * This class handle the player side of this game, which includes:
    *   1. receiving the player's input number
    *   2. parsing player's input - including handling inputs errors
    *   3. history? (need to decide if put this here or in the Manger class)
    * */
    BullsAndCowsUtils bncUtils = new BullsAndCowsUtils();

    private final String errorInputMassage = "InputError: Player input must contain 4 digits";

    private String currPlayerNumberAsString;
    private int currPlayerNumberAsInt;

    private int playerGuessIndex;

    private final Scanner scanner = new Scanner(System.in);


    public int getPlayerGuessIndex() {return this.playerGuessIndex; }

    public void incrementPlayerGuessIndex() {this.playerGuessIndex++; }

    public String getCurrPlayerNumberAsString() { return this.currPlayerNumberAsString; }

    public int getCurrPlayerNumberAsInt() {
        return this.currPlayerNumberAsInt;
    }

    public void setCurrPlayerNumberAsString(String s) { this.currPlayerNumberAsString = s; }

    public void setCurrPlayerNumberAsInt(int n) { this.currPlayerNumberAsInt = n; }


    public void setCurrUserNumberFromUserInput(String userInput) {
        int digit0 = bncUtils.charToInt(userInput.charAt(0));
        int digit1 = bncUtils.charToInt(userInput.charAt(1));
        int digit2 = bncUtils.charToInt(userInput.charAt(2));
        int digit3 = bncUtils.charToInt(userInput.charAt(3));

        this.setCurrPlayerNumberAsInt(bncUtils.buildNumberFromDigits(digit3, digit2, digit1, digit0));
        this.setCurrPlayerNumberAsString(bncUtils.prettifyNumber(this.getCurrPlayerNumberAsInt()));
    }

    public void waitForPlayerGuess() {
        System.out.println("enter your guess");
        String userInput = scanner.nextLine();

        if (this.parsePlayerInput(userInput)) {
            System.out.println("current player guess: " + userInput);
            System.out.println(this.getCurrPlayerNumberAsString());
        }
    }


    public boolean parsePlayerInput(String input) {

        System.out.println("get: " + input);
        System.out.println("Input Length:" + " " + input.length());

        if (input == null) {
            // handle case of empty user input
            System.out.println(this.errorInputMassage);
            System.out.println("user input is empty");
            return false;
        }

        if (input != null) {
            // handle case of non-empty user input

            // todo: check before:
            //      1. user enter 4-digit number (and nothing else)
            //      2. consider skip leading or closing empty spaces

            // remove all spaces (' ')
            input = input.replaceAll(" ", "");
            int inputLength = input.length();

            if (!(inputLength == 4)) {
                System.out.println(this.errorInputMassage);
                return false;
            }


            for (char c : input.toCharArray()) {
                if (!bncUtils.isDigitChar(c)) {
                    System.out.println(this.errorInputMassage);
                    System.out.println("bad character: " + " " + c);
                    return false;
                }
            }

            this.setCurrUserNumberFromUserInput(input);
        }
        return true;
    }

    public void initNewGame() {
        this.playerGuessIndex = 0;
    }

}
