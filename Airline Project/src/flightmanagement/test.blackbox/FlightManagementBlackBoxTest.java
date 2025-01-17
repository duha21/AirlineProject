package test.blackbox;

import flightmanagement.FlightManagement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FlightManagementBlackBoxTest {

    private FlightManagement flightManagement;

    @Before
    public void setUp() {
        flightManagement = new FlightManagement();

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddFlight_Valid() {
        flightManagement.addFlight("FL123", "NYC", "LAX", "2025-07-10", "09:00 AM", 200);

        boolean found = flightManagement.getFlights().stream()
                .anyMatch(f -> f.getFlightNumber().equals("FL123"));
        assertTrue("Flight FL123 should be added", found);
    }

    @Test
    public void testAddFlight_InvalidCapacity() {
        flightManagement.addFlight("FL999", "NYC", "LAX", "2025-07-10", "09:00 AM", -10);
        boolean found = flightManagement.getFlights().stream()
                .anyMatch(f -> f.getFlightNumber().equals("FL999"));
        assertFalse("Flight FL999 should not be added due to invalid capacity", found);
    }

    @Test
    public void testUpdateFlight_NonExisting() {
        flightManagement.updateFlight("FLNoExist", "Boston", "Chicago", "2026-01-01", "10:00 AM", 150);
        boolean found = flightManagement.getFlights().stream()
                .anyMatch(f -> f.getFlightNumber().equals("FLNoExist"));
        assertFalse("No new flight should be created for FLNoExist", found);
    }

    @Test
    public void testDeleteFlight() {
        // First add a flight
        flightManagement.addFlight("FLDel", "NYC", "Miami", "2025-08-10", "02:00 PM", 180);

        // Then delete it
        flightManagement.deleteFlight("FLDel");

        // Check if flight FLDel is gone
        boolean stillExists = flightManagement.getFlights().stream()
                .anyMatch(f -> f.getFlightNumber().equals("FLDel"));
        assertFalse("Flight FLDel should be removed", stillExists);
    }

    @Test
    public void testViewFlights_OriginFilter() {
        flightManagement.addFlight("FLA", "NYC", "Chicago", "2025-10-10", "07:00 AM", 100);
        flightManagement.addFlight("FLB", "Boston", "NYC", "2025-10-12", "09:00 AM", 120);
        flightManagement.addFlight("FLC", "NYC", "London", "2025-10-13", "10:00 AM", 220);

    }
}
