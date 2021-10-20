import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class XMLHandlerDB extends DefaultHandler {

    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private Voter voter;
    private StringBuilder insertQuery = new StringBuilder();
    private ArrayList<StringBuilder> queriesList = new ArrayList<>();

    public XMLHandlerDB() {
        DBConnection.getConnection();
    }

    public void printVoterCounts() {
        try {
            DBConnection.printVoterCounts();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }




    @Override
    public void startDocument() {
        System.out.println("Start parse to DB");
    }

    @Override
    public void endDocument() {
        if (insertQuery.length()>0){
            addToList();
        }

        addToDB();

        System.out.println("End parse to DB");
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
            insertToStringBuilder(voter.getName(), birthDayFormat.format(voter.getBirthDay()));
        }
    }

    private void addToList(){
        queriesList.add(insertQuery);
        insertQuery = new StringBuilder();

        if (queriesList.size()>=200){
            addToDB();
        }

    }


    private void addToDB(){
        for (StringBuilder str:queriesList) {
            try {
                DBConnection.insertMultilineQuery(str);

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

        queriesList.clear();
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("voter")) {
            if (insertQuery.length() > 250000) {
                addToList();
                //insertQuery = new StringBuilder();
            }

            voter = null;
        }
    }

    private void insertToStringBuilder(String name, String date) {
        insertQuery.append(insertQuery.length() == 0 ? "" : ",")
                .append("('")
                .append(name)
                .append("', '")
                .append(date)
                .append("')");
    }

}

