import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class Parser {
    private String number;
    private String name;
    private int stationsCount;
    private String connections;


}
