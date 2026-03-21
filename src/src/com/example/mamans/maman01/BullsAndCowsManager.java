package com.example.mamans.maman01;

public class BullsAndCowsManager {
    public BullsAndCowsBackend gameBackend = new BullsAndCowsBackend();
    public BullsAndCowsInputParser gamePlayer = new BullsAndCowsInputParser();

    public BullsAndCowsGuessArchive archive = new BullsAndCowsGuessArchive();

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

    public String summarizeGuessResult() {
        int guessIndex = this.gamePlayer.getPlayerGuessIndex();
        String guessNumber = this.gamePlayer.getCurrPlayerNumberAsString();
        String result = "#" + guessIndex + " guess:" + " " + guessNumber + " #bulls:"
                + " " + this.getCurrBulls() + " #cows:" + " " + this.getCurrCows();

        System.out.println(result);
        return result;

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
            this.summarizeGuessResult();
            this.checkForGameOver();
        }


    }

}
