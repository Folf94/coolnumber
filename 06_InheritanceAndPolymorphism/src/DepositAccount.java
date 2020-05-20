
import java.util.Calendar;
import java.util.Date;

public class DepositAccount extends Main.Account {
    private static int getMonthOfSet;

    public DepositAccount() {
    }

    public double getAccount() {
        return super.getAccount();
    }

    public void getMoney(double account) {
        int getMonthOfGet = this.getMonth();
        if (getMonthOfGet - getMonthOfSet > 0) {
            super.getMoney(account);
        } else {
            System.out.println("Вы не можете снять деньги");
        }

    }

    public void setAccount(double account) {
        getMonthOfSet = this.getMonth();
        super.setAccount(account);
    }

    protected int getMonth() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(2);
    }
}
