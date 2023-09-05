import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
    public double withdrawMoney(String userId, double amount) throws Exception
    {
        if (!account.containsKey(userId))
        {
            throw new Exception("no account found");
        }
        if (account.get(userId) > amount)
        {
            double currentAmount = checkBalance(userId);
            account.put(userId, currentAmount - amount);
            return account.get(userId);
        }
        else
        {
            throw new Exception("you're a broke boi");
        }
    }
    
    public boolean transferMoney (String fromAccount, String toAccount, double amount) throws Exception
    {
        withdrawMoney(fromAccount,amount);
        depositMoney (toAccount, amount);
        return true;
    }
    public void audit () throws IOException
    {
        FileWriter fw = new FileWriter ("AccountAudit.txt", false);
        PrintWriter pw = new PrintWriter (fw);
        for (String email : account.keySet())
        {
            pw.write (email + " -> " + account.get(email) + "\n");
        }
        fw.close();
        pw.close();
    }
    public static void main(String[] args) throws Exception {
        ATM atm = new ATM();
        atm.openAccount ("markyma",3400);
        atm.openAccount ("george",0);
        System.out.println(atm.checkBalance("markyma"));
        System.out.println(atm.depositMoney("markyma", 200));
        System.out.println(atm.depositMoney("markyma", 600));
        System.out.println(atm.transferMoney("markyma", "george", 2000));
        System.out.println(atm.checkBalance("george"));
        System.out.println(atm.transferMoney("markyma", "george", 2000));
        System.out.println(atm.checkBalance("george"));
        System.out.println(atm.transferMoney("markyma", "george", 2000));
        System.out.println(atm.checkBalance("george"));
        atm.audit();
    }
}