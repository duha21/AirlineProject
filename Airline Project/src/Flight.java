package flightmanagement;

import java.io.Serializable;

public class Flight implements Serializable {
    private String flightNumber;
    private String origin;
    private String destination;
    private String date;
    private String time;
    private int capacity;
    private List<String> passengers = new ArrayList<>(); // this has been added for passenger management which allow us to add, view and remove passengers from a specific flight


    public Flight(String flightNumber, String origin, String destination, String date, String time, int capacity) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.capacity = capacity;
    }

    public boolean addPassenger(String passengerName) {
        if (passengers.size() < capacity) {
            passengers.add(passengerName);
            return true;
        }
        return false; // this means that the specific flight is full
    }

    public boolean removePassenger(String passengerName) {
        return passengers.remove(passengerName);
    }

    public List<String> getPassengers() {
        return new ArrayList<>(passengers);
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

    @Override
    public String toString() {
        return "Flight{" +
                "flightNumber='" + flightNumber + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
