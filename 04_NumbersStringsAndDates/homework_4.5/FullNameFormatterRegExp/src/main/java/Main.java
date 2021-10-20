import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }

      String[] strings = input.split("[\\s]+");
      if (strings.length==3 && Pattern.matches("[\\s-а-яА-Я]+",input)){

        System.out.println("Фамилия: " + strings[0]);

        System.out.println("Имя: " + strings[1]);

        System.out.println("Отчество: " + strings[2]);
      }else{
        System.out.println("Введенная строка не является ФИО");
      }
      //При невалидном ФИО вывести в консоль: Введенная строка не является ФИО
    }
  }

}