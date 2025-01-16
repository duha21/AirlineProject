package flightmanagement;

public class FlightManagementTest {
    public static void main(String[] args) {
        FlightManagement flightManagement = new FlightManagement();

        // Test 1: Add flights
        System.out.println("\nAdding flights:");
        flightManagement.addFlight("FL123", "New York", "London", "2025-01-20", "10:00 AM", 150);
        flightManagement.addFlight("FL456", "Paris", "Tokyo", "2025-02-15", "11:30 PM", 200);

        // Test 2: View flights
        System.out.println("\nViewing flights:");
        flightManagement.viewFlights("origin", "New York");

        // Test 3: Add passengers
        System.out.println("\nAdding passengers:");
        flightManagement.addPassenger("FL123", "John Doe");
        flightManagement.addPassenger("FL123", "Jane Doe");
        flightManagement.viewPassengers("FL123");

        // Test 4: Remove a passenger
        System.out.println("\nRemoving a passenger:");
        flightManagement.removePassenger("FL123", "John Doe");
        flightManagement.viewPassengers("FL123");

        // Test 5: Update a flight
        System.out.println("\nUpdating flight FL123:");
        flightManagement.updateFlight("FL123", "New York", "Berlin", "2025-01-22", "2:00 PM", 200);

        // Test 6: View updated flights
        System.out.println("\nViewing updated flights:");
        flightManagement.viewFlights("origin", "New York");

        // Test 7: Delete a flight
        System.out.println("\nDeleting flight FL123:");
        flightManagement.deleteFlight("FL123");

        // Test 8: View flights after deletion
        System.out.println("\nViewing flights after deletion:");
        flightManagement.viewFlights("origin", "");
    }
}
