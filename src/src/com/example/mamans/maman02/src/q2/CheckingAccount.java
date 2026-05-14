package com.example.mamans.maman02.src.q2;

public class CheckingAccount extends BankAccount {
    public CheckingAccount(String accountId, String owner, String ownerPID, double balance) {
        super(accountId, owner, ownerPID, balance);
    }

    public CheckingAccount(String accountId, String owner, String ownerPID) {
        super(accountId, owner, ownerPID);
    }

    public String writeCheck(int amount) throws IllegalBalanceException, NegativeAmountException {

        this.validateAmount(amount);

        return "\n" +
                "=========================================\n" +
                "                 BANK CHECK              \n" +
                "=========================================\n" +
                "Account ID : " + this.getAccountId() + "\n" +
                "Owner      : " + this.getOwner() + "\n" +
                "Owner PID  : " + this.getOwnerPID() + "\n" +
                "-----------------------------------------\n" +
                "Pay To     :   ______________\n" +
                "AccountId  :   ______________\n" +
                "Amount     :   $" + amount + "\n" +
                "-----------------------------------------\n" +
                "This Check Is Approved By The Bank" +
                "\nAccording To The Current Owner Balance\n" +
                "=========================================\n" +
                "Name:          " + this.getOwner() + "\n\n" +
                "Date:          ______________\n\n" +
                "Signature:     ______________\n" +
                "=========================================\n";
    }
}
