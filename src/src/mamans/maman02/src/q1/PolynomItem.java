package mamans.maman02.src.q1;


/**
 * <h3> PolynomItem </h3>
 *
 * <p>
 *     polynomItem pairs a coefficient and a power. with this class object the definition of the
 *     Polynom class, as ArrayList of PolynomItems, is simplified
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


    /**
     * examples:
     * <br>
     * (3X^0)' = (3)' = 0
     * <br>
     * (3X^1)' = (3X)' = 3X^0 = 3
     * <br>
     * (3X^2)' = 3*2X^(2-1) = 6X^1 = 6X
     *
     * @return the derivative of this polynomItem
     * */
    protected PolynomItem derivative() {
        if (this.power == 0) { return new PolynomItem(0.0,0); }
        if (this.power == 1) { return new PolynomItem(this.coef, 0); }

        return new PolynomItem(this.coef * this.power, this.power - 1);
    }

}
