import client.IndividualEntrepreneur;
import client.IndividualPerson;
import client.JuridicalPerson;

public class Main {
    public static void main(String[] args) {
        IndividualPerson individualPerson = new IndividualPerson();
        individualPerson.setAccount(11586.51);
        individualPerson.getAccount();
        individualPerson.getBalance();
        individualPerson.getMoney(1586.51);
        individualPerson.getBalance();
        individualPerson.getInfo();
        System.out.println("++++++++++++++++++++++++++++++++++++++++");
        JuridicalPerson juridicalPerson = new JuridicalPerson();
        juridicalPerson.setAccount(16168585.0);
        juridicalPerson.getAccount();
        juridicalPerson.getBalance();
        juridicalPerson.getMoney(151655461.5);
        juridicalPerson.getBalance();
        juridicalPerson.getInfo();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++");
        IndividualEntrepreneur individualEntrepreneur = new IndividualEntrepreneur();
        individualEntrepreneur.setAccount(511556.0);
        individualEntrepreneur.getAccount();
        individualEntrepreneur.getBalance();
        individualEntrepreneur.getMoney(561561.156);
        individualEntrepreneur.getBalance();
        individualEntrepreneur.getInfo();

    }
}
