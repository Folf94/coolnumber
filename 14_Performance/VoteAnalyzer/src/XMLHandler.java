import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class XMLHandler extends DefaultHandler {
    private  Voter voter;
    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private int countVoters = 0;
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equals("voter")){
            Date birthday = null;
            try {
                birthday = birthDayFormat.parse(attributes.getValue("birthDay"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            voter = new Voter(attributes.getValue("name"), birthDayFormat.format(birthday));

        }
        else if (qName.equals("visit") && voter != null)
        {
            try {
                DBConnection.preparedStatement.setString(1,  voter.getName());
                DBConnection.preparedStatement.setString(2,  voter.getBirthDay());
                DBConnection.preparedStatement.addBatch();
                countVoters++;

                if (countVoters > 10000) {
                    DBConnection.preparedStatement.executeBatch();
                    DBConnection.connection.commit();
                    countVoters = 0;
                }

            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException
    {
        if(qName.equals("voter"))
        {
            voter = null;
        }
    }

    public void printDublicatedVoiters() throws SQLException {

        DBConnection.preparedStatement.executeBatch();
        DBConnection.connection.commit();
        DBConnection.printVoterCounts();
    }
}