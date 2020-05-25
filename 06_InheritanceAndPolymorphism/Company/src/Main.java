import company.Company;
import company.Manager;
import company.Operator;
import company.TopManager;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Company company = new Company();
        Company.setIncome((float) 519818918.00);
        Manager manager = new Manager(6151.5);
        // System.out.println(manager.getMonthSalary());
        Operator operator = new Operator(5515.0);
        System.out.println(operator.getMonthSalary());
        System.out.println(Company.getIncome());
        TopManager topManager = new TopManager(565.5);
        System.out.println(topManager.getMonthSalary());
        System.out.println(Company.getStaff());

    }
}
