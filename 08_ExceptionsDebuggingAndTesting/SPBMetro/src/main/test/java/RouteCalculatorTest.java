import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RouteCalculatorTest extends TestCase {

    StationIndex stationIndexTest = new StationIndex();
    RouteCalculator calculator = new RouteCalculator(stationIndexTest);
    List<Station> noTransfer;
    List<Station> oneTransfer;
    List<Station> twoTransfer;
    Station oneOne;
    Station oneThree;
    Station twoOne;
    Station threeOne;

    @Override
    protected void setUp() throws Exception {
        Line line1 = new Line(1, "Первая");
        Line line2 = new Line(2, "Вторая");
        Line line3 = new Line(3, "Третья");
        oneOne = new Station("Выхино", line1);
        Station oneTwo = new Station("Жулебино", line1);
        oneThree = new Station("Полежаевская", line1);
        twoOne = new Station("Косино", line2);
        Station twoTwo = new Station("Котельники", line2);
        Station twoThree = new Station("Ухтомская", line2);
        threeOne = new Station("Кузьминки", line3);
        Station threeTwo = new Station("Рязанский проспект", line3);
        Station threeThree = new Station("Текстильщики", line3);

        Stream.of(line1, line2, line3).forEach(stationIndexTest::addLine);
        Stream.of(oneOne, oneTwo, oneThree, twoOne, twoTwo, twoThree, threeOne, threeTwo, threeThree)
                .peek(s -> s.getLine().addStation(s)).forEach(stationIndexTest::addStation);
        stationIndexTest.addConnection(Stream.of(oneTwo, twoTwo).collect(Collectors.toList()));
        stationIndexTest.addConnection(Stream.of(twoThree, threeThree).collect(Collectors.toList()));

        stationIndexTest.getConnectedStations(oneTwo);
        stationIndexTest.getConnectedStations(twoThree);

        noTransfer = Stream.of(oneOne, oneTwo, oneThree).collect(Collectors.toList());
        oneTransfer = Stream.of(oneOne, oneTwo, twoTwo, twoOne).collect(Collectors.toList());
        twoTransfer =
                Stream.of(oneOne, oneTwo, twoTwo, twoThree, threeThree, threeTwo, threeOne).collect(Collectors.toList());


    }

    public void testGetShortestRoute() {
        List<Station> expectedNoTransfer = noTransfer;
        List<Station> expectedOneTransfer = oneTransfer;
        List<Station> expectedTwoTransfer = twoTransfer;

        List<Station> actualNoTransfer = calculator.getShortestRoute(oneOne, oneThree);
        List<Station> actualOneTransfer = calculator.getShortestRoute(oneOne, twoOne);
        List<Station> actualTwoTransfer = calculator.getShortestRoute(oneOne, threeOne);

        assertEquals(actualNoTransfer, expectedNoTransfer);
        assertEquals(actualOneTransfer, expectedOneTransfer);
        assertEquals(actualTwoTransfer, expectedTwoTransfer);
    }

    public void testCalculateDuration() {
        double actual = RouteCalculator.calculateDuration(twoTransfer);
        double expected = 17;
        assertEquals(expected, actual);
    }
}