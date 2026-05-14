package com.example.mamans.maman02.src.q1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * <h3> PolynomTUIBackend </h3>
 *
 * <p>
 *     backend terminal user interface (tui) that handle all the mechanics behind the user interface
 *     like command parsing and applyment
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

public class PolynomTUIBackend {


    /* by default, set p and q to be the zero polynoms (better than nothing, I think...) */
    private static Polynom p;
    private static Polynom q;

    static {
        try {
            p = new Polynom();
            q = new Polynom();
        } catch (CoefsAndPowersLengthMismatchException e) {
            throw new RuntimeException(e);
        }
    }


    protected static void setP(List<String> parameters) throws CoefsAndPowersLengthMismatchException {

        List<Double> coefs = new ArrayList<>();
        List<Integer> powers = new ArrayList<>();

        for (String pair : parameters) {

            // remove '(' and ')'
            pair = pair.replace("(", "");
            pair = pair.replace(")", "");

            // split by comma
            String[] values = pair.split(",");

            double coefficient = Double.parseDouble(values[0].trim());

            int power = Integer.parseInt(values[1].trim());

            coefs.add(coefficient);
            powers.add(power);
        }

        p = new Polynom(coefs, powers);

        System.out.println("set p ended successfully");
    }

    protected static void setQ(List<String> parameters) throws CoefsAndPowersLengthMismatchException {

        List<Double> coefs = new ArrayList<>();
        List<Integer> powers = new ArrayList<>();

        for (String pair : parameters) {

            // remove '(' and ')'
            pair = pair.replace("(", "");
            pair = pair.replace(")", "");

            // split by comma
            String[] values = pair.split(",");

            double coefficient =
                    Double.parseDouble(values[0].trim());

            int power =
                    Integer.parseInt(values[1].trim());

            coefs.add(coefficient);
            powers.add(power);
        }

        q = new Polynom(coefs, powers);

        System.out.println("set q ended successfully");
    }

    protected static void set(String[] tokens) throws CoefsAndPowersLengthMismatchException {

        List<String> parameters = new ArrayList<>(Arrays.asList(tokens).subList(2, tokens.length));

        switch (tokens[1]) {
            case "p":
                setP(parameters);
                break;
            case "q":
                setQ(parameters);
                break;
            default:
                System.out.println("unrecognized polynom symbol: " + tokens[1]);

        }

    }

    protected static void show(String[] tokens) {
        switch (tokens[1]) {
            case "p":
                System.out.println(p);
                break;
            case "q":
                System.out.println(q);
                break;
            default:
                System.out.println("unrecognized polynom symbol: " + tokens[1]);

        }
    }


    protected static void derive(String[] tokens) throws CoefsAndPowersLengthMismatchException {
        switch (tokens[1]) {
            case "p":
                System.out.println(p.derivative());
                break;
            case "q":
                System.out.println(q.derivative());
                break;
            default:
                System.out.println("unrecognized polynom symbol: " + tokens[1]);

        }

    }

    protected static void add() throws CoefsAndPowersLengthMismatchException {
        System.out.println(p.plus(q));
    }

    protected static void sub() throws CoefsAndPowersLengthMismatchException {
        System.out.println(p.minus(q));
    }

    protected static void equals() {
        System.out.println(p == q);
    }

}
