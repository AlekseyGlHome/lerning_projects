import java.sql.Ref;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }
            int countName = 0;
            boolean isChar = true;

            for (int i = 0; i < input.trim().length(); i++) {
                if (input.charAt(i) == ' ') {
                    countName++;
                }
                if (!Character.isLetter(input.charAt(i)) && input.charAt(i) != ' ' && input.charAt(i) != '-') {
                    isChar = false;
                }
            }

            if (countName == 2 && isChar) {
                int index = input.trim().indexOf(' ');
                int lastIndex = input.trim().lastIndexOf(' ');
                System.out.println("Фамилия: " + input.substring(0, index));

                System.out.println("Имя: " + input.substring(index + 1, lastIndex));

                System.out.println("Отчество: " + input.substring(lastIndex + 1));

            } else {
                System.out.println("Введенная строка не является ФИО");
            }

            //При невалидном ФИО вывести в консоль: Введенная строка не является ФИО
        }
    }

}