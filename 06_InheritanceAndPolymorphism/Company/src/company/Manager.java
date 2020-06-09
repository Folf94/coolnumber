package company;



public class Manager extends Company  implements Employee {
    private  int salary;
    private int random = (int) ((Math.random() * ((140000 - 115000) + 1)) + 115000);


    public Manager(int salary) {
        this.salary = salary;
    }

    public  void setSalary(int salary) {
        this.salary += salary;
    }

    @Override
    public  int getMonthSalary() {
        return (int) (salary + (Company.getIncome() * (0.005 * random)));
    }
}
