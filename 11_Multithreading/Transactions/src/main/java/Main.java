import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final int MAGIC_NUM = 10;

    public static void main(String[] args) {
        Bank bank = new Bank();
        /*long start = System.currentTimeMillis();
        ExecutorService service = Executors.newFixedThreadPool(MAGIC_NUM);

        for (int i = 0; i < MAGIC_NUM; i++) {
            service.execute(() -> {
                try {
                    for (int j = 1; j < MAGIC_NUM; j++) {
                        long amount = (long) (10000 + 20000 * Math.random());
                        bank.transfer(j, j + 1, amount);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }*/

        bank.addAccounts(50000);
        bank.addAccounts(1000);
        bank.addAccounts(2000);
        bank.addAccounts(3000);
        bank.addAccounts(4000);
        bank.printAccounts();


    }
}

