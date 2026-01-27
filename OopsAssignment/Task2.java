package OopsAssignment;
class SavingsAccount extends BankAccount
{
    
    private double interestRate;   
    
    public SavingsAccount(String accNo, String holderName, double interestRate)
    {
        super(accNo, holderName);
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
    
    public CheckingAccount(String accNo, String holderName, double overdraftLimit)
    {
        super(accNo, holderName);
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
            double newBalance = getBalance() - amount;

            
            deposit(-amount);

            if(newBalance < 0)
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
public class Task2
{
    public static void main(String[] args)
    {
        SavingsAccount sa = new SavingsAccount("12345", "Deepak", 0.02);
        sa.deposit(1000);
        sa.applyInterest();
        System.out.println(sa.getAccountDetails());

        CheckingAccount ca = new CheckingAccount("67890", "Amit", 500);
        ca.deposit(200);
        ca.withdraw(250);
        System.out.println(ca.getAccountDetails());
    }
}

