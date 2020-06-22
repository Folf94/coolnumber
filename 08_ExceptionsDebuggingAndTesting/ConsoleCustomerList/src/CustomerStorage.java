import exceptions.IllegalArgumentException;
import exceptions.IllegalMailException;
import exceptions.IllegalNumberException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.HashMap;
import java.util.regex.Pattern;

public class CustomerStorage {
    private HashMap<String, Customer> storage;


    public CustomerStorage() {
        storage = new HashMap<>();
    }


    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public static boolean isValidNumber(String number) {
        String numberRegex = "^\\+[0-9]{2,3}+-[0-9]{10}$";
        Pattern numberPattern = Pattern.compile(numberRegex);
        if (number == null) {
            return false;
        }
        return numberPattern.matcher(number).matches();
    }

    public void addCustomer(String data) {

        String[] components = data.split("\\s+");
        if (components.length != 4) {
            throw new IllegalArgumentException("Wrong format. Correct format: \n" + "Василий Петров vasily" +
                    ".petrov@gmail.com +79215637722");
        }
        if (!isValidEmailAddress(components[2])) {
            throw new IllegalMailException("Wrong format. Correct format: \n" + "Василий Петров vasily" + ".petrov" +
                    "@gmail.com +79215637722");
        }
        if (isValidNumber(components[3])) {
            throw new IllegalNumberException("Wrong format. Correct format: \n" + "Василий Петров vasily" + ".petrov" +
                    "@gmail.com +79215637722");
        }
        String name = components[0] + " " + components[1];
        storage.put(name, new Customer(name, components[3], components[2]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public int getCount() {
        return storage.size();
    }
}