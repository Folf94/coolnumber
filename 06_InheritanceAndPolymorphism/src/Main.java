public class Main {
    public Main() {
    }
    public static void main(String[] args) {
        Main.Account account = new Main.Account();
        account.setAccount(888418.04D);
        account.setAccount(618186.4D);
        account.getAccount();
        account.getMoney(16181.15D);
        account.getAccount();
        DepositAccount depositAccount = new DepositAccount();
        depositAccount.setAccount(181886.41D);
        depositAccount.getAccount();
        depositAccount.getMoney(15818.15D);
        CardAccount cardAccount = new CardAccount();
        cardAccount.setAccount(15000.0D);
        cardAccount.getAccount();
        cardAccount.getMoney(5000.0D);
        cardAccount.getAccount();
    }

    public static class Account {
        private double account;

        public Account() {
        }

        public double getAccount() {
            System.out.println("Ваш текущий счет: " + this.account);
            return this.account;
        }

        public void setAccount(double account) {
            this.account += account;
        }

        public void getMoney(double account) {
            this.account -= account;
            System.out.println("Вы сняли: " + account);
        }
    }
}

