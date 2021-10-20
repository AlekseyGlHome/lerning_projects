import java.io.*;

public class FileUtils {
    public static void copyFolder(String sourceDirectory, String destinationDirectory) throws IOException {
        File[] files = new File(sourceDirectory).listFiles();
        File fileNew = new File(destinationDirectory);
        if (files != null) {
            fileNew.mkdir();
            for (File file : files) {
                if (file.isFile()) {
                    FileInputStream fileInputStream = new FileInputStream(file.getAbsoluteFile());
                    byte[] bytes = fileInputStream.readAllBytes();
                    FileOutputStream fileOutputStream = new FileOutputStream(destinationDirectory + "/" + file.getName());
                    fileOutputStream.write(bytes);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    fileInputStream.close();
                } else {
                    File folderNew = new File(destinationDirectory + "/" + file.getName());
                    copyFolder(file.getAbsolutePath(), folderNew.getAbsolutePath());
                }
            }
        } else {
            throw new FileNotFoundException("Папка не найдена");
        }
    }
}
