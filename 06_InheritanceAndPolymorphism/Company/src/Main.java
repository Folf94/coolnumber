import company.Company;
import company.Manager;


public class Main {

    public static void main(String[] args)  {
        Manager manager = new Manager(5156.0);
        System.out.println(manager.getMonthSalary());
        Company company = new Company();
        company.setIncome(51561555.5f);
        System.out.println(manager.getMonthSalary());
        System.out.println(company.getIncome());
        company.hire(5651.0);
        company.hire(5415.0);
        company.getStaff();
    }
}
