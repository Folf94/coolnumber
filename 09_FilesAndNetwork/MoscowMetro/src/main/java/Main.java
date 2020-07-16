import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class Main {
private static final String URL = "https://www.moscowmap.ru/metro.html#lines";
    private static final String DATA_FILE = "data/metro.json";
    public static Map<String, Object> metro = new HashMap<>();
    public static Map<String, List<String>> stations = new HashMap<>();
    public static Set<String> lineNumbers = new HashSet<>();
    public static Set<Parser> lineSet = new HashSet<>();


    public static void main(String[] args) throws IOException, ParseException {

        Document document = Jsoup.connect(URL).maxBodySize(0).get();
        Elements stationsByLine = document.select("div.js-metro-stations");

        /*stationsByLine.forEach(e -> lineNumbers.add(e.select("div.js-metro-stations").attr("data-line")));
        lineNumbers.forEach(line -> {
            List<String> stationList = new ArrayList<>();

            stationsByLine.parallelStream().forEach(e -> stationList.add(stationsByLine.select("p").text()));
            stations.put(line, stationList);
            stationsByLine.parallelStream().forEach(e -> lineSet.add(new Parser(line,
                    e.select("span.js-metro-line").text(),
                    stationList.size())));
        });*/
        for (int i = 0; i < stationsByLine.size(); i++) {
            Element e = stationsByLine.get(i);
            lineNumbers.add(e.select("div.js-metro-stations").attr("data-line"));
            int finalI = i;
            lineNumbers.forEach(line -> {
                        List<String> stationList = new ArrayList<>();
            stationList.add(stationsByLine.select("p").text());
            stations.put(line, stationList);
                        lineSet.add(new Parser(line,
                                e.select("span.js-metro-line").get(finalI).text(),
                                stationList.size()));
            });
        }

        metro.put("stations", stations);
        metro.put("lines", lineSet);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(DATA_FILE), metro);

        print();
    }
    public static void print() throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonData = (JSONObject) parser.parse(getJsonFile());
        JSONObject stationsObject = (JSONObject) jsonData.get("stations");

        stationsObject.keySet().stream().sorted(Comparator.comparingInt(s -> Integer.parseInt(((String)s)
                .replaceAll("[^\\d]", "")))).forEach(lineNumberObject ->
        {
            JSONArray stationsArray = (JSONArray) stationsObject.get(lineNumberObject);
            int stationsCount = stationsArray.size();
            System.out.println("Номер линиии " + lineNumberObject + " - колличество станций : " + stationsCount);
        });
    }
    public static String getJsonFile() {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(DATA_FILE));
            lines.forEach(builder::append);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }
}

