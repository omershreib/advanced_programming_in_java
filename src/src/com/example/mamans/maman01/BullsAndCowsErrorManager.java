package com.example.mamans.maman01;

public class BullsAndCowsErrorManager {
    private final String ERROR_PREFIX = "ErrorInput";

    private final String ERROR_INPUT_IS_EMPTY = "Input cannot be empty";
    private final String ERROR_INPUT_CONTAINS_NON_DIGITS = "Input must includes only digits (0-9)";
    private final String ERROR_INPUT_CONTAINS_DUPLICATE_DIGITS = "All four digits must be unique! (repetitions are not allowed)";
    private final String ERROR_INPUT_LENGTH_TOO_SHORT = "Input length is too short! (required 4 unique digits)";
    private final String ERROR_INPUT_LENGTH_TOO_LONG = "Input length is too long! (required 4 unique digits)";

    public String getErrorInputContainsNonDigits() {
        return this.ERROR_PREFIX + ": " + this.ERROR_INPUT_CONTAINS_NON_DIGITS;
    }

    public String getErrorInputContainsDuplicatesDigits() {
        return this.ERROR_PREFIX + ": " + this.ERROR_INPUT_CONTAINS_DUPLICATE_DIGITS;
    }

    public String getErrorInputLengthIsTooShort() {
        return this.ERROR_PREFIX + ": " + this.ERROR_INPUT_LENGTH_TOO_SHORT;
    }

    public String getErrorInputLengthIsTooLong() {
        return this.ERROR_PREFIX + ": " + this.ERROR_INPUT_LENGTH_TOO_LONG;
    }

    public String getErrorInputIsEmpty() { return this.ERROR_PREFIX + ": " + this.ERROR_INPUT_IS_EMPTY; }
}
