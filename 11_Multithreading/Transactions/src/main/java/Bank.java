import java.util.HashMap;
import java.util.Random;

public class Bank {
    private HashMap<Integer, Account> accounts;
    private final Random random = new Random();

    public void setAccounts(HashMap<Integer, Account> accounts) {
        this.accounts = accounts;
    }

    {
        accounts = createAccounts();
    }

    public synchronized boolean isFraud(int fromAccountNum, int toAccountNum, long amount) throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public void transfer(int fromAccountNum, int toAccountNum, long amount) throws InterruptedException {
        Account fromAccount = accounts.get(fromAccountNum);
        Account toAccount = accounts.get(toAccountNum);
        if (fromAccount.isBlocked() || toAccount.isBlocked()) {
            return;
        }
        transaction(amount, fromAccount, toAccount);
        if (amount > 50000) {
            if (isFraud(fromAccountNum, toAccountNum, amount)) {
                transaction(amount, fromAccount, toAccount);
                fromAccount.blockAccount();
                toAccount.blockAccount();
            }
        }
    }

    private void transaction(long amount, Account fromAccount, Account toAccount) {
        fromAccount.getMoney(amount);
        toAccount.putMoney(amount);
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(int accountNum) {
        Account account = accounts.get(accountNum);
        return account.getBalance();
    }

    private static HashMap<Integer, Account> createAccounts() {
        HashMap<Integer, Account> accountMap = new HashMap<>();
        for (int i = 1; i <= 10; i++) {
            long accountMoney = (long) (50000 + 30000 * Math.random());
            Account account = new Account(i, accountMoney);
            accountMap.put(i, account);
        }
        return accountMap;
    }
}
