import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class main {
    public static void main(String[] args) {
        downloadingImagesFromFile("https://lenta.ru", "../images");
    }

    private static void downloadingImagesFromFile(String sourceUrl, String directory) {
        Connection con = Jsoup.connect(sourceUrl);
        try {
            Document document = con.get();
            Elements elements = document.select("img.g-picture");
            File fileDir = new File(directory);
            fileDir.mkdir();
            elements.forEach((o) -> {
                try {
                    System.out.println(downloadImg(o.attr("abs:src"), fileDir.getAbsolutePath()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static String downloadImg(String soursUrl, String directory) throws IOException {
        URL url = new URL(soursUrl);
        String fileName = soursUrl.substring(soursUrl.lastIndexOf("/") + 1);
        Path path = new File(directory + File.separator + fileName).toPath();
        Files.copy(url.openStream(), path, StandardCopyOption.REPLACE_EXISTING);

        return path.getFileName().toString();
    }
}
