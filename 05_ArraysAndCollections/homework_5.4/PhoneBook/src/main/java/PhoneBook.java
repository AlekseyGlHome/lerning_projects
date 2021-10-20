import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

public class PhoneBook {
    /*
     *   Ключом выбрал телефон так как телефоны уникальны а имя может повторяться
     *
     */
    private final HashMap<String, String> hashMapDB = new HashMap<>();

    public void addContact(String phone, String name) {
        // проверьте корректность формата имени и телефона
        // если такой номер уже есть в списке, то перезаписать имя абонента
        phone = phoneValidation(phone);
        if (!phone.equals("") && isCorrectName(name)) {
            hashMapDB.put(phone, name);
        }
    }

    public boolean isCorrectName(String name) {
        if (name.equals("")) {
            return false;
        }
        if (!Pattern.matches("[а-яА-Я]+", name)) {
            return false;
        }
        return true;
    }

    public String phoneValidation(String phone) {
        if (phone.equals("")) return "";
        phone = phone.replaceAll("[^0-9]", "");
        if (Pattern.matches("^(8|7)?[0-9]{10}$", phone)) {
            if (phone.length() == 11 && (phone.charAt(0) == '8' || phone.charAt(0) == '7')) {
                return phone.charAt(0) == '8' ? ("7" + phone.substring(1, 11)) : phone;
            } else if (phone.length() == 10) {
                return "7" + phone;
            } else {
                return "";
            }
        } else {
            return "";
        }
    }


    public String getNameByPhone(String phone) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найдены - вернуть пустую строку
        if (hashMapDB.containsKey(phone)) {
            return hashMapDB.get(phone) + " - " + phone;
        }
        return "";
    }

    public Set<String> getPhonesByName(String name) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найден - вернуть пустой TreeSet
        TreeSet<String> treeSet = new TreeSet<>();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name).append(" - ");
        int count = 0;
        for (String key : hashMapDB.keySet()) {
            if (hashMapDB.get(key).equals(name)) {
                if (count > 0) {
                    stringBuilder.append(",");
                }
                stringBuilder.append(key);
                count++;
            }
        }
        if (count > 0) {
            treeSet.add(stringBuilder.toString());
        }
        return treeSet;
    }

    public Set<String> getAllContacts() {
        // формат одного контакта "Имя - Телефон"
        // если контактов нет в телефонной книге - вернуть пустой TreeSet
        TreeSet<String> treeSet = new TreeSet<>();
        TreeSet<String> treeSetName = new TreeSet<>(hashMapDB.values());

        for (String name : treeSetName) {
            treeSet.addAll(getPhonesByName(name));
        }
        return treeSet;
    }

}