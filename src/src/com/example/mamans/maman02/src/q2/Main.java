package com.example.mamans.maman02.src.q2;

import java.util.ArrayList;

public class Main {

    private static class PendingCheck {
        private CheckingAccount writer;
        private double amount;
        private boolean receivedByBank;

        public PendingCheck(CheckingAccount writer, double amount, boolean receivedByBank) {
            this.writer = writer;
            this.amount = amount;
            this.receivedByBank = receivedByBank;
        }

        public void clear() throws NegativeAmountException, IllegalBalanceException {
            if (receivedByBank) {
                System.out.println("Clearing received check of $" + amount +
                        " from " + writer.getOwner());
                writer.withdrawal(amount);
            } else {
                System.out.println("Check of $" + amount + " from " +
                        writer.getOwner() + " was not received yet. It waits for next month.");
            }
        }
    }

    private static void printAccount(BankAccount account) {
        System.out.println("\nType: " + account.getClass().getSimpleName());
        System.out.println(account);
    }

    private static void safeDeposit(BankAccount account, double amount) {
        try {
            account.deposit(amount);
            printAccount(account);
        } catch (NegativeAmountException e) {
            System.out.println("Deposit failed: " + e.getMessage());
        }
    }

    private static void safeWithdrawal(BankAccount account, double amount) {
        try {
            account.withdrawal(amount);
            printAccount(account);
        } catch (NegativeAmountException | IllegalBalanceException e) {
            System.out.println("Withdrawal failed: " + e.getMessage());
        }
    }

    private static void safeMonthlyManagement(BankAccount account) {
        try {
            account.applyMonthlyManagement();
            printAccount(account);
        } catch (Exception e) {
            System.out.println("Monthly management failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        BankAccount[] accounts = {
                new ServiceChargeChecking("SC-001", "Mittens McWhiskers", "111", 800),
                new ServiceChargeChecking(25, "SC-002", "Sir Purrington", "222", 40),
                new NoServiceChargeChecking("NS-001", "Princess Tuna", "333", 3000),
                new NoServiceChargeChecking(1500, "NS-002", "Captain Meow", "444", 1800),
                new InterestChecking("IC-001", "Professor Catnip", "555", 5000),
                new InterestChecking(2000, 0.15, "IC-002", "Lady Fluffington", "666", 10000),
                new SavingsAccount("SA-001", "Bark Twain", "777", 7000),
                new SavingsAccount(0.08, "SA-002", "Queen Whiskerbell", "888", 4000),
                new HighInterestSavings("HS-001", "Chucha MaChucha" , "999", 12000),
                new HighInterestSavings(3000, 0.25, "HS-002", "Doctor Meowzart", "101", 20000)
        };

        System.out.println("===== INITIAL ACCOUNTS =====");
        for (BankAccount account : accounts) {
            printAccount(account);
        }

        System.out.println("\n===== DEPOSITS =====");
        safeDeposit(accounts[0], 250);
        safeDeposit(accounts[4], 1000);
        safeDeposit(accounts[6], -50);

        System.out.println("\n===== WITHDRAWALS =====");
        safeWithdrawal(accounts[0], 100);
        safeWithdrawal(accounts[1], 1000);
        safeWithdrawal(accounts[3], 500);
        safeWithdrawal(accounts[3], 1000);

        System.out.println("\n===== WRITING CHECKS =====");

        ArrayList<PendingCheck> pendingChecks = new ArrayList<>();

        try {
            CheckingAccount catChecking = (CheckingAccount) accounts[0];
            System.out.println(catChecking.writeCheck(200));
            pendingChecks.add(new PendingCheck(catChecking, 200, true));
        } catch (Exception e) {
            System.out.println("Could not write check: " + e.getMessage());
        }

        try {
            CheckingAccount richCatChecking = (CheckingAccount) accounts[4];
            System.out.println(richCatChecking.writeCheck(900));
            pendingChecks.add(new PendingCheck(richCatChecking, 900, false));
        } catch (Exception e) {
            System.out.println("Could not write check: " + e.getMessage());
        }

        System.out.println("\n===== CLEARING RECEIVED CHECKS =====");
        for (PendingCheck check : pendingChecks) {
            try {
                check.clear();
            } catch (NegativeAmountException | IllegalBalanceException e) {
                System.out.println("Check clearing failed: " + e.getMessage());
            }
        }

        System.out.println("\n===== MONTHLY MANAGEMENT =====");
        for (BankAccount account : accounts) {
            safeMonthlyManagement(account);
        }

        System.out.println("\n===== FINAL ACCOUNT STATUS =====");
        for (BankAccount account : accounts) {
            printAccount(account);
        }
    }
}