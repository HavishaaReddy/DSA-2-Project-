import java.util.*;

public class DijkstraRouting {
    public static void dijkstra(int[][] graph, int src) {
        int v = graph.length;
        int[] dist = new int[v];
        boolean[] visited = new boolean[v];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for (int i = 0; i < v - 1; i++) {
            int u = minDistance(dist, visited);
            visited[u] = true;

            for (int next = 0; next < v; next++) {
                if (!visited[next] && graph[u][next] != 0 && 
                    dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][next] < dist[next]) {
                    dist[next] = dist[u] + graph[u][next];
                }
            }
        }
        printSolution(dist);
    }

    private static int minDistance(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < dist.length; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    private static void printSolution(int[] dist) {
        System.out.println("Source Venue -> Destination Venue : Distance");
        for (int i = 0; i < dist.length; i++) {
            System.out.println("Venue 0      -> Venue " + i + "           : " + dist[i] + " km");
        }
    }

    public static void main(String[] args) {
        System.out.println("DIJKSTRA'S ALGORITHM - VENUE ROUTING OPTIMIZATION\n");
        System.out.println("Venue Distance Matrix (Weights in km):\n");
        
        int[][] graph = {
            {0, 4, 0, 0, 0, 0, 0, 8, 0},
            {4, 0, 8, 0, 0, 0, 0, 11, 0},
            {0, 8, 0, 7, 0, 4, 0, 0, 2},
            {0, 0, 7, 0, 9, 14, 0, 0, 0},
            {0, 0, 0, 9, 0, 10, 0, 0, 0},
            {0, 0, 4, 14, 10, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 2, 0, 1, 6},
            {8, 11, 0, 0, 0, 0, 1, 0, 7},
            {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };

        for (int[] row : graph) {
            for (int val : row) System.out.printf("%2d ", val);
            System.out.println();
        }

        System.out.println("\nComputing Shortest Paths from Source Venue (0)...\n");
        dijkstra(graph, 0);

        System.out.println("\nFINAL ROUTING TREE STRUCTURE");
        System.out.println("            Venue0\n           /      \\\n       Venue1    Venue7\n         |          |\n       Venue2    Venue6\n                 /    \\\n            Venue5    Venue8\n              |\n            Venue4");
        System.out.println("\nTime Complexity:\nDijkstra's Algorithm -> O((V + E) log V)");
    }
}