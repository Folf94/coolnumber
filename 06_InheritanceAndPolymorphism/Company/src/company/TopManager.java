package company;

public class TopManager extends Company implements Employee {
    private  double salary;

    public TopManager (double salary){
        this.salary = salary;
    }

    public  void setSalary(double salary) {
        this.salary += salary;
    }

    @Override
    public double getMonthSalary() {
        if (Company.getIncome() > 10000000){
            this.salary = salary + (salary * 1.5);
        }
        return salary;
    }
}
