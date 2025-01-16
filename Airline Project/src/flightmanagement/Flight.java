package flightmanagement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Flight implements Serializable {
    private String flightNumber;
    private String origin;
    private String destination;
    private String date;
    private String time;
    private int capacity;
    private List<String> passengers; // List of passengers
    private String status; // Flight status (e.g., Scheduled, Delayed, Cancelled)

    public Flight(String flightNumber, String origin, String destination, String date, String time, int capacity) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.capacity = capacity;
        this.passengers = new ArrayList<>();
        this.status = "Scheduled"; // Default status
    }

    // Add a passenger to the flight
    public boolean addPassenger(String passengerName) {
        if (passengers.size() < capacity) {
            passengers.add(passengerName);
            return true;
        }
        return false; // Flight is full
    }

    // Remove a passenger from the flight
    public boolean removePassenger(String passengerName) {
        return passengers.remove(passengerName); // Returns true if removed, false if not found
    }

    // Get the list of passengers
    public List<String> getPassengers() {
        return new ArrayList<>(passengers); // Return a copy of the list
    }

    // Getters and Setters
    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    // Get the flight status
    public String getStatus() {
        return status;
    }

    // Set the flight status
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightNumber='" + flightNumber + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", capacity=" + capacity +
                ", passengers=" + passengers +
                ", status='" + status + '\'' +
                '}';
    }
}
