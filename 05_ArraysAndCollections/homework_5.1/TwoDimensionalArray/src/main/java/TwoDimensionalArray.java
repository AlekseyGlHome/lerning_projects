public class TwoDimensionalArray {

  public static char symbol = 'X';

  public static char[][] getTwoDimensionalArray(int size) {

    char[][] chars = new char[size][size];
    for (int i = 0; i < chars.length; i++) {
      for (int j = 0; j < chars[i].length; j++) {
        if (i == j || (chars[i].length - 1) - i == j) {
          chars[i][j] = symbol;
        } else {
          chars[i][j] = ' ';
        }
      }

    }

    // массив должен содержать символ symbol по диагоналям, пример для size = 3
    // [X,  , X]
    // [ , X,  ]
    // [X,  , X]

    return chars;
  }
}
