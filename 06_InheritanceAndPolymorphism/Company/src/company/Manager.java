package company;

public class Manager extends Company implements Employee{
    private static double salary;

    public Manager (double salary){
        Manager.salary = salary + (Company.getIncome() * 0.05);
    }

    public static void setSalary(double salary) {
        Manager.salary = salary + (Company.getIncome() * 0.05) ;
    }

    @Override
    public double getMonthSalary() {
        return salary ;
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
