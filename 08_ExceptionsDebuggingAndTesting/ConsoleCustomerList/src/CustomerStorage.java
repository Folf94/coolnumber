import java.util.HashMap;
import java.util.regex.Pattern;

public class CustomerStorage
{
    private HashMap<String, Customer> storage;
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern PATTERN_EMAIL = Pattern.compile(EMAIL_REGEX);
    private static final String NUMBER_REGEX = "\"^7\\\\d{10}$\"" ;
    private static final Pattern PATTERN_NUMBER = Pattern.compile(NUMBER_REGEX);
    public CustomerStorage()
    {
        storage = new HashMap<>();
    }
    public static boolean isValidEmail(String email) {
        if (email == null)
            return false;
        return PATTERN_EMAIL.matcher(email).matches();
    }
    public static boolean isValidNumber(String number) {
        if (number == null)
            return false;
        return PATTERN_NUMBER.matcher(number).matches();
    }

        public void addCustomer(String data)
    {

        String[] components = data.split("\\s+");
        if (components.length !=4){
            throw new IllegalArgumentException("Wrong format. Correct format: \n" + "Василий Петров vasily.petrov@gmail.com +79215637722");
        }
        if (!isValidEmail(components[3])){
            throw new IllegalArgumentException("Wrong format. Correct format: \n" + "Василий Петров vasily" +
                    ".petrov@gmail.com +79215637722");
        }
        if(!isValidNumber(components[2])){
            throw new IllegalArgumentException("Wrong format. Correct format: \n" + "Василий Петров vasily" +
                    ".petrov@gmail.com +79215637722");
        }
        String name = components[0] + " " + components[1];
        storage.put(name, new Customer(name, components[3], components[2]));
    }

    public void listCustomers()
    {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name)
    {
        storage.remove(name);
    }

    public int getCount()
    {
        return storage.size();
    }
}