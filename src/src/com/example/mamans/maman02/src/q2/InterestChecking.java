package com.example.mamans.maman02.src.q2;

/**
 * <h3> InterestChecking </h3>
 *
 * <p>
 *     this class defines a bank account type interest checking
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

public class InterestChecking extends NoServiceChargeChecking {

    private int minimumAllowedBalance = 1000;
    private double interestRate = 0.1;


    /** InterestChecking constructors
     *
     * @param minimumAllowedBalance set the minimal balance allowed to this bank account
     * @param accountId bank account id
     * @param owner first and last owner name
     * @param ownerPID owner personal-id
     * @param balance pre-define balance
     * */
    public InterestChecking(int minimumAllowedBalance, String accountId, String owner, String ownerPID, double balance) {
        super(minimumAllowedBalance, accountId, owner, ownerPID, balance);
    }

    /** InterestChecking constructors
     *
     * @param accountId bank account id
     * @param owner first and last owner name
     * @param ownerPID owner personal-id
     * @param balance pre-define balance
     * */
    public InterestChecking(String accountId, String owner, String ownerPID, double balance) {
        super(accountId, owner, ownerPID, balance);
    }

    /** InterestChecking constructors
     *
     * @param minimumAllowedBalance set the minimal balance allowed to this bank account
     * @param accountId bank account id
     * @param owner first and last owner name
     * @param ownerPID owner personal-id
     * */
    public InterestChecking(int minimumAllowedBalance, String accountId, String owner, String ownerPID) {
        super(minimumAllowedBalance, accountId, owner, ownerPID);
    }

    /** InterestChecking constructors
     *
     * @param accountId bank account id
     * @param owner first and last owner name
     * @param ownerPID owner personal-id
     * */
    public InterestChecking(String accountId, String owner, String ownerPID) {
        super(accountId, owner, ownerPID);
    }

    /** InterestChecking constructors
     *
     * @param minimumAllowedBalance set the minimal balance allowed to this bank account
     * @param interestRate monthly rate of interest
     * @param accountId bank account id
     * @param owner first and last owner name
     * @param ownerPID owner personal-id
     * @param balance pre-define balance
     * */
    public InterestChecking(int minimumAllowedBalance, double interestRate, String accountId, String owner, String ownerPID, double balance) {
        super(minimumAllowedBalance, accountId, owner, ownerPID, balance);
        this.interestRate = interestRate;
    }

    /** InterestChecking constructors
     *
     * @param interestRate monthly rate of interest
     * @param accountId bank account id
     * @param owner first and last owner name
     * @param ownerPID owner personal-id
     * @param balance pre-define balance
     * */
    public InterestChecking(double interestRate, String accountId, String owner, String ownerPID, double balance) {
        super(accountId, owner, ownerPID, balance);
        this.interestRate = interestRate;
    }

    /** InterestChecking constructors
     *
     * @param minimumAllowedBalance set the minimal balance allowed to this bank account
     * @param interestRate monthly rate of interest
     * @param accountId bank account id
     * @param owner first and last owner name
     * @param ownerPID owner personal-id
     * */
    public InterestChecking(int minimumAllowedBalance, double interestRate, String accountId, String owner, String ownerPID) {
        super(minimumAllowedBalance, accountId, owner, ownerPID);
        this.interestRate = interestRate;
    }

    /** InterestChecking constructors
     *
     * @param interestRate monthly rate of interest
     * @param accountId bank account id
     * @param owner first and last owner name
     * @param ownerPID owner personal-id
     * */
    public InterestChecking(double interestRate, String accountId, String owner, String ownerPID) {
        super(accountId, owner, ownerPID);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void applyMonthlyManagement() {
        System.out.println("apply monthly management on " + this.getOwner() + " (" + this.getAccountId() + ")");

        double balance = this.getBalance();
        balance += balance*this.interestRate;

        this.setBalance(balance);
    }
}
