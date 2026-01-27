package OopsAssignment;

abstract class BankAccount
{
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    
    public BankAccount(String accountNumber, String accountHolderName)
    {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
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

    
    public void setAccountHolderName(String accountHolderName)
    {
        this.accountHolderName = accountHolderName;
    }

    
    public void deposit(double amount)
    {
        if (amount > 0)
        {
            balance += amount;
        }
        else
        {
            System.out.println("Deposit amount must be positive.");
        }
    }

    
    public void withdraw(double amount)
    {
        if (amount <= 0)
        {
            System.out.println("Withdrawal amount must be positive.");
        }
        else if (amount > balance)
        {
            System.out.println("Insufficient balance.");
        }
        else
        {
            balance -= amount;
        }
    }

    
    public abstract String getAccountDetails();
}
class SavingsAccount extends BankAccount
{
    public SavingsAccount(String accNo, String name)
    {
        super(accNo, name);
    }

    @Override
    public String getAccountDetails()
    {
        return "Savings Account | Balance: " + getBalance();
    }
}
public class Task1
{
    public static void main(String[] args)
    {
        SavingsAccount acc = new SavingsAccount("101", "Deepak");

        acc.deposit(5000);
        acc.withdraw(2000);

        System.out.println(acc.getAccountDetails());
    }
}
