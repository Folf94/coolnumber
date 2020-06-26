import java.io.File;
import java.util.Scanner;

public class Files {

    static int volues;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь до папки:");
        String address = scanner.nextLine();

        try {
            File folder = new File(address);
            File[] files = folder.listFiles();
            for (File file : files) {
                System.out.println(file.getPath() + " \t Размер папки или файла: " + file.length() / 1024 + " kb");
                volues += file.length() / (1024 * 1024);
            }
            System.out.println("\nОбщий размер папки " + folder + " составляет: " + volues + " Mb");


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
