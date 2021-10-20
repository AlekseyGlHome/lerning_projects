import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner;
    private static boolean isClosed;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        isClosed = false;
        while (!isClosed) {
            String sourceDirectory = scanerLine("Введите путь до папки откуда копировать:");
            String destinationDirectory = scanerLine("Введите путь до папки куда копировать:");
            if (!isClosed) {
                try {
                    FileUtils.copyFolder(sourceDirectory, destinationDirectory);
                    System.out.println("Папка скопирована из " + sourceDirectory + " в " + destinationDirectory);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String scanerLine(String header) {
        String path = "";
        if (!isClosed) {
            System.out.println(header + " или quit для выхода");
            path = scanner.nextLine();
            if (path.equalsIgnoreCase("quit")) {
                isClosed = true;
                return "";
            }
        }
        return path;
    }
}
