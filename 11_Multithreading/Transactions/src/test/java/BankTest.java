import junit.framework.TestCase;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class BankTest extends TestCase {

    private Bank bank = new Bank();
    private HashMap<Integer, Account> accounts = new HashMap<>();
    private Account one = new Account(1, 50000);
    private Account two = new Account(2, 50000);
    private Account three = new Account(3, 50000);
    private Account four = new Account(4, 50000);
    private Account five = new Account(5, 50000);
    private AtomicInteger CLIENT_ID_HOLDER = new AtomicInteger();

    @Override
    public void setUp() {
        accounts.put(CLIENT_ID_HOLDER.incrementAndGet(), one);
        accounts.put(CLIENT_ID_HOLDER.incrementAndGet(), two);
        accounts.put(CLIENT_ID_HOLDER.incrementAndGet(), three);
        accounts.put(CLIENT_ID_HOLDER.incrementAndGet(), four);
        accounts.put(CLIENT_ID_HOLDER.incrementAndGet(), five);
bank.addAccounts(one.getBalance());
bank.addAccounts(two.getBalance());
bank.addAccounts(three.getBalance());
bank.addAccounts(four.getBalance());
bank.addAccounts(five.getBalance());
    }


    public void testTransferOneThread() throws InterruptedException {
        bank.transfer(one.getId(), two.getId(), 1000);
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
        bank.transfer(one.getId(), two.getId(), 1000);
        long actualOne = one.getBalance();
        assertEquals(balance, actualOne);
    }

    public void testTransferManyTreads() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    bank.transfer(one.getId(), two.getId(), 1000);
                    bank.transfer(one.getId(), three.getId(), 2000);
                    bank.transfer(two.getId(), one.getId(), 2000);
                    bank.transfer(two.getId(), three.getId(), 2000);
                    bank.transfer(three.getId(), one.getId(), 1000);
                    bank.transfer(three.getId(), two.getId(), 1000);

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
    public void testCheckRaceCondition() {
        double beforeMoney = bank.getBalance();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                try {
                    bank.transfer(one.getId(), two.getId(), 1000);
                    bank.transfer(two.getId(), two.getId(), 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            double afterMoney = bank.getBalance();

            assertEquals(beforeMoney, afterMoney);
        }
    }
}

