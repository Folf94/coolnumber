import java.util.Scanner;


public class Main {

    public void yourFio() {
        System.out.println("Введите ваше ФИО");
        Scanner scanner = new Scanner(System.in);
        String fio = scanner.nextLine();
        String newFio = fio.trim();
        fio.replaceAll(" ", " ");
        String space = " ";
        String[] subStr = newFio.split(space, 3);
        System.out.println("Фамилия: " + subStr[0]);
        System.out.println("Имя: " + subStr[1]);
        System.out.println("Отчество: " + subStr[2]);
    }

    public void alphaped() {
        for (char i = 'A'; i <= 'Z'; i++) {
            int c = i;
            System.out.println(i + " :" + c);
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.yourFio();
        System.out.println("+++++++++++++++++++++++++++++++");
        main.alphaped();
    }
}
