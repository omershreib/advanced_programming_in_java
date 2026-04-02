package mamans.maman01.src.q1;

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

    private int currBulls;

    private int currCows;

    private boolean isGameOver;

    public int getCurrBulls() { return this.currBulls; }

    public void setCurrBulls(int n) { this.currBulls = n; }

    public void setCurrCows(int n) { this.currCows = n; }


    public int getCurrCows() { return this.currCows; }

    /** initialize player guess check by assigning zero to currBulls and currCows
     * (required before each and every player guess comparison) */
    protected void initCheck() {
        this.currBulls = 0;
        this.currCows = 0;
    }

    public void setGameOver(boolean b) { this.isGameOver = b; }

    public boolean getIsGameOver() { return this.isGameOver; }


    public boolean checkForGameOver() {
        if (this.currBulls == 4) {
            this.setGameOver(true);
            return true;
        }
        return false;
    }


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

    /** compare between the player's guessed number and the game's pre-generated number
     * <p> comparison logic: </p>
     * <ol>
     *      <li> first, count the cows </li>
     *      <li> then, for every counted bulls count decrease cows accordingly </li>
     * </ol>
     *
     * */
    public void compareWithGameNumber(String playerNumber) {
        String gameNumber = this.getGameNumberAsString();
        this.initCheck();


        for (char c : playerNumber.toCharArray()) {
            if (gameNumber.indexOf(c) >= 0) {
                this.currCows++;
            }
        }

        for (int i=0; i<4; i++) {
            if (gameNumber.charAt(i) == playerNumber.charAt(i)) {
                this.currCows--;
                this.currBulls++;
            }
        }

    }


}
