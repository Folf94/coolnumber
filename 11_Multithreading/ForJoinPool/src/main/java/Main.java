import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.ForkJoinPool;

public class Main {

    private static final String URL = "http://www.lenta.ru/";
    private static final int processorCoreCount = Runtime.getRuntime().availableProcessors();
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Parser parser = new Parser(URL);
        String siteMap = processorCoreCount == 0 ? new ForkJoinPool().invoke(parser)
                : new ForkJoinPool(processorCoreCount).invoke(parser);

        System.out.println("Scanning end, time = " + ((System.currentTimeMillis() - start) / 1000) + " sec");
        writeFiles(siteMap);
    }
    private static void writeFiles(String map) {
        String filePath = "Map.txt";
        File file = new File(filePath);
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.write(map);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
