import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class FlightPathTest {

    @Test
    public void testShortestPath() {
        FlightPath.Graph graph = new FlightPath.Graph();
        graph.addcountry("A");
        graph.addcountry("B");
        graph.addcountry("C");
        graph.addFlight("A", "B", 5);
        graph.addFlight("B", "C", 10);
        graph.addFlight("A", "C", 17);

        List<String> path = graph.getShortestPath("A", "C");
        assertEquals(List.of("A", "B", "C"), path);
    }

    @Test
    public void testNoPath() {
        FlightPath.Graph graph = new FlightPath.Graph();
        graph.addcountry("A");
        graph.addcountry("B");
        graph.addcountry("C");
        graph.addFlight("A", "B", 5);

        List<String> path = graph.getShortestPath("A", "C");
        assertTrue(path.isEmpty());
    }

    @Test
    public void testSingleCountry() {
        FlightPath.Graph graph = new FlightPath.Graph();
        graph.addcountry("A");

        List<String> path = graph.getShortestPath("A", "A");
        assertEquals(List.of("A"), path);
    }
}
