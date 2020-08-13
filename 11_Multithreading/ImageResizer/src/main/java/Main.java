import java.io.File;
import java.util.Objects;


public class Main
{
    private static final int processorCoreCount = Runtime.getRuntime().availableProcessors();
    private static long start = System.currentTimeMillis();
    private static int newWidth = 300;
    public static void main(String[] args)
    {
        String srcFolder = "D:/1";
        String dstFolder = "D:/2";

        File srcDir = new File(srcFolder);
        File[] files = srcDir.listFiles();

        int step = Objects.requireNonNull(files).length / processorCoreCount;

        int digit = 0;
        File[] files1 = new File[step];

        for (File file : files) {
            files1[digit++] = file;
            if (digit == step) {
                new ImageResizer(files1,newWidth,dstFolder,start).start();
                files1 = new File[step];
                digit = 0;
            }
        }

        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }
}
