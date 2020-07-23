import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
public class CreateLines {
    private String number;
    private String name;
    private int stationsCount;

}
