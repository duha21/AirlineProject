import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class FlightManagementTest {

    @Test
    public void testAddFlight() {
        FlightManagement management = new FlightManagement();
        management.addFlight("F001", "Japan", "USA", 10000, 12);

        List<FlightManagement.Flight> flights = management.flights;
        assertEquals(1, flights.size());
        assertEquals("F001", flights.get(0).flightNumber);
        assertEquals("Japan", flights.get(0).departure);
        assertEquals("USA", flights.get(0).destination);
        assertEquals("Scheduled", flights.get(0).status);
    }

    @Test
    public void testViewFlights() {
        FlightManagement management = new FlightManagement();
        management.addFlight("F001", "Japan", "USA", 10000, 12);
        management.addFlight("F002", "Germany", "France", 500, 1.5);

        String expectedOutput = "Flight Number: F001, Status: Scheduled, From: Japan, To: USA, Distance: 10000.0 km, Duration: 12.0 hours.\n"
                + "Flight Number: F002, Status: Scheduled, From: Germany, To: France, Distance: 500.0 km, Duration: 1.5 hours.";

        management.viewFlights();
    }

    @Test
    public void testUpdateFlightStatus() {
        FlightManagement management = new FlightManagement();
        management.addFlight("F001", "Japan", "USA", 10000, 12);
        management.updateFlightStatus("F001", "Delayed");

        List<FlightManagement.Flight> flights = management.flights;
        assertEquals("Delayed", flights.get(0).status);

        management.updateFlightStatus("F999", "Cancelled");
    }

    @Test
    public void testCalculateShortestPath() {
        FlightManagement management = new FlightManagement();
        management.addFlight("F001", "Japan", "USA", 10000, 12);
        management.addFlight("F002", "USA", "France", 9000, 11);

        List<String> path = management.flightGraph.getShortestPath("Japan", "France");
        assertNotNull(path);
        assertEquals(List.of("Japan", "USA", "France"), path);

        path = management.flightGraph.getShortestPath("Japan", "Unknown");
        assertTrue(path.isEmpty());
    }
}
