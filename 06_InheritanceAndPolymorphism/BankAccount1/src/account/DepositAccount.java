package account;

import java.util.Calendar;
import java.util.Date;

public class DepositAccount extends Account {
    private  Date monthOfDeposit;

    public DepositAccount() {
    }

    public double getAccount() {
        return super.getAccount();
    }

    public void getMoney(double account) {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        if (currentDate.after(monthOfDeposit)) {
            super.getMoney(account);
        } else {
            System.out.println("Вы не можете снять деньги");
        }

    }

    public void setAccount(double account) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        monthOfDeposit = calendar.getTime();
        super.setAccount(account);
        System.out.println(monthOfDeposit);
    }

}
