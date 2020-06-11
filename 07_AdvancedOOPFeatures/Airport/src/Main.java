import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        ArrayList<Terminal> terminals = new ArrayList<>(airport.getTerminals());


        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        formatter.format(date);

        Stream<Terminal> stream = terminals.stream();
        //stream.filter(terminal -> terminal.getFlights()

        for (Terminal list : terminals){
         System.out.println(list.getFlights());
     }
    }
}
