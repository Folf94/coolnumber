import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    private static final String MESSAGE = "Введите ваше имя или номер телефона";

    public static void main(String[] args) {
        AddressBook.setNameOrTelephone();

    }

    public static class AddressBook {

        public static void setNameOrTelephone() {
           // System.out.println(MESSAGE);
            TreeMap<String, String> addressBook = new TreeMap<>();

            Scanner scanner = new Scanner(System.in);
            for (; ; ) {

                    System.out.println(MESSAGE);
                    String name = scanner.nextLine();
                    if (name.equals("LIST")) {
                        printMap(addressBook);
                        System.out.println(MESSAGE);
                        continue;
                    }
                String telephonNumber = scanner.nextLine();
                    if (addressBook.containsKey(name)) {
                        addressBook.put(name, telephonNumber);
                        if (name.equals(name)) {
                            System.out.println("Данное имя уже используется, введите номер телефона");
                            telephonNumber = scanner.nextLine();
                            if (!telephonNumber.equals(telephonNumber)) {
                                addressBook.put(name, telephonNumber);
                                if (telephonNumber.equals(telephonNumber)) {
                                    System.out.println("Ваш номер уже используется");
                                    if (name.equals(name)) {
                                        System.out.println("Вы уже зарегестрированы");
                                        continue;
                                    }
                                }
                            }
                        }
                    }
                    addressBook.put(name, telephonNumber);
                    //System.out.println(MESSAGE);
                }
            }

        public static void printMap(Map<String, String> map) {
            for (String key : map.keySet()) {
                System.out.println(key + " => " + map.get(key));
            }
        }


    }
}
