package com.example.mamans.maman02.src.q2;

public class NoServiceChargeChecking extends CheckingAccount {

    private int minimumAllowedBalance = 10;
    public NoServiceChargeChecking(int minimumAllowedBalance, String accountId, String owner, String ownerPID, double balance) {
        super(accountId, owner, ownerPID, balance);
        this.minimumAllowedBalance = minimumAllowedBalance;
    }

    public NoServiceChargeChecking(String accountId, String owner, String ownerPID, double balance) {
        super(accountId, owner, ownerPID, balance);
    }

    public NoServiceChargeChecking(int minimumAllowedBalance, String accountId, String owner, String ownerPID) {
        super(accountId, owner, ownerPID);
        this.minimumAllowedBalance = minimumAllowedBalance;
    }

    public NoServiceChargeChecking(String accountId, String owner, String ownerPID) {
        super(accountId, owner, ownerPID);
    }

    public int getMinimumAllowedBalance() {
        return minimumAllowedBalance;
    }

    public void setMinimumAllowedBalance(int minimumAllowedBalance) throws IllegalBalanceException, NegativeAmountException {
        this.validateAmount(minimumAllowedBalance);
        this.minimumAllowedBalance = minimumAllowedBalance;
    }
}
