package mamans.maman01.src.q1;

/**
 * <h3> BullsAndCowsUtils (bncUtils) </h3>
 *
 * <p>
 *     this class object offer a collection of useful utilities used by both backand and inputParser classes.
 *     these include:
 * </p>
 * <ul>
 *     <li> <b>prettifyNumber:</b> adds a left-zero padding to a number (so "0123" will not be printed as "123") </li>
 *     <li> <b>buildNumberFromDigits:</b> build a 4-digit number (dcba) from 4 digits (a, b ,c ,d) </li>
 *     <li> <b>charToInt:</b> maps characters to their numeric ASCII value </li>
 *     <li> <b>isDigitChar:</b> check if a given char is digit or not </li>
 *     <li> <b>countDigitRepetition:</b> check if a given 4-digits number contains digit repetition </li>
 * </ul>
 *
 * @maman   01
 * @question    1
 * @author  Omer Shraibshtein (205984271)
 * @email   omershreib@gmail.com
 * @since   2026-04-02
 * */
public class BullsAndCowsUtils {

    /** <p> returns a pretty numeric String version of this integer </p>
     *
     * <p> example usage: </p>
     * <p> 0123 → "0123" </p>
     *
     * @param n an integer
     * @return a pretty numeric String version of this integer
     * */
    public String prettifyNumber(int n) {
        return String.format("%04d", n);
    }

    /** build a 4-digit number from 4 integers
     *
     * @param d0 the unit digit
     * @param d1 the tens digit
     * @param d2 the hundreds digit
     * @param d3 the thousands digit
     *
     * @return a 4-digit integer
     * */
    public int buildNumberFromDigits(int d0, int d1, int d2, int d3) {
        return d0 + (d1 * 10) + (d2 * 100) + (d3 * 1000);
    }

    /** maps the numeric ASCII value of a character
     * <p><br> example: <br> '7' → 7 </p>
     * <p> <br> mathematical explanation: </p>
     * <p> charToInt('7') = (int) '7' - '0' = 55 - 48 = 7 </p>
     * <p> <br> where ASCII('0') = 48 and ASCII('7') = 55 </p>
     * @param c a char
     * @return an integer equal to the ASCII value of c
     * */
    public int charToInt(char c) {
        return (int) c - '0';
    }

    /** check if a given character is a digit or not
     *
     * @param c a char
     * @return true if c is a digit. Otherwise, false.*/
    public boolean isDigitChar(char c) {
        return c >= '0' && c <= '9';
    }

    /** return the number of time a character @dig is repeated at a String @number
     *
     * @param dig a char (assumed to be a digit character)
     * @param number a String (assumed to be numeric)
     * @return an integer equal to the number of times @dig exists in @number
     * */

    public int countDigitRepetition(char dig, String number) {
        int count = 0;
        for (int i=0; i<4; i++) {
            if (number.charAt(i) == dig)
                count++;
        }
        return count;
    }

}
