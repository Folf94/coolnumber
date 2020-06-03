package client;

public class JuridicalPerson extends Client {
    private static final double MAX_PERCENT = 0.01;
    private static final String NAME_OF_ACCOUNT = "Счет юридического лица ";

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
        System.out.println(NAME_OF_ACCOUNT + " \n" + " Баланс составляет: " + this.account + "\n" + " Пополнение счета без комиссии." + "\n Снятие средств с коммиссией 1%.");
    }
}
