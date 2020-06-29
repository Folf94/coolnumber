import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Files {
    public static void main(String[] args) {


        for (; ; ) {
            try {

                Scanner scanner = new Scanner(System.in);
                System.out.println("Введите папку или файл, который хотите скопировать:");
                File fromAddress = new File(scanner.nextLine());
                System.out.println("Введите папку ,в  которую хотите скопировать:");
                File toAddress = new File(scanner.nextLine());
                copyDirect(fromAddress, toAddress);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    public static void copyDirect(File sourceLocation, File targetLocation) throws IOException {
        if (sourceLocation.isDirectory()) {
            copyDirectory(sourceLocation, targetLocation);
        } else {
            copyFile(sourceLocation, targetLocation);
        }
    }

    private static void copyDirectory(File source, File target) throws IOException {
        if (!target.exists()) {
            target.mkdir();
        }

        for (String f : Objects.requireNonNull(source.list())) {
            copyDirect(new File(source, f), new File(target, f));
        }
    }

    private static void copyFile(File source, File target) throws IOException {
        try (
                InputStream in = new FileInputStream(source);
                OutputStream out = new FileOutputStream(target)
        ) {
            byte[] buf = new byte[1024];
            int length;
            while ((length = in.read(buf)) > 0) {
                out.write(buf, 0, length);
            }
        }
    }
}
