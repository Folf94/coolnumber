import junit.framework.TestCase;

import java.util.HashMap;

public class BankTest extends TestCase {

    private Bank bank = new Bank();
    private HashMap<Integer, Account> accounts = new HashMap<>();
    private Account one = new Account(1, 50000);
    private Account two = new Account(2, 50000);
    private Account three = new Account(3, 50000);
    private Account four = new Account(4, 50000);
    private Account five = new Account(5, 50000);

    public void testTransferOneThread() throws InterruptedException {
        bank.transfer(1, 2, 1000);
        long actualFrom = one.getBalance();
        long expectedFrom = 49000;
        long actualTo = two.getBalance();
        long expectedTo = 51000;
        assertEquals(expectedFrom, actualFrom);
        assertEquals(expectedTo, actualTo);
    }

    public void testTransferBlock() throws InterruptedException {
        long balance = one.getBalance();
        one.setIsBlocked(true);
        bank.transfer(1, 2, 1000);
        long actualOne = bank.getBalance(1);
        assertEquals(balance, actualOne);
    }
}
