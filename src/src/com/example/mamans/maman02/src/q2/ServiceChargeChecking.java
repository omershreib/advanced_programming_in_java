package com.example.mamans.maman02.src.q2;

public class ServiceChargeChecking extends CheckingAccount {

    private int monthlyFee = 5;

    public void setMonthlyFee(int monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public int getMonthlyFee() {
        return monthlyFee;
    }

    public ServiceChargeChecking(int monthlyFee, String accountId, String owner, String ownerPID, double balance) {
        super(accountId, owner, ownerPID, balance);
        this.monthlyFee = monthlyFee;
    }

    public ServiceChargeChecking(String accountId, String owner, String ownerPID, double balance) {
        super(accountId, owner, ownerPID, balance);
    }

    public ServiceChargeChecking(int monthlyFee, String accountId, String owner, String ownerPID) {
        super(accountId, owner, ownerPID);
        this.monthlyFee = monthlyFee;
    }

    public ServiceChargeChecking(String accountId, String owner, String ownerPID) {
        super(accountId, owner, ownerPID);
    }

    public void applyMonthlyManagement() {
        System.out.println("apply monthly management on " + this.getOwner() + " (" + this.getAccountId() + ")");
        this.setBalance(this.getBalance() - monthlyFee);
    }
}
