import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Bank {
    private final Map<Integer, Account> accounts = Collections.synchronizedMap(new HashMap<>());
    private final Random random = new Random();
    private long balance;
    private  AtomicInteger CLIENT_ID_HOLDER = new AtomicInteger();


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
    public void transfer(int fromId, int toId, long amount) throws InterruptedException {
       Account fromAccount = accounts.get(fromId);
       Account toAccount = accounts.get(toId);
       if (fromId == toId){
           return;
       }
        if (fromAccount.isBlocked() || toAccount.isBlocked()) {
            return;
        }
        int c = Integer.compare(fromId, toId);
        synchronized (c > 0 ? fromAccount : toAccount) {
            synchronized (c > 0 ? toAccount : fromAccount) {
                if (amount > 50000) {
                    if (isFraud(fromId, toId, amount)) {
                        fromAccount.blockAccount();
                        toAccount.blockAccount();
                        return;
                    }
                }
                transaction(fromAccount, toAccount, amount);
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
        int id = CLIENT_ID_HOLDER.incrementAndGet();
        accounts.put(id, new Account(id, amount));
    }

    public long getBalance() {
        return accounts.values().stream().mapToLong(Account::getBalance).sum();
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}

