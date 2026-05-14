package com.example.mamans.maman02.src.q1;

/**
 * <h3> PolynomHelpText </h3>
 *
 * <p>
 *     ad-hoc interface to contains the help text users will see during this program run (by typing 'help')
 *     i did not wanted this long string in my PolynomMain
 * </p>
 * <br>
 * <p> Note: HTML tags helps to improve comments readability in editors like IntelliJ that support it </p>
 *
 * @maman   02
 * @question    1
 * @author  Omer Shraibshtein (205984271)
 * @email   omershreib@gmail.com
 * @since   2026-05-14
 * */


public interface PolynomHelpText {

    // note: this HELP_TEXT is ugly because Java 8 does not support
    // text block literals (required JDK 15+)
    static String HELP_TEXT =
            "Polynomial Calculator – Command Reference\n" +
                    "\n" +
                    "Available Commands:\n" +
                    "\n" +
                    "  help\n" +
                    "      Show this help manual.\n" +
                    "\n" +
                    "  exit\n" +
                    "      Quit the program.\n" +
                    "\n" +
                    "  set <p|q> (coef1,power1), (coef2,power2), ..., (coef_k,power_k)\n" +
                    "      Define a polynomial.\n" +
                    "      - p or q: the polynomial to set.\n" +
                    "      - Each term is written as (coefficient,power).\n" +
                    "      Example:\n" +
                    "          set p (3,2), (1,0)\n" +
                    "      This defines p(x) = 3x^2 + 1.\n" +
                    "      Important!: pay attention to spaces (SPACES IS DISALLOWED INSIDE PAIRS!\nTHIS (coef, power) WILL THROW EXCEPTION\nthis way (coef,power) is OK)" +
                    "\n" +
                    "  show <p|q>\n" +
                    "      Display the polynomial in standard mathematical form.\n" +
                    "      Example:\n" +
                    "          show p\n" +
                    "\n" +
                    "  derive <p|q>\n" +
                    "      Compute and display the derivative of the chosen polynomial.\n" +
                    "      Example:\n" +
                    "          derive q\n" +
                    "\n" +
                    "  add p q\n" +
                    "      Compute p + q and display the resulting polynomial.\n" +
                    "\n" +
                    "  sub p q\n" +
                    "      Compute p - q and display the resulting polynomial.\n" +
                    "\n" +
                    "  equals?\n" +
                    "      Check whether p and q represent the same polynomial.\n" +
                    "\n" +
                    "Notes:\n" +
                    "  - Coefficients may be integers or decimals.\n" +
                    "  - Powers must be non-negative integers.\n" +
                    "  - Terms with zero coefficients may be omitted.\n";


    static void printHelpText() {
        System.out.println(HELP_TEXT);
    }
}
