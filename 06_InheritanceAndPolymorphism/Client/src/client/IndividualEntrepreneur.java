package client;

public class IndividualEntrepreneur extends Client {
    private  double account;

    @Override
    public double getAccount() {
        System.out.println("Ваше последнее пополнения счета индивидуального предпринимателя: " + account);
        return account;
    }

    @Override
    public void setAccount(double account) {
        if (account < 1000.0) {
           this.account += (account - (account * 0.01));
            System.out.println("Вы пополнели счет индивидуального предпринимателя на: " + account + " комиссия составила: " + (account * 0.01));
        }
        if (account >= 1000.0) {
            this.account += (account - ((account / 100 * 0.5)));
            System.out.println("Вы пополнели счет индивидуального предпринимателя на: " + account + " комиссия составила: " + ((account / 100 * 0.5)));
        }
    }

    @Override
    public double getBalance() {
        double balance = account;
        System.out.println("Ваш баланс счета индивидуального предпринимателя: " + balance);
        return balance;
    }

    @Override
    public double getMoney(double account) {
        this.account -= account;
        System.out.println("Вы сняли со счета индивидуального предпринимателя: " + account);
        return account;
    }
}
