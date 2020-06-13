package company;

import java.util.*;


public class Company {
    private static int income;
    List<Employee> list = new ArrayList<>();

    public void hireAll(ArrayList arrayList) {
        list.addAll(arrayList);
    }

    public void getTopSalaryStaff(int count) {
        list.sort(Comparator.comparing(Employee::getMonthSalary).reversed());
        for (int i = 0; i < count; i++) {
            System.out.println(list.get(i).getMonthSalary() + " руб");
        }
    }

    public void getLowestSalaryStaff(int count) {
        list.sort(Comparator.comparing(Employee::getMonthSalary));
        for (int i = 0; i < count; i++) {
            System.out.println(list.get(i).getMonthSalary() + " руб");
        }
    }

    public void hire(Employee employee) {
        list.add(employee);
    }

    public void fire( int count) {
        for (int i = count - 1; i >= 0; i--) {
            list.remove(i);
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
