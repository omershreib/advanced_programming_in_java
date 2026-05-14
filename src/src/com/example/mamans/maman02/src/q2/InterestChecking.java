package com.example.mamans.maman02.src.q2;

public class InterestChecking extends NoServiceChargeChecking {

    private double minimumAllowedBalance = 1000.0;
    private double interestRate = 0.1;

    public InterestChecking(int minimumAllowedBalance, String accountId, String owner, String ownerPID, double balance) {
        super(minimumAllowedBalance, accountId, owner, ownerPID, balance);
    }

    public InterestChecking(String accountId, String owner, String ownerPID, double balance) {
        super(accountId, owner, ownerPID, balance);
    }

    public InterestChecking(int minimumAllowedBalance, String accountId, String owner, String ownerPID) {
        super(minimumAllowedBalance, accountId, owner, ownerPID);
    }

    public InterestChecking(String accountId, String owner, String ownerPID) {
        super(accountId, owner, ownerPID);
    }

    public InterestChecking(int minimumAllowedBalance, double interestRate, String accountId, String owner, String ownerPID, double balance) {
        super(minimumAllowedBalance, accountId, owner, ownerPID, balance);
        this.interestRate = interestRate;
    }

    public InterestChecking(double interestRate, String accountId, String owner, String ownerPID, double balance) {
        super(accountId, owner, ownerPID, balance);
        this.interestRate = interestRate;
    }

    public InterestChecking(int minimumAllowedBalance, double interestRate, String accountId, String owner, String ownerPID) {
        super(minimumAllowedBalance, accountId, owner, ownerPID);
        this.interestRate = interestRate;
    }

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
