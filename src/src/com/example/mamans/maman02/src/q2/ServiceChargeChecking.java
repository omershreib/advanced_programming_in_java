package com.example.mamans.maman02.src.q2;

import javax.swing.*;

/**
 * <h3> ServiceChargeChecking </h3>
 *
 * <p>
 *     this class defines a bank account type service charge checking
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

public class ServiceChargeChecking extends CheckingAccount {

    private static final int DEFAULT_MONTHLY_FEE = 5;
    private int monthlyFee;


    public void setMonthlyFee(int monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public int getMonthlyFee() {
        return monthlyFee;
    }


    /** ServiceChargeChecking constructors
     *
     * @param monthlyFee the monthly cost defined to manage this bank account
     * @param accountId bank account id
     * @param owner first and last owner name
     * @param ownerPID owner personal-id
     * @param balance pre-define balance
     * */
    public ServiceChargeChecking(int monthlyFee, String accountId, String owner, String ownerPID, double balance) {
        super(accountId, owner, ownerPID, balance);
        this.setMonthlyFee(monthlyFee);
    }

    /** ServiceChargeChecking constructors
     *
     * @param accountId bank account id
     * @param owner first and last owner name
     * @param ownerPID owner personal-id
     * @param balance pre-define balance
     * */
    public ServiceChargeChecking(String accountId, String owner, String ownerPID, double balance) {
        super(accountId, owner, ownerPID, balance);
        this.setMonthlyFee(DEFAULT_MONTHLY_FEE);
    }

    /** ServiceChargeChecking constructors
     *
     * @param monthlyFee the monthly cost defined to manage this bank account
     * @param accountId bank account id
     * @param owner first and last owner name
     * @param ownerPID owner personal-id
     * */
    public ServiceChargeChecking(int monthlyFee, String accountId, String owner, String ownerPID) {
        super(accountId, owner, ownerPID);
        this.setMonthlyFee(monthlyFee);
    }

    /** ServiceChargeChecking constructors
     *
     * @param accountId bank account id
     * @param owner first and last owner name
     * @param ownerPID owner personal-id
     * */
    public ServiceChargeChecking(String accountId, String owner, String ownerPID) {
        super(accountId, owner, ownerPID);
        this.setMonthlyFee(DEFAULT_MONTHLY_FEE);
    }


    /** apply monthly account management
     *
     * for SavingAccount, apply fee payment to the bank (reduce the current balance in the value of the fee)
     * */
    @Override
    public void applyMonthlyManagement() {
        System.out.println("apply monthly management on " + this.getOwner() + " (" + this.getAccountId() + ")");
        this.setBalance(this.getBalance() - monthlyFee);
    }

    /**
     * compare between two bank accounts
     *
     * @param obj
     * @return true if-and-only-if it is a ServiceChargeChecking class object that all its attribute identical with this
     * BankAccount class object.
     * */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof ServiceChargeChecking) {
            ServiceChargeChecking otherBankAccount = (ServiceChargeChecking) obj;

            return otherBankAccount.getAccountId().equals(this.getAccountId()) &&
                    otherBankAccount.getOwner().equals(this.getOwner()) &&
                    otherBankAccount.getOwnerPID().equals(this.getOwnerPID()) &&
                    otherBankAccount.getBalance() == this.getBalance() &&
                    otherBankAccount.getMonthlyFee() == this.getMonthlyFee();

        }

        return false;
    }

    @Override
    public String toString() {
        return "<InterestChecking" +
                " ; AccountId: " + this.getAccountId() +
                " ; Owner: " + this.getOwner() +
                " ; Owner Personal-ID: " + this.getOwnerPID() +
                " ; Monthly Service Fee: " + this.getMonthlyFee() +
                " ; Balance: $" + this.getBalance() + ">";
    }
}
