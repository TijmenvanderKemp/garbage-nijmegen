package com.tijmen.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Graph {
    private final List<Vertex> vertices = new ArrayList<>();
    private final List<Edge> edges = new ArrayList<>();

    public Graph(int numberOfVertices) {
        for (int i = 1; i <= numberOfVertices; i++) {
            vertices.add(new Vertex(i));
        }
    }

    public void addEdge(int v1, int v2) {
        Edge newEdge = new Edge(getVertex(v1), getVertex(v2));
        edges.add(newEdge);
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
