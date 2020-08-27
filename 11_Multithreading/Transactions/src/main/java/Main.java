import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static final int MAGIC_NUM = 1000;

    public static void main(String[] args) {
        Bank bank = new Bank();

        List<Integer> ids = new ArrayList<>();
        ids.add(bank.addAccounts(50000));
        ids.add(bank.addAccounts(40000));
        ids.add(bank.addAccounts(30000));
        ids.add(bank.addAccounts(20000));
        ids.add(bank.addAccounts(10000));
        ids.add(bank.addAccounts(9000));
        ids.add(bank.addAccounts(8000));
        ids.add(bank.addAccounts(7000));
        ids.add(bank.addAccounts(6000));
        ids.add(bank.addAccounts(5000));

        long beforeTest = bank.getBalance();

        List<Thread> threads = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < MAGIC_NUM; i++) {
            threads.add(new Thread(() -> {
                long amount = (long) (1000 + 2000 * Math.random());
                try {
                    bank.transfer(
                            ids.get(random.nextInt(ids.size())),
                            ids.get(random.nextInt(ids.size())),
                            amount);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
        }

        threads.forEach(Thread::start);
        threads.forEach(e -> {
            try {
                e.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });


        System.out.println("До тестов: " + beforeTest);
        System.out.println("После тестов: " + bank.getBalance());
    }
}