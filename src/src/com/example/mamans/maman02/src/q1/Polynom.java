package com.example.mamans.maman02.src.q1;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Polynom {

    private final Double zero = 0.0;
    private static final int DOUBLE_ROUND_FORMAT = 1;
    private ArrayList<PolynomItem> polynom;

    public Polynom (List<Double> coefs, List<Integer> powers) throws CoefsAndPowersLengthMismatchException {
        if (coefs.size() != powers.size()) {
            throw new CoefsAndPowersLengthMismatchException("coefficients and powers lists must have an equal length");
        }

        int polynomLength = coefs.size();
        this.polynom = new ArrayList<PolynomItem>();

        for (int i = 0; i < polynomLength; i++) this.polynom.add(i, new PolynomItem(coefs.get(i), powers.get(i)));


        // remove all pairs with coefficient equal to zero
        int firstZeroIndex = this.getFirstCoefIndexOf(zero);

        while (firstZeroIndex >= 0) {
            this.polynom.remove(firstZeroIndex);
            firstZeroIndex = this.getFirstCoefIndexOf(zero);
        }

        this.polynom.sort(Comparator.comparing(item -> item.getPower() * (-1)));
    }

    private int getFirstCoefIndexOf(double coef) {
        for (int i = 0; i < this.polynom.size(); i++) {
            PolynomItem polynomItem = this.polynom.get(i);
            if (Objects.equals(polynomItem.getCoef(), coef)) return i;
        }

        return -1;
    }

    private Double getCoefOf(int power) {
        for (int i = 0; i < this.polynom.size(); i++) {
            PolynomItem polynomItem = this.polynom.get(i);
            if (Objects.equals(polynomItem.getPower(), power)) return polynomItem.getCoef();
        }

        return zero;
    }

    public Polynom derivative() {

        ArrayList<PolynomItem> polynomItemArrayList = new ArrayList<>();
        ArrayList<Double> coefs = new ArrayList<>();
        ArrayList<Integer> powers = new ArrayList<>();

        this.polynom.forEach(polynomItem -> polynomItemArrayList.add(polynomItem.derivative()));

        polynomItemArrayList.forEach(polynomDerivativeItem -> {
            coefs.add(polynomDerivativeItem.getCoef());
            powers.add(polynomDerivativeItem.getPower());
        });

        Polynom deivativePolynom;

        try {
            deivativePolynom = new Polynom(coefs, powers);
        }

        catch (CoefsAndPowersLengthMismatchException e) {
            throw new RuntimeException(e);
        }

        return deivativePolynom;
    }

    public Polynom plus(Polynom other) {
        Set<Integer> allPowers = new HashSet<>();

        this.polynom.forEach(polynomItem -> allPowers.add(polynomItem.getPower()));
        other.polynom.forEach(polynomItem -> allPowers.add(polynomItem.getPower()));

        HashMap<Integer, Double> powersToCoefSum = new HashMap<>();
        allPowers.forEach(power -> powersToCoefSum.putIfAbsent(power,zero));

        this.polynom.forEach(polynomItem -> {
            int key = polynomItem.getPower();
            powersToCoefSum.put(key, polynomItem.getCoef());
        });

        other.polynom.forEach(polynomItem -> {
            int key = polynomItem.getPower();
            powersToCoefSum.put(key, powersToCoefSum.get(key) + polynomItem.getCoef());
        });

        ArrayList<Double> coefs = new ArrayList<>();
        ArrayList<Integer> powers = new ArrayList<>();

        powersToCoefSum.forEach((key, value) -> {
            coefs.add(value);
            powers.add(key);
        });

        Polynom thisPlusOther;

        try {
            thisPlusOther = new Polynom(coefs, powers);
        }

        catch (CoefsAndPowersLengthMismatchException e) {
            throw new RuntimeException(e);
        }

        return thisPlusOther;
    }

    public Polynom minus(Polynom other) {
        Set<Integer> allPowers = new HashSet<>();

        this.polynom.forEach(polynomItem -> allPowers.add(polynomItem.getPower()));
        other.polynom.forEach(polynomItem -> allPowers.add(polynomItem.getPower()));

        HashMap<Integer, Double> powersToCoefSub = new HashMap<>();
        allPowers.forEach(power -> powersToCoefSub.putIfAbsent(power,zero));

        this.polynom.forEach(polynomItem -> {
            int key = polynomItem.getPower();
            powersToCoefSub.put(key, polynomItem.getCoef());
        });

        other.polynom.forEach(polynomItem -> {
            int key = polynomItem.getPower();
            powersToCoefSub.put(key, powersToCoefSub.get(key) - polynomItem.getCoef());
        });

        ArrayList<Double> coefs = new ArrayList<>();
        ArrayList<Integer> powers = new ArrayList<>();

        powersToCoefSub.forEach((key, value) -> {
            coefs.add(value);
            powers.add(key);
        });

        Polynom thisMinusOther;

        try {
            thisMinusOther = new Polynom(coefs, powers);
        }

        catch (CoefsAndPowersLengthMismatchException e) {
            throw new RuntimeException(e);
        }

        return thisMinusOther;
    }

    public boolean equals(Polynom other) {

        AtomicBoolean leftCoefComparisonResult = new AtomicBoolean(true);
        AtomicBoolean rightCoefComparisonResult = new AtomicBoolean(true);

        other.polynom.forEach(polynomItem -> {
            if (Objects.equals(this.getCoefOf(polynomItem.getPower()), zero)) {
                leftCoefComparisonResult.set(false);
            }
        });

        this.polynom.forEach(polynomItem -> {
            if (Objects.equals(other.getCoefOf(polynomItem.getPower()), zero)) {
                rightCoefComparisonResult.set(false);
            }
        });

        return leftCoefComparisonResult.get() & rightCoefComparisonResult.get();
    }

    @Override
    public String toString() {
        StringBuilder polynomString = new StringBuilder();

        this.polynom.forEach(polynomItem -> {

            Double coef = polynomItem.getCoef();

            String coefAbsValue = String.format("%." + DOUBLE_ROUND_FORMAT + "f", Math.abs(coef));
            String power = polynomItem.getPower().toString();
            String sign = coef > 0 ? " + " : " - ";

            if (polynomItem.getPower() == 0)
                polynomString.append(sign).append(coefAbsValue);

            if (polynomItem.getPower() != 0)
                polynomString.append(sign).append(coefAbsValue + "x" + "^" + power);
        });

        if (Objects.equals(polynomString.substring(0, 3), " - ")) {
            polynomString.replace(0,3,"-");
        }

        if (Objects.equals(polynomString.substring(0, 3), " + ")) {
            polynomString.delete(0,3);
        }

        return polynomString.toString();
    }
}

