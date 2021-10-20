import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


public class Loader {

    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    private static HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();
    private static HashMap<Voter, Integer> voterCounts = new HashMap<>();

    public static void main(String[] args) throws Exception {
        String fileName = "res/data-1572M.xml";

        // parseFile(fileName);// 220 - 300

        parseSAXDB(fileName); //17 - 45
       //parseSAX(fileName); //25 - 45

    }


    private static void parseSAXDB(String fileName) throws ParserConfigurationException, SAXException, IOException {
        long memory = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());

        long start = System.currentTimeMillis();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandlerDB handler = new XMLHandlerDB();
        parser.parse(new File(fileName), handler);
        memory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - memory;
        System.out.println("Loading time to the database " + (System.currentTimeMillis() - start) + " ms");
        System.out.println("-----------------------------------");

        //start = System.currentTimeMillis();
        //handler.printVoterCounts();
        //System.out.println("Select time  " + (System.currentTimeMillis() - start) + " ms");
        //System.out.println("-----------------------------------");
        System.out.println("SAX to DB parser memory = " + memory);
    }

    private static void parseSAX(String fileName) throws ParserConfigurationException, SAXException, IOException {
        long memory = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(new File(fileName), handler);
        memory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - memory;
        handler.printVoterCounts();
        System.out.println("SAX to MAP parser memory = " + memory);
    }

    private static void printResultDOM() {
        //Printing results
        System.out.println("Voting station work times: ");
        for (Integer votingStation : voteStationWorkTimes.keySet()) {
            WorkTime workTime = voteStationWorkTimes.get(votingStation);
            System.out.println("\t" + votingStation + " - " + workTime);
        }

        System.out.println("Duplicated voters: ");
        for (Voter voter : voterCounts.keySet()) {
            Integer count = voterCounts.get(voter);
            if (count > 1) {
                System.out.println("\t" + voter + " - " + count);
            }
        }
    }

    private static void parseFile(String fileName) throws Exception {
        long memory = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(fileName));

        findEqualVoters(doc);
        fixWorkTimes(doc);
        // printResult();

        memory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - memory;
        System.out.println("DOM parser memory = " + memory);
    }

    private static void findEqualVoters(Document doc) throws Exception {
        NodeList voters = doc.getElementsByTagName("voter");
        int votersCount = voters.getLength();
        for (int i = 0; i < votersCount; i++) {
            Node node = voters.item(i);
            NamedNodeMap attributes = node.getAttributes();

            String name = attributes.getNamedItem("name").getNodeValue();
            Date birthDay = birthDayFormat
                    .parse(attributes.getNamedItem("birthDay").getNodeValue());

            Voter voter = new Voter(name, birthDay);
            Integer count = voterCounts.get(voter);
            voterCounts.put(voter, count == null ? 1 : count + 1);
        }
    }

    private static void fixWorkTimes(Document doc) throws Exception {
        NodeList visits = doc.getElementsByTagName("visit");
        int visitCount = visits.getLength();
        for (int i = 0; i < visitCount; i++) {
            Node node = visits.item(i);
            NamedNodeMap attributes = node.getAttributes();

            Integer station = Integer.parseInt(attributes.getNamedItem("station").getNodeValue());
            Date time = visitDateFormat.parse(attributes.getNamedItem("time").getNodeValue());
            WorkTime workTime = voteStationWorkTimes.get(station);
            if (workTime == null) {
                workTime = new WorkTime();
                voteStationWorkTimes.put(station, workTime);
            }
            workTime.addVisitTime(time.getTime());
        }
    }
}