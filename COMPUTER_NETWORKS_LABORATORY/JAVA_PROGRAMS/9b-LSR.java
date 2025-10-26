import java.util.*;

public class Dijkstra{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, s, d;

        System.out.print("Enter number of vertices: ");
        n = sc.nextInt();

        int[][] cost = new int[n][n];
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];

        // Input weighted adjacency matrix
        System.out.println("Enter weighted adjacency matrix (0 if no edge):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost[i][j] = sc.nextInt();
            }
        }

        System.out.print("Enter source vertex (1-" + n + "): ");
        s = sc.nextInt() - 1; // Convert to 0-based index

        System.out.print("Enter destination vertex (1-" + n + "): ");
        d = sc.nextInt() - 1; // Convert to 0-based index

        // Initialize distances
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }
        dist[s] = 0;

        // Dijkstra's algorithm
        for (int count = 0; count < n - 1; count++) {
            int min = Integer.MAX_VALUE;
            int u = -1;
            for (int i = 0; i < n; i++) {
                if (!visited[i] && dist[i] < min) {
                    min = dist[i];
                    u = i;
                }
            }

            if (u == -1) break; // No more reachable nodes
            visited[u] = true;

            for (int i = 0; i < n; i++) {
                if (cost[u][i] != 0 && !visited[i] && dist[i] > dist[u] + cost[u][i]) {
                    dist[i] = dist[u] + cost[u][i];
                }
            }
        }

        if (dist[d] == Integer.MAX_VALUE)
            System.out.println("No path exists from " + (s + 1) + " to " + (d + 1));
        else
            System.out.println("Shortest distance from " + (s + 1) + " to " + (d + 1) + " is " + dist[d]);

        sc.close();
    }
}
