package com.tijmen.entities;

import java.util.*;

public class Graph {
    private final Set<Vertex> vertices;
    private final Set<Edge> edges;
    private final Map<Vertex, Set<Vertex>> adjacencyLists = new HashMap<>();

    public Graph(int numberOfVertices) {
        vertices = new HashSet<>();
        edges = new HashSet<>();
        for (int i = 1; i <= numberOfVertices; i++) {
            vertices.add(new Vertex(i));
        }
    }

    public Graph(Set<Vertex> vertices, Set<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
        buildAdjacencyLists();
    }

    public void addEdge(int v1, int v2) {
        Edge newEdge = new Edge(getVertex(v1), getVertex(v2));
        edges.add(newEdge);
    }

    public void buildAdjacencyLists() {
        for (int i = 1; i <= vertices.size(); i++) {
            adjacencyLists.put(new Vertex(i), new HashSet<>());
        }
        for(Edge edge : edges) {
            Vertex v1 = edge.getV1();
            Vertex v2 = edge.getV2();
            Set<Vertex> verticesV1 = adjacencyLists.get(v1);
            verticesV1.add(v2);
            Set<Vertex> verticesV2 = adjacencyLists.get(v2);
            verticesV2.add(v1);
        }
    }

    public Vertex getVertex(int id) {
        return vertices.stream()
                .filter(v -> id == v.getId())
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Vertex with id %d not found: ", id)));
    }

    public Set<Vertex> getVertices() {
        return Collections.unmodifiableSet(vertices);
    }

    public Set<Edge> getEdges() {
        return Collections.unmodifiableSet(edges);
    }

    public Map<Vertex, Set<Vertex>> getAdjacencyLists() {
        return adjacencyLists;
    }

    public int numberOfVertices() {
        return vertices.size();
    }

    public int numberOfEdges() {
        return edges.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Graph graph = (Graph) o;
        return vertices.equals(graph.getVertices()) && edges.equals(graph.getEdges());
    }
}
