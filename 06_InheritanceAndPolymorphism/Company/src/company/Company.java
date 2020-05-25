package company;

import java.util.HashSet;


public class Company {
    private static float income;
    private static int staff;

    HashSet<Manager> managers = new HashSet<>();
    HashSet<TopManager> topManagers = new HashSet<>();
    HashSet<Operator> operators = new HashSet<>();

    public static double getStaff() {
        return staff;
    }

    public void hire(Manager manager) {
        managers.add(new Manager(10000.0));

    }

    public void hire(Operator operator) {
        operators.add(new Operator(5000.0));
    }

    public void hire(TopManager topManager) {
        topManagers.add(new TopManager(15000.0));
    }


    public void fire(Manager manager) {
        managers.remove(new Manager(10000.0));
    }

    public void hireAll() {


    }

    public static double getIncome() {
        return income;
    }

    public static void setIncome(float income) {
        Company.income += income;
    }

}
