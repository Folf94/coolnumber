import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Scanner;


public class FilesExp {
    public static final int MAGIC_NUM = 1024;

    public static void main(String[] args) {


        try {
            for (; ; ) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Введите путь до папки:");
                String address = scanner.nextLine();
                File file = new File(address);
                System.out.println("Метод: folderSizeByFiles,"+" объем папки: " + address + " составляет " + readableFileSize(folderSizeByFiles(file)));
                System.out.println("Метод: folderSize,"+" объем папки: " + address + " составляет " + readableFileSize(folderSize(file)));
                System.out.println("Метод: folderSizeByCycle,"+" объем папки: " + address + " составляет " + readableFileSize(folderSizeByCycle(file)));

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static long folderSize(File directory) {
        long length = 0;
        if (directory.isFile())
            length += directory.length();
        else {
            for (File file : directory.listFiles()) {
                if (file.isFile())
                    length += file.length();
                else
                    length += folderSize(file);
            }
        }
        return length;
    }

    public static long folderSizeByCycle(File directory) {
        long length = 0;
        String[] s = directory.list();
        for (int i = 0; i < s.length; i++) {
            File file2 = new File(directory.getAbsolutePath() + "/" + s[i]);
            if (file2.isFile())
                length += file2.length();
            else
                length += folderSize(file2);
        }
        return length;
    }

    public static long folderSizeByFiles(File directory) throws IOException {

        Path folder = Paths.get(String.valueOf(directory));
        return Files.walk(folder).filter(p -> p.toFile().isFile()).mapToLong(p -> p.toFile().length()).sum();

    }

    public static String readableFileSize(long size) {
        if (size <= 0) {
            return "0";
        }
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(MAGIC_NUM));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(MAGIC_NUM, digitGroups)) + " " + units[digitGroups];
    }
}




            /*try {
                File folder = new File(address);
                File file = folder.listFiles(FileFilter filter);
                for (File file : folder.listFiles()) {
                    if (file.isFile()) {
                        volues += file.length();
                        if(file.length() < MAGIC_NUM)
                        System.out.println(file.getPath() + " \t Размер  файла: " + file.length() + " байт");
                        if(file.length() > MAGIC_NUM)
                            System.out.println(file.getPath() + " \t Размер  файла: " + file.length()/MAGIC_NUM + " " +
                                    "Kb");
                        if(file.length()> MAGIC_NUM*MAGIC_NUM)
                            System.out.println(file.getPath() + " \t Размер  файла: " + file.length()/
                            (MAGIC_NUM*MAGIC_NUM) +

                                    " " +
                                    "Mb");
                    }
                    if (file.isDirectory()) {
                        for (File files : file.listFiles()) {
                            volues += files.length();
                            if(file.length() < MAGIC_NUM)
                                System.out.println(files.getPath() + " \t Размер  файла: " + files.length() + " байт");
                            if(file.length() > MAGIC_NUM)
                                System.out.println(files.getPath() + " \t Размер  файла: "
        + files.length()/MAGIC_NUM + " " +
                                        "Kb");
                            if(file.length()> MAGIC_NUM*MAGIC_NUM)
                                System.out.println(files.getPath() + " \t Размер  файла: "
        + files.length()/(MAGIC_NUM*MAGIC_NUM) +
                                        " " +
                                        "Mb");
                        }
                    }
                }
                System.out.println("\nОбщий размер папки " + folder + " составляет: " + volues + " байт");

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }
}


                if (folder.isFile()){
                    volues+=folder.length();
                }
                else {
                    for (File file : folder.listFiles()) {
                        if (file.isFile()) {
                            volues+=file.length();

                        if (volues > MAGIC_NUM) {
                            System.out.println(file.getPath() + " \t Размер папки или файла: " + volues / MAGIC_NUM +
                             " kb");

                            if (volues > (MAGIC_NUM * MAGIC_NUM)) {
                                System.out.println(file.getPath() + " \t Размер папки или файла: " + (volues /
                                (MAGIC_NUM * MAGIC_NUM)) + " Mb");
                            }
                        }
                        }
                    }
                }
                System.out.println("\nОбщий размер папки " + folder + " составляет: " + volues + " байт");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }*/