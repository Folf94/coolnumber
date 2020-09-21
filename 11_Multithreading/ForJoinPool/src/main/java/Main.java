import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Stack;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ForkJoinPool;

public class Main {

    //private static final int processorCoreCount = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        String url = "https://agat.lada.ru/";
        ConcurrentSkipListSet<String> links = new ConcurrentSkipListSet<>();
        long start = System.currentTimeMillis();
        //Parser parser = new Parser(url, url, links);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(new Parser(url, url, links));

        System.out.println("Scanning end, time = " + ((System.currentTimeMillis() - start) / 1000) + " sec");
        writeFiles("Map.txt", links);
    }

    public static void writeFiles(String fileName, ConcurrentSkipListSet<String> links) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            PrintWriter printWriter = new PrintWriter(fileWriter);
            int spaces = 1;
            String previousUrl = null;
            Iterator<String> urlIterator = links.iterator();
            Stack<String> parents = new Stack<>();
            while (urlIterator.hasNext()) {
                String curUrl = urlIterator.next();
                if (previousUrl != null) {
                    if (curUrl.contains(previousUrl)) {
                        spaces += 4;
                        parents.push(previousUrl);
                    } else {
                        while (!parents.isEmpty() && !curUrl.contains(parents.peek())) {
                            parents.pop();
                            spaces -= 4;
                        }
                    }
                }
                printWriter.printf("%" + spaces + "s%s%n", "", curUrl);
                previousUrl = curUrl;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
