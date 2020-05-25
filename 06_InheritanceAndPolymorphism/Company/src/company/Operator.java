package company;

public class Operator extends Company implements Employee {
    private static double salary;

    public Operator (double salary){
        Operator.salary = salary;
    }

    public static void setSalary(double salary) {
        Operator.salary = salary;
    }

    @Override
    public double getMonthSalary() {
        return salary;
    }

    @Override
    public int getTopSalaryStaff(int count) {
        return 0;
    }

    @Override
    public int getLowestSalaryStaff(int count) {
        return 0;
    }
}
