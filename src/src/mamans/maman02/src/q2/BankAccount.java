package mamans.maman02.src.q2;

/**
 * <h3> BankAccount </h3>
 *
 * <p>
 *     this class defines a bank account abstract class 
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

public abstract class BankAccount {

    private String accountId;
    private String owner;
    private String ownerPID;
    private double balance = 0.0;

    
    /** BankAccount constructors 
     * 
     * @param accountId bank account id
     * @param owner first and last owner name
     * @param ownerPID owner personal-id
     * @param balance pre-define balance
     *
     * */
    public BankAccount(String accountId, String owner, String ownerPID, double balance) {
        this.accountId = accountId;
        this.owner = owner;
        this.ownerPID = ownerPID;
        this.balance = balance;
    }
    
    /** BankAccount constructors 
     * 
     * @param accountId bank account id
     * @param owner first and last owner name
     * @param ownerPID owner personal-id
     * */
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
    
    
    /**
     * deposit into bank account
     * 
     * @param amount how much to add into current balance
     * */
    public void deposit(double amount) throws NegativeAmountException {
        if (amount < 0)
            throw new NegativeAmountException("amount cannot be negative");

        System.out.print(owner + " (" + ownerPID + ") make a deposit of " + amount + " to its bank account " + accountId);
        System.out.println(" (balance is updated from $" + balance + " to $" + (balance+amount) + ")");

        this.balance += amount;
    }
    
    
    /**
     * withdrawal from bank account
     * <br><br>
     * note: general bank account cannot withdraw more than it current balance.
     * namely, this bank account cannot be in overdraft.
     * 
     * @param amount how much to subtract from current balance
     * */
    public void withdrawal(double amount) throws NegativeAmountException, IllegalBalanceException {

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
    
    /**
     * compare between two bank accounts
     * 
     * @param obj
     * @return true if-and-only-if it is a BankAccount class object that all its attribute identical with this
     * BankAccount class object.
     * */
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
    
    
    /**
     * Validate Amount
     * <br>
     * validate that amount is a non-negative decimal number
     * 
     * @param amount a number should represent money
     * */
    protected void validateAmount(double amount) throws NegativeAmountException, IllegalBalanceException {
        if (amount < 0)
            throw new NegativeAmountException("amount cannot be negative");

        if (balance - amount < 0)
            throw new IllegalBalanceException("this withdrawal is disallowed\naccount "
                    + accountId + " is not permitted to be in overdraft");
    }

    protected abstract void applyMonthlyManagement();
}
