package com.example.mamans.maman01.bulls_and_cows;

import java.util.ArrayList;
import java.util.ListIterator;

public class BullsAndCowsGuessArchive {

    private ArrayList<String> guessArchive = new ArrayList<>();
    public void addPlayerGuessToArchive(String playerGuess) {
        this.guessArchive.add(playerGuess);
    }

    public ListIterator<String> getGuessArchive() {
        return this.guessArchive.listIterator();
    }
}
