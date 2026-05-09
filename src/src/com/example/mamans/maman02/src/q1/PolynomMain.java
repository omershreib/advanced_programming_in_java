package com.example.mamans.maman02.src.q1;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class PolynomMain extends PolynomHelpText {

    private static final String[] COMMANDS = {"help", "exit", "set", "show", "derive", "add", "sub", "equals?"};

    private static final String[] POLYNOMS_SYMBOLS = {"p", "q"};

    //private static String userInput;

    private static Polynom p;

    private static Polynom q;

    private static boolean exitTUI = false;


    public static void setExitTUI(boolean value) { exitTUI = value; }


    public static void parseUserInput(String userInput) {

        String[] tokens = userInput.trim().split("\\s+");
        String cmd = tokens[0];
        AtomicBoolean isSupportedCommand = new AtomicBoolean(false);

        Arrays.stream(COMMANDS).forEach(command -> {
            if (command.equals(cmd)) { isSupportedCommand.set(true); }
        });

        if (!isSupportedCommand.get()) {
            throw new RuntimeException();
        }

        if (cmd.equals("help")) {
            printHelpText();
        }

        if (cmd.equals("exit")) {
            setExitTUI(true);
        }

    }



    public static void main(String[] args) {

        do {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Maman02 Question 1: Polynom Terminal User Interface\n\nEnter command (type 'help' to display commands reference)");

            parseUserInput(scanner.nextLine());
        }

        while (!exitTUI);




//        System.out.println("Enter first (p(x)) and second (q(x)) polynoms in this form:\n(coef1, power1), (coef2, power2), ..., (coef_k, power_k):");
//        System.out.println("\n\nset first polynom as follows:");
//        System.out.println("\n\nset second polynom as follows:\n");

    }

}
