import java.util.*;

public class CMSC401_A3 {

    static int N, M; // Number of courses and lecture halls
    static int[][] capacity; // Capacity matrix for graph representation
    static List<Integer>[] adjList; // Adjacency list to store graph

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Reading number of courses and lecture halls
        N = scanner.nextInt();
        M = scanner.nextInt();

        int totalNodes = N + M + 2;
        capacity = new int[totalNodes][totalNodes];
        adjList = new List[totalNodes];
        for (int i = 0; i < totalNodes; i++) {
            adjList[i] = new ArrayList<>();
        }

        int source = 0;
        int sink = totalNodes - 1;

        // Read each course's lecture hall options and build the graph
        for (int i = 1; i <= N; i++) {
            int course = i;
            int lectureCount = scanner.nextInt();
            for (int j = 0; j < lectureCount; j++) {
                int hall = scanner.nextInt() + N; // Offset lecture hall IDs by N
                capacity[course][hall] = 1;
                adjList[course].add(hall);
                adjList[hall].add(course);
            }
            capacity[source][course] = 1; // Source to course
            adjList[source].add(course);
            adjList[course].add(source);
        }

        // Connect lecture halls to the sink
        for (int i = N + 1; i <= N + M; i++) {
            capacity[i][sink] = 1;
            adjList[i].add(sink);
            adjList[sink].add(i);
        }

        // Calculate max flow (maximum matching)
        int maxCourses = maxFlow(source, sink);
        System.out.println(maxCourses);
        scanner.close();
    }

    // Implementation of Ford-Fulkerson with BFS to find augmenting paths
    static int maxFlow(int source, int sink) {
        int maxFlow = 0;
        int[] parent = new int[capacity.length];

        while (bfs(source, sink, parent)) {
            int pathFlow = Integer.MAX_VALUE;
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, capacity[u][v]);
            }

            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                capacity[u][v] -= pathFlow;
                capacity[v][u] += pathFlow;
            }

            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    // BFS to find an augmenting path
    static boolean bfs(int source, int sink, int[] parent) {
        boolean[] visited = new boolean[capacity.length];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v : adjList[u]) {
                if (!visited[v] && capacity[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;

                    if (v == sink) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
