package com.example.mamans.maman02.src.q2;

/**
 * <h3> ServiceChargeChecking </h3>
 *
 * <p>
 *     this class defines a bank account type no service charge checking
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

public class NoServiceChargeChecking extends CheckingAccount {

    private static final int DEFAULT_MINIMUM_ALLOWED_BALANCE = 10;

    private int minimumAllowedBalance;

    /** NoServiceChargeChecking constructors
     *
     * @param minimumAllowedBalance set the minimal balance allowed to this bank account
     * @param accountId bank account id
     * @param owner first and last owner name
     * @param ownerPID owner personal-id
     * @param balance pre-define balance
     * */
    public NoServiceChargeChecking(int minimumAllowedBalance, String accountId, String owner, String ownerPID, double balance) {
        super(accountId, owner, ownerPID, balance);
        this.setMinimumAllowedBalance(DEFAULT_MINIMUM_ALLOWED_BALANCE);
    }

    /** NoServiceChargeChecking constructors
     *
     * @param accountId bank account id
     * @param owner first and last owner name
     * @param ownerPID owner personal-id
     * @param balance pre-define balance
     * */
    public NoServiceChargeChecking(String accountId, String owner, String ownerPID, double balance) {
        super(accountId, owner, ownerPID, balance);
    }

    /** NoServiceChargeChecking constructors
     *
     * @param minimumAllowedBalance set the minimal balance allowed to this bank account
     * @param accountId bank account id
     * @param owner first and last owner name
     * @param ownerPID owner personal-id
     * */
    public NoServiceChargeChecking(int minimumAllowedBalance, String accountId, String owner, String ownerPID) {
        super(accountId, owner, ownerPID);
        this.setMinimumAllowedBalance(DEFAULT_MINIMUM_ALLOWED_BALANCE);
    }

    /** NoServiceChargeChecking constructors
     *
     * @param accountId bank account id
     * @param owner first and last owner name
     * @param ownerPID owner personal-id
     * */
    public NoServiceChargeChecking(String accountId, String owner, String ownerPID) {
        super(accountId, owner, ownerPID);
    }


    public double getMinimumAllowedBalance() {
        return minimumAllowedBalance;
    }

    public void setMinimumAllowedBalance(int minimumAllowedBalance) {
        this.minimumAllowedBalance = minimumAllowedBalance;
    }

    /**
     * withdrawal from bank account
     * <br><br>
     * note: NoServiceChargeChecking bank account cannot withdraw more than it allowed minimum balance
     *
     * @param amount how much to subtract from current balance
     * */
    @Override
    public void withdrawal(double amount) throws NegativeAmountException, IllegalBalanceException {
        this.validateAmount(amount);

        double result = this.getBalance() - amount;

        if (result < this.getMinimumAllowedBalance()) {
            throw new IllegalBalanceException("this withdrawal is disallowed!\n" +
                    "following the current customer balance ($" + this.getBalance() + ") " +
                    "the result of this withdrawal: $" + amount + " bypasses minimum allowed balance ($"
                    + this.getMinimumAllowedBalance() + ")");
        }

        System.out.print(this.getOwner() + " (" + this.getOwner() + ") make a withdrawal of " + amount + " from its bank account " + this.getAccountId());
        System.out.println(" (balance is updated from $" + this.getBalance() + " to $" + (this.getBalance()-amount) + ")");

        this.setBalance(this.getBalance() - amount);
    }


    /**
     * compare between two bank accounts
     *
     * @param obj
     * @return true if-and-only-if it is a NoServiceChargeChecking class object that all its attribute identical with this
     * BankAccount class object.
     * */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof NoServiceChargeChecking) {
            NoServiceChargeChecking otherBankAccount = (NoServiceChargeChecking) obj;

            return otherBankAccount.getAccountId().equals(this.getAccountId()) &&
                    otherBankAccount.getOwner().equals(this.getOwner()) &&
                    otherBankAccount.getOwnerPID().equals(this.getOwnerPID()) &&
                    otherBankAccount.getBalance() == this.getBalance() &&
                    otherBankAccount.getMinimumAllowedBalance() == this.getMinimumAllowedBalance();
        }

        return false;
    }

    @Override
    public String toString() {
        return "<InterestChecking" +
                " ; AccountId: " + this.getAccountId() +
                " ; Owner: " + this.getOwner() +
                " ; Owner Personal-ID: " + this.getOwnerPID() +
                " ; Minimum Allowed Balance: " + this.getMinimumAllowedBalance() +
                " ; Balance: $" + this.getBalance() + ">";
    }

    @Override
    protected void applyMonthlyManagement() {

    }

}
