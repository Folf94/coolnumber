package client;

public class JuridicalPerson extends Client {
    private   double account;

    @Override
    public double getAccount() {
        System.out.println("Ваше последнее пополнения счета юридического лица: " + account);
        return account;
    }

    @Override
    public void setAccount(double account) {
        this.account += account;
        System.out.println("Вы пополнели счет юридического лица на: " + account);
    }

    @Override
    public double getBalance() {
        double balance = account;
        System.out.println("Ваш баланс счета юридического лица: " + balance);
        return balance;
    }

    @Override
    public double getMoney(double account) {
        this.account -= (account + (account * 0.01));
        System.out.println("Вы сняли со счета юридического лица: " + account + " комиссия составила: " + (account * 0.01));
        return account;
    }
}
