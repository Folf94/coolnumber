

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.RecursiveTask;

public class Parser  extends RecursiveTask<String> {
    @Getter
    @Setter
    private String url;
    private static CopyOnWriteArraySet<String> links = new CopyOnWriteArraySet<>();

    public Parser(String url) {
        this.url = url.trim();
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

    private void getChildren(Set<Parser> subTask) throws IOException {
        Document doc;
        Elements elements;
        try {
            Thread.sleep(150);
            doc = Jsoup.connect(url).get();
            elements = doc.select("a");
            for (Element element : elements) {
                String attr = element.attr("abs:href");
                if (!attr.isEmpty() && attr.startsWith(url) && !attr
                        .contains("#")){
                    Parser parser = new Parser(attr);
                    parser.fork();
                    subTask.add(parser);
                    links.add(attr);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

