public class Main {

  public static void main(String[] args) {
    char[][] chars = TwoDimensionalArray.getTwoDimensionalArray(7);
    for (int i = 0; i < chars.length; i++) {
      for (int j = 0; j < chars[i].length; j++) {
        System.out.print(chars[i][j]);
      }
      System.out.print(System.lineSeparator());

    }
    //Распечатайте сгенерированный в классе TwoDimensionalArray.java двумерный массив
  }
}
