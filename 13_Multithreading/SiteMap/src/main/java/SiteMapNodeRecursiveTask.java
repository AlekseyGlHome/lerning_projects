import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.RecursiveAction;


import static java.lang.Thread.sleep;

public class SiteMapNodeRecursiveTask extends RecursiveAction {
    private SiteMapNode url;
    private SiteMapNode rootUrl;
    private static CopyOnWriteArraySet<String> allUrl = new CopyOnWriteArraySet<>();


    public SiteMapNodeRecursiveTask(SiteMapNode url) {
        this.url = url;
    }

    public SiteMapNodeRecursiveTask(SiteMapNode url, SiteMapNode rootUrl) {
        this.url = url;
        this.rootUrl = rootUrl;
    }


    @Override
    protected void compute() {
        Set<SiteMapNodeRecursiveTask> taskList = new HashSet<>();
        try {
            sleep(500);
            Document doc = null;
            try {
                doc = Jsoup.connect(url.getUrl()).get();
            }catch (HttpStatusException ex){
                ex.printStackTrace();
            }
            if (doc !=null) {
                Elements links = doc.select("a[href]");
                for (Element link : links) {
                    String absUrl = link.attr("abs:href");
                    if (isCorrected(absUrl)) {
                        url.addSublinks(new SiteMapNode(absUrl));
                        allUrl.add(absUrl);
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        for (SiteMapNode link : url.getSublinks()) {
            SiteMapNodeRecursiveTask task = new SiteMapNodeRecursiveTask(link, rootUrl);
            task.fork();
            taskList.add(task);
        }

        for (SiteMapNodeRecursiveTask task : taskList) {
            task.join();
        }
    }

    private boolean isCorrected(String url) {
        return (!url.isEmpty() && url.startsWith(rootUrl.getUrl())
                && url.split("(?i)(www)").length==1 && !url.contains("?")
                && !allUrl.contains(url) && !url.contains("#")
                && !url.matches("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|pdf))$)"));
    }

}
