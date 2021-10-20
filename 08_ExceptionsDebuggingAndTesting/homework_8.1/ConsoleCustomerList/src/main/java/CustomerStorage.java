import java.io.IOException;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerStorage {
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        String[] components = data.split("\\s+");
        if (components.length!=4){
            throw new IllegalArgumentException("Неверное количество аргументов!");
        }
        if (!isCorrectEmail(components[INDEX_EMAIL])){
            throw new IllegalArgumentException("Неверный формат Email!");
        }
        if (!isCorrectPhone(components[INDEX_PHONE])){
            throw new IllegalArgumentException("Неверный формат Телефона!");
        }
        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
    }

    private boolean isCorrectPhone(String phone) {
        phone = phone.replaceAll("[^\\+0-9]", "");
        Pattern pattern = Pattern.compile("^(8|\\+?7)?[0-9]{10}$");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();

    }

    private boolean isCorrectEmail(String email) {
        Pattern pattern = Pattern.compile("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}