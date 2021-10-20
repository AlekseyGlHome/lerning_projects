import java.util.Scanner;
import java.util.Set;

public class Main {
    private static PhoneBook phoneBook = new PhoneBook();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Введите номер, имя или команду:");
            String input = scanner.nextLine();
            if (input.equals("QUIT")) {
                break;
            } else if (input.equals("LIST")) {
                printPhoneDB();
                continue;
            }
            if (phoneBook.isCorrectName(input)) {
                addNameBook(input);

            } else if (!phoneBook.phoneValidation(input).equals("")) {
                addPhoneBook(phoneBook.phoneValidation(input));
            } else {
                System.out.println("Неверный формат ввода");
            }
        }
    }

    private static void addPhoneBook(String input) {
        if (phoneBook.getNameByPhone(input).isEmpty()) {
            System.out.println("Такого номера нет в телефонной книге.");
            System.out.println("Введите имя абонента для номера \"" + input.trim() + "\":");
            String name = scanner.nextLine();
            if (phoneBook.isCorrectName(name)) {
                phoneBook.addContact(input, name);
                System.out.println("Контакт сохранен!");
            } else {
                System.out.println("Некорректное имя абонента");
            }
        } else {

            System.out.println(phoneBook.getNameByPhone(input));
        }
    }

    private static void addNameBook(String input) {
        if (phoneBook.getPhonesByName(input).isEmpty()) {
            System.out.println("Такого имени в телефонной книге нет.");
            System.out.println("Введите номер телефона для абонента \"" + input.trim() + "\":");
            String phone = scanner.nextLine();
            phone = phoneBook.phoneValidation(phone);
            if (!phone.equals("")) {
                phoneBook.addContact(phone, input);
                System.out.println("Контакт сохранен!");
            } else {
                System.out.println("Некорректный номер телефона");
            }
        } else {
            printingSingleAbonent(phoneBook.getPhonesByName(input));

        }
    }

    private static void printingSingleAbonent(Set<String> phonesByName) {
        for (String name : phonesByName) {
            System.out.println(name);
        }
    }

    private static void printPhoneDB() {
        for (String value : phoneBook.getAllContacts()) {
            System.out.println(value);
        }
    }
}
