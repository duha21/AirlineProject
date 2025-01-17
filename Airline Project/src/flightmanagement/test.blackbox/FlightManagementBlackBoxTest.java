papackage test.blackbox;

import flightmanagement.FlightManagement;
import flightmanagement.Flight;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Black-Box Tests for FlightManagement.
 *
 * Techniques Used:
 *  - Equivalence Partitioning (EP)
 *  - Boundary Value Analysis (BVA)
 *  - Basic Decision/Condition checks from a user's perspective.
 */
public class FlightManagementBlackBoxTest {

    private FlightManagement flightManagement;
    private static final String TEST_FILE = "flights_test.ser";

    @Before
    public void setUp() {
        // Create a fresh instance
        flightManagement = new FlightManagement() {
            @Override
            protected void saveFlights() {
                try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(new java.io.FileOutputStream(TEST_FILE))) {
                    oos.writeObject(getFlights());
                } catch (Exception e) {
                    System.out.println("Error saving test flights: " + e.getMessage());
                }
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void loadFlights() {
                java.io.File file = new java.io.File(TEST_FILE);
                if (!file.exists()) {
                    System.out.println("No test flights found. Starting fresh.");
                    return;
                }
                try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(new java.io.FileInputStream(TEST_FILE))) {
                    flights = (java.util.List<Flight>) ois.readObject();
                } catch (Exception e) {
                    System.out.println("Error loading test flights: " + e.getMessage());
                }
            }
        };
    }

    @After
    public void tearDown() {
        // Clean up the test file. Need it to run independently.
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * Black-Box Technique: Equivalence Partitioning (Valid Flight Data)
     *
     * Expected:
     *  - Flight with flightNumber="BB101" is successfully added (capacity > 0).
     * Actual:
     *  - Check if flight is present in flightManagement's list.
     */
    @Test
    public void testAddFlight_Valid() {
        flightManagement.addFlight("BB101", "Paris", "London", "2025-06-10", "08:00 AM", 180);

        boolean found = flightManagement.getFlights().stream()
                .anyMatch(f -> f.getFlightNumber().equals("BB101"));
        assertTrue("Flight BB101 should be added successfully", found);
    }

    /**
     * Black-Box Technique: Boundary Value Analysis (capacity = 0, borderline).
     *
     * Expected:
     *  - If the system allows capacity=0 or treats it as invalid,
     *    we must confirm behavior. Suppose we expect it's invalid:
     *    then flight shouldn't appear in the list.
     */
    @Test
    public void testAddFlight_CapacityZero() {
        flightManagement.addFlight("BB102", "NYC", "LA", "2025-07-07", "09:00 AM", 0);

        boolean found = flightManagement.getFlights().stream()
                .anyMatch(f -> f.getFlightNumber().equals("BB102"));

        assertFalse("Flight with capacity=0 is not allowed, so it shouldn't be added", found);
    }

    /**
     * Black-Box Technique: Decision - Updating a non-existing flight should fail gracefully.
     *
     * Expected:
     *  - System prints "Flight not found" or no flight is actually updated/added.
     */
    @Test
    public void testUpdateFlight_NonExisting() {
        flightManagement.updateFlight("XX999", "Rome", "Berlin", "2026-03-03", "10:00 AM", 100);
        // Check
        boolean found = flightManagement.getFlights().stream()
                .anyMatch(f -> f.getFlightNumber().equals("XX999"));
        assertFalse("No new flight should be created for XX999 if it doesn't exist", found);
    }

    /**
     * Tests deleting a flight that was successfully added.
     *
     * Expected:
     *  - The flight is removed from the list.
     */
    @Test
    public void testDeleteFlight_Existing() {
        flightManagement.addFlight("BB103", "Tokyo", "Seoul", "2025-08-01", "05:00 PM", 150);
        flightManagement.deleteFlight("BB103");

        boolean stillExists = flightManagement.getFlights().stream()
                .anyMatch(f -> f.getFlightNumber().equals("BB103"));
        assertFalse("Flight BB103 should be deleted", stillExists);
    }
}

