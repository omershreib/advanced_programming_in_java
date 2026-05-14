package com.example.mamans.maman02.src.q2;

public class BankAccount {

    private String accountId;
    private String owner;
    private String ownerPID;
    private double balance = 0.0;


    public BankAccount(String accountId, String owner, String ownerPID, double balance) {
        this.accountId = accountId;
        this.owner = owner;
        this.ownerPID = ownerPID;
        this.balance = balance;
    }

    public BankAccount(String accountId, String owner, String ownerPID) {
        this.accountId = accountId;
        this.owner = owner;
        this.ownerPID = ownerPID;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setOwnerPID(String ownerPID) {
        this.ownerPID = ownerPID;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getOwner() {
        return owner;
    }

    public String getOwnerPID() {
        return ownerPID;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(int amount) throws NegativeAmountException {
        if (amount < 0)
            throw new NegativeAmountException("amount cannot be negative");

        System.out.print(owner + " (" + ownerPID + ") make a deposit of " + amount + " to its bank account " + accountId);
        System.out.println(" (balance is updated from $" + balance + " to $" + (balance+amount) + ")");

        this.balance += amount;
    }

    public void withdrawal(int amount) throws NegativeAmountException, IllegalBalanceException {

        this.validateAmount(amount);

        System.out.print(owner + " (" + ownerPID + ") make a withdrawal of " + amount + " from its bank account " + accountId);
        System.out.println(" (balance is updated from $" + balance + " to $" + (balance-amount) + ")");

        this.balance -= amount;
    }

    @Override
    public String toString() {

        return "<BankAccount" +
                " ; AccountId: " + accountId +
                " ; Owner: " + owner +
                " ; Owner Personal-ID: " + ownerPID +
                " ; Balance: $" + balance + ">";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof BankAccount) {
            BankAccount otherBankAccount = (BankAccount) obj;

            return otherBankAccount.accountId.equals(accountId) &&
                    otherBankAccount.owner.equals(owner) &&
                    otherBankAccount.ownerPID.equals(ownerPID) &&
                    otherBankAccount.balance == balance;
        }

        return false;
    }

    protected void validateAmount(int amount) throws NegativeAmountException, IllegalBalanceException {
        if (amount < 0)
            throw new NegativeAmountException("amount cannot be negative");

        if (balance - amount < 0)
            throw new IllegalBalanceException("this withdrawal is disallowed\naccount "
                    + accountId + " is not permitted to be in overdraft");
    }
}
