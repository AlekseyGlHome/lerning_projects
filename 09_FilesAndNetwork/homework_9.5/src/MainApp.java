
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class MainApp {
    public static final String URL = "https://www.moscowmap.ru/metro.html#lines";
    private static final String FILE_PATH = "./file/mapHomework.json";
    private static final JSONObject map = new JSONObject();
    private static final JSONObject stationObject = new JSONObject();
    private static final JSONArray conectionObject = new JSONArray();


    public static void main(String[] args) {
        parseHTML();
        parseFileJson();
    }

    private static void saveFileJson() {
        try (FileWriter fileWriter = new FileWriter(FILE_PATH)) {
            fileWriter.write(String.valueOf(map));
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parseFileJson() {
        System.out.println("Количество станций на линиях");
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(getJsonFile());
            JSONObject station = (JSONObject) jsonData.get("stations");

            for (Object key : station.keySet()) {
                JSONArray stations = (JSONArray) station.get(key);
                System.out.println("\tЛиния: \"" + key.toString() + "\" " + stations.size() + " станций.");
            }
            JSONArray conection = (JSONArray) jsonData.get("connections");
            System.out.println("Количество переходов в метро " + conection.size());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private static String getJsonFile() {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> stations = Files.readAllLines(Paths.get(FILE_PATH));
            stations.forEach(builder::append);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return builder.toString();
    }


    private static void parseHTML() {

        try {
            Document document = Jsoup.connect(URL).maxBodySize(0).get();
            parseLineToJson(document);
            parseStationToJson(document);
            saveFileJson();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void parseStationToJson(Document document) {
        Elements elements = document.select("div.js-metro-stations");
        for (Element el : elements) {
            String numberLine = el.attributes().get("data-line");
            List<Node> nodes = el.childNodes();
            String[] nameStation = el.text().replaceAll("[0-9\\.]", "").trim().split("(\\s{2})");
            JSONArray stationList = new JSONArray();
            Collections.addAll(stationList, nameStation);
            stationObject.put(numberLine, stationList);
            parseConectionToJson(numberLine, nodes, nameStation);
        }
        map.put("stations", stationObject);
        map.put("connections", conectionObject);
    }

    private static void parseConectionToJson(String numberLine, List<Node> nodes, String[] nameStation) {
        for (int i = 0; i < nodes.size(); i++) {
            List<Node> childNode = nodes.get(i).childNodes().get(0).childNodes();
            if (childNode.size() > 2) {
                for (int j = 2; j < childNode.size(); j++) {
                    JSONArray conectionList = new JSONArray();
                    String lineTemp = childNode.get(j).attributes().get("class").replaceAll("t-icon-metroln ln-", "");
                    String[] nameTemp = childNode.get(j).attributes().get("title").trim().split("[«»]");
                    JSONObject conectTwo = new JSONObject(Map.of("line", numberLine, "station", nameStation[i]));
                    JSONObject conectOne = new JSONObject(Map.of("line", lineTemp, "station", nameTemp[1]));
                    conectionList.add(conectTwo);
                    conectionList.add(conectOne);
                    if (!isFindConection(conectionList)) {
                        conectionObject.add(conectionList);
                    }
                }
            }
        }
    }

    private static boolean isFindConection(JSONArray conectionList) {
        for (Object o : conectionObject) {
            String numberLine = getElementFromConection(o, 0, "line");
            String nameLine = getElementFromConection(o, 0, "station");
            String numberLine1 = getElementFromConection(o, 1, "line");
            String nameLine2 = getElementFromConection(o, 1, "station");

            String numberLineFind = getElementFromConection(conectionList, 0, "line");
            String nameLineFind = getElementFromConection(conectionList, 0, "station");
            String numberLine1Find = getElementFromConection(conectionList, 1, "line");
            String nameLine2Find = getElementFromConection(conectionList, 1, "station");

            if (numberLine.equals(numberLine1Find)
                    && numberLine1.equals(numberLineFind)
                    && nameLine.equals(nameLine2Find)
                    && nameLine2.equals(nameLineFind)) {
                return true;
            }
        }
        return false;
    }


    private static String getElementFromConection(Object object, int index, String key) {
        return getElementFromKey(((JSONArray) object).get(index), key);
    }

    private static String getElementFromKey(Object object, String key) {
        return ((JSONObject) object).get(key).toString();
    }

    private static void parseLineToJson(Document document) {
        Elements elements = document.select("div.js-toggle-depend");

        JSONArray linesJSON = new JSONArray();
        for (Element el : elements) {
            String numberLine = el.child(0).attributes().get("data-line");
            String nameLine = el.text();
            JSONObject lineObject = new JSONObject(Map.of("number", numberLine, "name", nameLine));
            linesJSON.add(lineObject);
        }
        map.put("lines", linesJSON);
    }
}
