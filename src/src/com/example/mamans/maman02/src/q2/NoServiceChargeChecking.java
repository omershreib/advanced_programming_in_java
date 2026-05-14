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

    public double getMinimumAllowedBalance() {
        return minimumAllowedBalance;
    }

    public void setMinimumAllowedBalance(int minimumAllowedBalance) {
        //this.validateAmount(minimumAllowedBalance);
        this.minimumAllowedBalance = minimumAllowedBalance;
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
                    otherBankAccount.getBalance() == this.getBalance();

        }

        return false;
    }
}
