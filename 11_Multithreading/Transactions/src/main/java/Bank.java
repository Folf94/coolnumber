import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Bank {
    private Map<Integer, Account> accounts = Collections.synchronizedMap(new HashMap<>());
    private final Random random = new Random();
    private long balance;
    private static final AtomicInteger CLIENT_ID_HOLDER = new AtomicInteger();


    public synchronized boolean isFraud(int fromAccountNum, int toAccountNum, long amount) throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    public void printAccounts(){
        for (Map.Entry entry : accounts.entrySet())
        {
            System.out.println("key: " + entry.getKey() + "; value: " + entry.getValue().toString());
        }
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public void transfer(Account fromAccount, Account toAccount, long amount) throws InterruptedException {
        int fromId = fromAccount.getId();
        int toId = fromAccount.getId();
        if (fromAccount.isBlocked() || toAccount.isBlocked()) {
            return;
        }
        else
            if (fromId < toId) {
                synchronized (fromAccount) {
                    synchronized (toAccount) {
                        transaction(fromAccount, toAccount, amount);
                    }
                }
            } else {
                synchronized (toAccount) {
                    synchronized (fromAccount) {
                        transaction(fromAccount, toAccount, amount);
                    }
                }
            }
        }

    public void transaction(Account fromAccount, Account toAccount, long amount) {
        if (fromAccount.getBalance() > amount) {
            fromAccount.getMoney(amount);
            toAccount.putMoney(amount);
        }
        else
            System.out.println("Not enough money");
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(int accountNum) {
        Account account = accounts.get(accountNum);
        return account.getBalance();
    }

    public void addAccounts(long amount){
        balance+=amount;
        accounts.put(CLIENT_ID_HOLDER.incrementAndGet(),new Account(CLIENT_ID_HOLDER.get(),amount));
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}

