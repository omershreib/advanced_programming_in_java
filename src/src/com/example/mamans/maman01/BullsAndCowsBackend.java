package com.example.mamans.maman01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

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
        Random rand = new Random();

        List<Integer> gameDigits = new ArrayList<Integer>(4);
        IntStream.range(0, 4).forEach(i -> gameDigits.add(rand.nextInt(10)));

        int digit0 = gameDigits.get(3);
        int digit1 = gameDigits.get(2);
        int digit2 = gameDigits.get(1);
        int digit3 = gameDigits.get(0);

        this.setGameNumberAsInt(bncUtils.buildNumberFromDigits(digit0, digit1, digit2, digit3));
        this.setGameNumberAsString(bncUtils.prettifyNumber(this.getGameNumberAsInt()));
    }


}
