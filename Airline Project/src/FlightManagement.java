import java.util.*;

public class FlightManagement {

    static class Flight {
        String flightNumber;
        String departure;
        String destination;
        String status; // to see whether the flights are scheduled, delayed or cancelled
        double distance;
        double duration;

        public Flight(String flightNumber, String departure, String destination, String status, double distance, double duration) {
            this.flightNumber = flightNumber;
            this.departure = departure;
            this.destination = destination;
            this.status = status;
            this.distance = distance;
            this.duration = duration;
        }

        @Override
        public String toString() {
            return "Flight Number: " + flightNumber + ", Status: " + status + ", From: " + departure + ", To: " + destination + ", Distance: " + distance + " km, Duration: " + duration + " hours.";
        }
    }

    private final List<Flight> flights = new ArrayList<>();
    private final FlightPath.Graph flightGraph = new FlightPath.Graph();

    // this is how we can add a flight to the system
    public void addFlight(String flightNumber, String departure, String destination, double distance, double duration) {
        flightGraph.addcountry(departure);
        flightGraph.addcountry(destination);
        flightGraph.addFlight(departure, destination, (int) distance);

        flights.add(new Flight(flightNumber, departure, destination, "Scheduled", distance, duration));
    }

    public void viewFlights() {
        if (flights.isEmpty()) {
            System.out.println("No flights available.");
            return;
        }
        for (Flight flight : flights) {
            System.out.println(flight);
        }
    }

    // Update the status of a flight
    public void updateFlightStatus(String flightNumber, String newStatus) {
        for (Flight flight : flights) {
            if (flight.flightNumber.equals(flightNumber)) {
                flight.status = newStatus;
                System.out.println("Flight " + flightNumber + " updated to " + newStatus);
                return;
            }
        }
        System.out.println("Flight not found.");
    }

    public void calculateShortestPath(String departure, String destination) {
        List<String> path = flightGraph.getShortestPath(departure, destination);
        if (path.isEmpty()) {
            System.out.println("No path found between " + departure + " and " + destination + ".");
        } else {
            System.out.println("Shortest path: " + String.join(" -> ", path));
        }
    }

    public static void main(String[] args) {
        FlightManagement management = new FlightManagement();

        // these are example flights
        management.addFlight("F001", "Japan", "USA", 10000, 12);
        management.addFlight("F002", "Germany", "France", 500, 1.5);

        // this is how we are able to view the flights that are avaliable
        System.out.println("Available Flights:");
        management.viewFlights();

        // additional features to calculate shortest paths, utilising the weather impact and to view fligth status
        management.updateFlightStatus("F001", "Delayed");

        System.out.println("\nCalculating Shortest Path:");
        management.calculateShortestPath("Japan", "France");

        System.out.println("\nCalculating Weather Impact:");
        management.calculateWeatherImpact("F001", 60, 15, "no");
    }
}