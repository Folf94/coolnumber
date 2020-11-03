import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

<<<<<<< HEAD
import java.sql.SQLException;
=======
import java.text.ParseException;
import java.text.SimpleDateFormat;
>>>>>>> 828464a8c117b78003057f543a3f617fb4d34ef4
import java.util.Date;
import java.util.HashMap;

public class XMLHandler extends DefaultHandler {

    StringBuilder insertQuery;
<<<<<<< HEAD
    private final HashMap<Voter,Byte> voters;
    private Voter voter;

    int maxSize = 3_000_000;

    public XMLHandler() {
        voters = new HashMap<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        try{
            if (voters.size() >= maxSize) {
                writeToDb();
                voters.clear();
            }
            if (qName.equals("voter") && voter == null) {

                voter = new Voter(attributes.getValue("name"), attributes.getValue("birthDay"));
            }
            if (qName.equals("visit") && voter != null) {

                int count = voters.getOrDefault(voter,(byte) 0);
                count++;
                voters.put(voter,(byte) count);

            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

=======
    private  HashMap<Voter, Byte> voterCounts;
    private Voter voter;
    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");


    public XMLHandler() {
        voterCounts = new HashMap<>();
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        String dateString = null;
        try {
            if (qName.equals("voter") && voter == null) {
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
>>>>>>> 828464a8c117b78003057f543a3f617fb4d34ef4
        if (qName.equals("voter")) {
            voter = null;
        }
    }

<<<<<<< HEAD

    public void writeToDb () {

        try {
            insertQuery = new StringBuilder();
            for (Voter voter : voters.keySet()) {

                String name = voter.getName();
                String birthDate = voter.getBirthDay();
                int count = voters.get(voter);
                insertQuery.append(insertQuery.length() == 0 ? "" : ",")
                        .append("('").append(name).append("','").append(birthDate).append("',").append(count).append(")");
            }
            DBConnection.executeMultiInsert(insertQuery);
        } catch (SQLException ex) {
            ex.printStackTrace();
=======
    public void printDuplicatedVoters() {

        System.out.println("Duplicated voters");
        for (Voter voter : voterCounts.keySet()) {
            int count = voterCounts.get(voter);
            if (count > 1) {
                System.out.println(voter.toString() + " - " + count);
            }
>>>>>>> 828464a8c117b78003057f543a3f617fb4d34ef4
        }
    }
}