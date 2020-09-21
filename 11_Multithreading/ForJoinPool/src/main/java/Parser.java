import lombok.Getter;
import lombok.Setter;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.RecursiveTask;

import static java.util.stream.Collectors.toList;

public class Parser extends RecursiveTask<ConcurrentSkipListSet<String>> {
    @Getter
    @Setter
    private String url;
    private static String startUrl;
    private ConcurrentSkipListSet<String> links;

    public Parser(String url, String startUrl, ConcurrentSkipListSet<String> links) {
        this.url = url;
        this.startUrl = startUrl;
        this.links = links;

    }

    @Override
    public ConcurrentSkipListSet<String> compute() {
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> linksArray = getChildren(url);
        List<Parser> tasks = new ArrayList<>();

        for (String curUrl : linksArray) {
            if (links.contains(curUrl))
                continue;
            links.add(curUrl);
            Parser parser = new Parser(curUrl, url, links);
            tasks.add(parser);
            parser.fork();
        }
        tasks.forEach(Parser::join);
        return links;
    }

    private List<String> getChildren(String url) {
        try {
            Document doc = Jsoup.parse(Jsoup.connect(url).get().toString());
            Elements map = doc.select("a[href]");
            return map.stream().map(element -> element.attr("href")).filter(mapSite -> !mapSite.isEmpty() && !mapSite.contains("#") && !mapSite.contains("?") && !mapSite.contains(".pdf") && (mapSite.startsWith("/") || mapSite.startsWith(startUrl)) && !mapSite.equals("/")).map(link -> getFullLink(link, startUrl)).collect(toList());
        } catch (HttpStatusException | UnsupportedMimeTypeException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private String getFullLink(String link, String startUrl) {
        return link.startsWith(startUrl) ? link : startUrl + link;
    }
}

