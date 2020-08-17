import java.io.File;
import java.util.Objects;


public class Main
{
    private static final int processorCoreCount = Runtime.getRuntime().availableProcessors();
    private static long start = System.currentTimeMillis();
    private static int newWidth = 300;
    public static void main(String[] args) {
        String srcFolder = "D:/1";
        String dstFolder = "D:/2";

        File srcDir = new File(srcFolder);
        File[] files = srcDir.listFiles();

        double step = Objects.requireNonNull(files).length / processorCoreCount;
        double result = Math.ceil(step);


         for (double i = 0.0; i <= result; i++){
             new ImageResizer(files,newWidth,dstFolder,start).start();
         }
        System.out.println("Duration: " + (System.currentTimeMillis() - start));
     }
}
