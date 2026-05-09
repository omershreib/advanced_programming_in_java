package com.example.mamans.maman02.src.q1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Polynom p;
        Polynom q;
        Polynom s;


        {
            try {
                p = new Polynom(Arrays.asList(-4.0, 2.8, -6.5, 0.0, 7.0), Arrays.asList(7, 5, 10, 1, 0));
                q = new Polynom(Arrays.asList(1.2), Arrays.asList(5));
                s = new Polynom(Arrays.asList(8.0, -1.0, 7.0, -3.0), Arrays.asList(3, 1, 0, 2));

                Polynom ppq = p.plus(q);
                Polynom pmq = p.minus(q);

                System.out.println("p(x) = " + p);
                System.out.println("q(x) = " + q);

                System.out.println("p(x) + q(x) = " + ppq);
                System.out.println("p(x) - q(x) = " + pmq);

                System.out.println("s(x) = " + s);
                System.out.println("s'(x) = " + s.derivative());

                System.out.println("p(x) == q(x) ? " + p.equals(q));
                System.out.println("s(x) == s(x) ? " + s.equals(s));

            }

            catch (CoefsAndPowersLengthMismatchException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
