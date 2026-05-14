package com.example.mamans.maman02.src.q1;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <h3> Polynom </h3>
 *
 * <p>
 *     this class defines a polynom as an ordered (by power from high to low) ArrayList of PolynomItem class objects.
 *     each PolynomItem contains a coefficient and a power attribute.
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

public class Polynom {



    // coefficient floating point print accuracy setup
    private static final int DOUBLE_ROUND_FORMAT = 1;
    private static final int PLUSMINUS_SUBSTRING_START_INDEX = 0;
    private static final int PLUSMINUS_SUBSTRING_END_INDEX = 3;
    private static final Double zero = 0.0;


    // required to sort polynom components from the highest power to the lowest power
    private static final int POLYNOM_SORT_MAGIC_FACTOR = -1;
    private ArrayList<PolynomItem> polynom;

    // defines this exception message once (not need to create it every time)
    public static final CoefsAndPowersLengthMismatchException coefsAndPowersLengthMismatchException =
            new CoefsAndPowersLengthMismatchException("coefficients and powers lists must have an equal length");


    /** a constructor to create the zero polynom p(x) = 0 **/
    public Polynom() throws CoefsAndPowersLengthMismatchException {
        this(Collections.singletonList(0.0), Collections.singletonList(0));
    }

    /**
     *
     * Polynom contractor
     *  <br><br>
     *  procedure:
     *  <ol>
     *      <li>validate that coefs and powers lists have equal length</li>
     *      <li>initialize class.polynom as an empty ArrayList of PolynomItems</li>
     *      <li>next, fill it with PolynomItems as pairs of (coef[i], power[i])</li>
     *      <li>PolynomItems with a zero (0) coefficient will be removed from this ArrayList (so why in the first place?)</li>
     *      <li>finally, sort ArrayList from high power to low power</li>
     *  </ol>
     *
     * @param coefs a List of doubles
     * @param powers a List of integers
     *
     * **/
    public Polynom (List<Double> coefs, List<Integer> powers) throws CoefsAndPowersLengthMismatchException {
        if (coefs.size() != powers.size()) {
            throw coefsAndPowersLengthMismatchException;
        }

        int polynomLength = coefs.size();
        this.polynom = new ArrayList<>();

        for (int i = 0; i < polynomLength; i++)
            this.polynom.add(i, new PolynomItem(coefs.get(i), powers.get(i)));

        // remove all pairs with coefficient equal to zero
        int firstZeroIndex = this.getFirstCoefIndexOf(zero);

        while (firstZeroIndex >= 0) {
            this.polynom.remove(firstZeroIndex);
            firstZeroIndex = this.getFirstCoefIndexOf(zero);
        }

        // apply just in case we have two different polynomItems with the same power
        this.simplify();

        this.polynom.sort(Comparator.comparing(item -> item.getPower() * POLYNOM_SORT_MAGIC_FACTOR));
    }

    private void simplify() throws CoefsAndPowersLengthMismatchException {

        Set<Integer> allPowers = new HashSet<>();
        polynom.forEach(polynomItem -> allPowers.add(polynomItem.getPower()));

        // if the number of polynomItems equals to the number of powers
        // then this polynom is in it the simplest state
        boolean isSimplest = (allPowers.size() == polynom.size());

        if (!(isSimplest)) {
            HashMap<Integer, Double> powersToCoefSum = new HashMap<>();
            allPowers.forEach(power -> powersToCoefSum.putIfAbsent(power,zero));

            polynom.forEach(polynomItem -> {
                int key = polynomItem.getPower();
                double value = powersToCoefSum.get(key);
                powersToCoefSum.put(key, value + polynomItem.getCoef());
            });

            Polynom p = this.ConstructPolynomFromHashMap(powersToCoefSum);
            this.polynom = p.polynom;
        }
    }

