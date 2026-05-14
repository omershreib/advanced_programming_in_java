package com.example.mamans.maman02.src.q2;

public class SavingsAccount extends BankAccount {

    private double interestRate = 0.1;
    public SavingsAccount(String accountId, String owner, String ownerPID, double balance) {
        super(accountId, owner, ownerPID, balance);
    }

    public SavingsAccount(String accountId, String owner, String ownerPID) {
        super(accountId, owner, ownerPID);
    }

    public SavingsAccount(double interestRate, String accountId, String owner, String ownerPID, double balance) {
        super(accountId, owner, ownerPID, balance);
        this.interestRate = interestRate;
    }


    public SavingsAccount(double interestRate, String accountId, String owner, String ownerPID) {
        super(accountId, owner, ownerPID);
        this.interestRate = interestRate;
    }

    public void applyMonthlyManagement() {
        System.out.println("apply monthly management on " + this.getOwner() + " (" + this.getAccountId() + ")");

        double balance = this.getBalance();
        balance += balance*this.interestRate;

        this.setBalance(balance);
    }
}
