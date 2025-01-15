package flightmanagement;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FlightManagement {
    private List<Flight> flights;

    public FlightManagement() {
        flights = new ArrayList<>();
        loadFlights();
    }

    // Passenger management to add view and remove passengers from a flight
    public void addPassenger(String flightNumber, String passengerName) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                if (flight.addPassenger(passengerName)) {
                    System.out.println("Passenger added to flight " + flightNumber);
                } else {
                    System.out.println("Flight is full.");
                }
                return;
            }
        }
        System.out.println("Flight not found: " + flightNumber);
    }

    public void removePassenger(String flightNumber, String passengerName) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                if (flight.removePassenger(passengerName)) {
                    System.out.println("Passenger removed from flight " + flightNumber);
                } else {
                    System.out.println("Passenger not found.");
                }
                return;
            }
        }
        System.out.println("Flight not found: " + flightNumber);
    }

    public void viewPassengers(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                System.out.println("Passengers on flight " + flightNumber + ": " + flight.getPassengers());
                return;
            }
        }
        System.out.println("Flight not found: " + flightNumber);
    }

    // Search and filter through different flights
    public void searchFlights(String origin, String destination, String startDate, String endDate) {
        for (Flight flight : flights) {
            boolean matchesOrigin = origin == null || flight.getOrigin().equalsIgnoreCase(origin);
            boolean matchesDestination = destination == null || flight.getDestination().equalsIgnoreCase(destination);
            boolean matchesDateRange = (startDate == null || flight.getDate().compareTo(startDate) >= 0) &&
                    (endDate == null || flight.getDate().compareTo(endDate) <= 0);

            if (matchesOrigin && matchesDestination && matchesDateRange) {
                System.out.println(flight);
            }
        }
    }

    // Real-time status updates on incoming flights and flights that are ready for departure
    public void startStatusUpdates() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Random random = new Random();
                for (Flight flight : flights) {
                    int chance = random.nextInt(100);
                    if (chance < 10) {
                        flight.setStatus("Delayed");
                    } else if (chance < 5) {
                        flight.setStatus("Cancelled");
                    } else {
                        flight.setStatus("Scheduled");
                    }
                }
                System.out.println("Flight statuses updated.");
            }
        }, 0, 60000); // this will update every minute
    }

    // Add a new flight
    public void addFlight(String flightNumber, String origin, String destination, String date, String time, int capacity) {
        Flight newFlight = new Flight(flightNumber, origin, destination, date, time, capacity);
        flights.add(newFlight);
        saveFlights();
        System.out.println("Flight added successfully: " + newFlight);
    }

    // Update an existing flight
    public void updateFlight(String flightNumber, String newOrigin, String newDestination, String newDate, String newTime, int newCapacity) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                flight.setOrigin(newOrigin);
                flight.setDestination(newDestination);
                flight.setDate(newDate);
                flight.setTime(newTime);
                flight.setCapacity(newCapacity);
                saveFlights();
                System.out.println("Flight updated successfully: " + flight);
                return;
            }
        }
        System.out.println("Flight not found: " + flightNumber);
    }

    // Delete a flight
    public void deleteFlight(String flightNumber) {
        flights.removeIf(flight -> flight.getFlightNumber().equals(flightNumber));
        saveFlights();
        System.out.println("Flight deleted successfully: " + flightNumber);
    }

    // View flights based on a filter
    public void viewFlights(String filterBy, String filterValue) {
        for (Flight flight : flights) {
            if ((filterBy.equalsIgnoreCase("origin") && flight.getOrigin().equalsIgnoreCase(filterValue)) ||
                    (filterBy.equalsIgnoreCase("destination") && flight.getDestination().equalsIgnoreCase(filterValue)) ||
                    (filterBy.equalsIgnoreCase("date") && flight.getDate().equalsIgnoreCase(filterValue))) {
                System.out.println(flight);
            }
        }
    }

    // Save flights to a file
    private void saveFlights() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("flights.ser"))) {
            oos.writeObject(flights);
        } catch (IOException e) {
            System.out.println("Error saving flights: " + e.getMessage());
        }
    }

    // Load flights from a file
    @SuppressWarnings("unchecked")
    private void loadFlights() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("flights.ser"))) {
            flights = (List<Flight>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No saved flights found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading flights: " + e.getMessage());
        }
    }
}
