package company;

import static company.Company.*;

public class TopManager  implements Employee {
    private  int salary;

    public TopManager (int salary){
        this.salary = salary;
    }

    public  void setSalary(int salary) {
        this.salary += salary;
    }

    @Override
    public int getMonthSalary() {
        if (Company.getIncome() > 10000000){
            this.salary = (int) (salary + (salary * 1.5));
        }
        return salary;
    }
}
