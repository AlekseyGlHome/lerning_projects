public class Main {

    public static void main(String[] args) {
        String str = "34><6346 <444 444> 77 <665 87>444";
        System.out.println(searchAndReplaceDiamonds(str,"***"));
    }

    public static String searchAndReplaceDiamonds(String text, String placeholder) {
        text = text.replaceAll("<[\\s0-9]+>",placeholder);
        return text;
    }

}