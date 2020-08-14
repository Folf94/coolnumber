import lombok.Data;

public class Account
{
    private long moneyAcc;
    private int accNumber;
    private boolean isBlocked = false;

    public Account(int accNumber,long money) {
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

    public synchronized boolean getMoney(long money) {
        if (moneyAcc >= money) {
            moneyAcc -= money;
            return true;
        }
        return false;
    }

    public synchronized void putMoney(long money) {
        moneyAcc += money;
    }

    public synchronized void blockAccount() {
        isBlocked = true;
    }

    public int getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(int accNumber) {
        this.accNumber = accNumber;
    }
}
