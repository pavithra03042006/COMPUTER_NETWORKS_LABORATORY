import java.util.*;
public class DVR {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Number of vertices: ");
        int n = sc.nextInt();
        System.out.print("Number of edges: ");
        int e = sc.nextInt();

        int[][] cost = new int[n][n];
        int[][] dist = new int[n][n];

        // Initialize cost and distance
        for (int i = 0; i < n; i++) {
            Arrays.fill(cost[i], 9999); // use 9999 as INF
            cost[i][i] = 0;
            Arrays.fill(dist[i], 9999);
            dist[i][i] = 0;
        }

        // Input edges
        for (int i = 0; i < e; i++) {
            System.out.println("Edge " + (i + 1));
            System.out.print("  Source=");
            int u = sc.nextInt() - 1;
            System.out.print("  Destination=");
            int v = sc.nextInt() - 1;
            System.out.print("  Cost=");
            int c = sc.nextInt();
            cost[u][v] = c;
            cost[v][u] = c; // undirected
            dist[u][v] = c;
            dist[v][u] = c;
        }

        // Compute initial routing table
        computeDistanceVector(n, dist, cost);
        System.out.println("\nThe initial Routing Tables are:");
        printRoutingTable(n, dist);

        // Edge cost change
        System.out.print("\nEdge cost change:\n");
        System.out.print("  Source=");
        int su = sc.nextInt() - 1;
        System.out.print("  Destination=");
        int sv = sc.nextInt() - 1;
        System.out.print("  New Cost=");
        int newCost = sc.nextInt();

        cost[su][sv] = newCost;
        cost[sv][su] = newCost;
        dist[su][sv] = newCost;
        dist[sv][su] = newCost;

        // Recompute after cost change
        computeDistanceVector(n, dist, cost);
        System.out.println("\nThe new Routing Tables are:");
        printRoutingTable(n, dist);

        sc.close();
    }

    static void computeDistanceVector(int n, int[][] dist, int[][] cost) {
        boolean updated;
        do {
            updated = false;
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    for (int v = 0; v < n; v++) {
                        if (dist[x][v] + dist[v][y] < dist[x][y]) {
                            dist[x][y] = dist[x][v] + dist[v][y];
                            updated = true;
                        }
                    }
                }
            }
        } while (updated);
    }

    static void printRoutingTable(int n, int[][] dist) {
        for (int i = 0; i < n; i++) {
            System.out.print("Dist from Node " + (i + 1) + ": ");
            for (int j = 0; j < n; j++) {
                System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }
}
