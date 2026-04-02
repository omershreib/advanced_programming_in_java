package mamans.maman01.src.q1;

import java.util.Scanner;

/**
 * <h3> BullsAndCowsInputParser </h3>
 *
 * <p>
 *     this is an inputParser class handle parsing the player's guess input and check if it is valid
 *     (i.e., stands all this game requirement which expected to receive a 4 unique digits number)
 * </p>
 * <p> when this parser detected that this player's input is <b>invalid</b> then it save a proper error message </p>
 *
 * @maman   01
 * @question    1
 * @author  Omer Shraibshtein (205984271)
 * @email   omershreib@gmail.com
 * @since   2026-04-02
 * */

public class BullsAndCowsInputParser {
    private static final BullsAndCowsUtils bncUtils = new BullsAndCowsUtils();

    // Constants input error massages
    private static final String ERROR_INPUT_IS_EMPTY = "Input cannot be empty";
    private static final String ERROR_INPUT_CONTAINS_NON_DIGITS = "Input must includes only digits (0-9)";
    private static final String ERROR_INPUT_CONTAINS_DUPLICATE_DIGITS = "All four digits must be unique! (repetitions are not allowed)";
    private static final String ERROR_INPUT_LENGTH_TOO_SHORT = "Input length is too short! (required 4 unique digits)";
    private static final String ERROR_INPUT_LENGTH_TOO_LONG = "Input length is too long! (required 4 unique digits)";

    // player's current guess number (as String)
    private String currPlayerNumberAsString;


    // player's current guess number (as Integer)
    private int currPlayerNumberAsInt;


    // current input error message (if input is valid then this attribute will be set to the empty String)
    private String InputErrorMessage;


    // player's guesses counter
    private int playerGuessIndex;

    // player input scanner
    private static final Scanner scanner = new Scanner(System.in);


    public String getInputErrorMessage() { return this.InputErrorMessage; }

    public void setInputErrorMessage(String msg) { this.InputErrorMessage = msg; }

    public int getPlayerGuessIndex() { return this.playerGuessIndex; }

    public void incrementPlayerGuessIndex() { this.playerGuessIndex++; }

    public void resetPlayerGuessIndex() { this.playerGuessIndex = 0; }

    public String getCurrPlayerNumberAsString() { return this.currPlayerNumberAsString; }

    public int getCurrPlayerNumberAsInt() {
        return this.currPlayerNumberAsInt;
    }

    public void setCurrPlayerNumberAsString(String s) { this.currPlayerNumberAsString = s; }

    public void setCurrPlayerNumberAsInt(int n) { this.currPlayerNumberAsInt = n; }


    /**
     * set a current (valid) player guess input
     *
     * <p> this includes: </p>
     * <ul>
     *     <li> creating an <b>Integer</b> version of it accessible using getCurrPlayerNumberAsInt() </li>
     *     <li> creating a <b>String</b> version of it accessible using getCurrPlayerNumberAsString() </li>
     * </ul>
     *
     * @param userInput a String (assumed to be a valid 4-digits number as the BullsAndCows game requires)
     * */
    public void setCurrPlayerGuess(String userInput) {
        int digit0 = bncUtils.charToInt(userInput.charAt(0));
        int digit1 = bncUtils.charToInt(userInput.charAt(1));
        int digit2 = bncUtils.charToInt(userInput.charAt(2));
        int digit3 = bncUtils.charToInt(userInput.charAt(3));

        this.setCurrPlayerNumberAsInt(bncUtils.buildNumberFromDigits(digit3, digit2, digit1, digit0));
        this.setCurrPlayerNumberAsString(bncUtils.prettifyNumber(this.getCurrPlayerNumberAsInt()));
    }


    /**
     * this method parses the player's input and check if it valid.

        <p> <b>BEFORE</b> this parsing, check if the cancel button was pressed (if positive then close this program) </p>

        <p>player input examinations are ordered as follows:</p>
        <ol>
            <li> verify input length (must be equal to 4) </li>
            <li> verify that all input characters are digits (i.e., 0-9) </li>
            <li> check of digits' duplication </li>
        </ol>

        <p>
            if this input passed all these three examinations, then this input is set as the current player's
            BullsAndCows' guess. Otherwise, set a proper error message (later will be alerted by the application class)
        </p>

        @param input player input (as String)
        @return true if this input is valid (false otherwise).
        * */
    public boolean parsePlayerInput(String input) {


        int inputLength = input.length();
        System.out.println("get: " + input);
        System.out.println("Input Length:" + " " + inputLength);

        if (input.equals("cancel")) {
            // handle Cancel button press during the game
            System.exit(0);
        }

        if (inputLength == 0) {
            // handle error case of empty input
            this.setInputErrorMessage(ERROR_INPUT_IS_EMPTY);
            return false;
        }

        if (inputLength < 4) {
            // handle error case of too short input
            this.setInputErrorMessage(ERROR_INPUT_LENGTH_TOO_SHORT);
            return false;
        }

        if (inputLength > 4) {
            // handle error case of too long input
            this.setInputErrorMessage(ERROR_INPUT_LENGTH_TOO_LONG);
            return false;
        }

        for (char c : input.toCharArray()) {
            // check if this input contains non-digits
            if (!bncUtils.isDigitChar(c)) {
                this.setInputErrorMessage(ERROR_INPUT_CONTAINS_NON_DIGITS);
                return false;
            }

            // check for duplicate digits
            if (bncUtils.countDigitRepetition(c, input) > 1) {
                this.setInputErrorMessage(ERROR_INPUT_CONTAINS_DUPLICATE_DIGITS);
                return false;
            }
        }

        this.setCurrPlayerGuess(input);
        this.setInputErrorMessage("");
        return true;
    }

    /** initialize inputParser setup before starting a new game
     * (practically, only need to reset the player's guesses index) */
    public void initNewGame() {
        this.playerGuessIndex = 0;
    }

}
