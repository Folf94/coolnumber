package company;


import java.util.ArrayList;
import java.util.List;

public class Company {
    private static float income;
    List<Employee>list = new ArrayList<>();

    public void hire(Employee employee) {
      list.add(employee);
    }

    public void fire(Employee employee){
        list.remove(employee);
    }

    public void getStaff() {
        for (Employee employee : list) {
            System.out.println(employee.getMonthSalary());
        }
    }
    public static float getIncome() {
        return income;
    }
    public void setIncome(float income) {
        Company.income += income;
    }
}
