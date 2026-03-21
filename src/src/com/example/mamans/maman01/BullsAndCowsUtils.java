package com.example.mamans.maman01;

public class BullsAndCowsUtils {

    public String prettifyNumber(int n) {
        return String.format("%04d", n);
    }


    public int buildNumberFromDigits(int d0, int d1, int d2, int d3) {
        return d0 + (d1 * 10) + (d2 * 100) + (d3 * 1000);
    }

    public int charToInt(char c) {
        // return the numeric value of a character using this formula:
        // (int) c - '0', where (int) 'c' resolves the ASCII-value of the character 'c'
        return (int) c - '0';
    }

    public boolean isDigitChar(char c) {
        return c >= '0' && c <= '9';
    }

    public int countDigitRepetition(char dig, String number) {
        int count = 0;
        for (int i=0; i<4; i++) {
            if (number.charAt(i) == dig)
                count++;
        }
        return count;
    }

}
