package mamans.maman02.src.q1;


/**
 * <h3> CoefsAndPowersLengthMismatchException </h3>
 *
 * <p>
 *     exception class object to handle with Polynom construction attempt with coefficients and powers lists
 *     that are not have the same length
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
public class CoefsAndPowersLengthMismatchException extends Exception {

    public CoefsAndPowersLengthMismatchException(String errorMessage) {
        super(errorMessage);
    }
}
