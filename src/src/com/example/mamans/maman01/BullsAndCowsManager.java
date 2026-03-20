package com.example.mamans.maman01;

public class BullsAndCowsManager {
    private BullsAndCowsBackend gameBackend = new BullsAndCowsBackend();
    private BullsAndCowsPlayer gamePlayer = new BullsAndCowsPlayer();

    private boolean isGameOver;

    private int currBulls;
    private int currCows;
    public int getCurrBulls() { return this.currBulls; }

    public int getCurrCows() { return this.currCows; }

    private void initCheck() {
        this.currBulls = 0;
        this.currCows = 0;
    }

    public void setGameOver(boolean b) { this.isGameOver = b; }

    public void compareNumbers(String gameNumber, String userNumber) {
        this.preCompareSetup();

        for (char c : userNumber.toCharArray()) {
            if (gameNumber.indexOf(c) >= 0) {
                this.currCows++;
            }
        }

        for (int i=0; i<4; i++) {
            if (gameNumber.charAt(i) == userNumber.charAt(i)) {
                this.currCows--;
                this.currBulls++;
            }
        }

    }

    public boolean checkForGameOver() {
        if (this.currBulls == 4) {
            this.setGameOver(true);
            return true;
        }
        return false;
    }

    public void displayGuessResult() {
        int guessIndex = this.gamePlayer.getPlayerGuessIndex();
        String guessNumber = this.gamePlayer.getCurrPlayerNumberAsString();
        System.out.println("Guess #" + guessIndex + " ; GuessNumber:" + " " + guessNumber + " ; #bulls:"
                            + " " + this.getCurrBulls() + " ; #cows:" + " " + this.getCurrCows());
    }

    private void preCompareSetup() {
        this.initCheck();
        this.gamePlayer.incrementPlayerGuessIndex();
    }

    public void initNewGame() {
        this.gameBackend.initNewGame();
        this.gamePlayer.initNewGame();
    }

    public void runGame() {
        this.initNewGame();
        String gameNumber = this.gameBackend.getGameNumberAsString();

        while (!this.isGameOver) {
            this.gamePlayer.waitForPlayerGuess();
            this.compareNumbers(gameNumber, this.gamePlayer.getCurrPlayerNumberAsString());
            this.displayGuessResult();
            this.checkForGameOver();
        }


    }

}
