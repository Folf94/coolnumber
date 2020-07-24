import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = "";
        Connection connection = DriverManager.getConnection(url, user, pass);
        Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT name,COUNT((subscriptions.subscription_date))/" +
                    "(TIMESTAMPDIFF(MONTH,min(subscriptions.subscription_date),max(subscriptions.subscription_date)))" +
                    " " +
                    "as avgMonth FROM courses  JOIN subscriptions " +
                    " " +
                    "ON courses.id = subscriptions.course_id GROUP" +
                    " BY courses.name;");
        while (resultSet.next()) {
            String courseName = resultSet.getString("name");
            String courseDate = resultSet.getString("avgMonth");

            System.out.println("Название курса: " + courseName + " - Количество подписок: " + courseDate);

        }

    }
}
