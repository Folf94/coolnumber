package client;

public class IndividualPerson extends Client {
    private double account;
    private static final String NAME_OF_ACCOUNT = "Cчет физического лица ";

    @Override
    public double getAccount() {
        System.out.println("Ваше последнее пополнения счета физического лица: " + account);
        return account;
    }

    @Override
    public void setAccount(double account) {
        this.account += account;
        System.out.println("Вы пополнели счет физического лица на: " + account);
    }

    @Override
    public double getBalance() {
        double balance = account;
        System.out.println("Ваш баланс счета физического лица: " + balance);
        return balance;
    }

    @Override
    public void getMoney(double account) {
        if (account > getAccount()){
            System.out.println("Недостаточно средств для снятия");
        }
        else {
            this.account -= account;
            System.out.println("Вы сняли со счета физического лица: " + account);
        }
    }

    @Override
    public void getInfo() {
        System.out.println(NAME_OF_ACCOUNT + " \n"+ " Баланс составляет: "+ getBalance() + "\n" + " Пополнение cxtnf без комиссии." + "\n Снятие средств без комиссии.");
    }
}
