package client;

public class IndividualPerson extends Client {
    private static final String NAME_OF_ACCOUNT = "Cчет физического лица ";

    @Override
    public void getInfo() {
        System.out.println(NAME_OF_ACCOUNT + " \n" + " Баланс составляет: " + getAccount() + "\n" + " Пополнение счета без комиссии." + "\n Снятие средств без комиссии.");
    }
}
