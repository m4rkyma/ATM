import java.util.HashMap;

public class ATM{
    public HashMap <String,Double> account = new HashMap<>();
    public void openAccount (String userId, double amount) throws Exception
    {
        if (account.containsKey(userId))
        {
            throw new Exception("bro you already registered something under " + userId);
        }
        account.put(userId, amount);
    }
    public void closeAccount (String userId) throws Exception
    {
        if (account.get(userId) <= 0 )
        {
            account.remove(userId);
        }
        throw new Exception("take yo money out before you withdraw, ya got " + account.get(userId) + " dollas");
    }
    public double checkBalance (String userId) throws Exception
    {
        if (!account.containsKey(userId))
        {
            throw new Exception("no account found");
        }
        return account.get(userId);
    }
    public double depositMoney (String userId, double amount) throws Exception
    {
        if (account.containsKey(userId))
        {
            double currentAmount = checkBalance(userId);
            account.put(userId, currentAmount + amount);
        }
        else
        {
            throw new Exception("you're a broke boi");
        }
        return amount;
    }
    public double withdrawMoney(String userId, double amount)
    {
        if (account.get(userId) > amount)
        {
            double currentAmount = checkBalance(userId);
            account.put(userId, currentAmount - amount);
            return account.get(userId);
        }
        return account.get(userId);
    }
    
    public boolean transferMoney (String fromAccount, String toAccount, double amount)
    {

    }
    public void audit ()
    {

    }
}