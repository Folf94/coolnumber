import java.sql.*;

public class DBConnection
{
    public static Connection connection;
    public static PreparedStatement preparedStatement;
    private static String dbName = "learn";
    private static String dbUser = "root";
    private static String dbPass = "1234";

    public static Connection getConnection()
    {
        if(connection == null)
        {
            try {
                connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + dbName +
                    "?user=" + dbUser + "&password=" + dbPass + "&serverTimezone=UTC");
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                        "id INT NOT NULL AUTO_INCREMENT, " +
                        "name TINYTEXT NOT NULL, " +
                        "birthDate DATE NOT NULL, " +
                        "PRIMARY KEY(id))");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
    public static void printVoterCounts() throws SQLException
    {
        String sql = "SELECT v.id, v.name, v.birthDate, count(*) cnt FROM learn.voter_count v  group by concat" +
                "(name, birthDate) having cnt>1 order by name";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        while(rs.next())
        {
            System.out.println("\t" + rs.getString("name") + " (" +
                    rs.getString("birthDate") + ") - " + rs.getInt("count"));
        }
    }
}
