package company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Company {
    private int income;
    List<Employee> list = new ArrayList<>();

    public void hireAll(ArrayList arrayList) {
        list.addAll(arrayList);
    }

    public List<Employee> getTopSalaryStaff(int count) {
        list.sort(Comparator.comparing(Employee::getMonthSalary).reversed());
        count = Math.min(count, list.size());
        System.out.println("Наибольшие " + count + " ЗП:");
        for (int i = 0; i < count; i++) {
            System.out.println(list.get(i).getMonthSalary() + " руб");
        }
        return list.subList(0, count);
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        list.sort(Comparator.comparing(Employee::getMonthSalary));
        count = Math.min(count, list.size());
        System.out.println("Наименьшие " + count + " ЗП:");
        for (int i = 0; i < count; i++) {
            System.out.println(list.get(i).getMonthSalary() + " руб");
        }
        return list.subList(0, count);
    }

    public void hire(Employee employee) {
        list.add(employee);
    }

    public void fire(int count) {
        for (int i = count - 1; i >= 0; i--) {
            list.remove(i);
        }
    }

    public void getStaff() {
        for (Employee employee : list) {
            System.out.println(employee.getMonthSalary());
        }
    }

    public int getIncome() {
        return this.income;
    }

    public void setIncome(int income) {
        this.income += income;
    }
}
