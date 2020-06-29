import java.io.File;
import java.util.Scanner;


public class Files {
    public static final int MAGIC_NUM = 1024;


    public static void main(String[] args) {
        try {
            for (; ; ) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Введите путь до папки:");
                String address = scanner.nextLine();
                File file = new File(address);
                folderSize(file);
                if (folderSize(file) < MAGIC_NUM)
                    System.out.println("Объем папки - " + file + " " + folderSize(file) + " байт");
                if (folderSize(file) < MAGIC_NUM * MAGIC_NUM)
                    System.out.println("Объем папки - " + file + " " + folderSize(file) / MAGIC_NUM + " Kбайт");
                if (folderSize(file) < MAGIC_NUM * MAGIC_NUM * MAGIC_NUM)
                    System.out.println("Объем папки - " + file + " " + folderSize(file) / (MAGIC_NUM * MAGIC_NUM) +
                            " Mбайт");
                else
                    System.out.println("Объем папки - " + file + " " + folderSize(file) / (MAGIC_NUM * MAGIC_NUM * MAGIC_NUM) +
                            " Гбайт");

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static float folderSize(File directory) {
        float length = 0.0f;

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