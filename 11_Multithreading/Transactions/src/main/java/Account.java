public class Account {
    private long moneyAcc;
    private int accNumber;
    private boolean isBlocked = false;

    public Account(int accNumber, long money) {
        this.accNumber = accNumber;
        this.moneyAcc = money;
    }

    public long getBalance() {
        return moneyAcc;
    }

    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public synchronized void getMoney(long money) {
            moneyAcc -= money;

    }

    @Override
    public String toString() {
        return String.valueOf(accNumber + " " + moneyAcc);
    }

    public synchronized void putMoney(long money) {
        moneyAcc += money;
    }

    public synchronized void blockAccount() {
        isBlocked = true;
    }

    public int getId() {
        return accNumber;
    }

    public void setId(int accNumber) {
        this.accNumber = accNumber;
    }
}
