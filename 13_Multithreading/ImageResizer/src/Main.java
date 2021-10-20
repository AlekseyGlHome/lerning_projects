
import javax.swing.*;
import java.io.File;
import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
    private static final int NEW_WIDTH = 300;
    private static final String SRC_FOLDER = System.getProperty("user.home") + "/Desktop/src1";
    private static final String DST_FOLDER = System.getProperty("user.home") + "/Desktop/dst";
    private static final int NUMBER_OF_PROCESSORS = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {

        File srcDir = new File(SRC_FOLDER);

        long start = System.currentTimeMillis();
        File[] files = srcDir.listFiles();
        if (files != null) {
            ConcurrentLinkedQueue<File> concurrentLinkedQueue = new ConcurrentLinkedQueue<>(Arrays.asList(files.clone()));

            for (int i = 0; i < NUMBER_OF_PROCESSORS; i++) {
                ImageResizer imageResizer = new ImageResizer(NEW_WIDTH, DST_FOLDER, concurrentLinkedQueue, start);
                imageResizer.start();
            }
        }else{
            System.out.println("Указанная папка не существует!");
        }

    }
}
