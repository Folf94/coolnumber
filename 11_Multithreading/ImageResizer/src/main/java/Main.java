import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Main {
    private static final int PROCESSOR_CORE_COUNT = Runtime.getRuntime().availableProcessors();
    private static long start = System.currentTimeMillis();
    //private static int newWidth = 300;

    public static void main(String[] args) {
        String srcFolder = "D:/1";
        String dstFolder = "D:/2";

        File srcDir = new File(srcFolder);
        File[] files = srcDir.listFiles();

        List<List<File>> newFiles = new ArrayList<>();

        for (int i = 0; i < Math.min(PROCESSOR_CORE_COUNT, files.length); i++) {
            newFiles.add(new ArrayList<>());
        }
        for (int i = 0; i < files.length; i++) {
            newFiles.get(i % PROCESSOR_CORE_COUNT).add(files[i]);
        }

        for (int i = 0; i < Math.min(PROCESSOR_CORE_COUNT, files.length); i++) {
            ImageResizer resizer = new ImageResizer(newFiles.get(i), dstFolder, start);
            resizer.start();
        }

        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }
}
