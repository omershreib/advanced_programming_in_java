package com.example.mamans.maman02.src.q2;

/**
 * <h3> HighInterestSavings </h3>
 *
 * <p>
 *     this class defines a bank account type high interest savings
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

public class HighInterestSavings extends SavingsAccount {

    private int minimumAllowedBalance = 1000;
    private double interestRate = 0.2;



    /** HighInterestSavings constructors
     *
     * @param minimumAllowedBalance set the minimal balance allowed to this bank account
     * @param accountId bank account id
     * @param owner first and last owner name
     * @param ownerPID owner personal-id
     * @param balance pre-define balance
     * */
    public HighInterestSavings(int minimumAllowedBalance, String accountId, String owner, String ownerPID, double balance) {
        super(minimumAllowedBalance, accountId, owner, ownerPID, balance);
    }


    /** HighInterestSavings constructors
     *
     * @param accountId bank account id
     * @param owner first and last owner name
     * @param ownerPID owner personal-id
     * @param balance pre-define balance
     * */
    public HighInterestSavings(String accountId, String owner, String ownerPID, double balance) {
        super(accountId, owner, ownerPID, balance);
    }

    /** HighInterestSavings constructors
     *
     * @param minimumAllowedBalance set the minimal balance allowed to this bank account
     * @param accountId bank account id
     * @param owner first and last owner name
     * @param ownerPID owner personal-id*
     * */
    public HighInterestSavings(int minimumAllowedBalance, String accountId, String owner, String ownerPID) {
        super(minimumAllowedBalance, accountId, owner, ownerPID);
    }

    /** HighInterestSavings constructors
     *
     * @param accountId bank account id
     * @param owner first and last owner name
     * @param ownerPID owner personal-id
     * */
    public HighInterestSavings(String accountId, String owner, String ownerPID) {
        super(accountId, owner, ownerPID);
    }


    /** HighInterestSavings constructors
     *
     * @param minimumAllowedBalance set the minimal balance allowed to this bank account
     * @param interestRate monthly rate of interest
     * @param accountId bank account id
     * @param owner first and last owner name
     * @param ownerPID owner personal-id
     * @param balance pre-define balance
     * */
    public HighInterestSavings(int minimumAllowedBalance, double interestRate, String accountId, String owner, String ownerPID, double balance) {
        super(minimumAllowedBalance, accountId, owner, ownerPID, balance);
        this.interestRate = interestRate;
    }

    /** HighInterestSavings constructors
     *
     * @param interestRate monthly rate of interest
     * @param accountId bank account id
     * @param owner first and last owner name
     * @param ownerPID owner personal-id
     * @param balance pre-define balance
     * */
    public HighInterestSavings(double interestRate, String accountId, String owner, String ownerPID, double balance) {
        super(accountId, owner, ownerPID, balance);
        this.interestRate = interestRate;
    }

    /** HighInterestSavings constructors
     *
     * @param minimumAllowedBalance set the minimal balance allowed to this bank account
     * @param interestRate monthly rate of interest
     * @param accountId bank account id
     * @param owner first and last owner name
     * @param ownerPID owner personal-id
     * */
    public HighInterestSavings(int minimumAllowedBalance, double interestRate, String accountId, String owner, String ownerPID) {
        super(minimumAllowedBalance, accountId, owner, ownerPID);
        this.interestRate = interestRate;
    }

    /** HighInterestSavings constructors
     *
     * @param interestRate monthly rate of interest
     * @param accountId bank account id
     * @param owner first and last owner name
     * @param ownerPID owner personal-id
     * */
    public HighInterestSavings(double interestRate, String accountId, String owner, String ownerPID) {
        super(accountId, owner, ownerPID);
        this.interestRate = interestRate;
    }
    

    public int getMinimumAllowedBalance() {
        return minimumAllowedBalance;
    }

    public void setMinimumAllowedBalance(int minimumAllowedBalance) {
        this.minimumAllowedBalance = minimumAllowedBalance;
    }
}
