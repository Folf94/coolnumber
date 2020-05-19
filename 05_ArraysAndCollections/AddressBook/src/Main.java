import java.util.*;

public class Main {

    private static final String MESSAGE = "Введите ваше име или номер телефона";
    private static final String REGEX = "\\d+";

    public static void main(String[] args) {
        AddressBook.setNameOrTelephone();
    }

    public static class AddressBook {
        public static void setNameOrTelephone() {
            Map<String, String> addressBook = new TreeMap<>();
            Scanner scanner = new Scanner(System.in);
            String inputData;

            for (; ; ) {
                System.out.println(MESSAGE);
                String number = "";
                inputData = scanner.nextLine();
                if (inputData.matches(REGEX)) {
                    number = inputData.trim();
                }
                if (inputData.equals("LIST")) {
                    printMap(addressBook);
                }
                if (addressBook.containsValue(number)){
                    System.out.println("Ваш номер телефона уже зарегестрирован, введите имя");
                    inputData = scanner.nextLine().trim().replaceAll("[^a-zA-Zа-яА-я]", "");
                    if (addressBook.containsKey(inputData)){
                        System.out.println("Вы уже зарегестрированы\n");
                        System.out.println("Ваше имя: " + inputData + " Номер телефона: " + number);
                    }
                }
                if (addressBook.containsKey(inputData)) {
                    System.out.println("Данное имя уже зарегестрировано, введите номер телефона");
                    number = scanner.nextLine().trim().replaceAll("[^\\d]", "");
                    if (addressBook.containsValue(number)) {
                        System.out.println("Вы уже зарегестрированы\n");
                        System.out.println("Ваше имя: " + inputData + " Номер телефона: " + number);
                    }
                }
                addressBook.put(inputData, number);
            }
        }
        public static void printMap(Map<String, String> addressBook) {
            for (String s : addressBook.keySet()) {
                System.out.println("Ваше имя: " + s + " Номер телефона: " + addressBook.get(s));
            }
        }
    }
}