    /**
     * construct a Polynom from power->coef hash-map
     * <br><br>
     * note: I created this method to avoid unnecessary duplicate lines of code
     * <br>
     *
     * @param hashMap with key=coef and value=power
     * @return a Polynom
     *
     * */
    private Polynom ConstructPolynomFromHashMap(HashMap<Integer, Double> hashMap) throws CoefsAndPowersLengthMismatchException {
        ArrayList<Double> coefs = new ArrayList<>();
        ArrayList<Integer> powers = new ArrayList<>();

        hashMap.forEach((key, value) -> {
            coefs.add(value);
            powers.add(key);
        });

        Polynom costructedPolynom;

        try {
            costructedPolynom = new Polynom(coefs, powers);
        }

        catch (CoefsAndPowersLengthMismatchException e) {
            throw coefsAndPowersLengthMismatchException;
        }

        catch (Exception e) {
            throw new RuntimeException(e);
        }

        return costructedPolynom;
    }

    /**
     *
     * returns the index of the first polynomItem in class.polynom with a specific coefficient.
     * <br>
     * if it does not exist, returns -1
     *
     * @param coef a double
     * @return i    if (coef_i, power_i) ← class.polynom[i] is the first polynomItem where coef_i == coef
     *              <br>otherwise, -1
     * */
    private int getFirstCoefIndexOf(double coef) {
        for (int i = 0; i < this.polynom.size(); i++) {
            PolynomItem polynomItem = this.polynom.get(i);
            if (Objects.equals(polynomItem.getCoef(), coef)) return i;
        }

        return -1;
    }

    /**
     * returns the coefficient that bounded to the polynomItem with a specific power.
     * <br>
     * this assuming that this polynom is at its simplest form
     * <br>(namely, there are no two different polynomItems that shared the same power)
     * <br>
     * if it does not exist, returns class.zero (0.0)
     *
     *
     * @param power an integer
     * @return a double `c` that equals to the coefficient number of cX^power exists in class.polynom
     * */
    private Double getCoefOf(int power) {
        for (PolynomItem polynomItem : this.polynom) {
            if (Objects.equals(polynomItem.getPower(), power)) return polynomItem.getCoef();
        }

        return zero;
    }

    /** returns the derivative of this Polynom class object
     * <br><br>
     * mathematically, the derivative of a polynom equals to the sum of all its polynomItems derivatives
     *
     * @return the derivative of this polynom
     * * */
    public Polynom derivative() throws CoefsAndPowersLengthMismatchException{

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
            throw Polynom.coefsAndPowersLengthMismatchException;
        }

        catch (Exception e) {
            throw new RuntimeException(e);
        }

