import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.List;


public class Main {
    private static final String URL = "https://www.moscowmap.ru/metro.html#lines";

    public static void main(String[] args) throws IOException, ParseException {

        Document document = Jsoup.connect(URL).maxBodySize(0).get();
        Elements stationsByLine = document.select ( "div.js-metro-stations" );
        stationsByLine.forEach(e -> {

                    String lineNum = e.attr("data-line");
                    String connections = e.attr("title");
                    System.out.println("Номер линии " + lineNum + connections + ". Станции: ");
                    e.select("p").forEach(g -> System.out.println(g.text()));
                    System.out.println();
        });
                }
    }




            // System.out.println(stationName);
            /*String stationName = cols.get(0).child(0).attr("div[class = js-toggle-depend s-depend-control-single " +
                    "s-depend-is-init s-depend-control-active]");
Elements stationsByLine = doc.select ( "div.js-metro-stations" );
stationsByLine.forEach(e -> {
    String lineNum = e.attr("data-line");
    String lineName = e.attr("name");
    System.out.println("Номер линии " + lineNum + ". Станции: ");
    e.select("p").forEach(g -> System.out.println(g.text()));
    System.out.println();
});
    }
}


             */
        /*for (Element element : lines) {
            //System.out.println(element.text());
        }
        Elements stationName = lines.select("div[class = js-metro-stations t-metrostation-list-table]");
        for (Element stations : stationName){
            System.out.println(stations.text());
        }*/






