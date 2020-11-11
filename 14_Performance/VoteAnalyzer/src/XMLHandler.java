import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class XMLHandler extends DefaultHandler {

    StringBuilder insertQuery;
    private HashMap<Voter, Byte> voterCounts;
    private Voter voter;
    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    int maxSize = 4_000_000;
    int number = 0;
    public XMLHandler() {
        voterCounts = new HashMap<>();
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        String dateString = null;
        try {
            if (qName.equals("voter") && voter == null && number < maxSize) {
                Date birthDay = birthDayFormat.parse(attributes.getValue("birthDay"));
                dateString = birthDayFormat.format(birthDay);
                voter = new Voter(attributes.getValue("name"), dateString);

            } else if (qName.equals("visit") && voter != null) {
                int count = voterCounts.getOrDefault(voter, (byte) 0);
                voterCounts.put(voter, (byte) (count + 1));
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("voter")) {
            voter = null;
        }
    }

    public void printDuplicatedVoters() {

        System.out.println("Duplicated voters");
        for (Voter voter : voterCounts.keySet()) {
            int count = voterCounts.get(voter);
            if (count > 1) {
                System.out.println(voter.toString() + " - " + count);
            }
        }
    }

    public void writeToDb() {

        try {
            insertQuery = new StringBuilder();
            for (Voter voter : voterCounts.keySet()) {

                String name = voter.getName();
                String birthDate = voter.getBirthDay();
                int count = voterCounts.get(voter);
                insertQuery.append(insertQuery.length() == 0 ? "" : ",").append("('").append(name).append("','").append(birthDate).append("',").append(count).append(")");
            }
            DBConnection.executeMultiInsert(insertQuery);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
