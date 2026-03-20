package com.example.mamans.maman01;

import java.util.Scanner;

public class BullsAndCowsInputParser {
    /*
    * The player code section of this Bulls-and-Cows game.
    * This class handle the player side of this game, which includes:
    *   1. receiving the player's input number
    *   2. parsing player's input - including handling inputs errors
    *   3. history? (need to decide if put this here or in the Manger class)
    * */
    private BullsAndCowsUtils bncUtils = new BullsAndCowsUtils();

    private BullsAndCowsErrorManager gameErrorManager = new BullsAndCowsErrorManager();

    private final String errorInputMassage = "InputError: Player input must contain 4 digits";

    private String currPlayerNumberAsString;

    private String InputErrorMessage;

    private int currPlayerNumberAsInt;

    private int playerGuessIndex;

    private final Scanner scanner = new Scanner(System.in);


    public String getInputErrorMessage() { return this.InputErrorMessage; }

    public void setInputErrorMessage(String msg) { this.InputErrorMessage = msg; }


    public int getPlayerGuessIndex() {return this.playerGuessIndex; }

    public void incrementPlayerGuessIndex() {this.playerGuessIndex++; }

    public String getCurrPlayerNumberAsString() { return this.currPlayerNumberAsString; }

    public int getCurrPlayerNumberAsInt() {
        return this.currPlayerNumberAsInt;
    }

    public void setCurrPlayerNumberAsString(String s) { this.currPlayerNumberAsString = s; }

    public void setCurrPlayerNumberAsInt(int n) { this.currPlayerNumberAsInt = n; }


    public void setCurrPlayerGuess(String userInput) {
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

        int inputLength = input.length();
        System.out.println("get: " + input);
        System.out.println("Input Length:" + " " + inputLength);

        if (inputLength == 0) {
            // handle error case of empty input
            this.setInputErrorMessage(gameErrorManager.getErrorInputIsEmpty());
            return false;
        }

        if (inputLength < 4) {
            // handle error case of too short input
            this.setInputErrorMessage(gameErrorManager.getErrorInputLengthIsTooShort());
            return false;
        }

        if (inputLength > 4) {
            // handle error case of too long input
            this.setInputErrorMessage(gameErrorManager.getErrorInputLengthIsTooLong());
            return false;
        }

        for (char c : input.toCharArray()) {
            // check if this input contains non-digits
            if (!bncUtils.isDigitChar(c)) {
                this.setInputErrorMessage(gameErrorManager.getErrorInputContainsNonDigits());
                return false;
            }

            // check for duplicate digits
            if (bncUtils.countDigitRepetition(c, input) > 1) {
                this.setInputErrorMessage(gameErrorManager.getErrorInputContainsDuplicatesDigits());
                return false;
            }
        }

        this.setCurrPlayerGuess(input);
        this.setInputErrorMessage("");
        return true;
    }

    public void initNewGame() {
        this.playerGuessIndex = 0;
    }

}
