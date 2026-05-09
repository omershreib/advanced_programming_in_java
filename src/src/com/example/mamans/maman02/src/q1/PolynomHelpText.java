package com.example.mamans.maman02.src.q1;

public class PolynomHelpText {

    // note: this HELP_TEXT is ugly because Java 8 does not support
    // text block literals (required JDK 15+)
    protected static String HELP_TEXT =
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
                    "  set <p|q> (coef1, power1), (coef2, power2), ..., (coef_k, power_k)\n" +
                    "      Define a polynomial.\n" +
                    "      - p or q: the polynomial to set.\n" +
                    "      - Each term is written as (coefficient, power).\n" +
                    "      Example:\n" +
                    "          set p (3,2), (1,0)\n" +
                    "      This defines p(x) = 3x^2 + 1.\n" +
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


    protected static void printHelpText() {
        System.out.println(HELP_TEXT);
    }
}
