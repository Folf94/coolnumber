package client;

public class IndividualEntrepreneur extends Client {
    private double account;
    private static final double MIN_AMOUNT = 1000.0;
    private static final double MAX_PERCENT = 0.01;
    private static final double MIN_PERCENT = 0.005;
    private static final String NAME_OF_ACCOUNT = "Cчет индивидуального предпринимателя";

    @Override
    public double getAccount() {
        System.out.println("Ваше последнее пополнения счета индивидуального предпринимателя: " + account);
        return account;
    }

    @Override
    public void setAccount(double account) {
        if (account < MIN_AMOUNT) {
            this.account += (account - (account * MAX_PERCENT));
            System.out.println("Вы пополнели счет индивидуального предпринимателя на: " + account + " комиссия составила: " + (account * MAX_PERCENT));
        } else {
            this.account += (account - ((account * MIN_PERCENT)));
            System.out.println("Вы пополнели счет индивидуального предпринимателя на: " + account + " комиссия составила: " + ((account * MIN_PERCENT)));
        }
    }

    @Override
    public double getBalance() {
        double balance = account;
        System.out.println("Ваш баланс счета индивидуального предпринимателя: " + balance);
        return balance;
    }

    @Override
    public void getMoney(double account) {
        if (account > getAccount()) {
            System.out.println("Недостаточно средств для снятия");
        } else {
            this.account -= account;
            System.out.println("Вы сняли со счета индивидуального предпринимателя: " + account);
        }
    }

    @Override
    public void getInfo() {
        System.out.println(NAME_OF_ACCOUNT + " \n" + " Баланс составляет: " + getBalance() + "\n" + " Пополнение с комиссией 1%, если сумма меньше 1000 рублей " +
                "пополнение с комиссией 0,5% если сумма больше либо равна 1000 рублей." + "\n Снятие средств без коммиссии.");
    }
}
