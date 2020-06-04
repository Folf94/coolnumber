import client.IndividualEntrepreneur;
import client.IndividualPerson;
import client.JuridicalPerson;

public class Main {
    public static void main(String[] args) {
        IndividualPerson individualPerson = new IndividualPerson();
        individualPerson.setAccount(11586.51);
        individualPerson.getMoney(1586.51);
        individualPerson.getAccount();
        individualPerson.getInfo();
        System.out.println("++++++++++++++++++++++++++++++++++++++++");
        JuridicalPerson juridicalPerson = new JuridicalPerson();
        juridicalPerson.setAccount(16168585.0);
        juridicalPerson.getMoney(1655461.5);
        juridicalPerson.getAccount();
        juridicalPerson.getInfo();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++");
        IndividualEntrepreneur individualEntrepreneur = new IndividualEntrepreneur();
        individualEntrepreneur.setAccount(511556.0);
        individualEntrepreneur.getMoney(361530.156);
        individualEntrepreneur.getAccount();
        individualEntrepreneur.getInfo();

    }
}
