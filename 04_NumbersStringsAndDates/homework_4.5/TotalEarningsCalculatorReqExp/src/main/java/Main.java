import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  public static void main(String[] args) {

  }

  public static int calculateSalarySum(String text){
    int summa=0;
    Pattern pattern=Pattern.compile("[0-9]+");
    Matcher matcher=pattern.matcher(text);
    while (matcher.find()) {
      summa+=Integer.parseInt(matcher.group());
    }
    return summa;
  }

}