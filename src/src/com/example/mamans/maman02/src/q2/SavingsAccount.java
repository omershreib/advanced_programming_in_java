package com.example.mamans.maman02.src.q2;


/**
 * <h3> SavingsAccount </h3>
 *
 * <p>
 *     this class defines a bank account type savings account abstract class
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

public class SavingsAccount extends BankAccount {

    protected double interestRate = 0.1;

    /** SavingsAccount constructors
     *
     * @param accountId bank account id
     * @param owner first and last owner name
     * @param ownerPID owner personal-id
     * @param balance pre-define balance
     * */
    public SavingsAccount(String accountId, String owner, String ownerPID, double balance) {
        super(accountId, owner, ownerPID, balance);
    }

    /** SavingsAccount constructors
     *
     * @param accountId bank account id
     * @param owner first and last owner name
     * @param ownerPID owner personal-id
     * */
    public SavingsAccount(String accountId, String owner, String ownerPID) {
        super(accountId, owner, ownerPID);
    }


    /** SavingsAccount constructors
     *
     * @param interestRate monthly rate of interest
     * @param accountId bank account id
     * @param owner first and last owner name
     * @param ownerPID owner personal-id
     * @param balance pre-define balance
     * */
    public SavingsAccount(double interestRate, String accountId, String owner, String ownerPID, double balance) {
        super(accountId, owner, ownerPID, balance);
        this.setInterestRate(interestRate);
    }


    /** SavingsAccount constructors
     *
     * @param interestRate monthly rate of interest
     * @param accountId bank account id
     * @param owner first and last owner name
     * @param ownerPID owner personal-id
     * */
    public SavingsAccount(double interestRate, String accountId, String owner, String ownerPID) {
        super(accountId, owner, ownerPID);
        this.setInterestRate(interestRate);
    }

    protected void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    protected double getInterestRate() {
        return this.interestRate;
    }

    /** apply monthly account management
     *
     * for SavingAccount, credits the customer's account with additional money according to the interest rate defined for the account.
     * */
    @Override
    protected void applyMonthlyManagement() {
        System.out.println("apply monthly management on " + this.getOwner() + " (" + this.getAccountId() + ")");

        double balance = this.getBalance();
        balance += balance*this.getInterestRate();

        this.setBalance(balance);
    }

    /**
     * compare between two bank accounts
     *
     * @param obj
     * @return true if-and-only-if it is a SavingsAccount class object that all its attribute identical with this
     * BankAccount class object.
     * */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof SavingsAccount) {
            SavingsAccount otherBankAccount = (SavingsAccount) obj;
            
            return otherBankAccount.getAccountId().equals(this.getAccountId()) &&
                    otherBankAccount.getOwner().equals(this.getOwner()) &&
                    otherBankAccount.getOwnerPID().equals(this.getOwnerPID()) &&
                    otherBankAccount.getBalance() == this.getBalance() &&
                    Double.compare(otherBankAccount.getInterestRate(), this.getInterestRate()) == 0;
        }

        return false;
    }

    @Override
    public String toString() {
        return "<SavingsAccount" +
                " ; AccountId: " + this.getAccountId() +
                " ; Owner: " + this.getOwner() +
                " ; Owner Personal-ID: " + this.getOwnerPID() +
                " ; Monthly Interest Rate: " + this.getInterestRate() +
                " ; Balance: $" + this.getBalance() + ">";
    }


}
