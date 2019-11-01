package com.tijmen.entities;

import java.util.Objects;
import java.util.Set;

public class Edge {
    private final Vertex v1;
    private final Vertex v2;

    public Edge(Vertex v1, Vertex v2) {
        if (v1.equals(v2)) {
            throw new IllegalStateException("Mogen niet hetzelfde zijn.");
        }
        this.v1 = v1;
        this.v2 = v2;
    }

    public Edge(int v1, int v2) {
        this(new Vertex(v1), new Vertex(v2));
    }

    public Vertex getV1() {
        return v1;
    }

    public Vertex getV2() {
        return v2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return (v1.equals(edge.getV1()) && v2.equals(edge.getV2()))
                || (v1.equals(edge.getV2()) && v2.equals(edge.getV1()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(Set.of(v1, v2));
    }

    @Override
    public String toString() {
        return "(" + v1 +
                ", " + v2 +
                ')';
    }
}
