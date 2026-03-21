package com.example.mamans.maman01.bulls_and_cows;

public class BullsAndCowsManager {
    public BullsAndCowsBackend gameBackend = new BullsAndCowsBackend();
    public BullsAndCowsInputParser gameInputParser = new BullsAndCowsInputParser();

    //public BullsAndCowsGuessArchive archive = new BullsAndCowsGuessArchive();

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
        int guessIndex = this.gameInputParser.getPlayerGuessIndex();
        String guessNumber = this.gameInputParser.getCurrPlayerNumberAsString();

        String result = "#" + guessIndex + "\t\t" + guessNumber + "\t\t#bulls:"
                + " " + this.getCurrBulls() + "\t\t#cows:" + " " + this.getCurrCows();

        System.out.println(result);
        return result;

    }

    private void preCompareSetup() {
        this.initCheck();
        this.gameInputParser.incrementPlayerGuessIndex();
    }

    public void initNewGame() {
        this.setGameOver(false);
        this.gameBackend.initNewGame();
        this.gameInputParser.initNewGame();
    }

    public void runGame() {
        this.initNewGame();
        String gameNumber = this.gameBackend.getGameNumberAsString();

        while (!this.isGameOver) {
            this.gameInputParser.waitForPlayerGuess();
            this.compareNumbers(gameNumber, this.gameInputParser.getCurrPlayerNumberAsString());
            this.summarizeGuessResult();
            this.checkForGameOver();
        }


    }

}
