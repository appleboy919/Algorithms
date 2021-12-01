package Graph;

import java.util.*;

class Vertex {
    String label;

    Vertex(String label) {
        this.label = label;
    }
}

class Graph {
    private Map<Vertex, List<Vertex>> adjList;

    void addVertex(String label) {
        adjList.putIfAbsent(new Vertex(label), new ArrayList<>());
    }

    void removeVertex(String label) {
        Vertex v = new Vertex(label);
        adjList.values().stream().forEach(e -> e.remove(v));
        adjList.remove(v);
    }

    void addEdge(String start, String end) {
        Vertex v1 = new Vertex(start);
        Vertex v2 = new Vertex(end);
        adjList.get(v1).add(v2);
        adjList.get(v2).add(v1);
    }

    void removeEdge(String start, String end) {
        Vertex v1 = new Vertex(start);
        Vertex v2 = new Vertex(end);
        List<Vertex> eV1 = adjList.get(v1);
        List<Vertex> eV2 = adjList.get(v2);
        if (eV1 != null)
            eV1.remove(v2);
        if (eV2 != null)
            eV1.remove(v1);
    }

    Graph createGraph() {
        Graph graph = new Graph();
        graph.addVertex("Bob");
        graph.addVertex("Alice");
        graph.addVertex("Mark");
        graph.addVertex("Rob");
        graph.addVertex("Maria");
        graph.addEdge("Bob", "Alice");
        graph.addEdge("Bob", "Rob");
        graph.addEdge("Alice", "Mark");
        graph.addEdge("Rob", "Mark");
        graph.addEdge("Alice", "Maria");
        graph.addEdge("Rob", "Maria");
        return graph;
    }

    List<Vertex> getAdjVertices(String label) {
        return adjList.get(new Vertex(label));
    }

    Set<String> dfs(Graph graph, String root) {
        Set<String> visited = new LinkedHashSet<String>();
        Stack<String> stack = new Stack<String>();
        stack.push(root);
        while (!stack.isEmpty()) {
            String vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                for (Vertex v : graph.getAdjVertices(vertex)) {
                    stack.push(v.label);
                }
            }
        }
        return visited;
    }

    Set<String> bfs(Graph graph, String root) {
        Set<String> visited = new LinkedHashSet<String>();
        Queue<String> queue = new LinkedList<String>();
        visited.add(root);
        queue.add(root);
        while (!queue.isEmpty()) {
            String vertex = queue.poll();
            for (Vertex v : graph.getAdjVertices(vertex)) {
                if (!visited.contains(v.label)) {
                    visited.add(v.label);
                    queue.add(v.label);
                }
            }
        }
        return visited;
    }
}
