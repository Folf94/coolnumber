import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ForkJoinPool;

public class Main {


    private static final int processorCoreCount = Runtime.getRuntime().availableProcessors();
    public static void main(String[] args) {
        String url = "https://www.lenta.ru";
        long start = System.currentTimeMillis();
        ConcurrentSkipListSet<String> siteMap = new ConcurrentSkipListSet<>();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(new Parser(url, url, siteMap));

        System.out.println("Scanning end, time = " + ((System.currentTimeMillis() - start) / 1000) + " sec");
        writeFiles(siteMap);
    }
    private static void writeFiles(ConcurrentSkipListSet<String> map) {
        String filePath = "Map.txt";
        File file = new File(filePath);
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.write(String.valueOf(map));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
