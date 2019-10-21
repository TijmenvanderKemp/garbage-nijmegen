package com.tijmen.entities;

import java.util.*;

public class Graph {
    private final List<Vertex> vertices = new ArrayList<>();
    private final List<Edge> edges = new ArrayList<>();
    private final Map<Vertex, Set<Vertex>> adjacencyLists = new HashMap<>();

    public Graph(int numberOfVertices) {
        for (int i = 1; i <= numberOfVertices; i++) {
            vertices.add(new Vertex(i));
            adjacencyLists.put(new Vertex(i), new HashSet<>());
        }
    }

    public void addEdge(int v1, int v2) {
        Edge newEdge = new Edge(getVertex(v1), getVertex(v2));
        edges.add(newEdge);
    }

    public void buildAdjacencyLists() {
        for(Edge edge : edges) {
            Vertex v1 = edge.getV1();
            Vertex v2 = edge.getV2();
            Set<Vertex> verticesV1 = adjacencyLists.get(v1);
            verticesV1.add(v2);
            Set<Vertex> verticesV2 = adjacencyLists.get(v2);
            verticesV2.add(v2);
        }
    }

    public Vertex getVertex(int id) {
        return vertices.stream()
                .filter(v -> id == v.getId())
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Vertex with id %d not found: ", id)));
    }

    public List<Vertex> getVertices() {
        return Collections.unmodifiableList(vertices);
    }

    public List<Edge> getEdges() {
        return Collections.unmodifiableList(edges);
    }

    public int numberOfVertices() {
        return vertices.size();
    }

    public int numberOfEdges() {
        return edges.size();
    }
}
