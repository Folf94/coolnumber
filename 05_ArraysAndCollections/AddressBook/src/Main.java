import java.util.*;

public class Main {

    private static final String MESSAGE = "Введите ваше имя.";

    public static void main(String[] args) {
        AddressBook.setNameOrTelephone();

    }

    public static class AddressBook {

        public static void setNameOrTelephone() {
            Map<String, Long> addressBook = new TreeMap<>();
            Map<String, Integer> nameBook = new TreeMap<>();
            Map<Long, Integer> numberBook = new TreeMap<>();

            for (; ; ) {
                Scanner scanner = new Scanner(System.in);
                System.out.println(MESSAGE);
                Long number = null;
                String name = scanner.nextLine().trim().replaceAll("[^0-9],", "");
                if (name.equals("LIST")) {
                    printMap(addressBook);
                    System.out.println(MESSAGE);
                    continue;
                }
                nameBook.put(name, nameBook.get(name) + 1);
                if (nameBook.containsKey(name)) {
                    System.out.println("Введите номер телефона");
                    number = scanner.nextLong();
                    numberBook.put(number, numberBook.get(number) + 1);
                }
                if (nameBook.containsKey(name) && numberBook.containsKey(number)) {
                    System.out.println("Вы уже зарегестрированы");
                } else {
                    addressBook.put(nameBook.keySet(name), numberBook.keySet(number));
                }

            }


            public static void printMap (Map < String, Integer > map){
                for (String key : map.keySet()) {
                    System.out.println(key + " => " + map.get(key));
                }
            }
        }
    }
}



