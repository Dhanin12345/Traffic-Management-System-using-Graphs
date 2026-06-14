import java.util.*;

class Edge {
    final int to;
    final int dist;

    Edge(int to, int dist) {
        this.to = to;
        this.dist = dist;
    }
}

public class TrafficManagementSystem {
    private final Map<Integer, List<Edge>> graph = new HashMap<>();
    private final Map<String, String> trafficStatus = new HashMap<>();

    public void addRoad(int u, int v, int dist) {
        graph.putIfAbsent(u, new ArrayList<>());
        graph.putIfAbsent(v, new ArrayList<>());

        String status = "MEDIUM";
        graph.get(u).add(new Edge(v, dist));
        graph.get(v).add(new Edge(u, dist));

        String key = Math.min(u, v) + "-" + Math.max(u, v);
        trafficStatus.put(key, status);

        System.out.println("Road Added Successfully");
    }

    public void displayGraph() {
        System.out.println("===== Traffic Graph =====");
        for (int u : graph.keySet().stream().sorted().toList()) {
            System.out.print(u + " -> ");
            List<Edge> edges = graph.get(u);
            edges.sort(Comparator.comparingInt(e -> e.to));
            for (int i = 0; i < edges.size(); i++) {
                Edge e = edges.get(i);
                System.out.print(e.to + "(" + e.dist + " km)");
                if (i < edges.size() - 1) System.out.print(" ");
            }
            System.out.println();
        }
    }

    public Map<Integer, Integer> dijkstra(int src) {
        Map<Integer, Integer> dist = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        for (int node : graph.keySet()) {
            dist.put(node, Integer.MAX_VALUE);
        }
        dist.put(src, 0);
        pq.offer(new int[]{src, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0];
            int d = curr[1];

            if (d > dist.get(u)) continue;

            for (Edge e : graph.getOrDefault(u, new ArrayList<>())) {
                int alt = d + e.dist;
                if (alt < dist.getOrDefault(e.to, Integer.MAX_VALUE)) {
                    dist.put(e.to, alt);
                    pq.offer(new int[]{e.to, alt});
                }
            }
        }
        return dist;
    }

    public void findShortestRoute(int src) {
        Map<Integer, Integer> distances = dijkstra(src);
        System.out.println("===== Shortest Route =====");
        for (int node : distances.keySet().stream().sorted().toList()) {
            int d = distances.getOrDefault(node, -1);
            if (d >= 0) {
                System.out.println(src + " -> " + node + " = " + d + " km");
            }
        }
    }

    public void detectTraffic() {
        System.out.println("===== Traffic Status =====");

        // Sample Traffic Data
        trafficStatus.put("1-2", "MEDIUM");
        trafficStatus.put("1-3", "HIGH");
        trafficStatus.put("2-4", "LOW");
        trafficStatus.put("3-4", "MEDIUM");

        for (String key : trafficStatus.keySet().stream().sorted().toList()) {
            String[] parts = key.split("-");
            int u = Integer.parseInt(parts[0]);
            int v = Integer.parseInt(parts[1]);
            String status = trafficStatus.get(key);

            String emoji = switch (status) {
                case "LOW" -> "🟢";
                case "MEDIUM" -> "🟡";
                case "HIGH" -> "🔴";
                default -> "⚪";
            };

            System.out.println("Road " + u + " -> " + v + " : " + status + " Traffic " + emoji);
        }
    }

    public void emergencyRoute(int start, int dest) {
        Map<Integer, Integer> distances = dijkstra(start);
        int time = distances.getOrDefault(dest, -1);

        System.out.println("===== Emergency Route 🚑 =====");
        System.out.println("Start Junction : " + start);
        System.out.println("Destination : " + dest);

        if (time == -1) {
            System.out.println("No route found!");
        } else {
            System.out.println("Fastest Time : " + time + " minutes");
            System.out.println("Route Status : CLEAR 🟢");
        }
    }

    public static void main(String[] args) {
        TrafficManagementSystem tms = new TrafficManagementSystem();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Traffic Management System =====");
            System.out.println("1. Add Road");
            System.out.println("2. Display Graph");
            System.out.println("3. Find Shortest Route");
            System.out.println("4. Detect Traffic");
            System.out.println("5. Emergency Route");
            System.out.println("6. Exit");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Start Junction: ");
                    int u = sc.nextInt();
                    System.out.print("End Junction: ");
                    int v = sc.nextInt();
                    System.out.print("Distance: ");
                    int d = sc.nextInt();
                    tms.addRoad(u, v, d);
                }
                case 2 -> tms.displayGraph();
                case 3 -> {
                    System.out.print("Enter Source Junction: ");
                    int src = sc.nextInt();
                    tms.findShortestRoute(src);
                }
                case 4 -> tms.detectTraffic();
                case 5 -> {
                    System.out.print("Enter Start Junction: ");
                    int start = sc.nextInt();
                    System.out.print("Enter Destination Junction: ");
                    int dest = sc.nextInt();
                    tms.emergencyRoute(start, dest);
                }
                case 6 -> {
                    System.out.println("System Closed");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}