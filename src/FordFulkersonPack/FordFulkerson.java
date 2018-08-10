package FordFulkersonPack;

import java.util.*;

class Graph {
    private int vertexCount;    // vCount
    private int[][] graph;      // adj

    // returns the cardinality of vertex set
    public int getVertexCount() {
        return vertexCount;
    }

    // returns the graph as in matrix form
    public int[][] getGraph() {
        return graph;
    }

    // creates a graph with weight of 0
    public Graph(int vertexCount) {
        this.vertexCount = vertexCount;
        graph = new int[vertexCount][vertexCount];
        for (int u = 0; u < vertexCount; u++)
            for (int v = 0; v < vertexCount; v++)
                graph[u][v] = 0;
    }

    // adds the weight to the edge or can say creates a edge with weight
    public void addEdge(int u, int v, int weight) {
        graph[u][v] = weight;
    }

    // remove the the given edge
    public void removeEdge(int u, int v) {
        graph[u][v] = 0;
    }

    // checks if there exists a edge B/W u and v
    public boolean hasEdge(int u, int v) {
        return (graph[u][v] != 0);
    }

    // returns all the neighbours of the given vertex by traversing
    public List<Integer> neighbours(int vertex) {
        List<Integer> edges = new ArrayList<Integer>();
        for (int v = 0; v < vertexCount; v++) {
            if (hasEdge(vertex, v)) {
                edges.add(v);
            }
        }
        return edges;
    }

    public void printGraph() {
        for (int u = 0; u < vertexCount; u++) {
            List<Integer> edges = neighbours(u);
            System.out.println("\n" + u + ": ");
            for (Integer v : edges) {
                System.out.print(v + " ");
            }
        }
    }
}

public class FordFulkerson {

    private int[] parent;
    private boolean[] visited;
    private Queue<Integer> queue;
    private int vertex;
    private int maxFlow;

    public FordFulkerson(int vertex) {
        this.vertex = vertex;
        queue = new LinkedList<Integer>();
        visited = new boolean[vertex + 1];
        parent = new int[vertex + 1];
    }

    private boolean hasAugmentingPath(int source, int sink, Graph residualGraph) {
        // Because all the vertex in the graph are not visited
        Arrays.fill(visited, false);

        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (Integer v : residualGraph.neighbours(u)) {
                if (!visited[v] && residualGraph.getGraph()[u][v] > 0) {
                    queue.add(v);
                    visited[v] = true;
                    parent[v] = u;
                }
            }
        }
        return visited[sink];
    }

    public int runFordFulkerson(Graph graph, int source, int sink) {
        int u, v;
        int V = graph.getVertexCount();
        Graph residualGraph = new Graph(V);
        for (u = 0; u < V; u++) {
            for (v = 0; v < V; v++) {
                residualGraph.getGraph()[u][v] = graph.getGraph()[u][v];
            }
        }

        maxFlow = 0;

        while (hasAugmentingPath(source, sink, residualGraph)) {
            int flow = Integer.MAX_VALUE;

            for (u = sink; u != source; u = parent[u]) {
                v = parent[u];
                flow = Math.min(flow, residualGraph.getGraph()[v][u]);
            }

            for (u = sink; u != source; u = parent[u]) {
                v = parent[u];
                residualGraph.getGraph()[v][u] -= flow;
                residualGraph.getGraph()[u][v] += flow;
            }

            maxFlow += flow;
        }
        return maxFlow;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdge(0, 1, 16);
        graph.addEdge(0, 2, 13);
        graph.addEdge(1, 2, 10);
        graph.addEdge(1, 3, 12);
        graph.addEdge(2, 1, 4);
        graph.addEdge(2, 4, 14);
        graph.addEdge(3, 2, 9);
        graph.addEdge(3, 5, 20);
        graph.addEdge(4, 3, 7);
        graph.addEdge(4, 5, 4);

        graph.printGraph();

        FordFulkerson fordFulkerson = new FordFulkerson(6);
        System.out.println("The max flow is : \n");
        System.out.println(fordFulkerson.runFordFulkerson(graph, 0, 5));
    }

}
