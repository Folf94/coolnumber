import java.util.*;

public class Main {

    private static final String MESSAGE_TELEPHONE = "Введите ваш номер телефона";
    private static final String MESSAGE_NAME = "Введите ваше име";

    public static void main(String[] args) {
        AddressBook.setNameOrTelephone();
    }

    public static class AddressBook {
        public static void setNameOrTelephone() {
            Map<String, String> adressBook = new TreeMap<>();
            Map<String, String> telephoneBook = new TreeMap<>();
            Scanner scanner = new Scanner(System.in);
            for (; ; ) {
                System.out.println(MESSAGE_TELEPHONE);
                String telephoneNumber = scanner.nextLine().trim().replaceAll("[^\\d, LIST]", "");
                if (telephoneNumber.equals("LIST")) {
                    printMap(adressBook);
                    System.out.println(MESSAGE_TELEPHONE);
                }
                telephoneBook.put(telephoneNumber, "1");
                if (telephoneBook.containsKey(telephoneNumber)) {
                    System.out.println("Данный номер уже зарегестрирован");
                    System.out.println(MESSAGE_NAME);
                    String name = scanner.nextLine().toLowerCase().trim().replaceAll("[^a-zA-Zа-яА-я]", "");
                    telephoneBook.put(telephoneNumber, name);
                    adressBook.put(name, telephoneNumber);
                    if (adressBook.containsKey(name)) {
                        System.out.println("Вы уже зарегестрированы");
                        System.out.println("Ваше имя: " + name + " Номер телефона: " + telephoneNumber);
                    } else {
                        System.out.println(MESSAGE_TELEPHONE);
                        telephoneNumber = scanner.nextLine().trim().replaceAll("[^\\d]", "");
                        adressBook.put(telephoneNumber, name);
                        telephoneBook.put(name, telephoneNumber);
                    }
                }
            }
        }

        public static void printMap(Map<String, String> addressBook) {
            for (String s : addressBook.keySet()) {
                System.out.println("Ваше имя: " + s + " Номер телефона: " + addressBook.get(s));
            }
        }
    }
}



