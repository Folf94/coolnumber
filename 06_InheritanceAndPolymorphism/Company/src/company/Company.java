package company;


import java.util.ArrayList;

public class Company {
    private static float income;
    ArrayList<Manager> managers = new ArrayList<>();
    ArrayList<TopManager> topManagers = new ArrayList<>();
    ArrayList<Operator> operators = new ArrayList<>();

    public void hire( double salary) {
       Manager manager = new Manager(salary);
       managers.add(manager);
    }

    public void getStaff() {
        for (Manager manager : managers) {
            System.out.println(manager);
        }
    }
    public static float getIncome() {
        return income;
    }
    public void setIncome(float income) {
        Company.income += income;
    }
}
