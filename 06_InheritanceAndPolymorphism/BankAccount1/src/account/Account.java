package account;

public class Account {
    private double account;

    public Account() {
    }

    public double getAccount() {
        System.out.println("Ваш текущий счет: " + this.account);
        return this.account;
    }

    public void setAccount(double account) {
        this.account += account;
    }

    public void getMoney(double account) {
        this.account -= account;
        System.out.println("Вы сняли: " + account);
    }
}
