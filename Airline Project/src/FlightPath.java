import java.util.*;

class FlightPath {
    static class Edge {
        String targetcountry;
        int distance;

        Edge(String targetcountry, int distance) {
            this.targetcountry = targetcountry;
            this.distance = distance;
        }
    }

    static class Graph {
        private final Map<String, List<Edge>> adjacencyList = new HashMap<>();

        void addcountry(String country) {
            adjacencyList.putIfAbsent(country, new ArrayList<>());
        }

        void addFlight(String fromcountry, String tocountry, int distance) {
        	adjacencyList.putIfAbsent(fromcountry, new ArrayList<>());
            adjacencyList.putIfAbsent(tocountry, new ArrayList<>());   
            adjacencyList.get(fromcountry).add(new Edge(tocountry, distance));
            adjacencyList.get(tocountry).add(new Edge(fromcountry, distance));
        }

        List<String> getShortestPath(String startcountry, String targetcountry) {
            Map<String, Integer> distances = new HashMap<>();
            Map<String, String> previouscountries = new HashMap<>();
            PriorityQueue<String> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
            
            for (String country : adjacencyList.keySet()) {
                distances.put(country, Integer.MAX_VALUE);
            }
            distances.put(startcountry, 0);
            priorityQueue.add(startcountry);

            while (!priorityQueue.isEmpty()) {
                String currentcountry = priorityQueue.poll();

                if (currentcountry.equals(targetcountry)) {
                    break;
                }

                for (Edge edge : adjacencyList.getOrDefault(currentcountry, new ArrayList<>())) {
                    int newDist = distances.get(currentcountry) + edge.distance;

                    if (newDist < distances.get(edge.targetcountry)) {
                        distances.put(edge.targetcountry, newDist);
                        previouscountries.put(edge.targetcountry, currentcountry);
                        priorityQueue.add(edge.targetcountry);
                    }
                }
            }

            return reconstructPath(startcountry, targetcountry, previouscountries, distances);
        }

        private List<String> reconstructPath(String startcountry, String targetcountry, Map<String, String> previouscountries, Map<String, Integer> distances) {
            List<String> path = new ArrayList<>();
            String currentcountry = targetcountry;

            if (!distances.containsKey(currentcountry) || distances.get(currentcountry) == Integer.MAX_VALUE) {
                System.out.println("No path found.");
                return path;
            }

            while (currentcountry != null) {
                path.add(0, currentcountry);
                currentcountry = previouscountries.get(currentcountry);
            }

            System.out.println("Shortest path: " + String.join(" -> ", path));
            System.out.println("Total distance: " + distances.get(targetcountry) + "Km");
            return path;
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();

        // Add countries
        graph.addcountry("Japan");
        graph.addcountry("Germany");
        graph.addcountry("USA");
        graph.addcountry("France");
        graph.addcountry("England");
        graph.addcountry("Spain");
        graph.addcountry("Italy");
        graph.addcountry("China");
        graph.addcountry("Denmark");
        graph.addcountry("India");
        graph.addcountry("Australia");
        graph.addcountry("Switzerland");
        graph.addcountry("Sweden");
        graph.addcountry("Belgium");
        graph.addcountry("Canada");
        graph.addcountry("Mexico");
        graph.addcountry("Peru");
        graph.addcountry("Egypt");
        graph.addcountry("Jamaica");
        graph.addcountry("Madagasca");
        graph.addcountry("Brazil");
        graph.addcountry("Poland");
        graph.addcountry("Thailand");
        graph.addcountry("Costa Rica");
        graph.addcountry("Greece");
        graph.addcountry("Ireland");
        graph.addcountry("Nigeria");
        

        // Add flights
        graph.addFlight("Japan", "China", 3000);
        graph.addFlight("Japan", "USA", 10000);
        graph.addFlight("Japan", "Australia", 7800);
        graph.addFlight("Germany", "France", 500);
        graph.addFlight("Germany", "England", 900);
        graph.addFlight("Germany", "Poland", 600);
        graph.addFlight("USA", "Canada", 800);
        graph.addFlight("USA", "Mexico", 2500);
        graph.addFlight("USA", "Jamaica", 3000);
        graph.addFlight("France", "Spain", 1000);
        graph.addFlight("France", "Italy", 1100);
        graph.addFlight("France", "Belgium", 300);
        graph.addFlight("England", "Ireland", 500);
        graph.addFlight("England", "Denmark", 1200);
        graph.addFlight("Spain", "Brazil", 8300);
        graph.addFlight("Spain", "Costa Rica", 8500);
        graph.addFlight("Italy", "Greece", 800);
        graph.addFlight("Italy", "Egypt", 2000);
        graph.addFlight("China", "Thailand", 3000);
        graph.addFlight("China", "India", 4000);
        graph.addFlight("India", "Australia", 7800);
        graph.addFlight("India", "Nigeria", 8000);
        graph.addFlight("Australia", "Madagascar", 9000);
        graph.addFlight("Australia", "Peru", 15000);
        graph.addFlight("Switzerland", "Sweden", 1500);
        graph.addFlight("Switzerland", "Belgium", 700);
        graph.addFlight("Sweden", "Denmark", 600);
        graph.addFlight("Sweden", "Poland", 800);
        graph.addFlight("Brazil", "Peru", 3500);
        graph.addFlight("Brazil", "Nigeria", 7000);
        graph.addFlight("Egypt", "Nigeria", 3000);

        // Input for the shortest path
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the starting country: ");
        String startcountry = scanner.nextLine();
        System.out.print("Enter the target country: ");
        String targetcountry = scanner.nextLine();

        // Find and display the shortest path
        graph.getShortestPath(startcountry, targetcountry);
        scanner.close();
    }
}