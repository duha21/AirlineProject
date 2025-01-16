public class FlightJourneyTest {
    public static void main(String[] args) {
        testCase1();
        testCase2();
        testCase3();
        testCase4();
        testCase5();
        testCase6();
        testCase7();
        testCase8();
    }

    private static void testCase1() {
        // Test case with moderate weather conditions and no layovers
        FlightJourney journey = new FlightJourney();
        journey.routeLength = 1000;
        journey.rainDeparture = 10;
        journey.rainDestination = 10;
        journey.snowDeparture = 5;
        journey.snowDestination = 5;
        journey.tailWindDeparture = 10;
        journey.tailWindDestination = 10;
        journey.crossWindDeparture = 5;
        journey.crossWindDestination = 5;
        journey.turbulenceDeparture = 0.5f;
        journey.turbulenceDestination = 0.5f;
        journey.layovers = false;
        journey.numberOfLayovers = 0;

        FlightTimeCalculator calculator = new FlightTimeCalculator(journey);
        calculator.calculateFlightTime();
        calculator.displayFlightTime();
    }

    private static void testCase2() {
        // Test case with moderate weather conditions and one layover
        FlightJourney journey = new FlightJourney();
        journey.routeLength = 2000;
        journey.rainDeparture = 20;
        journey.rainDestination = 20;
        journey.snowDeparture = 10;
        journey.snowDestination = 10;
        journey.tailWindDeparture = 20;
        journey.tailWindDestination = 20;
        journey.crossWindDeparture = 10;
        journey.crossWindDestination = 10;
        journey.turbulenceDeparture = 0.6f;
        journey.turbulenceDestination = 0.6f;
        journey.layovers = true;
        journey.numberOfLayovers = 1;

        FlightTimeCalculator calculator = new FlightTimeCalculator(journey);
        calculator.calculateFlightTime();
        calculator.displayFlightTime();
    }

    private static void testCase3() {
        // Test case with moderate weather conditions and two layovers
        FlightJourney journey = new FlightJourney();
        journey.routeLength = 1500;
        journey.rainDeparture = 30;
        journey.rainDestination = 30;
        journey.snowDeparture = 15;
        journey.snowDestination = 15;
        journey.tailWindDeparture = 30;
        journey.tailWindDestination = 30;
        journey.crossWindDeparture = 15;
        journey.crossWindDestination = 15;
        journey.turbulenceDeparture = 0.7f;
        journey.turbulenceDestination = 0.7f;
        journey.layovers = true;
        journey.numberOfLayovers = 2;

        FlightTimeCalculator calculator = new FlightTimeCalculator(journey);
        calculator.calculateFlightTime();
        calculator.displayFlightTime();
    }

    private static void testCase4() {
        // Test case with severe weather conditions and three layovers
        FlightJourney journey = new FlightJourney();
        journey.routeLength = 500;
        journey.rainDeparture = 40;
        journey.rainDestination = 40;
        journey.snowDeparture = 20;
        journey.snowDestination = 20;
        journey.tailWindDeparture = 25;
        journey.tailWindDestination = 25;
        journey.crossWindDeparture = 10;
        journey.crossWindDestination = 10;
        journey.turbulenceDeparture = 0.8f;
        journey.turbulenceDestination = 0.8f;
        journey.layovers = true;
        journey.numberOfLayovers = 3;

        FlightTimeCalculator calculator = new FlightTimeCalculator(journey);
        calculator.calculateFlightTime();
        calculator.displayFlightTime();
    }

    private static void testCase5() {
        // Test case with maximum weather conditions and four layovers
        FlightJourney journey = new FlightJourney();
        journey.routeLength = 2500;
        journey.rainDeparture = 50;
        journey.rainDestination = 50;
        journey.snowDeparture = 25.4f;
        journey.snowDestination = 25.4f;
        journey.tailWindDeparture = 30;
        journey.tailWindDestination = 30;
        journey.crossWindDeparture = 20;
        journey.crossWindDestination = 20;
        journey.turbulenceDeparture = 0.8f;
        journey.turbulenceDestination = 0.8f;
        journey.layovers = true;
        journey.numberOfLayovers = 4;

        FlightTimeCalculator calculator = new FlightTimeCalculator(journey);
        calculator.calculateFlightTime();
        calculator.displayFlightTime();
    }

    private static void testCase6() {
        // Test case with no weather conditions and no layovers
        FlightJourney journey = new FlightJourney();
        journey.routeLength = 3000;
        journey.rainDeparture = 0;
        journey.rainDestination = 0;
        journey.snowDeparture = 0;
        journey.snowDestination = 0;
        journey.tailWindDeparture = 0;
        journey.tailWindDestination = 0;
        journey.crossWindDeparture = 0;
        journey.crossWindDestination = 0;
        journey.turbulenceDeparture = 0;
        journey.turbulenceDestination = 0;
        journey.layovers = false;
        journey.numberOfLayovers = 0;

        FlightTimeCalculator calculator = new FlightTimeCalculator(journey);
        calculator.calculateFlightTime();
        calculator.displayFlightTime();
    }

    private static void testCase7() {
        // Test case with moderate weather conditions and five layovers
        FlightJourney journey = new FlightJourney();
        journey.routeLength = 3500;
        journey.rainDeparture = 25;
        journey.rainDestination = 25;
        journey.snowDeparture = 12.7f;
        journey.snowDestination = 12.7f;
        journey.tailWindDeparture = 15;
        journey.tailWindDestination = 15;
        journey.crossWindDeparture = 10;
        journey.crossWindDestination = 10;
        journey.turbulenceDeparture = 0.4f;
        journey.turbulenceDestination = 0.4f;
        journey.layovers = true;
        journey.numberOfLayovers = 5;

        FlightTimeCalculator calculator = new FlightTimeCalculator(journey);
        calculator.calculateFlightTime();
        calculator.displayFlightTime();
    }

    private static void testCase8() {
        // Test case with severe weather conditions and six layovers
        FlightJourney journey = new FlightJourney();
        journey.routeLength = 4000;
        journey.rainDeparture = 35;
        journey.rainDestination = 35;
        journey.snowDeparture = 18;
        journey.snowDestination = 18;
        journey.tailWindDeparture = 20;
        journey.tailWindDestination = 20;
        journey.crossWindDeparture = 15;
        journey.crossWindDestination = 15;
        journey.turbulenceDeparture = 0.5f;
        journey.turbulenceDestination = 0.5f;
        journey.layovers = true;
        journey.numberOfLayovers = 6;

        FlightTimeCalculator calculator = new FlightTimeCalculator(journey);
        calculator.calculateFlightTime();
        calculator.displayFlightTime();
    }

    private static void testCase9() {
        long startTime = System.currentTimeMillis();
        long endTime = startTime + 10000; // 10 seconds. A test to run multiple test cases in a loop 

        int maxRouteLength = 5000;
        int maxRain = 50;
        int maxSnow = 30;
        int maxWind = 30;
        int maxCrossWind = 20;
        float maxTurbulence = 1.0f;
        int maxLayovers = 6;

        for (int routeLength = 0; routeLength <= maxRouteLength && System.currentTimeMillis() < endTime; routeLength += 500) {
            for (int rain = 0; rain <= maxRain && System.currentTimeMillis() < endTime; rain += 10) {
                for (int snow = 0; snow <= maxSnow && System.currentTimeMillis() < endTime; snow += 5) {
                    for (int wind = 0; wind <= maxWind && System.currentTimeMillis() < endTime; wind += 5) {
                        for (int crossWind = 0; crossWind <= maxCrossWind && System.currentTimeMillis() < endTime; crossWind += 5) {
                            for (float turbulence = 0; turbulence <= maxTurbulence && System.currentTimeMillis() < endTime; turbulence += 0.2f) {
                                for (int layovers = 0; layovers <= maxLayovers && System.currentTimeMillis() < endTime; layovers++) {
                                    FlightJourney journey = new FlightJourney();
                                    journey.routeLength = routeLength;
                                    journey.rainDeparture = rain;
                                    journey.rainDestination = rain;
                                    journey.snowDeparture = snow;
                                    journey.snowDestination = snow;
                                    journey.tailWindDeparture = wind;
                                    journey.tailWindDestination = wind;
                                    journey.crossWindDeparture = crossWind;
                                    journey.crossWindDestination = crossWind;
                                    journey.turbulenceDeparture = turbulence;
                                    journey.turbulenceDestination = turbulence;
                                    journey.layovers = layovers > 0;
                                    journey.numberOfLayovers = layovers;

                                    FlightTimeCalculator calculator = new FlightTimeCalculator(journey);
                                    calculator.calculateFlightTime();
                                    calculator.displayFlightTime();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

