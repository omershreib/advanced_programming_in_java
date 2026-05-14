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
        super(accountId, owner, ownerPID, balance);
        this.setMinimumAllowedBalance(minimumAllowedBalance);
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
        super(accountId, owner, ownerPID);
        this.setMinimumAllowedBalance(minimumAllowedBalance);
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
        super(accountId, owner, ownerPID, balance);
        this.setInterestRate(interestRate);
        this.setMinimumAllowedBalance(minimumAllowedBalance);

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
        this.setInterestRate(interestRate);
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
        super(accountId, owner, ownerPID);
        this.setInterestRate(interestRate);
        this.setMinimumAllowedBalance(minimumAllowedBalance);
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
        this.setInterestRate(interestRate);
    }
    

    public int getMinimumAllowedBalance() {
        return minimumAllowedBalance;
    }

    public void setMinimumAllowedBalance(int minimumAllowedBalance) {
        this.minimumAllowedBalance = minimumAllowedBalance;
    }





    /** apply monthly account management
     *
     * for HighInterestSavings, credits the customer's account with additional money according to the interest rate defined for the account.
     * */
    @Override
    public void applyMonthlyManagement() {
        System.out.println("apply monthly management on " + this.getOwner() + " (" + this.getAccountId() + ")");

        double balance = this.getBalance();
        balance += balance*this.getInterestRate();

        this.setBalance(balance);
    }

    /**
     * compare between two bank accounts
     *
     * @param obj
     * @return true if-and-only-if it is a HighInterestSavings class object that all its attribute identical with this
     * BankAccount class object.
     * */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof HighInterestSavings) {
            HighInterestSavings otherBankAccount = (HighInterestSavings) obj;

            return otherBankAccount.getAccountId().equals(this.getAccountId()) &&
                    otherBankAccount.getOwner().equals(this.getOwner()) &&
                    otherBankAccount.getOwnerPID().equals(this.getOwnerPID()) &&
                    otherBankAccount.getBalance() == this.getBalance() &&
                    otherBankAccount.getMinimumAllowedBalance() == this.getMinimumAllowedBalance() &&
                    otherBankAccount.getInterestRate() == this.getInterestRate();

        }

        return false;
    }

    @Override
    public String toString() {
        return "<HighInterestSavings" +
                " ; AccountId: " + this.getAccountId() +
                " ; Owner: " + this.getOwner() +
                " ; Owner Personal-ID: " + this.getOwnerPID() +
                " ; Monthly Interest Rate: " + this.getInterestRate() +
                " ; Minimum Allowed Balance: " + this.getMinimumAllowedBalance() +
                " ; Balance: $" + this.getBalance() + ">";
    }
}
