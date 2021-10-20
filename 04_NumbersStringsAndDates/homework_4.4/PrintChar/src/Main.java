public class Main {
    public static void main(String[] args) {
        for (int ch = 65; ch <= 122; ch++) {
            if (Character.isLetter(ch))
                System.out.println(Character.toString(ch) + " - " + ch);
        }

    }
}
