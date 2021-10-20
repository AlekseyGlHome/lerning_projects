import java.io.*;
import java.util.Collections;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static final String SITE_URL = "https://skillbox.ru/";
    private static final String SITEMAP_TXT = "../main/resources/sitemap.txt";
    private static OutputStream outputStream;

    public static void main(String[] args) {
        try {
            outputStream = new FileOutputStream(SITEMAP_TXT);
            SiteMapNode rootUrl = new SiteMapNode(SITE_URL);
            new ForkJoinPool().invoke(new SiteMapNodeRecursiveTask(rootUrl,rootUrl));

            writeSitemapUrl(rootUrl);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void writeSitemapUrl(SiteMapNode node) {
        int depth = node.getDepth();
        String tabs = String.join("", Collections.nCopies(depth, "\t"));
        appendStringInFile(tabs + node.getUrl() + "\n");
        node.getSublinks().forEach(Main::writeSitemapUrl);
    }

    private static void appendStringInFile(String data) {
        try {
            outputStream.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

