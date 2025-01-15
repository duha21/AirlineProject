package com.flightmanagement;

import java.util.ArrayList;
import java.util.List;

public class FlightManager {
    private List<Flight> flights;

    public FlightManager() {
        this.flights = new ArrayList<>();
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public Flight searchFlight(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                return flight;
            }
        }
        return null;
    }

    public List<Flight> getAllFlights() {
        return flights;
    }

    public boolean bookSeatOnFlight(String flightNumber) {
        Flight flight = searchFlight(flightNumber);
        if (flight != null) {
            return flight.bookSeat();
        }
        return false;
    }
}
