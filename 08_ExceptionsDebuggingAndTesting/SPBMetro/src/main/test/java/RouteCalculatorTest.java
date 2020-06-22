import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class RouteCalculatorTest extends TestCase {
    List<Station> route = new ArrayList<>();
    StationIndex stationIndexTest = new StationIndex();
   // RouteCalculator routeCalculator = new RouteCalculator(stationIndexTest);
    List<Station> connections = new ArrayList<>();

    @Override
    protected void setUp() throws Exception {


         Line line1 = new Line(1,"Первая");
         Line line2 = new Line (2, "Вторая");
         Line line3 = new Line (3, "Третья");

        stationIndexTest.addLine(new Line(1,"Первая"));
        stationIndexTest.addLine(new Line(2,"Вторая"));
        stationIndexTest.addLine(new Line(3,"Третья"));

        line1.addStation(new Station("Выхино", line1));
        line1.addStation(new Station("Жулебино", line1));
        line2.addStation(new Station("Косино", line2));
        line2.addStation(new Station("Котельники", line2));
        line3.addStation(new Station("Кузьминки", line3));
        line3.addStation(new Station("Рязанский проспект", line3));

        connections.add(new Station("Выхино", line1));
        connections.add(new Station("Косино", line2));
        stationIndexTest.addConnection(connections);
        connections.clear();
        connections.add(new Station("Котельники", line2));
        connections.add(new Station("Кузьминки", line3));

        route.add(new Station("Выхино", line1));
        route.add(new Station("Жулебино", line1));
        route.add(new Station("Косино", line2));
        route.add(new Station("Котельники", line2));
        route.add(new Station("Кузьминки", line3));
        route.add(new Station("Рязанский проспект", line3));

        stationIndexTest.addStation(new Station("Выхино",stationIndexTest.getLine(1)));
        stationIndexTest.addStation(new Station("Жулебино",stationIndexTest.getLine(1)));
        stationIndexTest.addStation(new Station("Косино",stationIndexTest.getLine(2)));
        stationIndexTest.addStation(new Station("Котельники",stationIndexTest.getLine(2)));
        stationIndexTest.addStation(new Station("Кузьминки",stationIndexTest.getLine(3)));
        stationIndexTest.addStation(new Station("Рязанский проспект",stationIndexTest.getLine(3)));

    }
    @Override
    protected void tearDown() throws Exception {

    }

    public void testGetShortestRoute() {



    }

    public void testCalculateDuration() {
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 17;
        assertEquals(expected, actual);
    }
}