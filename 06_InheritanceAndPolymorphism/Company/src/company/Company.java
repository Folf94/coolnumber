package company;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Company {
    private static int income;
    List<Employee>list = new ArrayList<>();

   public void hireAll(ArrayList arrayList){
       list.addAll(arrayList);
   }

    public void  getTopSalaryStaff(int count) {
        list.sort(Comparator.comparing(Employee::getMonthSalary));
        for (int i = 0; i < count; i++) {
            for (Employee employee : list) {
                System.out.println(employee.getMonthSalary() + " руб");
            }
        }
    }
    public void getLowestSalaryStaff(int count) {
        list.sort(Comparator.comparing(Employee::getMonthSalary).reversed());
        for (int i = 0; i < count; i++) {
            for (Employee employee : list) {
                System.out.println(employee.getMonthSalary() + " руб");
            }
        }
    }

    public void hire(Employee employee) {
      list.add(employee);
    }

    public void fire(Employee employee, int count){
       for (int i = 0; i<= count; i++) {
           list.remove(employee);
       }
    }

    public void getStaff() {
        for (Employee employee : list) {
            System.out.println(employee.getMonthSalary());
        }
    }
    public static int getIncome() {
        return Company.income;
    }
    public void setIncome(int income) {
        Company.income += income;
    }
}
