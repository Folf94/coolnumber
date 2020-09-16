

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.RecursiveTask;

public class Parser  extends RecursiveTask<String> {
    @Getter
    @Setter
    private String url;
    private static String startUrl;
    private ConcurrentSkipListSet<String> links = new ConcurrentSkipListSet<>();
    public Parser(String url) {
        this.url = url.trim();
    }
    public Parser(String url, String startUrl, ConcurrentSkipListSet<String> links) {
        this.url = url.trim();
        Parser.startUrl = startUrl.trim();
        this.links = links;
    }

    @SneakyThrows
    @Override
    protected String compute() {
        StringBuffer stringBuffer = new StringBuffer(url);
        Set<Parser> subTask = new HashSet<>();
        getChildren(subTask);
        for (Parser link : subTask) {
            stringBuffer.append(link.join());
        }
        return stringBuffer.toString();
    }

    private void getChildren(Set<Parser> subTask)  {
        Document doc;
        Elements elements;

        try {
            Thread.sleep(150);
            doc = Jsoup.connect(url).get();
            elements = doc.select("a");
            for (Element element : elements) {
                String attr = element.attr("abs:href");
                URL u1 = new URL(url);
                URL u2 = new URL(attr);
                if (!attr.isEmpty() && !attr.contains("#")  && !attr.contains("?")
                        && !attr.contains(".pdf") && u1.getHost().equals(u2.getHost()) && (attr.startsWith("/") || attr.startsWith(startUrl))
                        && !attr.equals("/")){
                    Parser parser = new Parser(attr);
                    parser.fork();
                    subTask.add(parser);
                    links.add(attr);
                }
            }
        } catch (HttpStatusException | UnsupportedMimeTypeException | MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

