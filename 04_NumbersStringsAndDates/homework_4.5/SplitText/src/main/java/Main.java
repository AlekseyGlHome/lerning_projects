public class Main {

  public static void main(String[] args) {
    String text =
        "When England Under-20s beat South Africa to win the 2014 World Junior Championship, Conlon scored the winning try and Itoje lifted the trophy.\n"
            + "A year later, Conlon joined Itoje at Saracens, where they shared a dressing room for three seasons.\n"
            + "\"Maro was always a leader in the England age-grade group, but when he became captain in 2014 it was pretty new for him,\" he said.\n"
            + "\"He never felt pressured by it, but maybe he felt there was an expectation on him to be a certain way.\n"
            + "\"I think that is the biggest change I have seen in Maro from then to now.\n"
            + "\"He is not so worried about being too vocal anymore, he doesn't really get people into a huddle and give a big inspirational talk, especially at Saracens where there are so many world class and older players.\n"
            + "\"Maro would delegate different areas and share the responsibility of leadership. At Saracens, it never rests on one person's shoulders. Brad might have the final word but Owen would talk about attack, Jackson Wray maybe talking defence, the scrum-half leading on the kicking game.\n"
            + "\"Maro is very much into doing his job well and leading by example, in the way he applies himself and takes his own game and analysis to the next level.\n"
            + "\"He is very, very driven and has incredible attention to detail reviewing his own performances and his own training.\"\n"
            + "Former England scrum-half Danny Care saw Itoje's stratospheric standards force his senior international team-mates to keep sharp as well.\n"
            + "\"I have never seen him have a bad training session, let alone a bad game,\" Care told Rugby Union Weekly.\n"
            + "\"The energy, professionalism, dedication he brings day in day out has a knock-on effect of making everyone around him a better player - Owen is a bit more vocal with it, but Maro does it without talking. ";
    System.out.println(splitTextInToWords(text));
  }

  public static String splitTextInToWords(String text) {
    StringBuilder stringBuilder = new StringBuilder();
    String[] strings = text.split("[,-.\";\\s0-9]+");
    for (String s : strings) {
      stringBuilder.append(s).append(System.lineSeparator());
    }
    return stringBuilder.toString().trim();
  }

}