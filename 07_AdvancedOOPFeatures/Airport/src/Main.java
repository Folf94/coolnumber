import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.util.Calendar;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 2);
        airport.getTerminals().stream()
                .map(Terminal::getFlights)
                .flatMap(Collection::stream)
                .filter(flight -> {
            Calendar date = Calendar.getInstance();
            Calendar prasentTime = Calendar.getInstance();
            date.setTime(flight.getDate());
            return date.before(calendar) && date.after(prasentTime) &&
                    flight.getType().equals(Flight.Type.DEPARTURE); })
                .forEach(flight -> System.out.println(flight.getDate()));
    }

}