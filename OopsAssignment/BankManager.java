package OopsAssignment;
import java.util.ArrayList;
abstract class BankAccount
{
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    public BankAccount(String accNo, String holderName)
    {
        this.accountNumber = accNo;
        this.accountHolderName = holderName;
        this.balance = 0.0;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public String getAccountHolderName()
    {
        return accountHolderName;
    }

    public double getBalance()
    {
        return balance;
    }

    public void deposit(double amount)
    {
        if(amount > 0)
            balance += amount;
        else
            System.out.println("Invalid deposit amount");
    }

    public void withdraw(double amount)
    {
        if(amount > 0 && amount <= balance)
            balance -= amount;
        else
            System.out.println("Invalid withdrawal");
    }

    public abstract String getAccountDetails();
}
class SavingsAccount extends BankAccount
{
    private double interestRate;

    public SavingsAccount(String accNo, String name, double interestRate)
    {
        super(accNo, name);
        this.interestRate = interestRate;
    }

    public void applyInterest()
    {
        double interest = getBalance() * interestRate;
        deposit(interest);
    }

    @Override
    public String getAccountDetails()
    {
        return "Savings Account #" + getAccountNumber() +
               ", Balance: $" + getBalance() +
               ", Rate: " + (interestRate * 100) + "%";
    }
}
class CheckingAccount extends BankAccount
{
    private double overdraftLimit;

    public CheckingAccount(String accNo, String name, double overdraftLimit)
    {
        super(accNo, name);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount)
    {
        if(amount <= 0)
        {
            System.out.println("Invalid withdrawal amount");
        }
        else if(getBalance() - amount >= -overdraftLimit)
        {
            deposit(-amount);

            if(getBalance() < 0)
                System.out.println("Overdraft used!");
        }
        else
        {
            System.out.println("Overdraft limit exceeded!");
        }
    }

    @Override
    public String getAccountDetails()
    {
        return "Checking Account #" + getAccountNumber() +
               ", Balance: $" + getBalance() +
               ", Limit: $" + overdraftLimit;
    }
}


public class BankManager
{
    public static void main(String[] args)
    {
        
        ArrayList<BankAccount> accounts = new ArrayList<>();

        
        SavingsAccount sa = new SavingsAccount("12345", "Deepak", 0.02);
        CheckingAccount ca = new CheckingAccount("67890", "Amit", 500);

        accounts.add(sa);
        accounts.add(ca);

        
        System.out.println("Initial Account Details:");
        for(BankAccount acc : accounts)
        {
            System.out.println(acc.getAccountDetails());
        }

        sa.deposit(200);        
        ca.withdraw(100);       
        for(BankAccount acc : accounts)
        {
            if(acc instanceof SavingsAccount)
            {
                ((SavingsAccount) acc).applyInterest();
            }
        }

        System.out.println("\nAfter Transactions:");
        for(BankAccount acc : accounts)
        {
            System.out.println(acc.getAccountDetails());
        }
    }
}
