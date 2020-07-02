import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovementsList {

    private final static String FILE = "date/movementList.csv";

    public static void main(String[] args) throws IOException {
        Parse parse = new Parse();
        List<String> lines = Files.readAllLines(Paths.get(FILE));
        for (int i = 1; i < lines.size(); i++) {
            parse.Calculate(lines.get(i));
        }
        parse.printSum();
    }
}
