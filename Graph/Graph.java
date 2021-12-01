package Graph;

import java.util.*;

class Vertex {
    String label;

    Vertex(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Vertex))
            return false;
        Vertex v = (Vertex) o;
        return v.label.equals(this.label);
    }

    @Override
    public final int hashCode() {
        return this.label.hashCode();
    }
}

class Graph {
    private Map<Vertex, List<Vertex>> adjList;

    Graph() {
        adjList = new HashMap<>();
    }

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
        // adjList.get(v2).add(v1);
    }

    void removeEdge(String start, String end) {
        Vertex v1 = new Vertex(start);
        Vertex v2 = new Vertex(end);
        List<Vertex> eV1 = adjList.get(v1);
        // List<Vertex> eV2 = adjList.get(v2);
        if (eV1 != null)
            eV1.remove(v2);
        // if (eV2 != null)
        // eV1.remove(v1);
    }

    // Graph createGraph() {
    // Graph graph = new Graph();
    // graph.addVertex("A");
    // graph.addVertex("B");
    // graph.addVertex("C");
    // graph.addVertex("D");
    // graph.addVertex("F");
    // graph.addEdge("A", "B");
    // graph.addEdge("A", "D");
    // graph.addEdge("B", "C");
    // graph.addEdge("D", "C");
    // graph.addEdge("B", "F");
    // graph.addEdge("D", "F");
    // return graph;
    // }

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

    // Time: O(Edges) Space: O(Nodes)
    boolean hasPath_dfs(Graph graph, String start, String end) {
        Set<String> visited = new LinkedHashSet<>();
        Stack<String> st = new Stack<>();
        st.push(start);
        while (!st.isEmpty()) {
            String vertex = st.pop();
            if (!visited.contains(vertex)) {
                if (vertex.equals(end))
                    return true;
                visited.add(vertex);
                for (Vertex v : graph.getAdjVertices(vertex)) {
                    st.push(v.label);
                }
            }
        }
        return false;
    }

    // Time: O(Edges) Space: O(Nodes)
    boolean hasPath_bfs(Graph graph, String start, String end) {
        Set<String> visited = new LinkedHashSet<>();
        Queue<String> q = new LinkedList<>();
        visited.add(start);
        q.add(start);
        while (!q.isEmpty()) {
            String vertex = q.poll();
            for (Vertex v : graph.getAdjVertices(vertex)) {
                if (v.label.equals(end))
                    return true;
                if (!visited.contains(v.label)) {
                    visited.add(v.label);
                    q.add(v.label);
                }
            }
        }
        return false;
    }

    int connectedComponentCount(Graph graph) {
        int count = 0;
        Set<String> visited = new LinkedHashSet<>();
        Stack<String> st = new Stack<>();
        String vertex;
        List<String> nodes = new ArrayList<>();
        graph.adjList.keySet().stream().forEach(v -> nodes.add(v.label));
        for (String v : nodes) {
            if (!visited.contains(v)) {
                st.clear();
                st.add(v);
                while (!st.isEmpty()) {
                    vertex = st.pop();
                    if (!visited.contains(vertex)) {
                        visited.add(vertex);
                        for (Vertex v1 : graph.getAdjVertices(vertex)) {
                            st.push(v1.label);
                        }
                    }
                }
                count++;
            }
        }
        return count;
    }

    boolean isCyclic(Graph graph) {
        if (graph.adjList.keySet().size() == 0)
            return false;
        List<String> nodes = new ArrayList<>();
        graph.adjList.keySet().stream().forEach(v -> nodes.add(v.label));
        Stack<String> st = new Stack<>();
        String vertex;
        for (String v : nodes) {
            st.clear();
            st.push(v);
            while (!st.isEmpty()) {
                vertex = st.pop();
                for (Vertex v1 : graph.getAdjVertices(vertex)) {
                    if (st.contains(v1.label) || v1.label.equals(vertex)
                            || graph.getAdjVertices(v1.label).contains(new Vertex(vertex)))
                        return true;
                    st.push(v1.label);
                }
            }
        }
        return false;
    }

    static class QNode implements Comparable<QNode> {
        String vertex;
        Integer dist;

        QNode(String v, Integer d) {
            vertex = v;
            dist = d;
        }

        @Override
        public int compareTo(QNode o) {
            return this.dist - o.dist;
        }
    }

    /*
     * Map<String, Integer> dijkstra_shortestPath(Graph graph, String start, String
     * end) {
     * // set-up
     * Map<String, Integer> dist = new HashMap<>();
     * dist.put(start, 0);
     * Map<String, String> pred = new HashMap<>();
     * PriorityQueue<QNode> pq = new PriorityQueue<>();
     * Map<String, Integer> weights = new HashMap<>();
     * for (Vertex v: graph.adjList.keySet()) {
     * dist.putIfAbsent(v.label, Integer.MAX_VALUE);
     * pq.add(new QNode(v.label, dist.get(v.label)));
     * }
     * while (!pq.isEmpty()) {
     * QNode top = pq.remove();
     * for (Vertex v: graph.getAdjVertices(top.vertex)) {
     * 
     * }
     * }
     * 
     * }
     */

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        // graph.adjList.keySet().stream().forEach(v -> System.out.println(v.label));
        graph.addEdge("A", "B");
        graph.addEdge("A", "D");
        graph.addEdge("B", "C");
        graph.addEdge("D", "C");
        graph.addEdge("B", "E");
        graph.addEdge("D", "E");
        // graph.adjList.values().stream().forEach(e -> System.out.println(e.size()));

        // test connectComponentCount
        System.out.println("Component: " + graph.connectedComponentCount(graph));
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addEdge("F", "G");
        System.out.println("Component: " + graph.connectedComponentCount(graph));

        // test isCyclic
        System.out.println("Cyclic?\t" + graph.isCyclic(graph));
        graph.addEdge("G", "F");
        System.out.println("Cyclic?\t" + graph.isCyclic(graph));
        graph.addEdge("C", "A");
        System.out.println("Cyclic?\t" + graph.isCyclic(graph));

    }
}
