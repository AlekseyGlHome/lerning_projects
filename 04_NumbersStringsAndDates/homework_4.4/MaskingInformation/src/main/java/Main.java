

public class Main {

    public static void main(String[] args) {

    }

    public static String searchAndReplaceDiamonds(String text, String placeholder) {
        int index1 = text.indexOf("<");
        int index2 = text.indexOf(">");
        if (text.contains("<") && text.contains(">") &&(index1<index2)) {
            while (text.contains("<") && text.contains(">")) {
                text = pareString(text, placeholder);
            }
            return text;
        } else {
            return text;
        }
    }

    private static String pareString(String text, String placeholder) {
        String str = text.substring(0, text.indexOf("<"));
        String str2 = text.substring(text.indexOf(">") + 1);
        return str + placeholder + str2;
    }

}