        return deivativePolynom;
    }

    /**
     * provides plus supports between 2 Polynoms class objects, so:
     * <br>
     * if p, q two Polynoms class objects, then
     * p.plus(q) equivalents to p(x) + q(x)
     * <br><br>
     * <p>
     *     procedure:
     *     <ol>
     *         <li>create a set of all powers collected from both polynoms</li>
     *         <li>initialize a power->coef hash-map helping in this polynom sum result construction</li>
     *         <li>iterate other both polynoms components to resolve that hash-map[power] = coef_p + coef_q
     *         (w.l.o.g, if p(x) does not has a polynomItem with such power, then coef_p = 0.0)</li>
     *         <li>finally, split power->coef hash-map into 2 lists: coefs and powers, and reconstruct
     *         <br>the p(x) + q(x) Polynom class object </li>
     *     </ol>
     * </p>
     *
     * <br><br>
     * by the way, I try to add the plus (+) symbol to wrap this plus method to achieve p + q functionality,
     * but unfortunately Java does not support this (probably Kotlin do).
     * I did not check bt it is likely that same lack of support applied on the minus (-)
     *
     * @return a Polynom class object represent the sum of 2 others Polynoms class objects
     * */
    public Polynom plus(Polynom other) throws CoefsAndPowersLengthMismatchException {
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

        return this.ConstructPolynomFromHashMap(powersToCoefSum);
    }


    /**
     * provides minus supports between 2 Polynoms class objects, so:
     * <br>
     * if p, q two Polynoms class objects, then
     * p.minus(q) equivalents to p(x) - q(x)
     * <br><br>
     * <p>
     *     procedure:
     *     <ol>
     *         <li>create a set of all powers collected from both polynoms</li>
     *         <li>initialize a power->coef hash-map helping in this polynom sub result construction</li>
     *         <li>iterate other both polynoms components to resolve that hash-map[power] = coef_p - coef_q
     *         (w.l.o.g, if p(x) does not has a polynomItem with such power, then coef_p = 0.0)</li>
     *         <li>finally, split power->coef hash-map into 2 lists: coefs and powers, and reconstruct
     *         <br>the p(x) - q(x) Polynom class object </li>
     *     </ol>
     * </p>
     *
     * @return a Polynom class object represent the subtraction of 2 others Polynoms class objects
     * */
    public Polynom minus(Polynom other) throws CoefsAndPowersLengthMismatchException {
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

        return this.ConstructPolynomFromHashMap(powersToCoefSub);
    }


    /**
     * a wrapper function (with Object.equals inherit method) to add the (==) symbol support when checking Polynoms equality
     * <br><br>
     * technically, p == q and p.isEqualTo(q) are almost the same, with one little difference: the isEqualTo assume
     * that the compared other class object in a Polynom.
     * <br>
     * note: I cannot add Exception here since the signature of Object.equals does not include Exception throwing.
     *      (I consider to through IsNotAPolynomException instead of this print)
     *
     * @param obj hopefully a Polynom
     * @return  the boolean result of class.isEqualTo((Polynom) obj)
     *          if obj is not a Polynom class object then return false
     * */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Polynom)) {
            System.out.println("the other object is not a Polynom: " + obj.toString() + ". returns false");
            return false;
        }

        return this.isEqualTo((Polynom) obj);

    }

    /**
     * isEqualTo inner method of the override Object.equals
     * <br>
     * <p>
     *      mathematically, two polynoms:
     *      <br> p(x) := a1X^e1 + a2X^e2 + ... a_kX^e_k     (left side)
     *      <br> q(x) := b1X^r1 + c2X^r2 + ... c_kX^r_m     (right side)
     *      <br>
     *      are equals iff, for both left and right directions, their coefficients per power is equals, meaning that:
     *      <br> from the left size, for every shared power e so aX^e exists in p(x) and bX^e exists in q(x), a == b
     *      <br> from the right size, for every shared power r so aX^r exists in p(x) and bX^r exists in q(x), a == b
     * </p>
     * <br>
     * note: I hop it is ok that I used AtomicBoolean because boolean variable cannot be used
     * inside a lambda expression in Java.
     *
     * @return true if-and-only-if (iff) class.Polynom and other.Polynom represent the same polynom.
     * */
    public boolean isEqualTo(Polynom other) {

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

    /**
     * note: DOUBLE_ROUND_FORMAT controls the floating point level of accuracy of this polynom coefficients representation
     *
     * @return a nice String representation of this polynom
     * */
    @Override
    public String toString() {

        // in case it is the zero polynom
        if (this.polynom.isEmpty()) {
            return "0";
        }

        StringBuilder polynomString = new StringBuilder();

        this.polynom.forEach(polynomItem -> {

            Double coef = polynomItem.getCoef();

            String coefAbsValue = String.format("%." + DOUBLE_ROUND_FORMAT + "f", Math.abs(coef));
            String power = polynomItem.getPower().toString();

            // pick +/- prefix symbol according the sign of coef
            String sign = coef > zero.intValue() ? " + " : " - ";

            boolean isPowerZero = (polynomItem.getPower() == zero.intValue());

            if (isPowerZero)
                polynomString.append(sign).append(coefAbsValue);

            if (!isPowerZero)
                polynomString.append(sign).append(coefAbsValue + "x" + "^" + power);
        });

        // final cosmetic touch in the prefix of polynomString - so:
        // - 3.0X will look as -3.0X ; + 3.0X will look as 3.0X
        if (Objects.equals(polynomString.substring(PLUSMINUS_SUBSTRING_START_INDEX, PLUSMINUS_SUBSTRING_END_INDEX), " - ")) {
            polynomString.replace(PLUSMINUS_SUBSTRING_START_INDEX,PLUSMINUS_SUBSTRING_END_INDEX,"-");
        }

        if (Objects.equals(polynomString.substring(PLUSMINUS_SUBSTRING_START_INDEX, PLUSMINUS_SUBSTRING_END_INDEX), " + ")) {
            polynomString.delete(PLUSMINUS_SUBSTRING_START_INDEX,PLUSMINUS_SUBSTRING_END_INDEX);
        }

        return polynomString.toString();
    }
}

