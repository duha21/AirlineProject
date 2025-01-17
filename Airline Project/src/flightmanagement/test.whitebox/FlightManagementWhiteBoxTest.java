package test.whitebox;

import flightmanagement.FlightManagement;
import flightmanagement.Flight;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * White-Box Tests for FlightManagement.
 *
 * Techniques Used:
 *  - Statement Coverage
 *  - Branch Coverage
 */
public class FlightManagementWhiteBoxTest {

    private FlightManagement flightManagement;
    private static final String TEST_FILE = "flights_test_whitebox.ser";

    @Before
    public void setUp() {
 // fresh
        flightManagement = new FlightManagement() {
            @Override
            protected void saveFlights() {
                try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(new java.io.FileOutputStream(TEST_FILE))) {
                    oos.writeObject(getFlights());
                } catch (Exception e) {
                    System.out.println("Error saving flights (WhiteBox): " + e.getMessage());
                }
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void loadFlights() {
                java.io.File file = new java.io.File(TEST_FILE);
                if (!file.exists()) {
                    System.out.println("No test flights found (WhiteBox). Starting fresh.");
                    return;
                }
                try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(new java.io.FileInputStream(TEST_FILE))) {
                    flights = (java.util.List<Flight>) ois.readObject();
                } catch (Exception e) {
                    System.out.println("Error loading flights (WhiteBox): " + e.getMessage());
                }
            }
        };
    }

    @After
    public void tearDown() {
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * White-Box Technique: Branch Coverage for updateFlight()
     *
     * Branches in updateFlight():
     *  - If flightNumber found => update & return
     *  - If flightNumber not found => print not found
     */
    @Test
    public void testUpdateFlight_BranchCoverage() {
        // 1) Add a flight => covers the adding logic here from prev
        flightManagement.addFlight("WB111", "Berlin", "Moscow", "2025-09-09", "10:00 AM", 200);

        // 2) Update existing => triggers flight found branch
        flightManagement.updateFlight("WB111", "Berlin", "Moscow", "2025-09-10", "11:00 AM", 220);
        Flight updated = flightManagement.getFlights().stream()
                .filter(f -> f.getFlightNumber().equals("WB111"))
                .findFirst()
                .orElse(null);

        assertNotNull("WB111 should exist after update", updated);
        assertEquals("2025-09-10", updated.getDate());
        assertEquals(220, updated.getCapacity());

        // 3) Update non-existing => triggers not found branch
        flightManagement.updateFlight("WB999", "Rome", "LA", "2025-12-12", "10:00 AM", 100);
        boolean existsWB999 = flightManagement.getFlights().stream()
                .anyMatch(f -> f.getFlightNumber().equals("WB999"));
        assertFalse("WB999 should not be created if it doesn't exist", existsWB999);
    }

    /**
     * White-Box Technique: Statement Coverage for deleteFlight()
     * - We'll see if the removeIf statement is fully exercised.
     */
    @Test
    public void testDeleteFlight_StatementCoverage() {
        // Add multiple flights
        flightManagement.addFlight("WB200", "Sydney", "Melbourne", "2025-10-01", "09:00 AM", 150);
        flightManagement.addFlight("WB201", "Sydney", "Perth", "2025-10-02", "10:00 AM", 160);

        // Delete first one => triggers removeIf for WB200
        flightManagement.deleteFlight("WB200");
        boolean stillExists = flightManagement.getFlights().stream()
                .anyMatch(f -> f.getFlightNumber().equals("WB200"));
        assertFalse("WB200 should be removed", stillExists);

        // Check
        boolean secondExists = flightManagement.getFlights().stream()
                .anyMatch(f -> f.getFlightNumber().equals("WB201"));
        assertTrue("WB201 should still exist", secondExists);
    }
}
