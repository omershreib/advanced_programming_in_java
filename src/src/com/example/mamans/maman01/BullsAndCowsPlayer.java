package com.example.mamans.maman01;

public class BullsAndCowsPlayer {
    /*
    * The player code section of this Bulls-and-Cows game.
    * This class handle the player side of this game, which includes:
    *   1. receiving the player's input number
    *   2. parsing player's input - including handling inputs errors
    *   3. history? (need to decide if put this here or in the Manger class)
    * */
    BullsAndCowsUtils bncUtils = new BullsAndCowsUtils();
    private final String errorInputMassage = "InputError: user input must contain 4 digits";

    private String currUserNumberAsString;
    private int currUserNumberAsInt;

    public String getCurrUserNumberAsString() { return this.currUserNumberAsString; }

    public int getCurrUserNumberAsInt() {
        return this.currUserNumberAsInt;
    }

    public void setCurrUserNumberAsString(String s) { this.currUserNumberAsString = s; }

    public void setCurrUserNumberAsInt(int n) { this.currUserNumberAsInt = n; }

}
