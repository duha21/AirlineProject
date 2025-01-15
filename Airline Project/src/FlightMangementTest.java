package flightmanagement;

public class FlightManagementTest {
    public static void main(String[] args) {
        FlightManagement flightManagement = new FlightManagement();

        // Add flights
        flightManagement.addFlight("FL123", "New York", "London", "2025-01-15", "10:00 AM", 200);
        flightManagement.addFlight("FL456", "Paris", "Tokyo", "2025-01-16", "8:00 PM", 300);

        // Passenger management
        System.out.println("\nAdding passengers:");
        flightManagement.addPassenger("FL123", "John Doe");
        flightManagement.addPassenger("FL123", "Jane Doe");
        flightManagement.viewPassengers("FL123");

        System.out.println("\nRemoving a passenger:");
        flightManagement.removePassenger("FL123", "John Doe");
        flightManagement.viewPassengers("FL123");

        // Search and filter through flights
        System.out.println("\nSearching for flights:");
        flightManagement.searchFlights("New York", "London", null, null);

        // Real-time status updates on incoming/departing flights
        System.out.println("\nStarting real-time status updates (wait for updates):");
        flightManagement.startStatusUpdates();
        try {
            Thread.sleep(120000); // Let updates run for 2 minutes
        } catch (InterruptedException e) {
            e.printStackTrace();

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
