import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String WRONG_EMAIL_ANSWER = "Неверный формат email";
    private static final String INVALID_COMMAND = "Неверная комманда";
    private static final String UNKNOWN_COMMAND = "Неизвестная команда";

    private static EmailList emailList = new EmailList();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }
            String[] command = input.trim().split("\\s+");
            if (command.length == 0) {
                continue;
            }
            commandParsing(command);
        }
    }

    private static void commandParsing(String[] command) {
        switch (command[0]) {
            case "LIST":
                printingEmailList();
                break;
            case "ADD":
                if (command.length > 1) {
                    addEmail(command[1]);
                } else {
                    System.out.println(INVALID_COMMAND);
                }
                break;
            default:
                System.out.println(UNKNOWN_COMMAND);
                break;
        }
    }

    private static void printingEmailList() {
        List<String> list = emailList.getSortedEmails();
        for (String email : list) {
            System.out.println(email);
        }
    }

    private static void addEmail(String email) {
        if (emailList.isValidEmail(email)) {
            emailList.add(email);
        } else {
            System.out.println(WRONG_EMAIL_ANSWER);
        }
    }
}
