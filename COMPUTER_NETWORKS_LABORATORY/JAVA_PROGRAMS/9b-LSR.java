import java.util.*;

public class Dijkstra {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, s, d;

        System.out.print("Enter number of vertices: ");
        n = sc.nextInt();

        int[][] cost = new int[n][n];
        int[] dist = new int[n];
        int[] prev = new int[n];
        boolean[] visited = new boolean[n];

        // Input weighted adjacency matrix
        System.out.println("Enter weighted adjacency matrix (0 if no edge):");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                cost[i][j] = sc.nextInt();

        System.out.print("Enter source vertex (1-" + n + "): ");
        s = sc.nextInt() - 1;

        System.out.print("Enter destination vertex (1-" + n + "): ");
        d = sc.nextInt() - 1;

        // Initialize distances and previous array
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        Arrays.fill(visited, false);
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

            if (u == -1) break;
            visited[u] = true;

            for (int i = 0; i < n; i++) {
                if (cost[u][i] != 0 && !visited[i] && dist[i] > dist[u] + cost[u][i]) {
                    dist[i] = dist[u] + cost[u][i];
                    prev[i] = u; // track previous node
                }
            }
        }

        // Shortest distance to destination
        if (dist[d] == Integer.MAX_VALUE)
            System.out.println("\nNo path exists from " + (s + 1) + " to " + (d + 1));
        else
            System.out.println("\nShortest distance from " + (s + 1) + " to " + (d + 1) + " is " + dist[d]);

        // Routing table for source router
        System.out.println("\nRouter " + (s + 1) + " Routing Table:");
        System.out.println("Destination\tNext Hop\tDistance");
        for (int dest = 0; dest < n; dest++) {
            if (dest == s) continue;
            int nextHop = dest;
            while (prev[nextHop] != s && prev[nextHop] != -1)
                nextHop = prev[nextHop];
            System.out.println((dest + 1) + "\t\t" + (nextHop + 1) + "\t\t" + dist[dest]);
        }

        sc.close();
    }
}
