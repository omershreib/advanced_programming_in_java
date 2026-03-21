package com.example.mamans.maman01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class BullsAndCowsBackend {
    /*
    * The backend of this Bulls-and-Cows game contains the logic of this game.
    * This includes:
    *   1. generating the game's four digits number
    *   2. comparing the player's guessed number with the game's number, and count the numbers of bulls and cows
    *
    * */
    BullsAndCowsUtils bncUtils = new BullsAndCowsUtils();

    private int gameNumberAsInt;
    private String gameNumberAsString;


    public int getGameNumberAsInt() { return this.gameNumberAsInt; }

    public String getGameNumberAsString() { return this.gameNumberAsString; }

    private void setGameNumberAsInt(int n) { this.gameNumberAsInt = n; }

    private void setGameNumberAsString(String s) { this.gameNumberAsString = s; }



    protected void initNewGame() {
        this.generateGameNumber();
    }

    private void generateGameNumber() {
        /* implement random selection of numbers without repetitions as follows:
        * 1. shuffle all the numbers from 0 to 9
        * 2. take only the first 4 digits located at positions 0-3
        * */
        List<Integer> numbers = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        Collections.shuffle(numbers);

        int digit0 = numbers.get(3);
        int digit1 = numbers.get(2);
        int digit2 = numbers.get(1);
        int digit3 = numbers.get(0);

        this.setGameNumberAsInt(bncUtils.buildNumberFromDigits(digit0, digit1, digit2, digit3));
        this.setGameNumberAsString(bncUtils.prettifyNumber(this.getGameNumberAsInt()));

        // during debug only
        System.out.println("Random-Game-Number: " + this.getGameNumberAsString());
    }

}
