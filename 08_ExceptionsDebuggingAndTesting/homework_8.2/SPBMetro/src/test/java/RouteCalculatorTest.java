import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {

    public StationIndex stationIndex;
    public RouteCalculator calculator;

    @Override
    protected void setUp() throws Exception {

        stationIndex = new StationIndex();
        Line line1 = new Line(1,"green");
        Line line2 = new Line(2,"blue");
        Line line3 = new Line(3,"violet");

        Station station1 = new Station("Приморская",line1);
        Station station2 = new Station("Василеостровская",line1);
        Station station3 = new Station("Гостинный двор",line1);

        line1.addStation(station1);
        line1.addStation(station2);
        line1.addStation(station3);

        stationIndex.addStation(station1);
        stationIndex.addStation(station2);
        stationIndex.addStation(station3);

        Station station5 = new Station("Невский проспект",line2);
        Station station6 = new Station("Горьковская",line2);
        Station station7 = new Station("Петроградская",line2);
        Station station11 = new Station("Сенная площадь",line2);
        line2.addStation(station7);
        line2.addStation(station6);
        line2.addStation(station5);
        line2.addStation(station11);
        stationIndex.addStation(station7);
        stationIndex.addStation(station6);
        stationIndex.addStation(station5);
        stationIndex.addStation(station11);

        Station station10 = new Station("Спортивная",line3);
        Station station9 = new Station("Адмиралтейская",line3);
        Station station8 = new Station("Садовая",line3);
        line3.addStation(station8);
        line3.addStation(station9);
        line3.addStation(station10);
        stationIndex.addStation(station8);
        stationIndex.addStation(station9);
        stationIndex.addStation(station10);

        List<Station> conection1 = new ArrayList<>();
        conection1.add(station3);
        conection1.add(station5);

        List<Station> conection2 = new ArrayList<>();
        conection2.add(station11);
        conection2.add(station8);

        stationIndex.addConnection(conection1);
        stationIndex.addConnection(conection2);
        calculator = new RouteCalculator(stationIndex);

    }

    public void testGetShortestRouteTwoTransfers(){
        // проверка маршрута с двумя пересадками
        Station from = stationIndex.getStation("Василеостровская");
        Station to = stationIndex.getStation("Адмиралтейская");
        List<Station> actual = calculator.getShortestRoute(from, to);
        List<Station> expected = new ArrayList<>();
        expected.add(stationIndex.getStation("Василеостровская"));
        expected.add(stationIndex.getStation("Гостинный двор"));
        expected.add(stationIndex.getStation("Невский проспект"));
        expected.add(stationIndex.getStation("Сенная площадь"));
        expected.add(stationIndex.getStation("Садовая"));
        expected.add(stationIndex.getStation("Адмиралтейская"));

        assertEquals(expected,actual);


    }

    public void testGetShortestRouteOneTransfers(){
        // проверка маршрута с одной пересадкой
        Station from = stationIndex.getStation("Василеостровская");
        Station to = stationIndex.getStation("Сенная площадь");
        List<Station> actual = calculator.getShortestRoute(from, to);
        List<Station> expected = new ArrayList<>();
        expected.add(stationIndex.getStation("Василеостровская"));
        expected.add(stationIndex.getStation("Гостинный двор"));
        expected.add(stationIndex.getStation("Невский проспект"));
        expected.add(stationIndex.getStation("Сенная площадь"));

        assertEquals(expected,actual);


    }

    public void testGetShortestRouteWithoutTransfer(){
        // проверка маршрута без пересадки
        Station from = stationIndex.getStation("Приморская");
        Station to = stationIndex.getStation("Гостинный двор");
        List<Station> actual = calculator.getShortestRoute(from, to);
        List<Station> expected = new ArrayList<>();
        expected.add(stationIndex.getStation("Приморская"));
        expected.add(stationIndex.getStation("Василеостровская"));
        expected.add(stationIndex.getStation("Гостинный двор"));

        assertEquals(expected,actual);


    }


    public void testСalculateDurationWithoutTransfer(){
        // проверка расчета времени без пересадки
        Station from = stationIndex.getStation("Приморская");
        Station to = stationIndex.getStation("Гостинный двор");
        List<Station> listStation = calculator.getShortestRoute(from, to);
        double actual = RouteCalculator.calculateDuration(listStation);
        double expected = 5;
        assertEquals(expected,actual);
    }

    public void testСalculateDurationOneTransfers(){
        // проверка расчета времени с одной пересадкой
        Station from = stationIndex.getStation("Василеостровская");
        Station to = stationIndex.getStation("Сенная площадь");
        List<Station> listStation = calculator.getShortestRoute(from, to);
        double actual = RouteCalculator.calculateDuration(listStation);
        double expected = 8.5;
        assertEquals(expected,actual);
    }

    public void testСalculateDurationTwoTransfers(){
        // проверка расчета времени с двумя пересадками
        Station from = stationIndex.getStation("Василеостровская");
        Station to = stationIndex.getStation("Адмиралтейская");
        List<Station> listStation = calculator.getShortestRoute(from, to);
        double actual = RouteCalculator.calculateDuration(listStation);
        double expected = 14.5;
        assertEquals(expected,actual);
    }

}
