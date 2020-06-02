package client;

public class JuridicalPerson extends Client {
    private double account;
    private static final double MAX_PERCENT = 0.01;
    private static final String NAME_OF_ACCOUNT = "Счет юридического лица ";

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
    public void getMoney(double account) {
        if (account > getAccount()) {
            System.out.println("Недостаточно средств для снятия");
        } else {
            this.account -= (account + (account * MAX_PERCENT));
            System.out.println("Вы сняли со счета юридического лица: " + account + " комиссия составила: " + (account * MAX_PERCENT));
        }
    }

    @Override
    public void getInfo() {
        System.out.println(NAME_OF_ACCOUNT + " \n" + " Баланс составляет: " + getBalance() + "\n" + " Пополнение счета без комиссии." + "\n Снятие средств с коммиссией 1%.");
    }
}
