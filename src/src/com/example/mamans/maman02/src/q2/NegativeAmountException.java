package com.example.mamans.maman02.src.q2;

/**
 * <h3> NegativeAmountException </h3>
 *
 * <p>
 *    an exception class handling case of negative amount deposit or withdrawn
 * </p>
 * <br>
 * <p> Note: HTML tags helps to improve comments readability in editors like IntelliJ that support it </p>
 *
 * @maman   02
 * @question    2
 * @author  Omer Shraibshtein (205984271)
 * @email   omershreib@gmail.com
 * @since   2026-05-14
 * */

public class NegativeAmountException extends Exception{

    public NegativeAmountException(String errorMessage) {
        super(errorMessage);
    }
}
