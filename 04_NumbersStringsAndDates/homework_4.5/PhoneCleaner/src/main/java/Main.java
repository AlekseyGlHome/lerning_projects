import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }
      System.out.println(printPhone(input));
    }
  }

  private static String printPhone(String input) {
    final String MESS_ERROR="Неверный формат номера";
    String phone = input.replaceAll("[^0-9]","");
    switch (phone.length()){
      case 11:
        if (phone.charAt(0)=='7'){
          return phone;
        }else if (phone.charAt(0)=='8'){
          phone = "7" + phone.substring(1);
          return phone;
        }else{
          return MESS_ERROR;
        }
      case 10:
        phone="7"+phone;
        return phone;
      default:
        return MESS_ERROR;
    }
  }

}
