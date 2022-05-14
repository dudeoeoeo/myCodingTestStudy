import java.util.*;

public class Edge implements Comparable<Edge> {
    public int distance;
    public String vertex;

    public Edge(int distance, String vertex) {
        this.distance = distance;
        this.vertex = vertex;
    }

    public String toString() {
        return "vertex: " + this.vertex + ", distance: " + this.distance;
    }

    public int compareTo(Edge edge) {
        return this.distance - edge.distance;
    }

    public static void main(String[] args) {
        HashMap<String, ArrayList<Edge>> graph = new HashMap<String, ArrayList<Edge>>();
        graph.put("A", new ArrayList<Edge>(Arrays.asList(new Edge(8, "B"), new Edge(1, "C"), new Edge(2, "D") )));
        graph.put("B", new ArrayList<Edge>());
        graph.put("C", new ArrayList<Edge>(Arrays.asList(new Edge(5, "B"), new Edge(2, "D") )));
        graph.put("D", new ArrayList<Edge>(Arrays.asList(new Edge(3, "E"), new Edge(5, "F") )));
        graph.put("E", new ArrayList<Edge>(Arrays.asList(new Edge(1, "F")) ));
        graph.put("F", new ArrayList<Edge>(Arrays.asList(new Edge(5, "A") )));
        Edge.dijkstraFunc(graph, "A");
    }
    public static HashMap<String, Integer> dijkstraFunc(HashMap<String, ArrayList<Edge>> graph, String startNode) {
        HashMap<String, Integer> distanceMap = new HashMap<>();
        for(String key : graph.keySet())
            distanceMap.put(key, Integer.MAX_VALUE);

        distanceMap.put(startNode, 0);

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new Edge(distanceMap.get(startNode), startNode));

        Edge currEdge;
        int currDistance;
        String currVertex;

        while (priorityQueue.size() > 0) {
            System.out.println("priorityQueue: "+priorityQueue);
            currEdge = priorityQueue.poll();
            currDistance = currEdge.distance;
            currVertex = currEdge.vertex;

            if(distanceMap.get(currVertex) < currDistance)
                continue;

            List<Edge> list = graph.get(currVertex);
            System.out.println("list: "+list.toString());
            for (int i = 0; i < list.size(); i++) {
                int nowDistance = list.get(i).distance;
                String nowVertex = list.get(i).vertex;

                if(currDistance + nowDistance < distanceMap.get(nowVertex)) {
                    priorityQueue.offer(new Edge(currDistance + nowDistance, nowVertex));
                    distanceMap.put(nowVertex, currDistance + nowDistance);
                }
            }
        }
        System.out.println(distanceMap.toString());
        return distanceMap;
    }
}
