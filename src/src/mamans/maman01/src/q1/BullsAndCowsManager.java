package mamans.maman01.src.q1;

/**
 * <h3> BullsAndCowsManager </h3>
 *
 * <p>
 *     this class orchestrates the Bulls-and-Cows game by controlling its two components:
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

public class BullsAndCowsManager {
    protected BullsAndCowsBackend gameBackend = new BullsAndCowsBackend();
    protected BullsAndCowsInputParser gameInputParser = new BullsAndCowsInputParser();

    /* implements guesses-history archive as a long String
    *
    * note: I do considered to implement this with a class object,
    *       but later I decided that it is too overkilled...
    * */
    private StringBuilder guessesHistory;


    /** append a new guessData to the guessesHistory String archive
     *
     * @param guessData a formatted string
     * */
    protected void updateGuessesHistory(String guessData) {
        this.guessesHistory.append("\n").append(guessData);
    }

    /** clear guessesHistory by new StringBuilder override */
    protected void clearGuessesHistory() {
        this.guessesHistory = new StringBuilder();
    }

    protected String getGuessesHistory() { return this.guessesHistory.toString(); }

    /**
     * return a summarized version of the player's current guess <br>
     *
     * <p> all the data needed to build this summary is saved by
     * this game's backend and inputParser immediately after a valid player guess input.
     * the output of this method is later used as the @guessData input of the updateGuessesHistory() method </p>
     *
     * @return a formatted string
     * */
    protected String summarizeGuessResult() {

        gameInputParser.incrementPlayerGuessIndex();

        int guessIndex = gameInputParser.getPlayerGuessIndex();

        String guessNumber = gameInputParser.getCurrPlayerNumberAsString();

        String result = "#" + guessIndex + "\t\t" + guessNumber + "\t\t#bulls:"
                + " " + gameBackend.getCurrBulls() + "\t\t#cows:" + " " + gameBackend.getCurrCows();

        System.out.println(result);
        return result;

    }

    private void preCompareSetup() {
        gameBackend.initCheck();
    }

    /** manager new game initialize method
     *
     * <p> initialization setup includes: </p>
     * <ul>
     *     <li> calls backend initializer  </li>
     *     <li> calls inputParser initializer </li>
     *     <li> clear previous game history </li>
     * </ul>
     * **/
    public void initNewGame() {
        this.gameBackend.initNewGame();
        this.gameInputParser.initNewGame();
        this.clearGuessesHistory();
    }

    /**
     * play the current game's turn according to the current guess provided by the player
     *
     * @param guessInput a String
     * @return true if this play's turn ends successfully. this if and only if the @guessInput is valid (otherwise, false)
     * */
    protected boolean playGameCurrTurn(String guessInput) {

        // accept player guess input if valid (or exit upon cancel button press)
        if (gameInputParser.parsePlayerInput(guessInput)) {

            // compare between the current player's guess with the true game's number
            gameBackend.compareWithGameNumber(gameInputParser.getCurrPlayerNumberAsString());

            // update guesses-history and display it to the player
            updateGuessesHistory(this.summarizeGuessResult());

            return true;
        }
        return false;
    }

}
