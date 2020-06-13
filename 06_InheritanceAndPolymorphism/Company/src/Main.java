import company.*;

import java.awt.desktop.OpenFilesEvent;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        Company company = new Company();
        company.setIncome(5610);

        for (int i = 0; i < 80; i++) {
            Manager manager = new Manager(151);
            company.hire(manager);

        }
        for (int i = 0; i < 180; i++) {
            Operator operator = new Operator(10);
            company.hire(operator);

        }

        for (int i = 0; i < 10; i++) {
            TopManager topManager = new TopManager(50);
            company.hire(topManager);

        }
        company.getStaff();
        company.getLowestSalaryStaff(3);
        System.out.println("+++++++++++++++++++++++++++++++");
        company.getTopSalaryStaff(3);
        System.out.println("+++++++++++++++++++++++++++++++");

        company.fire(250);

        company.getStaff();

    }

}
