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

//    private boolean isGameOver;

//    private int currBulls;
//
//    private int currCows;


    /** append a new guessData to the guessesHistory String archive */
    protected void updateGuessesHistory(String guessData) {
        this.guessesHistory.append("\n").append(guessData);
    }

    /** clear guessesHistory by new StringBuilder override */
    protected void clearGuessesHistory() {
        this.guessesHistory = new StringBuilder();
    }

    protected String getGuessesHistory() { return this.guessesHistory.toString(); }

//    public int getCurrBulls() { return this.currBulls; }
//
//    public int getCurrCows() { return this.currCows; }


//    /** initialize player guess check by assigning zero to currBulls and currCows
//     * (required before each and every player guess comparison) */
//    private void initCheck() {
//        this.currBulls = 0;
//        this.currCows = 0;
//    }
//
//    public void setGameOver(boolean b) { this.isGameOver = b; }
//
//
//    public boolean checkForGameOver() {
//        if (this.currBulls == 4) {
//            this.setGameOver(true);
//            return true;
//        }
//        return false;
//    }

    public String summarizeGuessResult() {

        gameInputParser.incrementPlayerGuessIndex();

        int guessIndex = gameInputParser.getPlayerGuessIndex();

        String guessNumber = gameInputParser.getCurrPlayerNumberAsString();

        String result = "#" + guessIndex + "\t\t" + guessNumber + "\t\t#bulls:"
                + " " + gameBackend.getCurrBulls() + "\t\t#cows:" + " " + gameBackend.getCurrCows();

        System.out.println(result);
        return result;

    }

    private void preCompareSetup() {
        this.gameBackend.initCheck();
        this.gameInputParser.incrementPlayerGuessIndex();
    }

    public void initNewGame() {
        this.preCompareSetup();
        this.gameBackend.setGameOver(false);
        this.gameBackend.initNewGame();
        this.gameInputParser.initNewGame();
        this.clearGuessesHistory();
    }

    protected boolean playCurrGameTurn(String guessInput) {

        // accept player guess input if valid (or exit upon cancel button press)
        if (gameInputParser.parsePlayerInput(guessInput)) {

            // compare between the current player's guess with the true game's number
            gameBackend.compareWithGameNumber(gameInputParser.getCurrPlayerNumberAsString());

            // update guesses-history and display it to the player
            this.updateGuessesHistory(this.summarizeGuessResult());

            return true;
        }
        return false;
    }


}
