import account.Account;
import account.CardAccount;
import account.DepositAccount;

public class Main {
    public static void main(String[] args) {
        Account account = new Account();
        account.setAccount(888418.04D);
        account.setAccount(618186.4D);
        account.getAccount();
        account.getMoney(16181.15D);
        account.getAccount();
        DepositAccount depositAccount = new DepositAccount();
        depositAccount.setAccount(181886.41D);
        depositAccount.getAccount();
        depositAccount.getMoney(15818.15D);
        CardAccount cardAccount = new CardAccount();
        cardAccount.setAccount(15000.0D);
        cardAccount.getAccount();
        cardAccount.getMoney(5000.0D);
        cardAccount.getAccount();
    }
}
