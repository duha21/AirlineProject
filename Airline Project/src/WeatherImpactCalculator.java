import java.util.Scanner;
import java.util.Random;

public class WeatherImpactCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Gets input for distance
            System.out.print("Enter the distance of the trip (in km): ");
            int distance = scanner.nextInt();

            // Gets input for wind speed
            System.out.print("Enter the wind speed (in km/h): ");
            int windSpeed = scanner.nextInt();

            // Gets input for snow level
            System.out.print("Enter the snow level (in cm): ");
            int snowLevel = scanner.nextInt();

            // Gets input if the airport is closed
            System.out.print("Is there a possibility of airport closure? (yes/no): ");
            String airportClosure = scanner.next();

            // Geta input for layovers
            System.out.print("Are layovers expected? (yes/no): ");
            String layovers = scanner.next();

            // Calculate impact factor
            double impactFactor = calculateImpactFactor(windSpeed, snowLevel, airportClosure);
            double additionalTime = Math.min(distance * impactFactor, 32.0); // Ensure max additional time is 32 hours

            // additional time for layovers
            if (layovers.equalsIgnoreCase("yes")) {
                Random random = new Random();
                int layoverTime = random.nextInt(1440); // Layover time in minutes (0 to 24 hours)
                additionalTime += layoverTime / 60.0;
                System.out.println("Additional layover time: " + (layoverTime / 60) + " hours and " + (layoverTime % 60) + " minutes.");
            }

            // Check for airport closure
            if (airportClosure.equalsIgnoreCase("yes")) {
                System.out.println("Worst-case scenario: Flight cancellation due to airport closure.");
            } else {
                System.out.println("Estimated additional flight time due to weather anomalies: " + additionalTime + " hours.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter the correct values.");
        } finally {
            scanner.close();
        }
    }

    private static double calculateImpactFactor(int windSpeed, int snowLevel, String airportClosure) {
        double impactFactor = 0.0;

        // Increase IF for wind 
        if (windSpeed > 50) {
            impactFactor += 0.1;
        }

        // Increase IF for snow
        if (snowLevel > 10) {
            impactFactor += 0.2;
        }

        // Increase IF for airport closure
        if (airportClosure.equalsIgnoreCase("yes")) {
            impactFactor += 1.0;
        }

        return impactFactor;
    }
}