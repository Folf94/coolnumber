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
import java.util.stream.Stream;


public class Main {
    private static final String URL = "https://www.moscowmap.ru/metro.html#lines";
    private static final String DATA_FILE = "data/metro.json";
    public static Map<String, Object> metro = new HashMap<>();
    public static Map<String, List<String>> stations = new HashMap<>();
    public static Set<String> lineNumbers = new HashSet<>();
    public static Set<CreateLines> lineSet = new HashSet<>();



    public static void main(String[] args) throws IOException, ParseException {

        Document document = Jsoup.connect(URL).maxBodySize(0).get();
        Elements stationsByLine = document.select("div.js-metro-stations");
        Elements stationsFromURL = document.select("span.js-metro-line");


/*List<String> names = new ArrayList<>();
stationsFromURL.forEach(e -> names.add(e.select("span.js-metro-line").text()));

for (String name : names){
    System.out.println(name);
}*/

            stationsByLine.forEach(e -> lineNumbers.add(e.select("div.js-metro-stations").attr("data-line")));
            lineNumbers.forEach(line -> {
                List<String> stationList = new ArrayList<>();
                stationsByLine.parallelStream().filter(e -> e.select("div.js-metro-stations").attr("data-line").equals(line)).forEach(e ->
                    e.select("p").stream().map(Element::text).forEach(stationList::add));
                stations.put(line, stationList);
               List<String> names = new ArrayList<>();
                stationsFromURL.forEach(e -> names.add(e.select("span.js-metro-line").text()));
                stationsByLine.parallelStream().filter(e -> e.select("div.js-metro-stations").attr("data-line").equals(line)).findFirst().ifPresent((e -> lineSet.add(new CreateLines(line, null, stationList.size()))));
            });


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

        stationsObject.keySet().forEach(lineNumberObject -> {
            JSONArray stationsArray = (JSONArray) stationsObject.get(lineNumberObject);
            System.out.println("Номер линиии " + lineNumberObject + " - колличество станций : " + stationsArray.size());
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

