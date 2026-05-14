package com.example.mamans.maman02.src.q2;

/**
 * <h3> IllegalBalanceException </h3>
 *
 * <p>
 *    an exception class handling case of illegal balance (like disallowed overdraft or withdrawn attempt
 *    that bypass its minimum balance)
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

public class IllegalBalanceException extends Exception {
    public IllegalBalanceException(String errorMessage) {
        super(errorMessage);
    }
}
