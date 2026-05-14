package com.example.mamans.maman02.src.q2;

/**
 * <h3> CheckingAccount </h3>
 *
 * <p>
 *     this class defines a bank account type checking account abstract class
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

public class CheckingAccount extends BankAccount {

    /** CheckingAccount constructors
     *
     * @param accountId bank account id
     * @param owner first and last owner name
     * @param ownerPID owner personal-id
     * @param balance pre-define balance
     * */
    public CheckingAccount(String accountId, String owner, String ownerPID, double balance) {
        super(accountId, owner, ownerPID, balance);
    }

    /** CheckingAccount constructors
     *
     * @param accountId bank account id
     * @param owner first and last owner name
     * @param ownerPID owner personal-id
     * */
    public CheckingAccount(String accountId, String owner, String ownerPID) {
        super(accountId, owner, ownerPID);
    }

    /** write a check
     *
     * @param amount how much money to write on the check
     * @return a check paper
     * */
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
