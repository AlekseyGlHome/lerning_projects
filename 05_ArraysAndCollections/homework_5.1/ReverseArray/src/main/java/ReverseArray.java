public class ReverseArray {

  public static String[] reverse(String[] strings) {
    for (int i = strings.length - 1, count = 0; i > count; i--, count++) {
      String tmp = strings[count];
      strings[count] = strings[i];
      strings[i] = tmp;
    }
    return strings;
  }
}
