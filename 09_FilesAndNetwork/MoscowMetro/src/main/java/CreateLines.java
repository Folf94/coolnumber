import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class CreateLines {
    private String number;
    private String name;
    private int stationsCount;
}
