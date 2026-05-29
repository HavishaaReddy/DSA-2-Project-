import java.util.*;

class VenueGraph {
    private Map<String, List<String>> events = new HashMap<>();
    private Map<String, List<String>> adjList = new HashMap<>();

    public void addVenue(String venue) {
        events.putIfAbsent(venue, new ArrayList<>());
        adjList.putIfAbsent(venue, new ArrayList<>());
    }

    public void addEvent(String venue, String event) {
        events.get(venue).add(event);
    }

    public void addConnection(String v1, String v2) {
        adjList.get(v1).add(v2);
        adjList.get(v2).add(v1); // undirected graph
    }

    // BFS traversal with tree-like ASCII output
    public void bfsGraphTree(String start) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        Map<String, Integer> level = new HashMap<>();

        visited.add(start);
        queue.add(start);
        level.put(start, 0);

        System.out.println("BFS Graph Representation (Venues & Events):\n");

        while (!queue.isEmpty()) {
            String current = queue.poll();
            int depth = level.get(current);

            // Indentation based on BFS level
            String indent = " ".repeat(depth * 4);

            // Extra info: number of events at this venue
            int eventCount = events.get(current).size();

            System.out.println(indent + current + " " + events.get(current) +
                               "  (Level " + depth + ", Events: " + eventCount + ")");

            for (String neighbor : adjList.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    level.put(neighbor, depth + 1);

                    // Show connection visually
                    System.out.println(indent + "   |");
                    System.out.println(indent + "   +--> " + neighbor + " " + events.get(neighbor));
                }
            }
        }
    }
}

public class VenueGraphDemo {
    public static void main(String[] args) {
        VenueGraph graph = new VenueGraph();

        // Add venues
        graph.addVenue("Auditorium");
        graph.addVenue("SeminarHall");
        graph.addVenue("OpenStage");

        // Add events
        graph.addEvent("Auditorium", "Debate");
        graph.addEvent("Auditorium", "Drama");
        graph.addEvent("SeminarHall", "Guest Speech");
        graph.addEvent("OpenStage", "Singing");
        graph.addEvent("OpenStage", "Magic Show");

        // Add connections
        graph.addConnection("Auditorium", "SeminarHall");
        graph.addConnection("SeminarHall", "OpenStage");

        // Run BFS and print tree-like graph output
        graph.bfsGraphTree("Auditorium");
    }
}
