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
    private static final int DEFAULT_MINIMUM_ALLOWED_BALANCE = 1000;
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
        this.setMinimumAllowedBalance(DEFAULT_MINIMUM_ALLOWED_BALANCE);

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
        this.setMinimumAllowedBalance(DEFAULT_MINIMUM_ALLOWED_BALANCE);

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
        this.setInterestRate(interestRate);
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
        this.setInterestRate(interestRate);
        this.setMinimumAllowedBalance(DEFAULT_MINIMUM_ALLOWED_BALANCE);
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
        this.setMinimumAllowedBalance(DEFAULT_MINIMUM_ALLOWED_BALANCE);
        this.setInterestRate(interestRate);
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    /** apply monthly account management
     *
     * for InterestChecking, credits the customer's account with additional money according to the interest rate defined for the account.
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
     * @return true if-and-only-if it is a InterestChecking class object that all its attribute identical with this
     * BankAccount class object.
     * */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof InterestChecking) {
            InterestChecking otherBankAccount = (InterestChecking) obj;

            return otherBankAccount.getAccountId().equals(this.getAccountId()) &&
                    otherBankAccount.getOwner().equals(this.getOwner()) &&
                    otherBankAccount.getOwnerPID().equals(this.getOwnerPID()) &&
                    otherBankAccount.getBalance() == this.getBalance() &&
                    otherBankAccount.getMinimumAllowedBalance() == this.getMinimumAllowedBalance() &&
                    Double.compare(otherBankAccount.getInterestRate(), this.getInterestRate()) == 0;
        }

        return false;
    }

    @Override
    public String toString() {
        return "<InterestChecking" +
                " ; AccountId: " + this.getAccountId() +
                " ; Owner: " + this.getOwner() +
                " ; Owner Personal-ID: " + this.getOwnerPID() +
                " ; Monthly Interest Rate: " + this.getInterestRate() +
                " ; Minimum Allowed Balance: " + this.getMinimumAllowedBalance() +
                " ; Balance: $" + this.getBalance() + ">";
    }
}
