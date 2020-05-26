package company;



public class Manager  implements Employee {
    private  double salary;


    public Manager(double salary) {
        this.salary = salary;
    }

    public  void setSalary(double salary) {
        this.salary += salary;
    }

    @Override
    public  double getMonthSalary() {
        return salary + (Company.getIncome() * 0.005);
    }
}
