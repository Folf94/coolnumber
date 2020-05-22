package account;

public class CardAccount extends Account {
    public CardAccount() {
    }

    public void getMoney(double account) {

        super.getMoney(account + account * 0.01D);
    }

    public double getAccount() {
        return super.getAccount();
    }

    public void setAccount(double account) {
        super.setAccount(account);
    }
}
