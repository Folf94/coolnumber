import java.util.Scanner;

public class Main {
    private static final int MAX_CONTEINER = 12;
    private static final int MAX_BOXES = 27;

    public static void main(String[] args) {

        int box;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество ящиков");
        box = scanner.nextInt();
        System.out.println("++++++++++++++++++++++++++++++++");

        int car = 0;
        int container = 0;

        if (box != 0) {
            container = box % MAX_BOXES == 0 ? box / MAX_BOXES : box / MAX_BOXES + 1;
        }
        if (container != 0) {
            car = container % MAX_CONTEINER == 0 ? container / MAX_CONTEINER : container / MAX_CONTEINER + 1;
        }
        int a = 1;
        int b = 1;
        for (int i = 1; i <= car; i++) {
            System.out.println("Грузовик: " + i);
            int j = 0;
            while (j < MAX_CONTEINER && b <= container) {
                System.out.println("    Контейнер: " + b);
                int x = 0;
                while (x < MAX_BOXES && a <= box) {
                    System.out.println("      Ящик: " + a);
                    x++;
                    a++;
                }
                j++;
                b++;
                System.out.println();
            }
        }

    }
}