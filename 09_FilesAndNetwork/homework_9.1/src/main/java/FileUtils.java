import java.io.File;
import java.nio.file.FileSystemNotFoundException;

public class FileUtils {

    public static long calculateFolderSize(String path) {

        File[] files = new File(path).listFiles();
        long size = 0L;
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    size += calculateFolderSize(file.getAbsolutePath());
                } else {
                    size += file.length();
                }
            }
        } else {
            throw new FileSystemNotFoundException("Файл или папка не найдены");

        }

        return size;
    }
}
