import company.Company;
import company.Manager;


public class Main {

    public static void main(String[] args)  {
        Manager manager = new Manager(5156.0);
        Manager manager1 = new Manager(6515.0);
        System.out.println(manager.getMonthSalary());
        Company company = new Company();
        company.setIncome(51561555.5f);
        System.out.println(manager.getMonthSalary());
        System.out.println(Company.getIncome());
        company.hire(manager);
        company.hire(manager1);
        company.getStaff();

    }
}
