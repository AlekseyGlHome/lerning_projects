public class Main {

  public static void main(String[] args) {

    String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";

    int salary=0;

    while (text.contains(",")){
      int index=text.indexOf(",");
      String s=text.substring(0,index);
      text=text.substring(index+1);

      salary+=calculation(s);
    }
    salary += calculation(text);
    System.out.println(salary);

  }
  static int calculation(String s){
    StringBuilder str= new StringBuilder();
    for (int i=0;i<s.length();i++){

      if (Character.isDigit(s.charAt(i))){
        str.append(s.charAt(i));
      }
    }

    return Integer.parseInt(str.toString());
  }


}