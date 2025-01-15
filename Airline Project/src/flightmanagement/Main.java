package com.flightmanagement;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FlightManager flightManager = new FlightManager();
        Scanner scanner = new Scanner(System.in);

        // Sample flights
        flightManager.addFlight(new Flight("F101", "New York", "Los Angeles", "08:00 AM", "11:00 AM", 200));
        flightManager.addFlight(new Flight("F102", "London", "Paris", "10:00 AM", "11:30 AM", 150));
        flightManager.addFlight(new Flight("F103", "Tokyo", "Osaka", "01:00 PM", "02:00 PM", 100));

        System.out.println("Welcome to the Flight Management System!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. View All Flights");
            System.out.println("2. Search Flight by Number");
            System.out.println("3. Book a Seat");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("\nAvailable Flights:");
                    for (Flight flight : flightManager.getAllFlights()) {
                        System.out.println(flight);
                    }
                    break;

                case 2:
                    System.out.print("Enter Flight Number: ");
                    String flightNumber = scanner.nextLine();
                    Flight flight = flightManager.searchFlight(flightNumber);
                    if (flight != null) {
                        System.out.println(flight);
                    } else {
                        System.out.println("Flight not found!");
                    }
                    break;

                case 3:
                    System.out.print("Enter Flight Number to Book a Seat: ");
                    String bookFlightNumber = scanner.nextLine();
                    boolean success = flightManager.bookSeatOnFlight(bookFlightNumber);
                    if (success) {
                        System.out.println("Seat booked successfully!");
                    } else {
                        System.out.println("Booking failed! Either flight not found or fully booked.");
                    }
                    break;

                case 4:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
