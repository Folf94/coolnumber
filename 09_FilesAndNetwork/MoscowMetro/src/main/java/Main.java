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
import java.util.stream.Collectors;


public class Main {
    private static final String URL = "https://www.moscowmap.ru/metro.html#lines";
    private static final String DATA_FILE = "data/metro.json";
    public static Map<String, Object> metro = new HashMap<>();
    public static Map<String, List<String>> stations = new HashMap<>();
    public static Set<Connections> connections = new HashSet<>();
    public static Set<CreateLines> lineSet = new HashSet<>();
    public static Set<String> lineNumbers = new HashSet<>();
    public static List<String> stationsList;
    public static String lineNum;
    public static Map<String, List<String>> connects = new HashMap<>();

    public static void main(String[] args) throws IOException, ParseException {

        Document document = Jsoup.connect(URL).maxBodySize(0).get();
        Elements stationsByLine = document.select("div.js-metro-stations");
        Elements stationsFromURL = document.select("span.js-metro-line");
        Elements connect = document.select("span.t-icon-metroln");

        for (int i = 0; i < stationsByLine.size(); i++) {
            Element e = stationsByLine.get(i);
            lineNum = e.attr("data-line");
            stationsList = e.select("p").stream().map(Element::text).collect(Collectors.toList());
            connects.put(stationsList.get(i), connect.get(i).getAllElements().eachAttr("title"));
            stations.put(lineNum, stationsList);
            lineSet.add(new CreateLines(lineNum, stationsFromURL.get(i).text(), stationsList.size()));
            /*for (int j = 0; j < connect.size(); j++) {
                List<String> conn = connect.get(j).getAllElements().eachAttr("title");
                if (!conn.isEmpty()) {
                    connections.add(new Connections(lineNum, stationsList.toString(),
                            conn.toString().replaceAll("]", "").replaceAll("\\[", "")));
                }
            }*/
        }

           /* for (int j = 0; j < connect.size(); j++) {
                List<String> conn = connect.get(j).getAllElements().eachAttr("title");
                if (!conn.isEmpty()) {
                    connections.add(new Connections(lineNum, stationsList.get(j),
                            conn.toString().replaceAll("]", "").replaceAll("\\[",
                            "")));

            }
        }*/

        /*metro.put("stations", stations);
        metro.put("lines", lineSet);*/
        metro.put("connections", connects);


        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(DATA_FILE), metro);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //printStations();
        printConnections();
    }

    public static void printStations() throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonData = (JSONObject) parser.parse(getJsonFile());
        JSONObject stationsObject = (JSONObject) jsonData.get("stations");

        stationsObject.keySet().forEach(lineNumberObject -> {
            JSONArray stationsArray = (JSONArray) stationsObject.get(lineNumberObject);
            System.out.println("Номер линиии " + lineNumberObject + " - колличество станций : " + stationsArray.size());
        });
    }

    public static void printConnections() throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonData = (JSONObject) parser.parse(getJsonFile());
        JSONObject connectionsObject = (JSONObject) jsonData.get("connections");

        connectionsObject.keySet().forEach(connectionsConnectionsObject -> {
            JSONArray stationsArray = (JSONArray) connectionsObject.get(connectionsConnectionsObject);
            System.out.println("колличество переходов : " + stationsArray.size());
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

