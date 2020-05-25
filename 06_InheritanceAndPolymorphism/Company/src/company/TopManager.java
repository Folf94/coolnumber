package company;

public class TopManager extends Company implements Employee {
    private static double salary;

    public TopManager (double salary){
        TopManager.salary = salary;
        if (Company.getIncome() > 10000000){
            TopManager.salary = salary + (salary * 1.5);
        }
    }

    public static void setSalary(double salary) {
        TopManager.salary += salary;
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
