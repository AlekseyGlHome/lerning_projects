import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Введите путь до папки: или quit для выхода");
            String path = scanner.nextLine();
            if (path.equalsIgnoreCase("quit")) {
                break;
            }
            long size;
            try {
                size = FileUtils.calculateFolderSize(path);
                System.out.println("Размер папки " + path + formatSize(size));
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }

    public static String formatSize(long value) {
        final long K = 1024;
        final long M = K * K;
        final long G = M * K;
        final long T = G * K;

        long[] dividers = new long[]{T, G, M, K, 1};
        String[] units = new String[]{"Тб", "Гб", "Мб", "Кб", "Б"};
        if (value < 1)
            return " Папка пустая";
        String result = "";
        for (int i = 0; i < dividers.length; i++) {
            long divider = dividers[i];
            if (value >= divider) {
                double temp = divider > 1 ? (double) value / (double) divider : (double) value;
                result = new DecimalFormat("#,##0.#").format(temp) + " " + units[i];
                break;
            }
        }
        return " составляет " + result;
    }
}
