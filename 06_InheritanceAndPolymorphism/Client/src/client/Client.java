package client;

public abstract class Client {
    private double account;

    public abstract double getAccount();

    public  void setAccount(double account){
    }

    public abstract double getBalance();

    public abstract void getMoney(double account);

    public abstract void getInfo();
}
