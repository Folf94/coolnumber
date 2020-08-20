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

    @Override
    public void setUp() {
        accounts.put(1, one);
        accounts.put(2, two);
        accounts.put(3, three);
        accounts.put(4, four);
        accounts.put(5, five);
        bank.setAccounts(accounts);
    }

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

    public void testTransferManyTreads() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    bank.transfer(1, 2, 1000);
                    bank.transfer(1, 3, 2000);
                    bank.transfer(2, 1, 2000);
                    bank.transfer(2, 3, 2000);
                    bank.transfer(3, 1, 1000);
                    bank.transfer(3, 2, 1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        Thread.sleep(1000);
        long actualOne = one.getBalance();
        long expectedOne = 50000;
        long actualTwo = two.getBalance();
        long expectedTwo = 30000;
        long actualThree = three.getBalance();
        long expectedThree = 70000;

        assertEquals(expectedOne, actualOne);
        assertEquals(expectedTwo, actualTwo);
        assertEquals(expectedThree, actualThree);

    }
}
