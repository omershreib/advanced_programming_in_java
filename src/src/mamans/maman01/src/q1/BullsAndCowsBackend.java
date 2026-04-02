package mamans.maman01.src.q1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <h3> BullsAndCowsBackend </h3>
 *
 * <p>
 *     this class handle the Bulls-and-Cows game mechanic and logic.:
 *     <ol>
 *         <li> the backand (or logic) </li>
 *         <li> the inputParser </li>
 *     </ol>
 *
 * </p>
 *
 * @maman   01
 * @question    1
 * @author  Omer Shraibshtein (205984271)
 * @email   omershreib@gmail.com
 * @since   2026-04-02
 * */

public class BullsAndCowsBackend {
    private static final BullsAndCowsUtils bncUtils = new BullsAndCowsUtils();
    private int gameNumberAsInt;
    private String gameNumberAsString;
    private int currBulls;
    private int currCows;
    private boolean isGameOver;

    private static final String RANDOM_GAME_GENERATING_PRINTLN_PREFIX = "generated a random game number: ";

    public int getCurrBulls() { return this.currBulls; }

//    public void setCurrBulls(int n) { this.currBulls = n; }
//
//    public void setCurrCows(int n) { this.currCows = n; }


    public int getCurrCows() { return this.currCows; }

    /** initialize player guess check by assigning zero to currBulls and currCows
     * (required before each and every player guess comparison) */
    protected void initCheck() {
        currBulls = 0;
        currCows = 0;
    }

    public void setGameOver(boolean b) { this.isGameOver = b; }

    public boolean getIsGameOver() { return this.isGameOver; }

    public int getGameNumberAsInt() { return this.gameNumberAsInt; }

    public String getGameNumberAsString() { return this.gameNumberAsString; }

    private void setGameNumberAsInt(int n) { this.gameNumberAsInt = n; }

    private void setGameNumberAsString(String s) { this.gameNumberAsString = s; }

    /** check for game over
     *
     * @return true if the current number of bulls (stored at currBulls) is equal to 4. Otherwise, false.
     * */
    public boolean checkForGameOver() {
        if (currBulls == 4) {
            this.setGameOver(true);
            return true;
        }
        return false;
    }

    protected void initNewGame() {
        this.generateGameNumber();
    }


    /** generate new game number
     *
     * <p> implement random selection of numbers without repetitions as follows: </p>
     * <ol>
     *     <li> shuffle all the numbers from 0 to 9 </li>
     *     <li> take only the first 4 digits located at positions 0-3 </li>
     * </ol>
     *
     * <p> that new generated number is saved by this class and can be received from the gameNumber getters </p>
     * */
    private void generateGameNumber() {

        List<Integer> numbers = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        Collections.shuffle(numbers);

        int digit0 = numbers.get(3);
        int digit1 = numbers.get(2);
        int digit2 = numbers.get(1);
        int digit3 = numbers.get(0);

        this.setGameNumberAsInt(bncUtils.buildNumberFromDigits(digit0, digit1, digit2, digit3));
        this.setGameNumberAsString(bncUtils.prettifyNumber(this.getGameNumberAsInt()));

        // during debug only
        System.out.println(RANDOM_GAME_GENERATING_PRINTLN_PREFIX + this.getGameNumberAsString());
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
                currCows++;
            }
        }

        for (int i=0; i<4; i++) {
            if (gameNumber.charAt(i) == playerNumber.charAt(i)) {
                currCows--;
                currBulls++;
            }
        }

    }

}
