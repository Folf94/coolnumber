import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final String ACTION_MESSAGE = "Если вам нужно добавить email - введите 1.\nЕсли вам нужно вывести список email - введите 2.\nДля выходы из программы - введите 0.";

    public static void main(String[] args) {
        Email.inputEmail();

    }

    public static class Email {

        public static void inputEmail() {
            int number;
            System.out.println(ACTION_MESSAGE);
            Scanner scanner = new Scanner(System.in);
            ArrayList<String> emailList = new ArrayList<>();
            while ((number = scanner.nextInt()) != 0) {
                switch (number) {
                    case 1:
                        String email;
                        System.out.println("Введите email");
                        Scanner scanner1 = new Scanner(System.in);
                        email = scanner1.nextLine();

                        if (!isValid(email)) {
                            System.out.print("Вы ввели неверный email.\n");
                            System.out.println(ACTION_MESSAGE);
                            break;
                        } else {
                            emailList.add(email);
                            System.out.println(ACTION_MESSAGE);
                        }
                        break;
                    case 2:
                        for (int i = 0; i < emailList.size(); i++) {
                            System.out.println(i + " - " + emailList.get(i));
                        }
                        System.out.println(ACTION_MESSAGE);
                        break;
                }
            }
        }

        public static boolean isValid(String email) {
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            Pattern pattern = Pattern.compile(emailRegex);
            if (email == null)
                return false;
            return pattern.matcher(email).matches();
        }
    }
}