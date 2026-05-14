package com.example.mamans.maman02.src.q2;

public class HighInterestSavings extends SavingsAccount {

    private double minimumAllowedBalance = 1000.0;
    private double interestRate = 0.2;

    public HighInterestSavings(int minimumAllowedBalance, String accountId, String owner, String ownerPID, double balance) {
        super(minimumAllowedBalance, accountId, owner, ownerPID, balance);
    }

    public HighInterestSavings(String accountId, String owner, String ownerPID, double balance) {
        super(accountId, owner, ownerPID, balance);
    }

    public HighInterestSavings(int minimumAllowedBalance, String accountId, String owner, String ownerPID) {
        super(minimumAllowedBalance, accountId, owner, ownerPID);
    }

    public HighInterestSavings(String accountId, String owner, String ownerPID) {
        super(accountId, owner, ownerPID);
    }

    public HighInterestSavings(int minimumAllowedBalance, double interestRate, String accountId, String owner, String ownerPID, double balance) {
        super(minimumAllowedBalance, accountId, owner, ownerPID, balance);
        this.interestRate = interestRate;
    }

    public HighInterestSavings(double interestRate, String accountId, String owner, String ownerPID, double balance) {
        super(accountId, owner, ownerPID, balance);
        this.interestRate = interestRate;
    }

    public HighInterestSavings(int minimumAllowedBalance, double interestRate, String accountId, String owner, String ownerPID) {
        super(minimumAllowedBalance, accountId, owner, ownerPID);
        this.interestRate = interestRate;
    }

    public HighInterestSavings(double interestRate, String accountId, String owner, String ownerPID) {
        super(accountId, owner, ownerPID);
        this.interestRate = interestRate;
    }

    public double getMinimumAllowedBalance() {
        return minimumAllowedBalance;
    }

    public void setMinimumAllowedBalance(double minimumAllowedBalance) {
        this.minimumAllowedBalance = minimumAllowedBalance;
    }
}
