import java.util.Scanner;
import java.util.Random;

public class FlightJourney {
    public float routeLength;
    public float rainDeparture;
    public float rainDestination;
    public float snowDeparture;
    public float snowDestination;
    public float tailWindDeparture;
    public float tailWindDestination;
    public float crossWindDeparture;
    public float crossWindDestination;
    public float turbulenceDeparture;
    public float turbulenceDestination;
    public boolean layovers;
    public int numberOfLayovers;
    public boolean cancelled;
    public String cancelReason;

    public FlightJourney() {
        // Constructor
        cancelled = false;
        cancelReason = "";
    }

    public void finalize() {
        // Destructor
    }

    public void getInput() {
        Scanner scanner = new Scanner(System.in);

        routeLength = getValidatedInput(scanner, "Route length (km)", 0, Float.MAX_VALUE);
        rainDeparture = getValidatedInput(scanner, "Rain at departure (mm/hr)", 0, 50);
        rainDestination = getValidatedInput(scanner, "Rain at destination (mm/hr)", 0, 50);
        snowDeparture = getValidatedInput(scanner, "Snow at departure (cm/hr)", 0, 25.4f);
        snowDestination = getValidatedInput(scanner, "Snow at destination (cm/hr)", 0, 25.4f);
        tailWindDeparture = getValidatedInput(scanner, "Tail Wind at departure (km/hr)", 0, 30);
        tailWindDestination = getValidatedInput(scanner, "Tail Wind at destination (km/hr)", 0, 30);
        crossWindDeparture = getValidatedInput(scanner, "Cross Wind at departure (km/hr)", 0, 20);
        crossWindDestination = getValidatedInput(scanner, "Cross Wind at destination (km/hr)", 0, 20);
        turbulenceDeparture = getValidatedInput(scanner, "Turbulence at departure (EDR)", 0, 0.8f);
        turbulenceDestination = getValidatedInput(scanner, "Turbulence at destination (EDR)", 0, 0.8f);

        if (cancelled) {
            System.out.println("Flight conditions for " + cancelReason + " too dangerous, flight cancelled.");
            scanner.close();
            return;
        }

        System.out.print("Are there layovers? (true/false): ");
        while (!scanner.hasNextBoolean()) {
            System.out.println("That is the wrong data type, please enter true or false.");
            scanner.next();
        }
        layovers = scanner.nextBoolean();

        if (layovers) {
            System.out.print("Number of layovers (0-6): ");
            numberOfLayovers = getValidatedLayovers(scanner);
        }

        scanner.close();
    }

    private float getValidatedInput(Scanner scanner, String prompt, float min, float max) {
        float value;
        while (true) {
            System.out.print("Please input " + prompt + " (" + min + " - " + max + "): ");
            if (scanner.hasNextFloat()) {
                value = scanner.nextFloat();
                if (value >= min && value <= max) {
                    return value;
                } else if (value > max) {
                    cancelled = true;
                    cancelReason = prompt;
                    return value;
                } else {
                    System.out.println("Value out of range, please enter between " + min + " and " + max);
                }
            } else {
                System.out.println("That is the wrong data type, please enter it to 2 dp.");
                scanner.next();
            }
        }
    }

    private int getValidatedLayovers(Scanner scanner) {
        int value;
        while (true) {
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                if (value >= 0 && value <= 6) {
                    return value;
                } else {
                    System.out.println("Too many layovers, please input between 0 and 6.");
                }
            } else {
                System.out.println("That is the wrong data type, please enter an integer.");
                scanner.next();
            }
        }
    }

    public void displayInputs() {
        if (cancelled) {
            System.out.println("Flight conditions for " + cancelReason + " too dangerous, flight cancelled.");
            return;
        }
        System.out.println("Route Length: " + routeLength + " km");
        System.out.println("Rain at departure: " + rainDeparture + " mm/hr");
        System.out.println("Rain at destination: " + rainDestination + " mm/hr");
        System.out.println("Snow at departure: " + snowDeparture + " cm/hr");
        System.out.println("Snow at destination: " + snowDestination + " cm/hr");
        System.out.println("Tail Wind at departure: " + tailWindDeparture + " km/hr");
        System.out.println("Tail Wind at destination: " + tailWindDestination + " km/hr");
        System.out.println("Cross Wind at departure: " + crossWindDeparture + " km/hr");
        System.out.println("Cross Wind at destination: " + crossWindDestination + " km/hr");
        System.out.println("Turbulence at departure: " + turbulenceDeparture + " EDR");
        System.out.println("Turbulence at destination: " + turbulenceDestination + " EDR");
        System.out.println("Layovers: " + layovers);
        if (layovers) {
            System.out.println("Number of Layovers: " + numberOfLayovers);
        }
    }

    public static void main(String[] args) {
        FlightJourney journey = new FlightJourney();
        journey.getInput();
        journey.displayInputs();

        if (!journey.cancelled) {
            FlightTimeCalculator calculator = new FlightTimeCalculator(journey);
            calculator.calculateFlightTime();
            calculator.displayFlightTime();
        }
    }
}

class FlightTimeCalculator {
    private FlightJourney journey;
    private float planeSpeed;
    private float totalFlightTime;
    private float weatherTime;
    private float ATtime;

    public FlightTimeCalculator(FlightJourney journey) {
        this.journey = journey;
        this.planeSpeed = 960; // Default speed in km/h
    }

    public void calculateFlightTime() {
        // Adjust plane speed based on turbulence, cross wind, and tail wind
        float turbulenceEffect = (journey.turbulenceDeparture + journey.turbulenceDestination) / 2 * 0.1f;
        float crossWindEffect = (journey.crossWindDeparture + journey.crossWindDestination) / 2 * 0.1f;
        float tailWindEffect = (journey.tailWindDeparture + journey.tailWindDestination) / 2 * 0.1f;

        planeSpeed -= planeSpeed * (turbulenceEffect + crossWindEffect - tailWindEffect);

        // Calculate total flight time
        totalFlightTime = journey.routeLength / planeSpeed;

        // Calculate weather time
        weatherTime = 0;
        weatherTime += (journey.rainDeparture / 50) * 0.5;
        weatherTime += (journey.rainDestination / 50) * 0.5;
        weatherTime += (journey.snowDeparture / 25.4f) * 1;
        weatherTime += (journey.snowDestination / 25.4f) * 1;

        // Calculate ATtime
        Random random = new Random();
        ATtime = totalFlightTime + weatherTime;
        for (int i = 0; i < journey.numberOfLayovers; i++) {
            ATtime += random.nextInt(1440) / 60.0; // Random minutes converted to hours
        }
    }

    public void displayFlightTime() {
        System.out.println("Adjusted Plane Speed: " + planeSpeed + " km/h");
        System.out.println("Total Flight Time: " + totalFlightTime + " hours");
        System.out.println("Weather Time: " + weatherTime + " hours");
        System.out.println("Total Flight Time with Layovers and Weather Time (ATtime): " + ATtime + " hours");
    }
}
