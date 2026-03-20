package com.example.mamans.maman01;

public class BullsAndCowsManager {
    private BullsAndCowsBackend gameBackend = new BullsAndCowsBackend();
    private BullsAndCowsPlayer gamePlayer = new BullsAndCowsPlayer();

    private boolean isGameOver = false;

    public void setGameOver(boolean b) { this.isGameOver = b; }

    public void runGame() {

    }


}
