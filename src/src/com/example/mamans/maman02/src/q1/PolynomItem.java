package com.example.mamans.maman02.src.q1;

import javafx.util.Pair;

import java.util.ArrayList;

public class PolynomItem {

    private double coef;
    private int power;

    public PolynomItem(double coef, int power) {
        this.coef = coef;
        this.power = power;
    }

    public Double getCoef() {
        return this.coef;
    }

    public Integer getPower() {
        return this.power;
    }


    protected PolynomItem derivative() {
        if (this.power == 0) { return new PolynomItem(0.0,0); }
        if (this.power == 1) { return new PolynomItem(this.coef, 0); }

        return new PolynomItem(this.coef * this.power, this.power - 1);
    }

    protected boolean equals(PolynomItem other) {
        return (this.coef == other.coef && this.power == other.power);
    }

//    public Pair<Double, Integer> toPair() {
//        return new Pair<Double, Integer>(this.coef, this.power);
//    }
}
