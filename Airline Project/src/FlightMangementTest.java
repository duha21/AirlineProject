package flightmanagement;

public class FlightManagementTest {
    public static void main(String[] args) {
        FlightManagement flightManagement = new FlightManagement();

        // Add flights
        flightManagement.addFlight("FL123", "New York", "London", "2025-01-15", "10:00 AM", 200);
        flightManagement.addFlight("FL456", "Paris", "Tokyo", "2025-01-16", "8:00 PM", 300);

        // View all flights
        System.out.println("\nViewing flights from New York:");
        flightManagement.viewFlights("origin", "New York");

        // Update a flight
        System.out.println("\nUpdating flight FL123:");
        flightManagement.updateFlight("FL123", "New York", "Berlin", "2025-01-17", "11:00 AM", 250);

        // View updated flight
        System.out.println("\nViewing flights from New York:");
        flightManagement.viewFlights("origin", "New York");

        // Delete a flight
        System.out.println("\nDeleting flight FL123:");
        flightManagement.deleteFlight("FL123");

        // View all flights after deletion
        System.out.println("\nViewing all flights:");
        flightManagement.viewFlights("origin", "");
    }
}
