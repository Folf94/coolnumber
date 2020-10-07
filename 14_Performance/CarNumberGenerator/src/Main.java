import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final int PROCESSOR_CORE_COUNT = ((Runtime.getRuntime().availableProcessors()));

    private static int regionCount = 199;


    public static void main(String[] args) throws InterruptedException {

        long startTime = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(PROCESSOR_CORE_COUNT);

        for (int i = 1; i <= regionCount; i++) {

            executorService.submit(new Loader(i));
        }
        executorService.shutdown();
        System.out.println((System.currentTimeMillis() - startTime) + " ms");
    }
}


