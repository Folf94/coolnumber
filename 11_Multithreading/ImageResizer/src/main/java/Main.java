import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main
{
    private static final int processorCoreCount = Runtime.getRuntime().availableProcessors();
    private static long start = System.currentTimeMillis();
    private static int newWidth = 300;
    private static Queue<File> files = new ConcurrentLinkedQueue<>();
    public static void main(String[] args)
    {
        String srcFolder = "D:/1";
        String dstFolder = "D:/2";

        File srcDir = new File(srcFolder);
        files.addAll(Arrays.asList(Objects.requireNonNull(srcDir.listFiles())));

        for (int i = 0; i < processorCoreCount; i++) {
            new Thread(new ImageResizer(files, newWidth, dstFolder, start)).start();
        }

        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }
}
