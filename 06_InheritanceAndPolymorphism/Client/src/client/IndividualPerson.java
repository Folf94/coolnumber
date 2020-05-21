package client;

public class IndividualPerson extends Client {
    private static double account;

    @Override
    public double getAccount() {
        System.out.println("Ваше последнее пополнения счета физического лица: " + account);
        return account;
    }

    @Override
    public void setAccount(double account) {
        IndividualPerson.account += account;
        System.out.println("Вы пополнели счет физического лица на: " + account);
    }

    @Override
    public double getBalance() {
        double balance = account;
        System.out.println("Ваш баланс счета физического лица: " + balance);
        return balance;
    }

    @Override
    public double getMoney(double account) {
        IndividualPerson.account -= account;
        System.out.println("Вы сняли со счета физического лица: " + account);
        return account;
    }
}
