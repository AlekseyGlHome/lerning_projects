import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class XMLHandler extends DefaultHandler {

    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private final HashMap<Voter, Integer> voters;
    private Voter voter;

    public XMLHandler() {
        voters = new HashMap<>();
    }

    public HashMap<Voter, Integer> getVoters() {
        return voters;
    }

    public void printVoterCounts() {

        for (Voter voter : voters.keySet()) {
            int count = voters.get(voter);
            if (count > 1) {
                System.out.println("\t" + voter + " - " + count);
            }
        }
    }

    @Override
    public void startDocument() {
        System.out.println("Start parse to MAP");
    }

    @Override
    public void endDocument() {
        System.out.println("End parse to MAP");
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals("voter") && voter == null) {
            try {
                voter = new Voter(attributes.getValue("name"), birthDayFormat.parse(attributes.getValue("birthDay")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (qName.equals("visit") && voter != null) {
            int count = voters.getOrDefault(voter, 0);
            count++;
            voters.put(voter, count);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("voter")) {
            voter = null;
        }
    }
}

