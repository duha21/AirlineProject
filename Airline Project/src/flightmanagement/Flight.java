package com.flightmanagement;

public class Flight {
    private String flightNumber;
    private String departureLocation;
    private String arrivalLocation;
    private String departureTime;
    private String arrivalTime;
    private int capacity;
    private int seatsBooked;

    public Flight(String flightNumber, String departureLocation, String arrivalLocation,
                  String departureTime, String arrivalTime, int capacity) {
        this.flightNumber = flightNumber;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.capacity = capacity;
        this.seatsBooked = 0;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public boolean bookSeat() {
        if (seatsBooked < capacity) {
            seatsBooked++;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Flight " + flightNumber + " from " + departureLocation + " to " + arrivalLocation +
                " departs at " + departureTime + " and arrives at " + arrivalTime +
                ". Capacity: " + capacity + ", Seats Booked: " + seatsBooked;
    }
}


