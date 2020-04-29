import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("u0041, u0042, u0043, u0044, u0045, u0046, u0047, u0048, u0049, u004A, u004B, u004C, u004D, u004E, u004F  " +
                "u0050, u0051, u0052, u0053, u0054, u0055, u0056, u0057, u0058, u0059, u005A");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("\u0041, \u0042, \u0043, \u0044, \u0045, \u0046, \u0047, \u0048, \u0049, \u004A, \u004B, \u004C, \u004D, \u004E, \u004F," +
                "\u0050, \u0051, \u0052, \u0053, \u0054, \u0055, \u0056, \u0057, \u0058, \u0059, \u005A");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ваше ФИО");
        String fio = scanner.nextLine();
        String razdel = " ";
        String[] subStr = fio.split(razdel, 3);
        System.out.println("Фамилия: " + subStr[0]);
        System.out.println("Имя: " + subStr[1]);
        System.out.println("Отчество: " + subStr[2]);

    }
}
