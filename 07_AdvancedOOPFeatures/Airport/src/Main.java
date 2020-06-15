import com.skillbox.airport.Airport;
import com.skillbox.airport.Terminal;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR , 2);


       airport.getTerminals().stream()
                .map(Terminal::getFlights)
                .flatMap(Collection::stream)
                .flatMap(flight ->
         Stream.of(flight.getDate()))
                //.filter(date -> date < calendar)
                .forEach(System.out::println);


    }
}
