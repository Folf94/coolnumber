import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Airport;

public class TestAirport {
    public static void main(String[] args) {
        Aircraft aircraft = new Aircraft("Boeing 737-200");
        Airport airport = Airport.getInstance();
        aircraft.getModel();

        System.out.println(aircraft.getModel());
        System.out.println( airport.getAllAircrafts());


        // Airport.getAllAircrafts();

    }
}
