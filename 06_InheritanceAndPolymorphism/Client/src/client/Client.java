package client;

public abstract class Client {
     double account;

    public double getAccount(){
        System.out.println("Ваш баланс: " + this.account);
        return this.account;
    }

    public  void setAccount(double account){
       this.account += account;
        System.out.println("Вы пополнили счет на: "+ account);
    }

    public  void getMoney(double account) {
        if (account > getAccount()) {
            System.out.println("Недостаточно средств для снятия");
        } else {
            this.account -= account;
            System.out.println("Вы сняли со счета: " + account);
        }
    }
    public abstract void getInfo();
}
